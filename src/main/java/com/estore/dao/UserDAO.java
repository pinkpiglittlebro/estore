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

				//Load all profile fields
				u.setFullName(rs.getString("full_name"));
				u.setAddress(rs.getString("address"));
				u.setCity(rs.getString("city"));
				u.setProvince(rs.getString("province"));
				u.setPostal(rs.getString("postal"));
				u.setPhone(rs.getString("phone"));
				u.setCreditCard(rs.getString("credit_card"));

				return u;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUser(User user) {
		String sql = "UPDATE users SET full_name=?, email=?, address=?, city=?, province=?, postal=?, phone=?, credit_card=? WHERE id=?";

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getCity());
			ps.setString(5, user.getProvince());
			ps.setString(6, user.getPostal());
			ps.setString(7, user.getPhone());
			ps.setString(8, user.getCreditCard());
			ps.setInt(9, user.getId());

			ps.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}




}
