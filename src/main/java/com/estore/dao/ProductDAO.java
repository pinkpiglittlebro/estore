package com.estore.dao;

import com.estore.model.Product;
import com.estore.util.DB;

import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class ProductDAO {

    // existing findAll()
	public List<Product> findAll() {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT id, name, category, price, image_url, description, inventory FROM products";
		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setCategory(rs.getString("category"));
				p.setPrice(rs.getBigDecimal("price"));
				p.setImageUrl(rs.getString("image_url"));
				p.setDescription(rs.getString("description"));
				p.setInventory(rs.getInt("inventory")); // ← REQUIRED

				list.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


    public List<Product> findByCategory(String category) {
        List<Product> list = new ArrayList<>();
		String sql = "SELECT id, name, category, price, image_url, description, inventory FROM products WHERE category = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product findById(long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setImageUrl(rs.getString("image_url"));
                p.setDescription(rs.getString("description"));
                p.setInventory(rs.getInt("inventory"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


	private Product mapProduct(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setCategory(rs.getString("category"));
		p.setPrice(rs.getBigDecimal("price"));
		p.setImageUrl(rs.getString("image_url"));
		p.setDescription(rs.getString("description"));
		p.setInventory(rs.getInt("inventory"));
		return p;
	}


	public boolean reduceInventory(int productId, int quantity) {
		String sql = "UPDATE products SET inventory = inventory - ? WHERE id = ? AND inventory >= ?";

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, quantity);
			ps.setInt(2, productId);
			ps.setInt(3, quantity);

			int updated = ps.executeUpdate();
			return updated > 0; // true only if enough inventory

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getInventory(int productId) {
		String sql = "SELECT inventory FROM products WHERE id = ?";
		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("inventory");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}


}
