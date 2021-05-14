package org.tain.working._gzip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipMain {

	private static final int BUF_SIZ = 4096;
	
	public static void main(String[] args) throws IOException {
		
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		
		String file1 = "gzip1.jar";
		String file2 = "gzip2.gz";
		String file3 = "gzip3.jar";
		
		System.out.println("압축 시작");
		
		if (Boolean.TRUE) {
			InputStream in = new FileInputStream(file1);
			OutputStream out = new GZIPOutputStream(new FileOutputStream(file2));
			byte[] buf = new byte[BUF_SIZ];
			int n;
			while ((n = in.read(buf)) != -1)
				out.write(buf, 0, n);
			in.close();
			out.close();
		}
		
		endTime = System.currentTimeMillis();
		System.out.printf("압축시간: %d ms\n", endTime - startTime);
		startTime = endTime;
		
		if (Boolean.TRUE) {
			InputStream in = new GZIPInputStream(new FileInputStream(file2));
			OutputStream out = new FileOutputStream(file3);
			byte[] buf = new byte[BUF_SIZ];
			int n;
			while ((n = in.read(buf)) != -1)
				out.write(buf, 0, n);
			in.close();
			out.close();
		}
		
		endTime = System.currentTimeMillis();
		System.out.printf("복원시간: %d ms\n", endTime - startTime);
	}
}
