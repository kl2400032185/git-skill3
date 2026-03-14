package com.hqllab;

import com.hqllab.loader.ProductDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HqlSkill3Application implements CommandLineRunner {

	@Autowired
	private ProductDataLoader productDataLoader;

	public static void main(String[] args) {
		SpringApplication.run(HqlSkill3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n╔════════════════════════════════╗");
		System.out.println("║   🛍️  PRODUCT MANAGER APP  🛍️   ║");
		System.out.println("║  HQL - Sorting, Pagination      ║");
		System.out.println("╚════════════════════════════════╝\n");
		
		// Start interactive menu
		productDataLoader.startInteractiveMenu();
	}
}
