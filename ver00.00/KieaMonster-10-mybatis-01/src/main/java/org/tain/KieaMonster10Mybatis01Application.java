package org.tain;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.models.User;
import org.tain.service.TimeService;
import org.tain.service.UserService;

//@MapperScan("org.tain.mappers")
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
	private UserService userService;
	
	@Autowired
	private TimeService timeService;
	
	@Override
	public void run(String... args) throws Exception {
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
	}
}
