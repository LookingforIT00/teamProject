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
  private static MemberDAO dao; // �̱��� ���� (static:�޸� ������ �Ѱ��� ����� ����) => ���� 
  // Connection �ּ� �б� => Connection��ü�� �̸����� => �����ð��� �ٿ��� �ӵ��� ������ ����� 
  // ���� ������ �߱⿡ Connection��ü ������ ���ϴ� => ��� ������Ʈ ���ߴ� 95%�� DBCP�� ����Ѵ� 
  // Connection ��ü ��� 
  public void getConnection()
  {
	  try
	  {
		  Context init=new InitialContext(); // ����� ��ġ�� ���� 
			// ����� ��ġ �̸� = JNDI (java naming directory interface)
			Context c=(Context)init.lookup("java://comp//env"); // ã�ƿ���
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			  conn=ds.getConnection();
		  }catch(Exception ex) 
		  {
			  ex.printStackTrace();
		  }
	  }
	  // ����� ��ȯ -> �ٸ������ ������ �����ϰ� ����� 
	  public void disConnection()
	  {
		  try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) conn.close();
		  }catch(Exception ex) {}
	  }
	  // �̱��� ���� 
	  public static MemberDAO newInstance()
	  {
		  if(dao==null) // �̻����ÿ��� 
			  dao=new MemberDAO();
		  return dao; // �̹� ������� dao��ü�� ����Ѵ� 
			  
	  }
	// ù ��° ��� => ���̵� �ߺ� üũ
	  public int memberidCheck(String id)
	  {
		  int count=0;
		  try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) FROM project_member "
					    +"WHERE id=?";
			  // count=0 (��밡���� ID), count=1 (������� ID)
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
	  // ��� => �����ȣ �˻� 
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
	  // ���� ȸ������ 
	  public void memberJoinInsert(MemberVO vo)
	  {
		  try
		  {
			  getConnection();
			  String sql="INSERT INTO project_member VALUES(?,?,?,?,?,"
					   +"?,?,?,?,?,'n')"; // admin(n=�Ϲ�,y=������)
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
	  // �α��� 
	  public String isLogin(String id,String pwd)
	  {
		  String result="";
		  try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) "
					    +"FROM project_member "
					    +"WHERE id=?";
			  ps=conn.prepareStatement(sql); //id�� �����ϴ��� üũ
			  ps.setString(1, id);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  int count=rs.getInt(1);
			  rs.close();
			  
			  if(count==0) //ID�� ���� ����
			  {
				  result="NOID";
			  }
			  else // ID�� �ִ� ����
			  {
				  sql="SELECT pwd,name,admin FROM project_member "
					 +"WHERE id=?";
				  ps=conn.prepareStatement(sql);
				  ps.setString(1, id);
				  rs=ps.executeQuery();
				  rs.next();
				  String db_pwd=rs.getString(1);
				  String name=rs.getString(2);
				  String admin=rs.getString(3);
				  rs.close();
				  // ��й�ȣ Ȯ�� 
				  if(db_pwd.equals(pwd))//�α���
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
	  // ȸ������ 
	  // ȸ��Ż��
	  // ���̵�ã�� , ��й�ȣã�� 
	}