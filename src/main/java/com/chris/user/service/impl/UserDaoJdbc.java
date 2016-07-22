package com.chris.user.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.chris.user.domain.Level;
import com.chris.user.domain.User;
import com.chris.user.service.UserDao;

public class UserDaoJdbc implements UserDao {
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> userMapper = 
		new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("ID"));
				user.setName(rs.getString("NAME"));
				user.setPasswd(rs.getString("PASSWD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setLvl(Level.valueOf(rs.getInt("LVL")));
				user.setLogin(rs.getInt("LOGIN"));
				user.setRecommend(rs.getInt("RECOMMEND"));
				return user;
			}
		};

	public void add(User user) {
		this.jdbcTemplate.update(
				"INSERT INTO CHRS_USER(ID, NAME, PASSWD, EMAIL, LVL, LOGIN, RECOMMEND) " +
				"VALUES(?,?,?,?,?,?,?)", 
					user.getId(), user.getName(), user.getPasswd(), user.getEmail(), 
					user.getLvl().intValue(), user.getLogin(), user.getRecommend());
	}

	public User get(String id) {
		return this.jdbcTemplate.queryForObject("SELECT ID, NAME, PASSWD, EMAIL, LVL, LOGIN, RECOMMEND FROM CHRS_USER WHERE ID = ?",
				new Object[] {id}, this.userMapper);
	} 

	public void deleteAll() {
		this.jdbcTemplate.update("DELETE FROM CHRS_USER");
	}

	public int getCount() {
		return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM CHRS_USER", Integer.class);
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("SELECT ID, NAME, PASSWD, EMAIL, LVL, LOGIN, RECOMMEND FROM CHRS_USER ORDER BY ID", this.userMapper);
	}

	public void update(User user) {
		this.jdbcTemplate.update(
				"UPDATE CHRS_USER SET NAME = ?, PASSWD = ?, EMAIL = ?, LVL = ?, LOGIN = ?, " +
				"RECOMMEND = ? WHERE ID = ? ", user.getName(), user.getPasswd(), user.getEmail(), 
		user.getLvl().intValue(), user.getLogin(), user.getRecommend(),
		user.getId());
		
	}
}
