package com.ayulit.base;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ayulit.config.DataSourcesConfig;
import com.ayulit.config.EntityManagerFactoriesConfig;
import com.ayulit.config.TransactionManagersConfig;

import ru.yandex.qatools.embed.service.PostgresEmbeddedService;

public class SpringJPASample {

	public static void main(String[] args) {

		/* EmbeddedPostgres start */
				
        // Starting the embedded services within temporary dir
        PostgresEmbeddedService postgres = null;
        Connection connection = null;
        
        int port = 5429;
        String dbName = "grocery";
        String db_user = "postgres";
        String db_password = "123";        
                
        // just for testing connection
        String db_conection = "jdbc:postgresql://localhost:" + port + "/" + dbName;
        
		try {
			
		/* Server start */
			
			postgres = new PostgresEmbeddedService(
			        "localhost", port, db_user, db_password,  dbName
			);
			
			postgres.start();
			 
			System.out.println("EbeddedPostgreSQL started.");
			 
			// Simple JDBC connection to test connection to EbeddedPostgreSQL server
			connection = DriverManager.getConnection(db_conection, db_user, db_password);
			 
			System.out.println("Connection ok.");
			 
		/* Working with data */

//			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//			ctx.load("app-context-annotation.xml");

			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();		
			ctx.register(DataSourcesConfig.class,
					     EntityManagerFactoriesConfig.class,
					     TransactionManagersConfig.class);

			ctx.refresh();
			
			FruitService fruitService = ctx.getBean("jpaFruitServce", FruitService.class);

			FruitORM fruit = null;
			
			// test #0: saving
			fruit = new FruitORM();
			fruit.setName("Orange");
			fruitService.save(fruit);
			
			// test #1: getting row
			fruit = fruitService.findById(1);		
			System.out.println(fruit);
			
			// test #2: getting all rows
			listFruits(fruitService.findAll());

			ctx.close();

			// wait for input to pause
			System.out.println("Press ENTER to continue...");
			System.in.read();
		 
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
			
		} finally {

			/* Server stop */			
			
			postgres.stop();			
			System.out.println("\nEmbeddedPostgreSQL stopped.\n");
		}

	}

	private static void listFruits(List<FruitORM> fruits) {
		System.out.println("\nAll fruits info from table");
		
		for(FruitORM fruit: fruits) {
			System.out.println(fruit);
		}		
		
	}

}
