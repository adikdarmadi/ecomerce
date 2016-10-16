package com.ecomerce.dao.custom;

import org.springframework.stereotype.Repository;

import com.ecomerce.dao.custom.base.impl.CoreDaoImpl;
import com.ecomerce.entities.Agama;

/**
 * Repository Custom class
 * 
 * @author Roberto
 */
@Repository("AgamaDaoCustom")
public class AgamaDaoCustomImpl extends CoreDaoImpl<Agama> implements
		AgamaDaoCustom {

	public AgamaDaoCustomImpl() {
		super(Agama.class);
	}

}
