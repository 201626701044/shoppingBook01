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

@WebServlet(name = "ResetServlet",urlPatterns = "/reset.do")
public class ResetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String cusName=request.getParameter("name");
        String cusPwd=request.getParameter("oldPWD");
       String newcusPwd=request.getParameter("newPWD");
        Customer customer = (Customer)request.getSession().getAttribute("loginer");
        String cusName=customer.getCustNo();
        CustomerDao customerDao=new CustomerDaoImpl();
        //验证原密码是否正确
        Customer cus=null;
        try {
            cus=customerDao.getCustomer(cusName,cusPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(cus==null){
            request.getRequestDispatcher("/personal/reseterror.jsp").forward(request,response);
            return;
        }
        boolean flag=false;
        try {
            flag=customerDao.resetPwd(cusName,newcusPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag==true){
            request.getRequestDispatcher("/personal/resetok.jsp").forward(request,response);

        }else {
            request.getRequestDispatcher("/personal/reseterror.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
