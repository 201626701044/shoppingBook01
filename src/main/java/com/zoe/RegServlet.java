package com.zoe;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import mo.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegServlet",urlPatterns = "/reg.do")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String cusName=request.getParameter("name");
        String cusPwd=request.getParameter("pwd");
        if (cusName!=null&&cusName.trim().equals("")||cusPwd!=null&&cusPwd.trim().equals("")){
            request.setAttribute("loginName","用户名或密码为空");
            request.getRequestDispatcher("/front/regerror.jsp").forward(request,response);
            return;
        }
        CustomerDao customerDao=new CustomerDaoImpl();
        Customer customer=new Customer();
        customer.setCustNo(cusName);
        customer.setCustPwd(cusPwd);
        boolean flag=false;
        Customer cus=null;
        try {
            cus=customerDao.getCustomer(cusName);
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            flag=customerDao.addCustomer(customer);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (flag){
            request.setAttribute("loginName",cusName);
            request.getRequestDispatcher("/front/regok.jsp").forward(request,response);
        }
        if (cus!=null){
            request.setAttribute("loginName","用户名已经存在");
            request.getRequestDispatcher("/front/regerror.jsp").forward(request,response);
            return;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
