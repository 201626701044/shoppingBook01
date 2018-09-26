package dao;


import mo.BookType;

import java.sql.SQLException;
import java.util.List;

public interface BookTypeDao {
    public List<BookType> getListType(int size) throws SQLException ;
}
