package com.ayulit.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourcesConfig {

	/* dataSource is like generalised factory for connections
	 * We will use Spring's realization of 'dataSource'*/
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5432/grocery");
        ds.setUsername("postgres");
        ds.setPassword("123");
		
        return ds;		
	}

}
