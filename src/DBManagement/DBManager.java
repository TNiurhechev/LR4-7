package DBManagement;

import ParkingSystem.Car;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private String host;
    private String user;
    private String pass;
    private String dbName;
    private Connection conn;
    public DBManager(String host, String user, String pass, String dbName) {
        this.conn = null;
        this.dbName = dbName;
        this.host = host;
        this.pass = pass;
        this.user = user;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public Connection connect() {
        String url = "jdbc:mysql://" + this.host + "/" + this.dbName +"?useUnicode=true&characterEncoding=utf-8";
        try {
            this.conn = (Connection) DriverManager.getConnection(url, this.user, this.pass);
            return this.conn;
        } catch (SQLException e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    public ResultSet getSelectQuery(String sql, Connection conn) {
        Statement comm = null;
        ResultSet set = null;
        try {
            comm = (Statement) conn.createStatement();
            set = comm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }
}
