package org.tain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.model.Husband;
import org.tain.model.Wife;
import org.tain.repository.HusbandRepository;
import org.tain.repository.WifeRepository;

@SpringBootApplication
public class KieaMonster05Jpa01Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster05Jpa01Application.class, args);
	}

	@Autowired
	WifeRepository wifeRepository;
	 
	@Autowired
	HusbandRepository husbandRepository;

	@Override
	public void run(String... arg0) throws Exception {
		deleteData();
		saveData();
		showData();
	}
	
	@Transactional
	private void deleteData(){
		wifeRepository.deleteAll();
		husbandRepository.deleteAll();
	}
	
	@Transactional
	private void saveData(){
		
		Wife mary = new Wife("Mary", "Smith", 24);
		Wife lauren = new Wife("Lauren", "Hill", 32);
		
		Husband peter = new Husband("Peter", "Johnson", 19);
		Husband phillip = new Husband("Phillip", "Davis", 27);
		
		mary.setHusband(peter);
		lauren.setHusband(phillip);
		
		// save wife
		wifeRepository.save(mary);
		wifeRepository.save(lauren);

	}
	
	@Transactional
	private void showData(){
		// get All data
		List<Wife> wifes = wifeRepository.findAll();
		List<Husband> husbands = husbandRepository.findAll();
		 
		System.out.println("===================Wifes:==================");
		wifes.forEach(System.out::println);
		 
		System.out.println("===================Husband:==================");
		husbands.forEach(System.out::println);
	}
}
