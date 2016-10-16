package com.ecomerce.logging.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ecomerce.logging.entities.Logging;
import com.ecomerce.logging.entities.wrapper.LoggingMapper;

/**
 * simple implemmentation dao LoggingJdbcDao
 * 
 * @author Roberto
 */
public class LoggingJdbcDaoImpl implements LoggingJdbcDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(String nama, String deskripsi) {
		String SQL = "insert into Logging (nama, deskripsi) values (?, ?)";

		jdbcTemplateObject.update(SQL, nama, deskripsi);
		System.out.println("Created Record Nama = " + nama + " Deskripsi = "
				+ deskripsi);
		return;
	}

	public Logging getLogging(Integer id) {
		String SQL = "select * from Logging where id = ?";
		Logging logging = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { id }, new LoggingMapper());
		return logging;
	}

	public List<Logging> listLoggings() {
		String SQL = "select * from Logging";
		List<Logging> loggings = jdbcTemplateObject.query(SQL,
				new LoggingMapper());
		return loggings;
	}

	public void delete(Integer id) {
		String SQL = "delete from Logging where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	public void update(Integer id, String nama, String deskripsi) {
		String SQL = "update Logging set nama = ?, deskripsi = ? where id = ?";
		jdbcTemplateObject.update(SQL, nama, deskripsi, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}

}