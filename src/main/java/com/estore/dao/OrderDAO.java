package com.estore.dao;

import com.estore.model.Order;
import com.estore.model.OrderItem;
import com.estore.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

	public int createOrder(Order order) throws Exception {
		String sql = "INSERT INTO orders " +
			"(user_id, total, created_at, full_name, address, city, postal, phone) " +
			"VALUES (?,?,?,?,?,?,?,?)";

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			if (order.getUserId() == null) ps.setNull(1, Types.INTEGER);
			else ps.setInt(1, order.getUserId());

			ps.setDouble(2, order.getTotal());
			ps.setString(3, order.getCreatedAt());
			ps.setString(4, order.getFullName());
			ps.setString(5, order.getAddress());
			ps.setString(6, order.getCity());
			ps.setString(7, order.getPostal());
			ps.setString(8, order.getPhone());

			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1); // new order id
				}
			}
		}

		throw new Exception("Failed to create order");
	}

	public void insertOrderItems(int orderId, List<OrderItem> items) throws Exception {
		String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?,?,?,?)";

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			for (OrderItem item : items) {
				ps.setInt(1, orderId);
				ps.setLong(2, item.getProductId());
				ps.setInt(3, item.getQuantity());
				ps.setDouble(4, item.getPrice());
				ps.addBatch();
			}

			ps.executeBatch();
		}
	}

	public long saveOrder(Order order) throws Exception {
		int orderId = createOrder(order);
		insertOrderItems(orderId, order.getItems());
		return orderId;
	}

	public List<Order> getOrdersByUser(int userId) throws Exception {
		String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC";
		List<Order> list = new ArrayList<>();

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setUserId(userId);
				o.setTotal(rs.getDouble("total"));
				o.setCreatedAt(rs.getString("created_at"));
				o.setFullName(rs.getString("full_name"));

				list.add(o);
			}
		}
		return list;
	}

	public List<OrderItem> getItemsByOrderId(int orderId) throws Exception {
		String sql = "SELECT * FROM order_items WHERE order_id = ?";
		List<OrderItem> list = new ArrayList<>();

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderItem oi = new OrderItem(
					rs.getLong("product_id"),
					rs.getInt("quantity"),
					rs.getDouble("price")
				);
				list.add(oi);
			}
		}
		return list;
	}


	public Order getOrderById(int orderId) throws Exception {
		String sql = "SELECT * FROM orders WHERE id = ?";

		try (Connection conn = DB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setUserId(rs.getInt("user_id"));
				o.setTotal(rs.getDouble("total"));
				o.setCreatedAt(rs.getString("created_at"));
				o.setFullName(rs.getString("full_name"));
				o.setAddress(rs.getString("address"));
				o.setCity(rs.getString("city"));
				o.setPostal(rs.getString("postal"));
				o.setPhone(rs.getString("phone"));
				return o;
			}
		}

		throw new Exception("Order not found");
	}


}
