package com.ayulit.base;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ayulit.config.DataSourcesConfig;
import com.ayulit.config.EntityManagerFactoriesConfig;
import com.ayulit.config.TransactionManagersConfig;

public class SpringJPASample {

	public static void main(String[] args) {
		
//		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//		ctx.load("app-context-annotation.xml");

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();		
		ctx.register(DataSourcesConfig.class,
				     EntityManagerFactoriesConfig.class,
				     TransactionManagersConfig.class);

		ctx.refresh();
		
		FruitService fruitService = ctx.getBean("jpaFruitServce", FruitService.class);

		FruitORM fruit = null;
		
		// test #0
		fruit = new FruitORM();
		fruit.setName("Orange");
		fruitService.save(fruit);
		
		// test #1
		fruit = fruitService.findById(1);		
		System.out.println(fruit);
		
		// test #2
		listFruits(fruitService.findAll());
		
		
		ctx.close();
	}

	private static void listFruits(List<FruitORM> fruits) {
		System.out.println("\nAll fruits info from table");
		
		for(FruitORM fruit: fruits) {
			System.out.println(fruit);
		}		
		
	}

}
