package dao;

import mo.Customer;
import util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{


    @Override
    public Customer getCustomer(String cusName, String cusPwd) throws SQLException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection= JdbcUtils.getConn();
            String sql="select cust_id,cust_no,cust_pwd from customert where cust_no=? and cust_pwd=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,cusName);
            preparedStatement.setString(2,cusPwd);
            resultSet=preparedStatement.executeQuery();
            Customer customer =null;
            if (resultSet.next()){
                customer=new Customer();
                customer.setCustId(resultSet.getInt(1));
                customer.setCustNo(resultSet.getString(2));
                customer.setCustPwd(resultSet.getString(3));
            }
            return customer;
        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);

        }
    }

    @Override
    public boolean resetPwd(String cusName, String cusPwd) throws SQLException {
        boolean flag = false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try
        {
            connection= JdbcUtils.getConn();
            String sql="update customert set cust_pwd=? where cust_no=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,cusPwd);
            preparedStatement.setString(2,cusName);
            int intflag= preparedStatement.executeUpdate();
            if(intflag==1) flag=true;
            return flag;
        }finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);

        }

    }

    @Override
    public Customer getCustomer(String cusName) throws SQLException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection= JdbcUtils.getConn();
            String sql="select cust_id,cust_no,cust_pwd from customert where cust_no=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,cusName);
            resultSet=preparedStatement.executeQuery();
            Customer customer =new Customer();
            if (resultSet.next()){
                customer.setCustId(resultSet.getInt(1));
                customer.setCustNo(resultSet.getString(2));
                customer.setCustPwd(resultSet.getString(3));
            }
            return customer;
        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);

        }
    }




    /**
     * 添加新用户
     * @param customer
     * @return
     * @throws SQLException
     */
    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        boolean flag = false;
        if (customer==null)   return flag;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try
        {
            connection= JdbcUtils.getConn();
            String sql="insert into customert(cust_no,cust_pwd) " +
                    "values(?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,customer.getCustNo());
            preparedStatement.setString(2,customer.getCustPwd());
            int intflag= preparedStatement.executeUpdate();
            if(intflag==1) flag=true;
            return flag;
        }finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);

        }

    }


    public List<Customer> queryCustomer() throws SQLException {

        List<Customer> customerList=new ArrayList<Customer>();
        Connection connection= JdbcUtils.getConn();
        String sql="SELECT cust_id,cust_no,cust_pwd FROM customert";
        Statement statement=connection.createStatement();

        ResultSet resultSet=  statement.executeQuery(sql);



        while (resultSet.next()){

            Customer customer=new Customer();

            customer.setCustId(resultSet.getInt(1));
            customer.setCustNo(resultSet.getString(2));
            customer.setCustPwd(resultSet.getString(3));

            customerList.add(customer);

        }//结果集封装到了list


        resultSet.close();
        statement.close();
        connection.close();

        return customerList;
    }



    public boolean checkUser(String userName, String userPWD) throws SQLException {

        boolean flag = false;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {

            connection = JdbcUtils.getConn();
            statement = connection.createStatement();
            String sql = "select cust_id,cust_no,cust_pwd from customert where cust_no='" + userName + "'";
            rs = statement.executeQuery(sql);

            if (rs.next()) {
                String pwd = rs.getString(3);
                System.out.println("pwd:" + pwd);
                if (pwd.equals(userPWD)) flag = true;
            }


            return flag;
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }
    }
}


