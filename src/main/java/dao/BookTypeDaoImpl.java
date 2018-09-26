package dao;


import mo.BookType;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDaoImpl implements BookTypeDao {
    public List<BookType> getListType(int size) throws SQLException {
        List<BookType> bookTypeList = new ArrayList<BookType>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = JdbcUtils.getConn();
            String sql = "SELECT bookType_ID,bookType_Name\n" +
                    "FROM booktypet limit 0,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookType bookType = new BookType();
                bookType.setBookTypeId(resultSet.getString(1));
                bookType.setBookTypeName(resultSet.getString(2));

                bookTypeList.add(bookType);
            }

            return bookTypeList;
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }
}

