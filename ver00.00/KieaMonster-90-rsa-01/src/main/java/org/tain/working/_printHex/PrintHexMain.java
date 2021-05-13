package org.tain.working._printHex;

import java.io.UnsupportedEncodingException;

public class PrintHexMain {

	public static void main(String[] args) throws Exception {
		String strData = "0123456789abcdefghijklmnop!@#$%^&*()qrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		PrintHexMain.printHex(strData);
		
		if (Boolean.TRUE) {
			byte[] bData = new byte[256];
			for (int i=0x00; i <= 0xff; i++) {
				bData[i] = (byte) i;
			}
			PrintHexMain.printHex(bData);
		}
	}
	
	private static final String PRINT_HEX_CHARSET = "euc-kr";
	//private static final String PRINT_HEX_CHARSET = "utf-8";
	
	public static void printHex(String strData) throws UnsupportedEncodingException, Exception {
		printHex(strData.getBytes(PRINT_HEX_CHARSET));
	}
	
	public static void printHex(final byte[] bData) throws Exception {
		
		int lenData = bData.length;
		System.out.println("Size: " + lenData);
		System.out.println("Offset--  Hex--------------------- ------------------------  Data-------------");
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		int maxLen = (lenData + 15) / 16 * 16;
		for (int i=0; i < maxLen; i += 16) {
			int begSec = i;
			int midSec = i + 8;
			int endSec = i + 16;
			sb1.setLength(0);
			sb2.setLength(0);
			sb3.setLength(0);
			
			sb1.append(String.format("%08d", begSec));
			for (int j=begSec; j < endSec; j++) {
				if (j == midSec) {
					sb2.append(" ");
					sb3.append(" ");
				}
				if (j < lenData) {
					sb2.append(String.format("%02X ", bData[j] & 0xff));
					//char ch = (char)(bData[j] & 0xFF);
					// if (Character.isLetterOrDigit(ch)) {
					if (0x20 <= bData[j] && bData[j] < 0x7f) {
						sb3.append((char)(bData[j] & 0xFF));
					} else {
						sb3.append('.');
					}
				} else {
					sb2.append("   ");
					sb3.append(" ");
				}
			}
			sb.append(sb1.toString()).append("  ");
			sb.append(sb2.toString()).append("  ");
			sb.append(sb3.toString()).append('\n');
			System.out.println(sb1.toString() + "  " + sb2.toString() + "  " + sb3.toString());
		}
		//System.out.println(sb.toString());
		System.out.println("--------  ------------------------ ------------------------  -------- --------");
	}
}
