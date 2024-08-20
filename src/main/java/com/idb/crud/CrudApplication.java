package com.idb.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.idb.crud.entity.Student;
import com.idb.crud.repository.StudentRepository;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context =	SpringApplication.run(CrudApplication.class, args);

		// StudentRepository studentRepository = context.getBean(StudentRepository.class);

		// for (int i = 0; i < Integer.MAX_VALUE; i++) {
		// 	try {
		// 		Student student = new Student("firstName"+i, "lastName "+i, "roll "+i, "address "+i, "stClass "+i, "session "+i, "photo "+i);
		// 		studentRepository.save(student);
		// 	} catch (Exception e) {
				
		// 	}
		// }
	}

}
