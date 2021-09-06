package com.sist.dao;

import java.sql.*;

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
			String sql = "insert into jobInfo(idx, job_name, edu, career, addr, sal, emp_type, work_type, work_time, welfare, content, startline, deadline, reception, hit, co_name) " + 
			"values(jobInfo_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
}