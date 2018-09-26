package dao;

import mo.Book;
import util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {


    public List<Book> getRecommadation(int start, int size) throws SQLException {
        List<Book> bookList = new ArrayList<Book>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = JdbcUtils.getConn();
            String sql = "SELECT book_id,book_name,book_Author,book_image,price,\n" +
                    "book_ISBN,book_Introduction,bookType_Name,publishing_Name\n" +
                    "from bookinfoview limit ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, size);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt(1));
                book.setBookName(resultSet.getString(2));
                book.setBookAuthor(resultSet.getString(3));
                book.setBookImage(resultSet.getString(4));
                book.setPrice(resultSet.getFloat(5));
                book.setBookISBN(resultSet.getString(6));
                book.setBookIntroduction(resultSet.getString(7));
                book.setPublishingName(resultSet.getString(8));

                bookList.add(book);
            }

            return bookList;
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }

    @Override
    public int getBookNumber() throws SQLException {
        int bookNumber=0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConn();
            statement=connection.createStatement();
            String sql ="SELECT count(*) booknumber from bookinfoview";
            resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                bookNumber=resultSet.getInt(1);
            }
            return bookNumber;
        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConn(connection);
        }

    }

    public List<Book> getRecommadation(int size) throws SQLException {
        List<Book> bookList = new ArrayList<Book>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = JdbcUtils.getConn();
            String sql = "SELECT book_Name,book_image\n" +
                    "FROM bookt limit 0,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookName(resultSet.getString(1));
                book.setBookImage(resultSet.getString(2));

                bookList.add(book);
            }

            return bookList;
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }

    @Override
    public List<Book> getRecommThisWeek(int size) throws SQLException {
        List<Book> bookList = new ArrayList<Book>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection= JdbcUtils.getConn();
            String sql = "SELECT book_Name,book_image,book_Introduction FROM bookt\n " +
                    "order by book_SaleNum desc \n" +
                    "limit 0,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookName(resultSet.getString(1));
                book.setBookImage(resultSet.getString(2));
                book .setBookIntroduction(resultSet.getString(3));
                bookList.add(book);
            }

        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
        return bookList;
    }

}
