package com.ecomerce.dao.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("GenericDao")
public class GenericServDaoImpl<T> implements GenericServDao<Object> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> type;
	private String construc = "like";

	@Override
	public int dataCount(String entity, String value, String fieldS, String criteria, String values) {

		StringBuffer buffer = new StringBuffer();
		StringBuilder filedCriteria = new StringBuilder("");
		filedCriteria.append(getCriteria(fieldS, value));// parse field
		filedCriteria.append(parseCriteria(criteria, values)); // criteria

		buffer.append("select count(model.id)  from ").append(entity).append(" model  where  model.id is not null ")
				.append(filedCriteria.toString());

		Query query = em.createQuery(buffer.toString());

		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	@Transactional
	public List<Object> getDatas(String entity, String field, Integer rowStart, Integer rowEnd, String logic,
			String value, String fieldS, String operator, String criteria, String values) {

		StringBuilder filedShow = new StringBuilder();
		filedShow.append(parseField(field));// parse jumlah field di yang akan
											// ditampilkan

		StringBuilder filedCriteria = new StringBuilder();
		filedCriteria.append(getCriteria(fieldS, value));// parse field
		filedCriteria.append(parseCriteria(criteria, values)); // criteria

		StringBuilder entitySTR = new StringBuilder();
		entitySTR.append(entity).append(" model");

		StringBuffer buffer = new StringBuffer();
		buffer.append("select ").append(filedShow.toString()).append(" from ").append(entity)
				.append(" model  where model.id is not null ").append(filedCriteria.toString());
		if(field.split(",").length ==2)
		{
			buffer.append(" order by "+field.split(",")[1]);
		}
		Query query = em.createQuery(buffer.toString());
		query.setFirstResult(rowStart);
		query.setMaxResults(rowEnd);

		return query.getResultList();
	}

	public String parseField(String field) {

		StringBuilder filedShow = new StringBuilder();
		StringBuilder tmp = new StringBuilder();
		if (field.equals("*")) {
			filedShow.append("model");
		} else if (!field.equals("")) {
			String[] fields = field.split(",");

			for (String fieldisi : fields) {
				if (tmp.length() > 0) {
					tmp.append(",");
				}
				tmp.append("model.").append(fieldisi).append(" as ").append(fieldisi.replace(".", "_"));
			}

			filedShow.append(" new map(").append(tmp.toString()).append(" ) ");

		} else {
			filedShow.append("model");
		}

		return filedShow.toString();
	}

	public String getCriteria(String fieldS, String value) {
		StringBuilder buffer = new StringBuilder();

		if ((fieldS != "" && fieldS != null) && (value != "" && value != null)) {
			buffer.append("and lower(model.").append(fieldS).append(") like ");
			buffer.append("'%").append(value.toLowerCase()).append("%'");
		}

		return buffer.toString();
	}

	public String parseCriteria(String criteria, String values) {
		StringBuilder buffer = new StringBuilder();
		if (criteria != null && values != null) {
			String[] field = criteria.split(",");
			String[] value = values.split(",");
			if (field.length == value.length) {

				for (int x = 0; x < field.length; x++) {
					if (value[x].contains("{") && value[x].contains("}")) {
						buffer.append(" and model.").append(field[x]).append(" = ")
								.append(value[x].replace("{", "").replace("}", ""));
					} else if (value[x].contains("[") && value[x].contains("]")) {
						buffer.append(" and date(model.").append(field[x]).append(") = ")
								.append(value[x].replace("[", "'").replace("]", "'"));
					} else {
						buffer.append(" and cast(model.").append(field[x]).append(" as string) like ");
						buffer.append("'%").append(value[x]).append("%' ");
					}
				}

			}
		}
		return buffer.toString();
	}
}
