package dao;

import mo.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    public List<Customer> queryCustomer() throws SQLException;
    public boolean addCustomer(Customer customer) throws SQLException;
    public Customer getCustomer(String cusName) throws SQLException;
    public Customer getCustomer(String cusName, String cusPwd) throws SQLException;
    public boolean resetPwd(String cusName, String cusPwd) throws SQLException;
}
