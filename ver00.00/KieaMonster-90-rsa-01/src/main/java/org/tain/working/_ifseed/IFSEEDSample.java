package org.tain.working._ifseed;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * <p>금융정보 교환 예제</p>
 * <li>본 예제는 금융결제원에서 참가기관의 업무 이해를 돕기위하여 작성된 것으로,</li>
 * <li>예제의 정상동작 여부, 적정성 및 정당성 등은 해당기관에서 기관 환경에 맞추어 직접 확인하여야 합니다.</li>
 * <li>금융결제원은 이에 대하혀 책임지지 않습니다.</li>
 * @author 금융결제원 IT개발부 강성주(sonaki@kftc.or.kr)
 * @version 2016. 4. 28.
 */
public class IFSEEDSample
{
	private final static int PUB_KEY_SIZE = 2048;						// 공개키 알고리즘 키크기
	private final static String PUB_ALGORITHM = "RSA";			// 공개키암호화 알고리즘 지정
	private final static String PUB_CIPHER_ALGORITHM = "RSA/ECB/OAEPWithSHA1AndMGF1Padding";	// 공개키암호화 세부 알고리즘 지정

	private final static String ENCODE_STR = "KSC5601";			// 파일 송수신 인코딩 형식

	private PrivateKey localPriKey;								// 송신기관 개인키
	private PublicKey localPubKey;								// 송신기관 공개키
	private byte[] localASKey = new byte[16];			// 송신기관 대칭키
	private byte[] localIv = new byte[16];					// 송신기관 이니셜벡터

	private static PublicKey remotePubKey;				// 수신기관 공개키
	private byte[] remoteASKey = new byte[16];		// 수신기관 대칭키
	private byte[] remoteIv = new byte[16];				// 수신기관 이니셜벡터

	// 파일송수신을 대신 파일 저장으로 테스트 수행
	private final static String PUBLIC_KEY_FILE_NAME = "pubkey.bin";
	private final static String PRIVATE_KEY_FILE_NAME = "prikey.bin";
	private final static String TRANS_EKEY_FILE_NAME = "transkey.bin";
	private final static String TRANS_EMSG_FILE_NAME = "transmsg.bin";


	/**
	 * main
	 * @version 2016. 4. 28.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)
			throws Exception
	{
		IFSEEDSample sample = new IFSEEDSample();
		sample.start();
	}

	/**
	 * 샘플
	 * @version 2016. 4. 28.
	 * @throws Exception
	 */
	public void start()
			throws Exception
	{
		int step = 1;

		System.out.println("[금융거래정보 암호화 송수신 예제 START]\n");

		/** (1) RSA 2048BIT 개인키 및 공개키를 생성 **/
		// 송신기관용 키쌍 생성
		// 생성된 키쌍은 다시 재생성할 수 없으므로 반드시 별도로 저장해야 함
		KeyPair keyPair = getKeyPair();

		// 송신기관 개인키 및 공개키 저장 (local)
		localPriKey = keyPair.getPrivate();
		localPubKey = keyPair.getPublic();

		// 송신기관 공개키
		// 공캐키는 X5.09 ASN.1 DER 로 1차 인코딩
		byte[] bPubKey = localPubKey.getEncoded();
		byte[] bPriKey = localPriKey.getEncoded();


		// BASE64 인코딩
		byte[] b64PubKey = Base64.getEncoder().encode(bPubKey);
		byte[] b64PriKey = Base64.getEncoder().encode(bPriKey);

		System.out.println("[" + step++ + "] 송신기관 공개키 및 대칭키 생성. 공개키 size[" + bPubKey.length + "] b64 size[" + b64PubKey.length + "]");
		System.out.println("[공개키(BASE64)]");
		//for (int i = 0; i < bPubKey.length; i++) System.out.print(Integer.toHexString(0xff & bPubKey[i])+" "); System.out.println("");
		System.out.println(new String(b64PubKey) + "\n");
		System.out.println("[개인키(BASE64)]");
		//for (int i = 0; i < bPubKey.length; i++) System.out.print(Integer.toHexString(0xff & bPubKey[i])+" "); System.out.println("");
		System.out.println(new String(b64PriKey) + "\n");


		/** (2) 생성된 공개키(BASE64형식, ePubKey)를 일괄전송 FILE을 통하여 상대편 기관(지자체->은행, 은행->지자체)에 전송 (결제원 처리) **/
		// 송신기관 파일 송신
		// 예제에서는 파일에 저장하고 사용함
		saveFile(PUBLIC_KEY_FILE_NAME, b64PubKey);
		saveFile(PRIVATE_KEY_FILE_NAME, b64PriKey);

		System.out.println("[" + step++ + "] 송신기관 공개키 파일 전송");
		System.out.println("");


		/** (3) 수신기관 파일 수신 **/
		byte[] b64RemotePubKey = readFile(PUBLIC_KEY_FILE_NAME);

		// BASE64 디코딩
		byte[] bRemotePubKey = Base64.getDecoder().decode(b64RemotePubKey);

		System.out.println("[" + step++ + "] 수신기관 공개키 파일 수신");
		System.out.println("");


		/** (4) 수신기관 공개키를 저장(local) **/
		// 수신된 타기관 공개키 얻음
		// 공캐키는 X5.09 ASN.1 DER 로 인코딩 되어있음
		remotePubKey = getPublicKey(bRemotePubKey);

		System.out.println("[" + step++ + "] 수신기관 공개키 얻음 size[" + bRemotePubKey.length + "] b64 size[" + b64RemotePubKey.length + "]");
		System.out.println("[공개키(BASE64)]");
		//for (int i = 0; i < bRemotePubKey.length; i++) System.out.print(Integer.toHexString(0xff & bRemotePubKey[i])+" ");
		System.out.println(new String(b64RemotePubKey));
		System.out.println("");


		/** (5) 송신기관 자료 암호화를 위한 대칭키(128BIT-16BYTE) 생성 **/
		// 대칭키(16byte) 생성 (전송 기관 자율 RANDOM 생성)
		new Random().nextBytes(localASKey);
		// 이니셜벡터(Initial Vector) 생성 - 생성된 대칭키와 같은 값으로 사용함
		localIv = localASKey.clone();

		System.out.println("[" + step++ + "] 랜덤생성한 송신기관 대칭키 size[" + localASKey.length + "] 이니셜벡터(IV) size[" + localASKey.length + "]");
		System.out.println("[대칭키(HEX)]");
		for (int i = 0; i < localASKey.length; i++) System.out.print(Integer.toHexString(0XFF & localASKey[i])); System.out.println("");
		System.out.println("");


		/** (6) 대칭키 암호화 알고리즘(SEED)을 사용하여 자료 암호화 **/
		// 송신할 자료 (암호화 대상)
		String myMsg = "지자체와 은행간 금융정보를 암호화하여 전송하며, 암호된 결과는 타 기관에서 복호화할 수 없다.\n연락처:금융결제원 금융정보업무기획팀 과장 김정훈 ♧02-531-1712";

		byte[] bMyMsg = myMsg.getBytes(ENCODE_STR);

		// 자료 암호화
		// 전 참가기관은 자료를 동일한 인코딩 방식으로 송수신
		byte[] bEncMyMsg = seedEncrypt(localASKey, localIv, bMyMsg);

		// BASE64 인코딩
		byte[] b64EncMyMsg = Base64.getEncoder().encode(bEncMyMsg);

		System.out.println("[" + step++ + "] 송신기관에서 암호화한 전달 대상 자료 원문 size[" + bMyMsg.length + "] 암호문 size[" + bEncMyMsg.length + "] b64 size[" + b64EncMyMsg.length + "]");
		System.out.println("[원문]");
		System.out.println(myMsg);
		//for (int i = 0; i < bEncMyMsg.length; i++) System.out.print(Integer.toHexString(0xff & bEncMyMsg[i])+" ");
		System.out.println("[암호문(BASE64)]");
		System.out.println(new String(b64EncMyMsg));
		System.out.println("");


		/** (7) 송신기관은 대칭키 암호화에 사용한 대칭키를 수신기관의 공개키로 암호화(RSA) **/
		byte[] bEncASKey = RSAEncrypt(remotePubKey, localASKey);

		// BASE64 인코딩
		byte[] b64EncASKey = Base64.getEncoder().encode(bEncASKey);

		System.out.println("[" + step++ + "] 수신기관 공개키로 암호화된 대칭키 size[" + bEncASKey.length + "] b64 size[" + b64EncASKey.length + "]");
		//for (int i = 0; i < bEncASKey.length; i++) System.out.print(Integer.toHexString(0xff & bEncASKey[i])+" ");
		System.out.println("[공개키로 암호화된 대칭키(BASE64)]");
		System.out.println(new String(b64EncASKey));
		System.out.println("");


		/** (8) [(6)대칭키로 암호화된 송신 자료]와 [(7)대칭키를 수신기관 공개키로 암호화한 결과]를 일괄전송 FILE을 통하여 수신기관에 전송 (결제원 처리) **/
		// 예제에서는 파일에 저장하고 사용함
		saveFile(TRANS_EKEY_FILE_NAME, b64EncASKey);
		saveFile(TRANS_EMSG_FILE_NAME, b64EncMyMsg);

		System.out.println("[" + step++ + "] 송신기관 암호화자료 송신");
		System.out.println("");


		/** (9) 수신기관 파일 수신 **/
		// 예제에서는 파일에서 읽음
		byte[] b64EncRemoteASKey = readFile(TRANS_EKEY_FILE_NAME);
		byte[] b64EncRemoteMsg = readFile(TRANS_EMSG_FILE_NAME);

		// BASE64 디코딩
		byte[] bEncRemoteASKey = Base64.getDecoder().decode(b64EncRemoteASKey);
		byte[] bEncRemoteMsg =  Base64.getDecoder().decode(b64EncRemoteMsg);

		System.out.println("[" + step++ + "] 수신기관 암호화자료 수신 암호화자료 size[" + bEncRemoteMsg.length + "] 암호화된 대칭키size[" + bEncRemoteASKey.length + "]");
		//for (int i = 0; i < bEncRemoteMsg.length; i++) System.out.print(Integer.toHexString(0xff & bEncRemoteMsg[i])+" ");
		//for (int i = 0; i < bEncRemoteASKey.length; i++) System.out.print(Integer.toHexString(0xff & bEncRemoteASKey[i])+" ");
		System.out.println("");


		/** (10) 전달받은 암호화된 대칭키를 를 수신기관의 개인키(local)로 복호화 **/
		// 수신된 대칭키를 개인키로 복호화
		remoteASKey = RSADecrypt(localPriKey, bEncRemoteASKey);
		// 이니셜백터 생성 - 대칭키와 동일값
		remoteIv = remoteASKey.clone();

		System.out.println("[" + step++ + "] 수신기관에서 복호화한 대칭키 size[" + remoteASKey.length + "] IV size[" + remoteIv.length + "]");
		System.out.println("[대칭키]");
		for (int i = 0; i < remoteASKey.length; i++) System.out.print(Integer.toHexString(0XFF & remoteASKey[i])); System.out.println("");
		System.out.println("");


		/** (11) 복호화된 대칭키로 암호화된 송신 자료를 복호화 **/
		// 송신기관 자료 대칭키 복호화
		byte[] bRemoteMsg = seedDecrypt(remoteASKey, remoteIv, bEncRemoteMsg);


		// 전 참가기관은 자료를 동일한 인코딩 방식으로 송수신
		String  remoteMsg = new String(bRemoteMsg, ENCODE_STR);

		System.out.println("[" + step++ + "] 송신기관 복호화 전달 자료 암호문 size[" + bEncRemoteMsg.length + "] 원문 size[" + bRemoteMsg.length + "]");
		System.out.println("[수신된 원문]");
		//for (int i = 0; i < bEncRemoteMsg.length; i++) System.out.print(Integer.toHexString(0xff & bEncRemoteMsg[i])+" ");
		System.out.println(remoteMsg);
		System.out.println("");

		System.out.println("[금융거래정보 암호화 송수신 예제 END]");
	}

	/**
	 * 암호화키쌍(private key, public key) 생성
	 * @version 2016. 4. 15.
	 * @return 암호화키쌍
	 * @throws NoSuchAlgorithmException
	 */
	public KeyPair getKeyPair()
			throws NoSuchAlgorithmException
	{
		// 암호화 알고리즘 생성 : 2048bit RSA
		KeyPairGenerator generator = KeyPairGenerator.getInstance(PUB_ALGORITHM);
		generator.initialize(PUB_KEY_SIZE);

		// 키쌍 생성 (개인키, 공개키)
		KeyPair keyPair = generator.generateKeyPair();

		return keyPair;
	}

	/**
	 * 공개키 복원
	 * @version 2016. 4. 15.
	 * @param bPubKey 공개키
	 * @return 공개키
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public PublicKey getPublicKey(byte[] bPubKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		KeyFactory factory = KeyFactory.getInstance(PUB_ALGORITHM);

		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(bPubKey);

		PublicKey pubKey = factory.generatePublic(pubKeySpec);

		return pubKey;
	}

	public byte[] seedEncrypt(byte[] asKey, byte[] iv, byte[] bPlain)
	{
		//KISA_SEED_CBC cipher = new KISA_SEED_CBC();
		//return cipher.SEED_CBC_Encrypt(asKey, iv, bPlain, 0, bPlain.length);
		return KISA_SEED_CBC.SEED_CBC_Encrypt(asKey, iv, bPlain, 0, bPlain.length);
	}

	public byte[] seedDecrypt(byte[] asKey, byte[] iv, byte[] bEnc)
	{
		//KISA_SEED_CBC cipher = new KISA_SEED_CBC();
		//return cipher.SEED_CBC_Decrypt(asKey, iv, bEnc, 0, bEnc.length);
		return KISA_SEED_CBC.SEED_CBC_Decrypt(asKey, iv, bEnc, 0, bEnc.length);
	}

	/**
	 * RSA 공개키 암호화
	 * @version 2016. 4. 20.
	 * @param pubKey 공개키
	 * @param bPlainText 평문
	 * @return 암호화 결과
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] RSAEncrypt(PublicKey pubKey, byte[] bPlainText)
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher = Cipher.getInstance(PUB_CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] bEncText = cipher.doFinal(bPlainText);

		return bEncText;
	}

	/**
	 * RSA 공개키 복호화
	 * @version 2016. 4. 20.
	 * @param priKey 개인키
	 * @param bEncText 암호문
	 * @return 복호화 결과
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] RSADecrypt(PrivateKey priKey, byte[] bEncText)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher = Cipher.getInstance(PUB_CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		byte[] bDecText = cipher.doFinal(bEncText);

		return bDecText;
	}

	/**
	 * byte data를 지정된 file로 생성한다.
	 * @param filename 저장할 파일명.
	 * @param data byte 배열 데이터.
	 * @throws Exception 파일 저장 실패시 발생.
	 */
	public void saveFile(String filename, byte[] data)
			throws Exception
	{
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			fos.write(data);
			fos.flush();
		}
		catch( Exception e ) {
			throw e;
		}
		finally {
			if(fos != null) { try { fos.close(); } catch(IOException e) { } }
		}
	}

	/**
	 * 파일을 읽어서 바이트 배열로 리턴한다.
	 * @param filename 읽을 파일명.
	 * @return 파일의 내용인 바이트 배열.
	 * @throws Exception 파일 읽기 실패시 발생.
	 */
	public byte[] readFile(String filename)
			throws Exception
	{
		FileInputStream fis = null;
		DataInputStream dis = null;
		byte[] result = null;
		try {
			fis = new FileInputStream(filename);
			dis = new DataInputStream(fis);

			long length = new File(filename).length();

			result = new byte[(int)length];

			dis.readFully(result);
		}
		catch( Exception e ) {
			throw e;
		}
		finally {
			if( fis != null ) { try { fis.close(); } catch( IOException e ) { } }
			if( dis != null ) { try { dis.close(); } catch( IOException e ) { } }
		}

		return result;
	}
}
