package com.basepack.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.yandex.qatools.embed.service.PostgresEmbeddedService;


public class Runner {

	public static void main(String[] args)  {
		
        // Starting the embedded services within temporary dir
        PostgresEmbeddedService postgres;
        Connection connection;
        
        String dbName = "tomato";
        String db_user = "username";
        String db_password = "password";        
        int port = 5429;
        
        String db_conection = "jdbc:postgresql://localhost:" + port + "/" + dbName;
        
		try {
			postgres = new PostgresEmbeddedService(
			        "localhost", port, db_user, db_password,  dbName
			);
			
			 postgres.start();
			 
			 System.out.println("EbeddedPostgreSQL started.");
			 
			 // Simple JDBC connection to test connection to EbeddedPostgreSQL server
			 connection = DriverManager.getConnection(db_conection, db_user, db_password);
			 
			 System.out.println("Connection ok. Press ENTER to stop server...");
			 
			 // wait for input to pause
			 System.in.read();
			 
			 postgres.stop();
			 
			 System.out.println("/nEbeddedPostgreSQL stopped./n");
			 
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		

        

		
		
	}
}
