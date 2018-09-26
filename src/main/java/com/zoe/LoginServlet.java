package com.zoe;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import mo.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        String userName = request.getParameter("uName");
//        String userPwd = request.getParameter("uPwd");
//        String userSex = request.getParameter("uSex");
//        String userHobby[] = request.getParameterValues("uHobby");
        // PrintWriter out = response.getWriter();
//        int i=0;
//        if(userName.equals("abc")&&userPwd.equals("111")){
//            request.setAttribute("userName",userSex);
//            request.setAttribute("userSex",userSex);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ok.jsp");
//            dispatcher.forward(request,response);
//        }else {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/error.jsp");
//            dispatcher.forward(request,response);
//        }
        String userName = request.getParameter("name");
        String userPwd = request.getParameter("pwd");
        CustomerDao customerDao=new CustomerDaoImpl();
        Customer customer=null;
        try {
            customer = customerDao.getCustomer(userName,userPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(customer!=null){
            HttpSession session=request.getSession();
            session.setAttribute("loginer",customer);
            response.sendRedirect(request.getContextPath()+"/index.do");
        }else{
            request.setAttribute("loginName",userName);
            RequestDispatcher dispatcher =request.getRequestDispatcher("/front/logerror.jsp");

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
