package org.tain.working._kisaseed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class KisaSeedMain {

	private static String CHARSET = "utf-8";
	private static final String PBUserKey = "kics2019Hwang!@#"; //16Byte로 설정
	private static final String DEFAULT_IV = "1234567890123456"; //16Byte로 설정

	/*
	public static byte pbUserKey[] = { (byte) 0x2c, (byte) 0x11, (byte) 0x19, (byte) 0x1d, (byte) 0x1f, (byte) 0x16, (byte) 0x12,
            (byte) 0x12, (byte) 0x11, (byte) 0x19, (byte) 0x1d, (byte) 0x1f, (byte) 0x10, (byte) 0x14, (byte) 0x1b,
            (byte) 0x16 };
    */
	private static byte pbUserKey[] = PBUserKey.getBytes();

	/*
    public static byte bszIV[] = { (byte) 0x27, (byte) 0x28, (byte) 0x27, (byte) 0x6d, (byte) 0x2d, (byte) 0xd5, (byte) 0x4e,
            (byte) 0x29, (byte) 0x2c, (byte) 0x56, (byte) 0xf4, (byte) 0x2a, (byte) 0x65, (byte) 0x2a, (byte) 0xae,
            (byte) 0x08 };
    */
	private static byte bszIV[] = DEFAULT_IV.getBytes();
    

    /***************************************
     * Main 함수
     * @param args
     **************************************/
    public static void main(String[] args) {

    	
    	String filePath = "C:/egov_dev/workspace/JavaSample/testdata/case0.txt";
    	
        try {
        	
        	String plainText = fileRead(filePath);
        	
		    /* (1) 시작 시간 측정 */
			long startTimeLong = getCurrentTimeLong();
			/*test*/System.out.println( "startTimeLong : " + startTimeLong );
        	
        	/*************************************
        	 * 암호화
        	 *************************************/
        	byte[] encryptData = encrypt(plainText);
            //System.out.println("encrypt:" + new String(encryptData, "utf-8"));
            
            /*************************************
             * 복호화
             *************************************/
            plainText = decrypt(encryptData);
            //System.out.println("decrypt:" + plainText);
            
			/* (2)종료 시간  측정   */
			long endTimeLong = getCurrentTimeLong();
			/*test*/System.out.println( "endTimeLong : " + endTimeLong );

			/* (3)시스템 사용기간 계산
			 *  - 시간 차이는 초(sec)단위로 계산되며 long 타입 변수에 저장된다.
			 */
			//long useTime = Math.abs( endTimeLong - startTimeLong )/1000; //절대값을 반환 - sec
			//*test*/System.out.println( "  useTime : " + useTime + "(Sec)");
			long useTime = Math.abs( endTimeLong - startTimeLong ); //절대값을 반환 - 밀리세컨
			/*test*/System.out.println( "  useTime : " + useTime + "(MM)");
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
        
    }

    public static byte[] encrypt(String str) {

        byte[] enc = null;
        try {

        	//암호화 함수 호출
            enc = KISA_SEED_CBC.SEED_CBC_Encrypt(pbUserKey, bszIV, str.getBytes(CHARSET), 0, str.getBytes(CHARSET).length);
            //enc = KISA_SEED_ECB.SEED_ECB_Encrypt(pbUserKey, str.getBytes(CHARSET),  0, str.getBytes(CHARSET).length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**JDK1.8 일 때 사용
        Encoder encoder = Base64.getEncoder();
        byte[] encArray = encoder.encode(enc);
        */
        byte[] encArray  = Base64.getEncoder().encode(enc);
         
        return encArray;
    }

    public static String decrypt(byte[] str) {
        
    	/**JDK1.8 일 때 사용
    	Decoder decoder = Base64.getDecoder();
        byte[] enc = decoder.decode(str);
        */
    	byte[] enc  = Base64.getDecoder().decode(str);
        
        String result = "";
        byte[] dec = null;

        try {
            //복호화 함수 호출
            dec = KISA_SEED_CBC.SEED_CBC_Decrypt(pbUserKey, bszIV, enc, 0, enc.length);
        	//dec = KISA_SEED_ECB.SEED_ECB_Decrypt(pbUserKey, enc, 0, enc.length);
            result = new String(dec, CHARSET);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

       return result;
    }
    
	//전체 파일 내용 읽어오기
	public static String fileRead(String filePath) throws Exception{
        
		String line = "", fullLine = "";
		
		FileReader fr = new FileReader(filePath);
		BufferedReader reader = new BufferedReader( fr );
		while( ( line = reader.readLine()) != null ){
			//System.out.println( line );
			fullLine += line + "\n";
			//fullLine += line;
		}
		reader.close();
		return fullLine;
	}
	
	/**
	 * - 용도 : 현재 시간을 long 타입으로 변환하며 시간 차를 구하는 함수로 사용
	 * - Return Type: long
	 * - Argument Type : none
	 */
	public static long getCurrentTimeLong() {
		long time;
        java.util.Calendar cal = java.util.Calendar.getInstance();
        time = cal.getTimeInMillis();
        return time;
    }
}
