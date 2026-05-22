package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    public static Connection con;
    protected static final String DB_URL = "jdbc:mysql://localhost:3306/parttime_employee_management?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    protected static final String DB_CLASS = "com.mysql.cj.jdbc.Driver";
    protected static final String DB_USER = "root";
    protected static final String DB_PASSWORD = "hieu1205";

    public DAO() {
        if (con == null) {
            try {
                Class.forName(DB_CLASS);
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
