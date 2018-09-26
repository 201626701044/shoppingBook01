package dao;

import mo.Customer;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class CustomerDaoImplTest {
    @Test
    public void addCustomerOk1() {
        CustomerDao customerDao=new CustomerDaoImpl();
        Customer customer=new Customer();
        customer.setCustNo("abcde");
        customer.setCustPwd("123123");
        boolean flag=false;
        try {
            flag=customerDao.addCustomer(customer);
        }catch (SQLException e){
            e.printStackTrace();
        }
        assertEquals(flag,true);
    }

    @Test
    public void addCustomerOk() {
        CustomerDao customerDao=new CustomerDaoImpl();
        Customer customer=new Customer();
        customer.setCustNo("abcdeeeeeee");
        customer.setCustPwd("123123");
        boolean flag=false;
        try {
            flag=customerDao.addCustomer(customer);
        }catch (SQLException e){
            e.printStackTrace();
        }
        assertEquals(flag,true);
    }

    @Test
    public void addCustomer() {
        CustomerDao customerDao=new CustomerDaoImpl();
        Customer customer=new Customer();
        customer.setCustNo("abcd");
        customer.setCustPwd("123123");
        boolean flag=false;
        try {
            flag=customerDao.addCustomer(customer);
        }catch (SQLException e){
            e.printStackTrace();
        }
        assertEquals(flag,false);
    }
}