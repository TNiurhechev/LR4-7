package DBManagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    //READ
    T get(long id);

    List<T> getAll();

    //CREATE
    void save(T t) throws SQLException;

    //UPDATE
    void update(T t, String[] params);

    //DELETE
    void delete(T t) throws SQLException;
}
