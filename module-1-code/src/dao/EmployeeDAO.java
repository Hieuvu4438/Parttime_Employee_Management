package dao;

import model.Employee;
import model.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDAO extends DAO {
    public EmployeeDAO() {
        super();
    }

    public ArrayList<Employee> searchEmployee(String key) {
        ArrayList<Employee> result = new ArrayList<Employee>();
        if (con == null) {
            return result;
        }
        String sql = "SELECT ID, code, fullName, phoneNumber, email, dob, contractDate, restaurantID "
                + "FROM tblEmployee "
                + "WHERE code LIKE ? OR fullName LIKE ? OR phoneNumber LIKE ? OR email LIKE ? "
                + "ORDER BY fullName";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            String searchKey = "%" + key + "%";
            ps.setString(1, searchKey);
            ps.setString(2, searchKey);
            ps.setString(3, searchKey);
            ps.setString(4, searchKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("ID"));
                employee.setCode(rs.getString("code"));
                employee.setFullName(rs.getString("fullName"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setEmail(rs.getString("email"));
                employee.setDob(rs.getDate("dob"));
                employee.setContractDate(rs.getDate("contractDate"));

                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt("restaurantID"));
                employee.setRestaurant(restaurant);

                result.add(employee);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
