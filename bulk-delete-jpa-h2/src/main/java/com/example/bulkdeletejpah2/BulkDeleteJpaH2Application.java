package com.example.bulkdeletejpah2;

import com.example.bulkdeletejpah2.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BulkDeleteJpaH2Application implements CommandLineRunner {
	@Autowired
	private DeleteService deleteService;

	public static void main(String[] args) {
		SpringApplication.run(BulkDeleteJpaH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CriteriaDelete::Bulk Delete Started...");
		deleteService.deleteBulkProducts("Desktop");
		System.out.println("CriteriaDelete::Bulk Delete Done.");
		//2021-11-07 21:57:52.407  INFO 13304 --- [           main] c.e.b.BulkDeleteJpaH2Application         : Started BulkDeleteJpaH2Application in 3.338 seconds (JVM running for 3.745)
		//CriteriaDelete::Bulk Delete Started...
		//Hibernate:
		//    delete
		//    from
		//        product
		//    where
		//        name=?
		//2021-11-07 21:57:52.556 TRACE 13304 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [Desktop]
		//CriteriaDelete::Bulk Delete Done.
		//

	}
}
