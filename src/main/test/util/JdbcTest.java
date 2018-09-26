package util;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JdbcTest {

    @Test
    public void getConn() {
        Jdbc jdbc = new Jdbc();
        Connection connection =jdbc.getConn();
        assertNotNull(connection);
    }

    @Test
    public void queryCustomer() {
    }
}