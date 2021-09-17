package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.MemberVO;
import com.sist.vo.ZipcodeVO;

import javax.naming.*;
public class MemberDAO {
  private Connection conn;
  private PreparedStatement ps;
  private static MemberDAO dao; 
  public void getConnection()
  {
	  try
	  {
		  Context init=new InitialContext(); 
			Context c=(Context)init.lookup("java://comp//env"); 
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			  conn=ds.getConnection();
		  }catch(Exception ex) 
		  {
			  ex.printStackTrace();
		  }
	  }
	  public void disConnection()
	  {
		  try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) conn.close();
		  }catch(Exception ex) {}
	  }
	  // 싱글턴 패턴 
	  public static MemberDAO newInstance()
	  {
		  if(dao==null) 
			  dao=new MemberDAO();
		  return dao; 
			  
	  }
	// 첫 번째 기능 => 아이디 중복 체크
	  public int memberidCheck(String id)
	  {
		  int count=0;
		  try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) FROM member "
					    +"WHERE id=?";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1,id);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  count=rs.getInt(1);
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return count;
	  }
	  public List<ZipcodeVO> postfind(String dong)
	  {
		  List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT zipcode,sido,gugun,dong,NVL(bunji,' ') "
					    +"FROM zipcode "
					    +"WHERE dong LIKE '%'||?||'%'";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1,dong);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  ZipcodeVO vo=new ZipcodeVO();
				  vo.setZipcode(rs.getString(1));
				  vo.setSido(rs.getString(2));
				  vo.setGugun(rs.getString(3));
				  vo.setDong(rs.getString(4));
				  vo.setBunji(rs.getString(5));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  public int postfindCount(String dong)
	  {
		  int count=0;
		  try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) "
					    +"FROM zipcode "
					    +"WHERE dong LIKE '%'||?||'%'";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1,dong);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  count=rs.getInt(1);
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return count;
	  }
	  // 실제 회원가입 
	  public void memberJoinInsert(MemberVO vo)
	  {
		  try
		  {
			  getConnection();
			  String sql="INSERT INTO member VALUES(?,?,?,?,?,"
					   +"?,?,?,?,?,?,?,'n')"; // admin(n=일반,y=관리자)
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, vo.getId());
			  ps.setString(2, vo.getPwd());
			  ps.setString(3, vo.getName());
			  ps.setString(4, vo.getSex());
			  ps.setString(5, vo.getBirthday());
			  
			  ps.setString(6, vo.getEmail());
			  ps.setString(7, vo.getPost());
			  ps.setString(8, vo.getAddr1());
			  ps.setString(9, vo.getAddr2());
			  ps.setString(10, vo.getTel());
			  ps.setString(10, vo.getHope_region());
			  ps.setString(10, vo.getHope_job());
			  
			  ps.executeUpdate();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
	  }
	  // 로그인 
	  public String isLogin(String id,String pwd)
	  {
		  String result="";
		  try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) "
					    +"FROM member "
					    +"WHERE id=?";
			  ps=conn.prepareStatement(sql); 
			  ps.setString(1, id);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  int count=rs.getInt(1);
			  rs.close();
			  
			  if(count==0) 
			  {
				  result="NOID";
			  }
			  else 
			  {
				  sql="SELECT pwd,name,admin FROM member "
					 +"WHERE id=?";
				  ps=conn.prepareStatement(sql);
				  ps.setString(1, id);
				  rs=ps.executeQuery();
				  rs.next();
				  String db_pwd=rs.getString(1);
				  String name=rs.getString(2);
				  String admin=rs.getString(3);
				  rs.close();
				  
				  if(db_pwd.equals(pwd))
				  {
					  result=name+"|"+admin;
				  }
				  else
				  {
					  result="NOPWD";
				  }
			  }
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return result;
	  }
	  
	  // 회원 수정
	  public boolean memberJoinUpdate(MemberVO vo)
	  {
		  boolean bCheck=false;
		  try
		  {
			  getConnection();
			  String sql="SELECT pwd FROM member "
					    +"WHERE id=?";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, vo.getId());
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  String db_pwd=rs.getString(1);
			  rs.close();
			  if(db_pwd.equals(vo.getPwd()))
			  {
				  bCheck=true;
				  sql="UPDATE member SET "
					 +"name=?,sex=?,birthday=?,post=?,addr1=?,addr2=?,"
				     +"email=?,tel=?, hope_region=?, hope_job=?"
					 +"WHERE id=?";
				  ps=conn.prepareStatement(sql);
				  ps.setString(1, vo.getName());
				  ps.setString(2, vo.getSex());
				  ps.setString(3, vo.getBirthday());
				  ps.setString(4, vo.getPost());
				  ps.setString(5, vo.getAddr1());
				  ps.setString(6, vo.getAddr2());
				  ps.setString(7, vo.getEmail());
				  ps.setString(8, vo.getTel());
				  ps.setString(9, vo.getId());
				  ps.setString(10, vo.getHope_region());
				  ps.setString(10, vo.getHope_job());
				  ps.executeUpdate();
			  }
			  else
			  {
				  bCheck=false;
			  }
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return bCheck;
	  }
	  // 회원탈퇴
	  public boolean memberJoinDelete(String id,String pwd)
	  {
		  boolean bCheck=false;
		  try
		  {
			  getConnection();
			  String sql="SELECT pwd FROM member "
					    +"WHERE id=?";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, id);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  String db_pwd=rs.getString(1);
			  rs.close();
			  if(db_pwd.equals(pwd))
			  {
				  bCheck=true;
				  sql="DELETE FROM member "
					 +"WHERE id=?";
				  ps=conn.prepareStatement(sql);
				  ps.setString(1,id);
				  ps.executeUpdate();
			  }
			  else
			  {
				  bCheck=false;
			  }
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return bCheck;
	  }
	  // 아이디찾기 , 비밀번호찾기 
	  public String member_idfind_email(String email)
	  {
		   String result="";
		   try
		   {
			   getConnection();
			   String sql="SELECT COUNT(*) FROM member "
					     +"WHERE email=?";
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, email);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   int count=rs.getInt(1);
			   rs.close();
			   
			   if(count==0)
			   {
				   result="no";
			   }
			   else
			   {
				   sql="SELECT RPAD(SUBSTR(id,1,1),LENGTH(id),'*') FROM member "
					  +"WHERE email=?";
				   ps=conn.prepareStatement(sql);
				   ps.setString(1, email);
				   rs=ps.executeQuery();
				   rs.next();
				   result=rs.getString(1);
				   rs.close();
			   }
			   
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
		   return result;
	  }
	  public String member_idfind_tel(String tel)
	  {
		   String result="";
		   try
		   {
			   getConnection();
			   String sql="SELECT COUNT(*) FROM member "
					     +"WHERE tel=?";
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, tel);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   int count=rs.getInt(1);
			   rs.close();
			   
			   if(count==0)
			   {
				   result="no";
			   }
			   else
			   {
				   sql="SELECT RPAD(SUBSTR(id,1,1),LENGTH(id),'*') FROM member "
					  +"WHERE tel=?";
				   ps=conn.prepareStatement(sql);
				   ps.setString(1, tel);
				   rs=ps.executeQuery();
				   rs.next();
				   result=rs.getString(1);
				   rs.close();
			   }
			   
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
		   return result;
	  }
	  
	}