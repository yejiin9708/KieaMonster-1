package org.tain;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tain.models.User;
import org.tain.service.TimeService;
import org.tain.service.UserService;

// 고정 페키지 하위 폴더의 모든 파일을 매퍼로 한다.
//@MapperScan("org.tain.mappers")

// @Mapper 가 붙은 클래스를 스캔하여 매퍼 빈으로 등록한다.
@MapperScan(basePackageClasses = KieaMonster10Mybatis01Application.class)

@SpringBootApplication
public class KieaMonster10Mybatis01Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster10Mybatis01Application.class, args);
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TimeService timeService;
	
	@Override
	public void run(String... args) throws Exception {
		if (Boolean.TRUE) {
			Connection connection = this.dataSource.getConnection();
			System.out.println(">>>>> dbcp: " + this.dataSource.getClass());
			System.out.println(">>>>> driverName: " + connection.getMetaData().getDriverName());
			System.out.println(">>>>> driverVersion: " + connection.getMetaData().getDriverVersion());
			System.out.println(">>>>> url: " + connection.getMetaData().getURL());
			System.out.println(">>>>> userName: " + connection.getMetaData().getUserName());
		}
		
		if (!Boolean.TRUE) {
			this.jdbcTemplate.execute("insert into products (prod_name, prod_price) values ('hello', 12340)");
		}
		
		if (Boolean.TRUE) {
			List<User> lstUser = this.userService.getAllUsers();
			System.out.println(">>>>> lstUser: " + lstUser);
		}
		
		if (Boolean.TRUE) {
			String now1 = this.timeService.getNow1();
			System.out.println(">>>>> now1: " + now1);
		}
		
		if (Boolean.TRUE) {
			String now2 = this.timeService.getNow2();
			System.out.println(">>>>> now2: " + now2);
		}
		
		if (Boolean.TRUE) {
			System.exit(0);
		}
	}
}
