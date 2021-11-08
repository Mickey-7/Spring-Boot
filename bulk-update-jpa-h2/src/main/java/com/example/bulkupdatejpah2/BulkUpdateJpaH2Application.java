package com.example.bulkupdatejpah2;

import com.example.bulkupdatejpah2.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BulkUpdateJpaH2Application implements CommandLineRunner {

	@Autowired
	private UpdateService updateService;

	public static void main(String[] args) {
		SpringApplication.run(BulkUpdateJpaH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CriteriaUpdate::Bulk Update Started...");
		updateService.updateBulkProducts("Desktop", 31000.50);
		System.out.println("CriteriaUpdate::Bulk Update Done.");
	}
}
