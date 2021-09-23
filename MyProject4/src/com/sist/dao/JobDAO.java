package com.sist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.JobVO;

public class JobDAO {

	private static JobDAO instance;

	public static JobDAO getInstance() {
		if (instance == null) {
			instance = new JobDAO();
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

	public void insertJob(JobVO vo) {
		try {
			getConnection();
			String sql = "insert into jobInfo(idx, job_name, edu, career, addr, sal, emp_type, work_type, work_time, werfare, content, startline, deadline, reception, hit, co_name, job_type, personnel, work_place) " + 
			"values(jobInfo_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getJobName());
			ps.setString(2, vo.getEdu());
			ps.setString(3, vo.getCareer());
			ps.setString(4, vo.getAddr());
			ps.setString(5, vo.getSal());
			ps.setString(6, vo.getEmpType());
			ps.setString(7, vo.getWorkType());
			ps.setString(8, vo.getWorkTime());
			ps.setString(9, vo.getWelfare());
			ps.setString(10, vo.getContent());
			ps.setString(11, vo.getStartline());
			ps.setString(12, vo.getDeadline());
			ps.setString(13, vo.getReception());
			ps.setInt(14, 0);
			ps.setString(15, vo.getCoName());
			ps.setString(16, vo.getJobType());
			ps.setString(17, vo.getPersonnel());
			ps.setString(18, vo.getWorkPlace());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public JobVO selectJob(Integer idx) {
		JobVO vo = new JobVO();
		try {
			getConnection();
			String sql = "select idx, job_name, edu, career, addr, sal, emp_type, work_type, work_time, werfare, content, startline, deadline, reception, hit, co_name, job_type, personnel, work_place "
					+ "from jobInfo "
					+ "where idx=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vo.setIdx(rs.getInt(1));
				vo.setJobName(rs.getString(2));
				vo.setEdu(rs.getString(3));
				vo.setCareer(rs.getString(4));
				vo.setAddr(rs.getString(5));
				vo.setSal(rs.getString(6));
				vo.setEmpType(rs.getString(7));
				vo.setWorkType(rs.getString(8));
				vo.setWorkTime(rs.getString(9));
				vo.setWelfare(rs.getString(10));
				vo.setContent(rs.getString(11));
				vo.setStartline(rs.getString(12));
				vo.setDeadline(rs.getString(13));
				vo.setReception(rs.getString(14));
				vo.setHit(rs.getInt(15));
				vo.setCoName(rs.getString(16));
				vo.setJobType(rs.getString(17));
				vo.setPersonnel(rs.getString(18));
				vo.setWorkPlace(rs.getString(19));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}

		return vo;
	}

	public List<JobVO> selectJobList(String search,String duties,String area, int min, int max) {
		List<JobVO> list = new ArrayList<JobVO>();
		try {
			getConnection();
			
			String sql = "SELECT idx, job_name, edu, career, sal, hit "
					+ "FROM "
					+ "(SELECT idx, job_name, edu, career, sal, hit, rownum as page "
					+ "FROM "
					+ "(SELECT idx, job_name, edu, career, sal, hit FROM jobInfo WHERE (job_name LIKE '%'||?||'%' OR co_name like '%'||?||'%') and job_type like '%'||?||'%' and addr like '%'||?||'%' ORDER BY idx ASC"
					+ ")"
					+ ") "
					+ "WHERE page >= ? and page <= ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			ps.setString(2, search);
			ps.setString(3, duties);
			ps.setString(4, area);
			ps.setInt(5, min);
			ps.setInt(6, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				JobVO vo = new JobVO();
				vo.setIdx(rs.getInt(1));
				vo.setJobName(rs.getString(2));
				vo.setEdu(rs.getString(3));
				vo.setCareer(rs.getString(4));
				vo.setSal(rs.getString(5));
				vo.setHit(rs.getInt(6));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}

	public int selectJobCount(String search,String duties,String area) {
		int count = 0;
		try {
			getConnection();
			
			String sql = "SELECT count(*) "
			+ "FROM jobInfo "
			+ "WHERE (job_name LIKE '%'||?||'%' OR co_name like '%'||?||'%') and job_type like '%'||?||'%' and addr like '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			ps.setString(2, search);
			ps.setString(3, duties);
			ps.setString(4, area);
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

	public void hitCount(int idx) {
		try {
			getConnection();
			
			String sql = "update jobInfo "
			+ "set hit = hit+1 "
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
}