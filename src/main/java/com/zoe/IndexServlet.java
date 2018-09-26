package com.zoe;

import dao.BookDaoImpl;
import dao.BookTypeDaoImpl;
import mo.Book;
import mo.BookType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "IndexServlet",urlPatterns = "/index.do")
public class IndexServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doIndex(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 doIndex(request,response);
    }


    protected void doIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookDaoImpl bookDao=new BookDaoImpl();
        BookTypeDaoImpl bookTypeDao=new BookTypeDaoImpl();
        List<Book> bookList=null;
        List<Book> booksThisWeek=null;
        Book bookThisWeek=null;
        Book bookThisWeek1=null;
        List<BookType> bookTypeList=null;
        try {
            bookList=bookDao.getRecommadation(7);
            booksThisWeek=bookDao.getRecommThisWeek(2);
            bookThisWeek=booksThisWeek.get(0);
            bookThisWeek1=booksThisWeek.get(1);
            bookTypeList=bookTypeDao.getListType(5);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("bList",bookList);
        request.setAttribute("bThis",bookThisWeek);
        request.setAttribute("bThis1",bookThisWeek1);
        request.setAttribute("bType",bookTypeList);
        request.getRequestDispatcher("/front/front.jsp").forward(request,response);

    }



}
