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
	  request.setAttribute("index_jsp", "../member/join.jsp");
	  return "../index.jsp";
  }
  
  // ���� => �������� �޾ƿ´� (Ajax) => Shadowbox�� �״���ϸ� ������� �޾Ƽ� ��� (������ ��ü ó��)
  /*
   *    Ŭ���̾�Ʈ�� ��û ==> ���� ==> ���ο� �������� ����� �ش� (ȭ���� �ʱ�ȭ)
   *    Ŭ���̾�Ʈ�� ��û ==> ���� ==> ���� ���������� ������� �о� �´� 
   *                            ========= Web2.0 (�ǹ� => ��κ��� AJax)
   *                                      Vue.js(Jquery���� �ӵ��� ������,Ajax)
   *                                      �ڹ� (Vue,react), (�ں� , ��Ʈ��:jquery)
   *    => MVC ���۰���  (Model,Controller,View) => SpringMVC(AI:�����ͺм�,���)
   *    => SQL���� ������  => MyBatis
   *    => Ajax(Javascript) => React,Vue
   */
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
	  String tel2=request.getParameter("tel2");//
	  String hope_region=request.getParameter("tel2");//
	  String hope_job=request.getParameter("tel2");//
	  
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
	  vo.setHope_job(hope_job);
	  vo.setHope_region(hope_region);
	  vo.setTel(tel1+"-"+tel2);
	  // DAO�� ���� 
	  MemberDAO dao=MemberDAO.newInstance();
	  dao.memberJoinInsert(vo);
//	  return "redirect:../main/main.do";// main���� ȸ������ �����Ͱ� �ʿ䰡 ���� (request�ʱ�ȭ)
	  return "redirect:../index.do";// main���� ȸ������ �����Ͱ� �ʿ䰡 ���� (request�ʱ�ȭ)
	  
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
//	  return "redirect:../main/main.do";
	  return "redirect:../index.do";
  }
}