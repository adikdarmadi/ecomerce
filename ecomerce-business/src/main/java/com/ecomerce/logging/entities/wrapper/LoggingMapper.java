package com.ecomerce.logging.entities.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecomerce.logging.entities.Logging;

/**
 * Mapper class for Logging, contoh saja
 * 
 * @author Roberto
 */
public class LoggingMapper implements RowMapper<Logging> {
	public Logging mapRow(ResultSet rs, int rowNum) throws SQLException {
		Logging student = new Logging();
		student.setId(rs.getInt("id"));
		student.setNama(rs.getString("nama"));
		student.setDeskripsi(rs.getString("deskripsi"));
		return student;
	}
}