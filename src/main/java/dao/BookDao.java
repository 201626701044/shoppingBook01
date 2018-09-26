package dao;

import mo.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public List<Book> getRecommadation(int size) throws SQLException ;
    public List<Book> getRecommThisWeek(int size) throws SQLException ;
    public List<Book> getRecommadation(int start, int size) throws SQLException ;
    //start记录的起始位置
    public int getBookNumber() throws SQLException;
}
