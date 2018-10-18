package com.cg.project.daoservices;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.project.beans.Associate;

public class AssociateDAOImpl implements AssociateDAO{
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("JPA-PU");

	@Override
	public Associate save(Associate associate){
		EntityManager entityManager=factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(associate);
		entityManager.getTransaction().commit();
		entityManager.close();
		return associate;
	}
	
	@Override
	public boolean update(Associate associate) {
		EntityManager entityManager=factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(associate);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}
	

	@Override
	public Associate findOne(int associateId) {
		EntityManager entityManager=factory.createEntityManager();
		return entityManager.find(Associate.class, associateId);
		
	}

	@Override
	public ArrayList<Associate> findAll(){
		EntityManager entityManager=factory.createEntityManager();
		Query query=entityManager.createQuery("from Associate a");
		ArrayList<Associate>list=(ArrayList<Associate>)query.getResultList();
		return null;
	}



}
