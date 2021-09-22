package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import java.util.*;
import com.sist.vo.*;

@Controller
public class MemberModel {
  @RequestMapping("member/join.do")
  public String member_join(HttpServletRequest request,HttpServletResponse response)
  {
//    request.setAttribute("uri", "../member/join.jsp"); 
	  request.setAttribute("uri", "../member/join.jsp");
//	  return "../main/main.jsp";
	  return "../views/index.jsp";
  }
  
  @RequestMapping("member/idcheck.do")
  public String member_idcheck(HttpServletRequest request,HttpServletResponse response)
  {
	  //����ڰ� ������ ID�ޱ�
	  String id=request.getParameter("id");// �ѱ� => �ѱۺ�ȯ 
	  // DAO�� ���ؼ� Ȯ�� (�ִ� ���̵�,���� ���̵�)
	  MemberDAO dao=MemberDAO.newInstance();
	  int count=dao.memberidCheck(id);
	  request.setAttribute("count", count);
	  return "../member/idcheck_result.jsp";
  }
  @RequestMapping("member/postfind.do")
  public String member_postfind(HttpServletRequest request,HttpServletResponse response)
  {
	  // ���� �޴´� (�ѱ�)
	  // �ѱۺ�ȯ(���ڵ�) => ���ڵ� (����Ʈ)->���ڵ�(Unicode=>UTF-8)
	  try
	  {
		  request.setCharacterEncoding("UTF-8");//���ڵ� => post����϶��� ���
	  }catch(Exception ex) {}
	  // ���� �޴´� 
	  // <input type=text name=dong id=dong size=15 class="input-sm">
	  // getParameter(name��)
	  String dong=request.getParameter("dong");
	  // DAO�� �����ؼ� ������ �б�
	  MemberDAO dao=MemberDAO.newInstance(); // �Ѱ��� �޷θ� ���(�޸� ��������) = �̱���
	  List<ZipcodeVO> list=dao.postfind(dong);
	  int count=dao.postfindCount(dong);
	  // postfind_result.jsp�� ����ϱ� ���ؼ� �����͸� �����ش� (request,session)
	  /*
	   *  request : �ش� ������������ ����Ҷ�
	   *  session : �Ⱓ,�������� jsp���� ���  ==> ���� ������ ������ ���� (�α���,��ٱ���)
	   */
	  request.setAttribute("list", list);
	  request.setAttribute("count", count);
	  return "../member/postfind_result.jsp";
  }
  // �α��� , �˻� => Ajax
  // ȸ������ ó�� 
  @RequestMapping("member/join_ok.do")
  public String member_join_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  System.out.println("ȸ�� ������ ���ۿϷ�");
	  // ����ڰ� ������ �����͸� �޴´� 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String id=request.getParameter("id");//������ => �Ű����� (MemberVO vo)
	  String pwd=request.getParameter("pwd");
	  String name=request.getParameter("name");
	  String sex=request.getParameter("sex");
	  String birthday=request.getParameter("birthday");
	  String email=request.getParameter("email");// UNIQUE  => ���̵� ã��
	  String post1=request.getParameter("post1");
	  String post2=request.getParameter("post2");
	  String addr1=request.getParameter("addr1");
	  String addr2=request.getParameter("addr2");
	  String tel1=request.getParameter("tel1");// UNIQUE => ���̵� ã�� (�ĺ�Ű)
	  String tel2=request.getParameter("tel2");
	  String hope_region=request.getParameter("hope_region");
	  String hope_job=request.getParameter("hope_job");
	  
	  MemberVO vo=new MemberVO();
	  vo.setId(id);
	  vo.setPwd(pwd);
	  vo.setName(name);
	  vo.setSex(sex);
	  vo.setBirthday(birthday);
	  vo.setPost(post1+"-"+post2);
	  vo.setEmail(email);
	  vo.setAddr1(addr1);
	  vo.setAddr2(addr2);
	  vo.setTel(tel1+"-"+tel2);
	  vo.setHope_region(hope_region);
	  vo.setHope_job(hope_job);
	  // DAO�� ���� 
	  MemberDAO dao=MemberDAO.newInstance();
	  dao.memberJoinInsert(vo);
//	  return "redirect:../main/main.do";
	  return "redirect:../main/view.do"; // main���� ȸ������ �����Ͱ� �ʿ䰡 ���� (request�ʱ�ȭ)
	  // sendRedirect() ==> DispatcherServlet => redirect:
  }
  // ȸ�� ���� , �α��� , ����/���� , �˻�  , ��õ (���)  => ��������� 
  @RequestMapping("member/login.do")
  public String member_login(HttpServletRequest request,HttpServletResponse response)
  {
	  // ����� ������ id,pwd
	  String id=request.getParameter("id");
	  String pwd=request.getParameter("pwd");
	  // DAO�� ���� => ������� �޾Ƽ� => login_result.jsp ���� (���=>main.jsp�� �о��)
	  MemberDAO dao=MemberDAO.newInstance();
	  // �޼ҵ� ȣ�� 
	  String result=dao.isLogin(id, pwd);
	  if(!(result.equals("NOID")|| result.equals("NOPWD")))
	  {
		  HttpSession session=request.getSession(); // ���� ��� 
		  // ����/��Ű => request�� �̿��Ѵ�  (request.getCookie())
		  StringTokenizer st=new StringTokenizer(result,"|");
		  String name=st.nextToken();
		  String admin=st.nextToken();
		  
		  // ���ǿ� ���� 
		  session.setAttribute("id", id);
		  session.setAttribute("admin", admin);
		  session.setAttribute("name", name);
		  result="OK";
	  }
	  request.setAttribute("result", result);
	  return "../member/login_result.jsp";
  }
  // �α� �ƿ�
  @RequestMapping("member/logout.do")
  public String member_logout(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  session.invalidate(); // ������ ��ü ����
	  return "redirect:../main/view.do";
  }
  // idã��
  @RequestMapping("member/idfind.do")
  public String member_idfind(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("uri", "/member/idfind.jsp");
	  return "../views/index.jsp";
  }
/*  
  @RequestMapping("member/join_update.do")
  public String member_join_update(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  MemberDAO dao=MemberDAO.newInstance();
	  MemberVO vo=dao.memberJoinUpdate();
	  
	
	  String temp=vo.getPost();
	  String post1=temp.substring(0,3);
	  String post2=temp.substring(4,7);
	  temp=vo.getTel();
	  String tel1=temp.substring(0,3);
	  String tel2=temp.substring(4);
	  
	  request.setAttribute("vo", vo);
	  request.setAttribute("post1", post1);
	  request.setAttribute("post2", post2);
	  
	  request.setAttribute("tel1", tel1);
	  request.setAttribute("tel2", tel2);
	  request.setAttribute("uri", "../member/join_update.jsp");
	  return "../views/index.jsp";
  }
*/  
  @RequestMapping("member/join_update_ok.do")
  public String member_join_update_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  System.out.println("ȸ�� ���� ����");
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String id=request.getParameter("id");//������ => �Ű����� (MemberVO vo)
	  String pwd=request.getParameter("pwd");
	  String name=request.getParameter("name");
	  String sex=request.getParameter("sex");
	  String birthday=request.getParameter("birthday");
	  String email=request.getParameter("email");// UNIQUE  => ���̵� ã��
	  String post1=request.getParameter("post1");
	  String post2=request.getParameter("post2");
	  String addr1=request.getParameter("addr1");
	  String addr2=request.getParameter("addr2");
	  String tel1=request.getParameter("tel1");// UNIQUE => ���̵� ã�� (�ĺ�Ű)
	  String tel2=request.getParameter("tel2");//
	  String hope_region=request.getParameter("hope_region");//
	  String hope_job=request.getParameter("hope_job");//
	  
	  MemberVO vo=new MemberVO();
	  vo.setId(id);
	  vo.setPwd(pwd);
	  vo.setName(name);
	  vo.setSex(sex);
	  vo.setBirthday(birthday);
	  vo.setPost(post1+"-"+post2);
	  vo.setEmail(email);
	  vo.setAddr1(addr1);
	  vo.setAddr2(addr2);
	  vo.setTel(tel1+"-"+tel2);
	  vo.setHope_region(hope_region);
	  vo.setHope_job(hope_job);
	  // DAO�� ���� 
	  MemberDAO dao=MemberDAO.newInstance();
	  boolean bCheck=dao.memberJoinUpdate(vo);
	  if(bCheck==true)
	  {
		  HttpSession session=request.getSession();
		  session.setAttribute("name", vo.getName());
	  }
	  request.setAttribute("bCheck", bCheck);
	  return "../member/join_update_ok.jsp";
  }
  @RequestMapping("member/join_delete.do")
  public String member_join_delete(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("uri", "/member/join_delete.jsp");
	  return "/views/index.jsp";
  }
  @RequestMapping("member/join_delete_ok.do")
  public String member_join_delete_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  String pwd=request.getParameter("pwd");
	  MemberDAO dao=MemberDAO.newInstance();
	  boolean bCheck=dao.memberJoinDelete(id, pwd);
	  if(bCheck==true)
	  {
		  session.invalidate();
	  }
	  request.setAttribute("bCheck", bCheck);
	  return "../member/join_delete_ok.jsp";
  }
  @RequestMapping("member/idfind_email.do")
  public String member_idfind_email(HttpServletRequest request,HttpServletResponse response)
  {
	  String email=request.getParameter("email");
	  MemberDAO dao=MemberDAO.newInstance();
	  String s=dao.member_idfind_email(email);
	  request.setAttribute("result", s);
	  return "../member/idfind_email.jsp";
  }
  @RequestMapping("member/idfind_tel.do")
  public String member_idfind_tel(HttpServletRequest request,HttpServletResponse response)
  {
	  String tel=request.getParameter("tel");
	  MemberDAO dao=MemberDAO.newInstance();
	  String s=dao.member_idfind_tel(tel);
	  request.setAttribute("result", s);
	  return ".../member/idfind_tel.jsp";
  }
}