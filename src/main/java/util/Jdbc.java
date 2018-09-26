package util;


import mo.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {

    public Connection getConn(){
        /**
         * (1)同一个数据库建立连接
         * (2)向数据库发送sql语句
         * (3)处理数据库返回的结果
         */
        String url = "jdbc:mysql://localhost:3306/shoppingbook?useUnicode=true&characterEncoding=utf8";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,"root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public List<Customer> queryCustomer() throws SQLException {

        List<Customer> customerList = new ArrayList<Customer>();
        Connection connection = getConn();

        String sql="select cust_id,cust_o,cust_pwd from customert";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        return customerList;
        }
}
