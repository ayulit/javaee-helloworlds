package com.ayulit.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** This class makes operations with DB */
@Service("jpaFruitServce") // =@Component (which means that is bean for <context:component-scan..) 
@Repository				   // =@Component, just informs that class has data access logics 
@Transactional // for defining transaction requirements (see Pro.Spring ch.9)
public class FriutServiceImpl implements FruitService {

	@PersistenceContext // for injection: JPA classics
	private EntityManager em;
	
	@Override
	public FruitORM findById(int id) {
		
		// using method of EntityManager with JPQL
		// returns implementation of interface!
		TypedQuery<FruitORM> query = em.createQuery("select f from FruitORM f where f.id=:id", FruitORM.class);
		query.setParameter("id", id);
		
		FruitORM  fruit = query.getSingleResult(); // row extraction as an object
		
		return fruit;
	}

	@Override
	public void save(FruitORM fruit) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FruitORM> findAll() {
		
		TypedQuery<FruitORM> query = em.createQuery("select f from FruitORM f", FruitORM.class);		
		List<FruitORM> fruits = query.getResultList();
		
		return fruits;
	}

}
