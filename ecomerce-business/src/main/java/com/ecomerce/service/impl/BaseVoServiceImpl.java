package com.ecomerce.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ecomerce.core.web.WebConstants;
import com.ecomerce.dao.custom.base.util.QueryOrder;
import com.ecomerce.dao.custom.base.util.QueryOrderDirection;

/**
 * Created by Roberto
 */
public abstract class BaseVoServiceImpl {

	private static Logger LOGGER = LoggerFactory
			.getLogger(BaseVoServiceImpl.class);

	protected String constructLikeParam(String str) {
		if (null != str)
			return str + "%";
		return str;
	}

	protected String constructLikeParam2(String str) {
		if (null != str)
			return "%" + str + "%";
		return str;
	}

	@SuppressWarnings("rawtypes")
	protected Map<String, Object> constructMapReturn(List voList,
			long totalElements, int totalPages) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(WebConstants.PageParameter.LIST_DATA, voList);
		map.put(WebConstants.PageParameter.TOTAL_ELEMENTS, totalElements);
		map.put(WebConstants.PageParameter.TOTAL_PAGES, totalPages);

		return map;
	}

	public Pageable getPageable(int page, int limit, String sortBy, String dir) {
		if (sortBy != null && dir != null) {
			Sort sort = getSortBy(sortBy, dir);
			return new PageRequest(page, limit, sort);
		}
		return new PageRequest(page, limit);
	}

	public Sort getSortBy(String sortBy, String direction) {
		if (StringUtils.equalsIgnoreCase(direction, "asc")) {
			return new Sort(Sort.Direction.ASC, sortBy);
		} else {
			return new Sort(Sort.Direction.DESC, sortBy);
		}
	}
	
	
	public QueryOrder getSortByForQueryDirection(String sortBy, String direction) {
		if (StringUtils.equalsIgnoreCase(direction, "asc")) {
			return new QueryOrder(sortBy, QueryOrderDirection.ASC);
		} else {
			return new QueryOrder(sortBy, QueryOrderDirection.DESC);
		}
	}
	
	@PersistenceContext
	protected EntityManager em;
	
	
	public String GetSettingDataFixed(String prefix) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select model.nilaiField from SettingDataFixed ")
				.append(" model  where model.namaField ='"+prefix+"' ");
		Query query = em.createQuery(buffer.toString());

		return (String)query.getSingleResult();
	}

}
