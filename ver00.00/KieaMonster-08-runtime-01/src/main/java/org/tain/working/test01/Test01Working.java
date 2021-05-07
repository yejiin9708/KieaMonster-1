package org.tain.working.test01;

import java.lang.ProcessBuilder.Redirect;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Component;
import org.tain.utils.ProcessUtils;

@Component
public class Test01Working {

	/*
	 * - “Runtime runtime = Runtime.getRuntime();”: 런타임 객체를 생성합니다.
	 * - “Process p = runtime.exec(“프로그램경로명”);”: exec 메소드를 이용하여 프로세스를 생성합니다.
	 * - “p.waitFor();”: 자식 프로세스가 종료될 때까지 기다립니다.
	 * - “p.destroy();”: 부모 프로세스에서 자식 프로세스를 강제로 종료시킵니다.
	 * - “System.exit(0);”: 부모 프로세스만 종료되고 자식 프로세스는 계속 실행됩니다.
	 */
	/*
	 *  5.2 Runtime 클래스가 제공해 주는 주요 메소드
	 * ------------------------------------------------------------------------
	 * - public static Runtime getRuntime(): 현재 실행되고 있는 자바 애플리케이션과 관련된 런타임 객체를 리턴해 줍니다.
	 * - public void exit(int status): 현재 자바 가상머신을 종료합니다. status 매개변수는 종료시의 상태값을 나타내며,
	 *     일반적으로 0 이외의 값은 비정상적으로 종료되었음을 의미합니다.
	 * - public Process exec(String command) throws IOException: 주어진 명령어를 독립된 프로세스로 실행시켜 줍니다.
	 *     exec(command, null)와 같이 실행시킨 것과 같습니다.
	 * - public Process exec(String command, String envp[]) throws IOException:
	 *     주어진 명령어를 주어진 환경을 갖는 독립된 프로세스로 실행시켜 줍니다.
	 *     이 메소드는 명령어 문자열을 토큰으로 나누어 이 토큰들을 포함하고 있는 cmdarray라는 새로운 배열을 생성합니다.
	 *     그리고 나서 exec(cmdarray, envp)을 호출합니다.
	 * - public Process exec(String cmdarray[]) throws IOException:
	 *     주어진 문자열 배열에 있는 명령어와 매개변수를 이용하여 독립된 프로세스로 실행시켜 줍니다.
	 *     exec(cmdarray, null)을 호출합니다.
	 * - public Process exec(String cmdarray[], String envp[]) throws IOException:
	 *     주어진 문자열 배열에 있는 명령어와 매개변수를 이용하여 주어진 환경을 갖는 독립된 프로세스로 실행시켜 줍니다.
	 *     문자열 배열 cmdarray에는 명령어와 명령행 인자들을 나타내고 있습니다.
	 * - public native long freeMemory(): 시스템에 남아있는 메모리의 양을 얻습니다.
	 *     이 값은 항상 totalMemory() 메소드에 의해 얻어지는 값보다 작습니다.
	 * - public native long totalMemory(): 자바 가상머신의 최대 메모리 크기를 얻습니다.
	 */
	/*
	 * 5.3 Process 클래스가 제공해 주는 주요 메소드
	 * ------------------------------------------------------------------------
	 * - public abstract OutputStream getOutputStream(): 자식 프로세스의 출력 스트림을 얻습니다.
	 * - public abstract InputStream getInputStream(): 자식 프로세스의 입력 스트림을 얻습니다.
	 * - public abstract InputStream getErrorStream(): 자식 프로세스의 에러 스트림을 얻습니다.
	 * - public abstract int waitFor() throws InterruptedException: 자식 프로세스가 종료될 때까지 기다립니다.
	 * - public abstract int exitValue(): 자식 프로세스가 종료할 때의 상태값을 얻습니다.
	 * - public abstract void destroy(): 자식 프로세스를 강제로 종료시킵니다.
	 * ------------------------------------------------------------------------
	 * 
	 * 6. 메모리 관리
	 * ------------------------------------------------------------------------
	 * public long totalMemory ( ) : 현재 할당받은 총 시스템 메모리 바이트 갯수.
	 * public long freeMemory ( ) : 현재 남아 있는 자유 메모리 바이트 갯수(근사치).
	 * 
	 * Runtime rt = Runtime.getRuntime ( );
	 * System.out.println ( rt.totalMemory ( ) );
	 * System.out.println ( rt.freeMemory ( ) );
	 * ------------------------------------------------------------------------
	 * 
	 * 
	 * 나. 시스템 정보
	 * 
	 * static PrintStream err - The "standard" error output stream.
	 * static InputStream in - The "standard" input stream.
	 * static PrintStream out - The "standard" output stream.
	 * 
	 * static void arraycopy (Object src, int src_position, Object dst, int dst_position, int length)
	 * static long currentTimeMillis () - Returns the current time in milliseconds.
	 * static void exit (int status) - Terminates the currently running Java Virtual Machine.
	 * static void gc () - Runs the garbage collector.
	 * static String getenv (String name) - Deprecated from getProperty method
	 * 
	 * static Properties getProperties() - Determines the current system properties.
	 * static String getProperty(String key) - Gets the system property indicated by the specified key.
	 * static String getProperty(String key, String def) - Gets the system property indicated by the specified key.
	 * static SecurityManager getSecurityManager() - Gets the system security interface.
	 * static int identityHashCode(Object x) - Returns the same hashcode for the given object as would be returned by the default method hashCode(), whether or not the given object's class overrides hashCode().
	 * static void load(String filename) - Loads a code file with the specified filename from the local file system as a dynamic library.
	 * static void loadLibrary(String libname) - Loads the system library specified by the libname argument.
	 * static String mapLibraryName(String libname) - Maps a library name into a platform-specific string representing a native library.
	 * static void runFinalization() - Runs the finalization methods of any objects pending finalization.
	 * static void runFinalizersOnExit(boolean value) - Deprecated. This method is inherently unsafe. It may result in finalizers being called on live objects while other threads are concurrently manipulating those objects, resulting in erratic behavior or deadlock.
	 * static void setErr(PrintStream err) - Reassigns the "standard" error output stream.
	 * static void setIn(InputStream in) - Reassigns the "standard" input stream.
	 * static void setOut(PrintStream out) - Reassigns the "standard" output stream.
	 * static void setProperties(Properties props) - Sets the system properties to the Properties argument.
	 * static String setProperty(String key, String value) - Sets the system property indicated by the specified key.
	 * static void setSecurityManager(SecurityManager s)
	 * 
	 * String System.getProperty ( String key ): key에 해당하는 시스템정보를 가져온다.
	 * String System.setProperty ( String key, String value )
	 * Properties System.getProperties ( ): 시스템 정보를 몽땅 갖고 온다.
	 * System.setProperties ( Properties props )
	 * 
	 * 사용법
	 * ------------------------------------------------------------------------
	 * String currentDirectory = System.getProperty ( "user.dir" );
	 * ------------------------------------------------------------------------
	 * 
	 * Key값
	 * ------------------------------------------------------------------------
	 * // 일반적으로, 보안에 걸리지 않는 표준 시스템 프로퍼티
	 * "java.version", // 자바의 버전 ( "1.0.2", "1.1", "1.1.3", "1.2", ...)
	 * "java.class.version", // 자바 클래스 화일 포맷 버전
	 * "java.vendor", // 자바 가상 머쉰 판매 회사
	 * "java.vendor.url", // 자바 가상 머쉰 판매 회사의 URL
	 * "os.name", // 운영체제 이름 ( "Windows 95", "Windows NT", "MacOS", "Linux", "Solaris", "OS/2", "OSF1", ... )
	 * "os.version", // 운영체제 버전
	 * "os.arch", // 컴퓨터 기종 ( "x86", "sparc", ... )
	 * "line.separator", // 시스템에서의 행 분리 문자열 ( " ", " ", " " )
	 * "file.separator", // 화일 경로 이름내의 디렉토리 구분 문자 ("", "/")
	 * "path.separator", // 화일 경로 이름 리스트의 구분 문자 (";", ":")
	 * 
	 * // 일반적으로, 보안에 의한 접근 제한을 받는 표준 시스템 프로퍼티 (애플릿의 경우)
	 * "java.home", // JDK 설치 디렉토리
	 * "java.class.path", // 패키지가 있는 디렉토리 리스트 (일반적으로, CLASSPATH 환경변수값에 의해 영향 받음)
	 * "user.name", // 사용자의 등록 이름
	 * "user.home", // 사용자의 홈 디렉토리
	 * "user.dir", // 사용자의 현재 작업 디렉토리(파일을 뺀 전체 경로)
	 * 
	 * // JDK 1.1에서 비공식적으로 추가된 지역 정보 시스템 프로퍼티
	 * // 일반적으로, 보안에 의한 접근 제한을 받음 (애플릿의 경우)
	 * "file.encoding", // 사용자의 디폴트 문자 인코딩 이름
	 * "user.language", // 사용자의 언어
	 * "user.region", // 사용자의 거주 지역 (보통, 국가)
	 * "user.timezone", // 사용자 지역의 표준 시간대 이름
	 * 
	 * // 웹 브라우저 정보 비표준 시스템 프로퍼티
	 * "browser", // 웹 브라우저 이름
	 * "browser.version", // 웹 브라우저 버전
	 * "browser.vendor", // 웹 브라우저 회사
	 * ------------------------------------------------------------------------
	 */
	/*
	 * public Process exec(String command);
	 * public Process exec(String [] cmdArray);
	 * public Process exec(String command, String [] envp);
	 * public Process exec(String [] cmdArray, String [] envp);
	 */
	
	/*
	 * method-1
	 */
	public void jobForRuntime() throws Exception {
		String[] command = new String[] { "echo", ">>>>>>>>>>> Hello, world!!!" };
		
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(command);
		//Process process = Runtime.getRuntime().exec(command);
		
		ProcessUtils.printStream(process);
	}
	
	/*
	 * method-2
	 */
	public void jobForProcessBuilder() throws Exception {
		String[] command = new String[] { "echo", ">>>>>>>>>>> Hello, world!!!" };
		
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		ProcessUtils.printStream(process);
	}
	
	/*
	 * method-3
	 */
	public void jobForCommandsExec() throws Exception {
		String[] command = new String[] { "echo", ">>>>>>>>>>> Hello, world!!!" };
		
		int size = command.length;
		CommandLine commandLine = CommandLine.parse(command[0]);
		for (int i=1; i < size; i++) {
			commandLine.addArgument(command[i]);
		}
		
		DefaultExecutor executor = new DefaultExecutor();
		executor.execute(commandLine);
	}
	
	/*
	 * method-4
	 */
	public void jobForProcessBuilderRedirect() throws Exception {
		String[] command = new String[] { "echo", ">>>>>>>>>>> Hello, world!!!" };
		
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.redirectOutput(Redirect.INHERIT);
		builder.redirectError(Redirect.INHERIT);
		
		builder.start();
	}
}
