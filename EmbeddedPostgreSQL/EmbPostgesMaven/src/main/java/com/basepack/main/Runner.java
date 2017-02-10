package com.basepack.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.basepack.model.UserDetails;

import ru.yandex.qatools.embed.service.PostgresEmbeddedService;


public class Runner {

	public static void main(String[] args)  {
		
        // Starting the embedded services within temporary dir
        PostgresEmbeddedService postgres = null;
        Connection connection;
        
        String dbName = "tomato";
        String db_user = "username";
        String db_password = "password";        
        int port = 5429;
        
        String db_conection = "jdbc:postgresql://localhost:" + port + "/" + dbName;
        
		try {
			
		/** Server start */
			
			postgres = new PostgresEmbeddedService(
			        "localhost", port, db_user, db_password,  dbName
			);
			
			postgres.start();
			 
			System.out.println("EbeddedPostgreSQL started.");
			 
			// Simple JDBC connection to test connection to EbeddedPostgreSQL server
			connection = DriverManager.getConnection(db_conection, db_user, db_password);
			 
			System.out.println("Connection ok.");
			 
		/** Working with data */
			 
			UserDetails user = new UserDetails();		
			user.setUserName("Seniour Tomato");
					
			/* now we need persist the object to the DB
			   let's use Hibernate API without DAO! */
			
			// reading configuration file and creating a session factory		
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			
			// open new session from SessionFactory object
			Session session = sessionFactory.openSession();
			
			// need to create a transaction
			session.beginTransaction();
			
			// saving objects
			session.save(user);
			
			
			// after saving we will have to end the transaction
			session.getTransaction().commit();
			
			// closing session
			session.close();

			System.out.println("Press ENTER to continue...");
			System.in.read();
			
			
			/* Let's fetch user from DB now */
			
			user = null;
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			// fetching: specify class and what data do we need,
			// Primary Key field usually, e.g. =1 here	
			user = (UserDetails) session.get(UserDetails.class, 1);
			System.out.println("User Name retrieved is " + user.getUserName());
			 
			 
		/** Server stop */
		 
			// wait for input to pause
			System.out.println("Press ENTER to continue...");
			System.in.read();
			 

			 
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			postgres.stop();
			
			System.out.println("\nEbeddedPostgreSQL stopped.\n");
		}
       
		

        

		
		
	}
}
