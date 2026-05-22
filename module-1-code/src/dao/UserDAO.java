package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DAO {
    public UserDAO() {
        super();
    }

    public boolean checkLogin(User user) {
        if (con == null) {
            return false;
        }
        String sql = "SELECT ID, username, role, description FROM tblUser WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setDescription(rs.getString("description"));
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
