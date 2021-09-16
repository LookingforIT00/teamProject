package com.sist.dao;
import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.*;

import com.sist.vo.CoporateReviewVO;
public class CoporateReviewDAO {
	private Connection conn;
	private PreparedStatement ps;	
	
	private static CoporateReviewDAO instance;
	public static CoporateReviewDAO getInstance() {
		if (instance == null) {
			instance = new CoporateReviewDAO();
		}
		return instance;
	}
	
	private void getConnection() {
		try {
			Context init = new InitialContext();
			Context c = (Context) init.lookup("java://comp//env");
			DataSource ds = (DataSource) c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
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
	
	// 목록
	public List<CoporateReviewVO> coporateListData(int page)
	{
		List<CoporateReviewVO> list= new ArrayList<CoporateReviewVO>();
		try
		{
			getConnection();
			String sql="SELECT idk,coporate_nm, score, co_evaluation, advantages, disadvantages, job,regdate, employment, employment_status, area, num "
					+"FROM (SELECT idk,coporate_nm, score, co_evaluation, advantages, disadvantages, job,regdate, employment, employment_status, area, rownum as num "
					+"FROM (SELECT idk,coporate_nm, score, co_evaluation, advantages, disadvantages, job,regdate, employment, employment_status, area "
					+"FROM coporate_review ORDER BY idk ASC)) "
					+"WHERE num BETWEEN ? AND	 ?";
			
			ps=conn.prepareStatement(sql);
			int rowsize = 10;
			int start = (rowsize * page) - (rowsize - 1);
			int end = rowsize * page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			   
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CoporateReviewVO vo=new CoporateReviewVO();
				vo.setIdk(rs.getInt(1));
				vo.setCoporate_nm(rs.getString(2));
				vo.setScore(rs.getInt(3));
				vo.setCo_evaluation(rs.getString(4));
				vo.setAdvantages(rs.getString(5));
				vo.setDisadvantages(rs.getString(6));
				vo.setJob(rs.getString(7));
				vo.setRegdate(rs.getDate(8));
				vo.setEmployment(rs.getString(9));
				vo.setEmployemnt_status(rs.getString(10));
				vo.setArea(rs.getString(11));
				list.add(vo);
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return list;
	}
	
	// 총페이지
	public int coporateTotalPage() {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) FROM coporate_review";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return total;
	}

	// 데이터 입력
	public void reviewInsert(CoporateReviewVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO coporate_review(idk,coporate_nm, score, co_evaluation, advantages, disadvantages, job,regdate, employment, employment_status, area) "
					+ "VALUES(cr_idk_seq.nextval,?,?,?,?,?,?,SYSDATE,null,null,null)";
			ps=conn.prepareStatement(sql);
			ps.setString(1,vo.getCoporate_nm());
			ps.setInt(2, vo.getScore());
			ps.setString(3, vo.getCo_evaluation());
			ps.setString(4, vo.getAdvantages());
			ps.setString(5, vo.getDisadvantages());
			ps.setString(6, vo.getJob());		
			ps.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	
}
