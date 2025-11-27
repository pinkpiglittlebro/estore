package com.estore.dao;

import com.estore.model.User;
import com.estore.util.DB;

import java.sql.*;

public class UserDAO {

	public boolean register(User user) {
		String sql = "INSERT INTO users (username, password, email) VALUES (?,?,?)";

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public User login(String username, String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password=?";

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				return u;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
