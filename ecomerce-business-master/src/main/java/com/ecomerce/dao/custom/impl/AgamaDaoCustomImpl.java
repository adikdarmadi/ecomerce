package com.ecomerce.dao.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecomerce.dao.custom.AgamaDaoCustom;

/**
 * Repository Custom class
 * 
 * @author Roberto
 */
@Repository("AgamaDaoCustom")
public class AgamaDaoCustomImpl  implements AgamaDaoCustom {

	@PersistenceContext
	protected EntityManager em;
	
	
	@Override
	public int countAgama() {

		StringBuffer buffer = new StringBuffer();

		buffer.append("select count(model.id) from  Agama model where  model.id is not null ");

		Query query = em.createQuery(buffer.toString());

		return ((Long) query.getSingleResult()).intValue();
	}

	

}
