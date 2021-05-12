package org.tain.working.base64;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Base64Working {

	// 직인 파일
	private final static String SEND_STAMP_IMG_NAME = "stamp.jpg";
	private final static String B64_STAMP_IMG_NAME = "b64stamp.b64";
	private final static String RECV_STAMP_IMG_NAME = "recvstamp.jpg";

	/**
	 * main
	 * @version 2016. 4. 28.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Base64Working sample = new Base64Working();
		sample.start();
	}

	/**
	 * 샘플
	 * @version 2016. 4. 20.
	 * @throws Exception
	 */
	public void start() throws Exception {
		System.out.println("[금융거래정보 직인정보 송수신 예제 START]");

		// 직인 이미지 파일 읽음
		byte[] sendImg = readFile(SEND_STAMP_IMG_NAME);

		// BASE64 인코딩
		//byte[] b64SendImg = Base64.encodeBase64(sendImg);
		byte[] b64SendImg = Base64.getEncoder().encode(sendImg);

		System.out.println("송신 직인 이미지 size[" + sendImg.length + "] b64 size[" + b64SendImg.length + "]");
		//System.out.println("[직인]");
		//System.out.println(new String(sendImg) + "\n");
		//System.out.println("[직인(BASE64)]");
		//System.out.println(new String(b64SendImg) + "\n");

		// 파일 송신
		saveFile(B64_STAMP_IMG_NAME, b64SendImg);

		// 파일 수신
		byte[] b64RecvImg = readFile(B64_STAMP_IMG_NAME);

		// BASE64 디코딩
		byte[] recvImg = Base64.getDecoder().decode(b64RecvImg);

		System.out.println("수신 직인 이미지 size[" + recvImg.length + "] b64 size[" + b64RecvImg.length + "]");
		//System.out.println("[직인]");
		//System.out.println(new String(recvImg) + "\n");

		// 파일 저장
		saveFile(RECV_STAMP_IMG_NAME, recvImg);

		System.out.println("[금융거래정보 직인정보 송수신 예제 END]\n");
	}

	/**
	 * byte data를 지정된 file로 생성한다.
	 * @param filename 저장할 파일명.
	 * @param data byte 배열 데이터.
	 * @throws Exception 파일 저장 실패시 발생.
	 */
	public void saveFile(String filename, byte[] data) throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			fos.write(data);
			fos.flush();
		} catch( Exception e ) {
			throw e;
		} finally {
			if(fos != null) { try { fos.close(); } catch(IOException e) { } }
		}
	}

	/**
	 * 파일을 읽어서 바이트 배열로 리턴한다.
	 * @param filename 읽을 파일명.
	 * @return 파일의 내용인 바이트 배열.
	 * @throws Exception 파일 읽기 실패시 발생.
	 */
	public byte[] readFile(String filename) throws Exception {
		FileInputStream fis = null;
		DataInputStream dis = null;
		byte[] result = null;
		try {
			fis = new FileInputStream(filename);
			dis = new DataInputStream(fis);

			long length = new File(filename).length();

			result = new byte[(int)length];

			dis.readFully(result);
		} catch( Exception e ) {
			throw e;
		} finally {
			if( fis != null ) { try { fis.close(); } catch( IOException e ) { } }
			if( dis != null ) { try { dis.close(); } catch( IOException e ) { } }
		}

		return result;
	}
}
