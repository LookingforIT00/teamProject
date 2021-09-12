package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.CommentVO;

public class CommentDAO {

	private static CommentDAO instance;

	public static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
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
		} catch (Exception ex) {
		}
	}

	public void insertComment(CommentVO vo) {
		try {
			getConnection();

			String sql = "insert into board_comment(idx, target_idx, target_type, content, writer, delete_check, insert_time, update_time, delete_time) " + 
			"values(board_comment_seq.nextval, ?, ?, ?, ?, 'N', SYSTIMESTAMP, null, null)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getTargetIdx());
			ps.setString(2, vo.getTargetType());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getWriter());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public void updateComment(int idx, String content) {
		try {
			getConnection();

			String sql = "update board_comment "
			+ "set update_time = SYSTIMESTAMP, content = ? " 
			+ "where idx = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, idx);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public void deleteComment(int idx) {
		try {
			getConnection();

			String sql = "update board_comment "
			+ "set delete_check = 'Y', delete_time = SYSTIMESTAMP " 
			+ "where idx = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public List<CommentVO> selectCommentList(int idx, String type) {
		List<CommentVO> list = new ArrayList<CommentVO>();
		try {
			getConnection();
			
			String sql = "SELECT idx, target_idx, target_type, content, writer, delete_check, insert_time, update_time, delete_time "
					+ "FROM board_comment "
					+ "WHERE target_idx = ? AND target_type = ? "
					+ "ORDER BY insert_time DESC";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.setString(2, type);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CommentVO vo = new CommentVO();
				vo.setIdx(rs.getInt(1));
				vo.setTargetIdx(rs.getInt(2));
				vo.setTargetType(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setWriter(rs.getString(5));
				vo.setDelete_check(rs.getString(6));
				LocalDateTime localDate = rs.getObject (7, LocalDateTime.class);
				vo.setInsertTime(localDate);
				Object ob = rs.getObject (8);
				if(ob != null) {
					localDate = rs.getObject (8, LocalDateTime.class);
					vo.setUpdateTime(localDate);
				}
				ob = rs.getObject (9);
				if(ob != null) {
					localDate = rs.getObject (9, LocalDateTime.class);
					vo.setDelete_time(localDate);
				}
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
}