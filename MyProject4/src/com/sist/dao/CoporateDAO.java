package com.sist.dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.CoporateVO;

public class CoporateDAO {

	private static CoporateDAO instance;

	public static CoporateDAO getInstance() {
		if (instance == null) {
			instance = new CoporateDAO();
		}

		return instance;
	}

	private Connection conn;
	private PreparedStatement ps;

	private void getConnection() {
		try {
			Context init = new InitialContext();
			Context c = (Context) init.lookup("java://comp//env");
			DataSource ds = (DataSource) c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertCoporate(CoporateVO vo) {
		try {
			getConnection();

			System.out.println(vo.getCoName());
			System.out.println(vo.getCoType());
			
			String sql = "insert into coporateData(co_name, co_type, co_scale, co_year, co_sales, co_link, co_workers) " + 
			"values(?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getCoName());
			ps.setString(2, vo.getCoType());
			ps.setString(3, vo.getCoScale());
			ps.setString(4, vo.getCoYear());
			ps.setString(5, vo.getCoSales());
			ps.setString(6, vo.getCoLink());
			ps.setInt(7, vo.getCoWorkers());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public CoporateVO selectCoporate(String coName) {
		CoporateVO vo = new CoporateVO();
		try {
			getConnection();
			String sql = "select co_name, co_type, co_scale, co_year, co_sales, co_link, co_workers "
					+ "from coporateData "
					+ "where co_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, coName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vo.setCoName(rs.getString(1));
				vo.setCoType(rs.getString(2));
				vo.setCoScale(rs.getString(3));
				vo.setCoYear(rs.getString(4));
				vo.setCoSales(rs.getString(5));
				vo.setCoLink(rs.getString(6));
				vo.setCoWorkers(rs.getInt(7));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}

		return vo;
	}
}