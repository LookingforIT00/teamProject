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
	public List<CoporateReviewVO> coporateListData(int page) {
		List<CoporateReviewVO> list = new ArrayList<CoporateReviewVO>();
		try {
			getConnection();
			String sql = "SELECT idk,coporate_nm, co_evaluation, advantages, disadvantages, job,regdate, employment, employment_status, area, num "
					+ "FROM (SELECT idk,coporate_nm, co_evaluation, advantages, disadvantages, job,regdate, employment, employment_status, area, rownum as num "
					+ "FROM (SELECT idk,coporate_nm,  co_evaluation, advantages, disadvantages, job,regdate, employment, employment_status, area "
					+ "FROM coporate_review ORDER BY idk DESC)) " + "WHERE num BETWEEN ? AND ?";

			ps = conn.prepareStatement(sql);
			int rowsize = 10;
			int start = (rowsize * page) - (rowsize - 1);
			int end = rowsize * page;
			ps.setInt(1, start);
			ps.setInt(2, end);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CoporateReviewVO vo = new CoporateReviewVO();
				vo.setIdk(rs.getInt(1));
				vo.setCoporate_nm(rs.getString(2));
				vo.setCo_evaluation(rs.getString(3));
				vo.setAdvantages(rs.getString(4));
				vo.setDisadvantages(rs.getString(5));
				vo.setJob(rs.getString(6));
				vo.setRegdate(rs.getDate(7));
				vo.setEmpolyment(rs.getString(8));
				vo.setEmployment_status(rs.getString(9));
				vo.setArea(rs.getString(10));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	/*
	 * IDK NOT NULL NUMBER COPORATE_NM NOT NULL VARCHAR2(100) CO_EVALUATION NOT NULL
	 * VARCHAR2(100) ADVANTAGES NOT NULL VARCHAR2(100) DISADVANTAGES NOT NULL
	 * VARCHAR2(100) JOB VARCHAR2(100) REGDATE NOT NULL DATE EMPLOYMENT
	 * VARCHAR2(100) EMPLOYMENT_STATUS VARCHAR2(100) AREA VARCHAR2(100)
	 * WELFARE_SALARY_SCORE NOT NULL NUMBER WORK_LIFE_SCORE NOT NULL NUMBER
	 * INHOUSE_CULTURE_SCORE NOT NULL NUMBER OPPORTUNITIES_SCORE NOT NULL NUMBER
	 * CEO_SCORE NOT NULL NUMBER CAREER NOT NULL VARCHAR2(50)
	 */
	// 데이터 입력
	public void reviewInsert(CoporateReviewVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO coporate_review(idk,coporate_nm, co_evaluation, advantages, disadvantages, job,regdate, employment, employment_status, area"
					+ ", welfare_salary_score, work_life_score, inhouse_culture_score, opportunities_score, ceo_score,career) "
					+ "VALUES(cr_idk_seq.nextval,?,?,?,?,null,SYSDATE,null,null,null,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getCoporate_nm());
			ps.setString(2, vo.getCo_evaluation());
			ps.setString(3, vo.getAdvantages());
			ps.setString(4, vo.getDisadvantages());
			ps.setInt(5, vo.getWelfare_salary_score());
			ps.setInt(6, vo.getWork_life_score());
			ps.setInt(7, vo.getInhouse_culture_score());
			ps.setInt(8, vo.getOpportunities_score());
			ps.setInt(9, vo.getCeo_score());
			ps.setString(10, vo.getCareer());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	// 총 별점
	public List<Integer> reviewTotalScore() {
		List<Integer> scoreList = new ArrayList<Integer>();
		try {
			getConnection();
			String sql = "SELECT ROUND(AVG(WELFARE_SALARY_SCORE+WORK_LIFE_SCORE+INHOUSE_CULTURE_SCORE+OPPORTUNITIES_SCORE+CEO_SCORE)/5) score "
					+ "FROM coporate_review WHERE idk=? GROUP BY idk";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				scoreList.add(rs.getInt(1));
			}

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}

		return scoreList;
	}

	public CoporateReviewVO reviewDetailData(int idk) {
		CoporateReviewVO vo = new CoporateReviewVO();
		try {
			getConnection();

			// 상세볼 게시물 읽기
			String sql = "SELECT idk,regdate,coporate_nm,co_evaluation,advantages,disadvantages,welfare_salary_score,work_life_score,inhouse_culture_score,opportunities_score,ceo_score "
					+ "FROM coporate_review " + "WHERE idk=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idk);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setIdk(rs.getInt(1));
			vo.setRegdate(rs.getDate(2));
			vo.setCoporate_nm(rs.getString(3));
			vo.setCo_evaluation(rs.getString(4));
			vo.setAdvantages(rs.getString(5));
			vo.setDisadvantages(rs.getString(6));
			vo.setWelfare_salary_score(rs.getInt(7));
			vo.setWork_life_score(rs.getInt(8));
			vo.setInhouse_culture_score(rs.getInt(9));
			vo.setOpportunities_score(rs.getInt(10));
			vo.setCeo_score(rs.getInt(11));
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
	// 수정
	/*
	 * public CoporateReviewVO freeboardUpdateData(int idk) { CoporateReviewVO
	 * vo=new CoporateReviewVO(); try { getConnection(); // 조회수 증가 String
	 * sql="SELECT idk,name,subject,content " +"FROM project_freeboard "
	 * +"WHERE no=?"; ps=conn.prepareStatement(sql); ps.setInt(1, no); ResultSet
	 * rs=ps.executeQuery(); rs.next(); vo.setNo(rs.getInt(1));
	 * vo.setSubject(rs.getString(3)); vo.setName(rs.getString(2));
	 * vo.setContent(rs.getString(4)); rs.close(); }catch(Exception ex) {
	 * ex.printStackTrace(); } finally { disConnection(); } return vo; } // 실제 수정
	 * public boolean freeboardUpdate(FreeBoardVO vo) { boolean bCheck=false;// 비밀번호
	 * 체크 (true/수정,false/다시 입력) try { getConnection(); // 비밀번호 확인 String
	 * sql="SELECT pwd FROM project_freeboard " +"WHERE no=?";
	 * ps=conn.prepareStatement(sql); ps.setInt(1, vo.getNo()); ResultSet
	 * rs=ps.executeQuery(); rs.next(); String db_pwd=rs.getString(1); rs.close();
	 * 
	 * if(db_pwd.equals(vo.getPwd())) { bCheck=true; // 실제 수정
	 * sql="UPDATE project_freeboard SET " +"name=?,subject=?,content=? "
	 * +"WHERE no=?"; ps=conn.prepareStatement(sql); ps.setString(1, vo.getName());
	 * ps.setString(2, vo.getSubject()); ps.setString(3, vo.getContent());
	 * ps.setInt(4, vo.getNo()); ps.executeUpdate(); } else { bCheck=false; }
	 * }catch(Exception ex) { ex.printStackTrace(); } finally { disConnection(); }
	 * return bCheck; } // 삭제 => 트랜잭션 프로그램 (일괄처리) => SQL문장 전체가 실행 , error가 났을 경우에 전체
	 * 취소
	 * 
	 * public boolean freeboardDelete(int no,String pwd) { boolean bCheck=false; try
	 * { getConnection(); conn.setAutoCommit(false); // 비밀번호 체크 String
	 * sql="SELECT pwd FROM project_freeboard " +"WHERE no=?";
	 * ps=conn.prepareStatement(sql); ps.setInt(1, no); ResultSet
	 * rs=ps.executeQuery(); rs.next(); String db_pwd=rs.getString(1); rs.close();
	 * if(pwd.equals(db_pwd)) { bCheck=true;//freeboard/list.jsp // 삭제 한다
	 * sql="DELETE FROM project_reply " +"WHERE bno=?";
	 * ps=conn.prepareStatement(sql); ps.setInt(1, no); ps.executeUpdate();// 참조하고
	 * 있는 데이터를 먼저 삭제한다
	 * 
	 * sql="DELETE FROM project_freeboard " +"WHERE no=?";
	 * ps=conn.prepareStatement(sql); ps.setInt(1, no); ps.executeUpdate();
	 * conn.commit(); } else { bCheck=false;// history.back() } }catch(Exception ex)
	 * { ex.printStackTrace(); try { conn.rollback(); }catch(Exception e) {
	 * e.printStackTrace();
	 * 
	 * } } finally { try { conn.setAutoCommit(true); }catch(Exception ex) {}
	 * disConnection(); } return bCheck; }
	 */
}
