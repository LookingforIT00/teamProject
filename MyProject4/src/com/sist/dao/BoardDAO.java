package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.BoardVO;

public class BoardDAO {

	private static BoardDAO instance;

	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
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

	public void insertBoard(BoardVO vo) {
		try {
			getConnection();

			String sql = "insert into board(idx, title, content, writer, view_count, insert_time, update_time) " + 
			"values(board_seq.nextval, ?, ?, ?, 0, sysdate, null)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getWriter());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public BoardVO selectBoard(int idx) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			
			String sql = "select idx, title, content, writer, view_count, insert_time, update_time "
			+ "from board "
			+ "where idx=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vo.setIdx(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setWriter(rs.getString(4));
				vo.setViewCount(rs.getInt(5));
				LocalDateTime localDate = rs.getObject (6, LocalDateTime.class);
				vo.setInsertTime(localDate);
				Object ob = rs.getObject (7);
				if(ob != null) {
					localDate = rs.getObject (7, LocalDateTime.class);
					vo.setUpdateTime(localDate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return vo;
	}

	public void updateBoard(BoardVO vo) {
		try {
			getConnection();

			String sql = "update board "
			+ "set update_time = sysdate, title = ?, content = ? " 
			+ "where idx = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getIdx());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public void deleteBoard(int idx) {
		try {
			getConnection();

			String sql = "delete "
			+ "FROM board " 
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

	public List<BoardVO> selectBoardList(String search, int min, int max) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			
			String sql = "SELECT idx, title, content, writer, view_count, insert_time, update_time "
					+ "FROM "
					+ "(SELECT idx, title, content, writer, view_count, insert_time, update_time, rownum as page "
					+ "FROM "
					+ "(SELECT idx, title, content, writer, view_count, insert_time, update_time FROM board WHERE title LIKE '%'||?||'%' OR content like '%'||?||'%' OR writer like '%'||?||'%' ORDER BY insert_time DESC"
					+ ")"
					+ ") "
					+ "WHERE page >= ? and page <= ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			ps.setString(2, search);
			ps.setString(3, search);
			ps.setInt(4, min);
			ps.setInt(5, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setIdx(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setWriter(rs.getString(4));
				vo.setViewCount(rs.getInt(5));
				LocalDate localDate = rs.getObject (6, LocalDate.class);
				vo.setInsertTime(localDate.atStartOfDay());
				Object ob = rs.getObject (7);
				if(ob != null) {
					localDate = rs.getObject (7, LocalDate.class);
					vo.setUpdateTime(localDate.atStartOfDay());
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

	public int selectBoardCount(String search) {
		int count = 0;
		try {
			getConnection();
			
			String sql = "SELECT count(*) "
			+ "FROM board "
			+ "WHERE title LIKE '%'||?||'%' OR content like '%'||?||'%' OR writer like '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			ps.setString(2, search);
			ps.setString(3, search);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return count;
	}

	public void viewCount(int idx) {
		try {
			getConnection();
			
			String sql = "update board "
			+ "set view_count = view_count+1 "
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

	public String selectMemberName(String id) {
		String name = "Null";
		try {
			getConnection();
			
			String sql = "SELECT name "
			+ "FROM member "
			+ "where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return name;
	}
}