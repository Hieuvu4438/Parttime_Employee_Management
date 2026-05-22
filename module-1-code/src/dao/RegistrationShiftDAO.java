package dao;

import model.Employee;
import model.RegistrationShift;
import model.Shift;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class RegistrationShiftDAO extends DAO {
    public RegistrationShiftDAO() {
        super();
    }

    public ArrayList<RegistrationShift> getRegistrationByEmployee(Employee employee) {
        ArrayList<RegistrationShift> result = new ArrayList<RegistrationShift>();
        if (con == null) {
            return result;
        }
        String sql = "SELECT r.ID AS registrationID, r.registeredTime, r.status, r.description AS registrationDescription, "
                + "s.ID AS shiftID, s.date, s.shiftNumber, s.description AS shiftDescription, s.startDate, s.endDate, "
                + "u.ID AS userID, u.username, u.role, u.description AS userDescription "
                + "FROM tblRegistrationShift r "
                + "JOIN tblShift s ON r.shiftID = s.ID "
                + "JOIN tblUser u ON r.userID = u.ID "
                + "WHERE r.employeeID = ? "
                + "ORDER BY s.date, s.shiftNumber";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, employee.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Shift shift = new Shift();
                shift.setId(rs.getInt("shiftID"));
                shift.setDate(rs.getDate("date"));
                shift.setShiftNumber(rs.getInt("shiftNumber"));
                shift.setDescription(rs.getString("shiftDescription"));
                shift.setStartDate(rs.getTimestamp("startDate"));
                shift.setEndDate(rs.getTimestamp("endDate"));

                User user = new User();
                user.setId(rs.getInt("userID"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setDescription(rs.getString("userDescription"));

                RegistrationShift registrationShift = new RegistrationShift();
                registrationShift.setId(rs.getInt("registrationID"));
                registrationShift.setRegisteredTime(rs.getTimestamp("registeredTime"));
                registrationShift.setStatus(rs.getString("status"));
                registrationShift.setDescription(rs.getString("registrationDescription"));
                registrationShift.setEmployee(employee);
                registrationShift.setShift(shift);
                registrationShift.setUser(user);
                result.add(registrationShift);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveRegistration(ArrayList<RegistrationShift> list) {
        if (con == null || list == null || list.isEmpty()) {
            return false;
        }

        String sql = "INSERT INTO tblRegistrationShift(registeredTime, status, description, employeeID, shiftID, userID) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        boolean oldAutoCommit = true;
        boolean ownsTransaction = false;
        try {
            oldAutoCommit = con.getAutoCommit();
            if (oldAutoCommit) {
                con.setAutoCommit(false);
                ownsTransaction = true;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            for (RegistrationShift item : list) {
                if (item.getEmployee() == null || item.getShift() == null || item.getUser() == null
                        || item.getEmployee().getId() <= 0 || item.getShift().getId() <= 0 || item.getUser().getId() <= 0) {
                    ps.close();
                    if (ownsTransaction) {
                        con.rollback();
                    }
                    return false;
                }

                Date registeredTime = item.getRegisteredTime();
                if (registeredTime == null) {
                    registeredTime = new Date();
                    item.setRegisteredTime(registeredTime);
                }
                String status = item.getStatus();
                if (status == null || status.trim().isEmpty()) {
                    status = "registered";
                    item.setStatus(status);
                }

                ps.setTimestamp(1, new Timestamp(registeredTime.getTime()));
                ps.setString(2, status);
                ps.setString(3, item.getDescription());
                ps.setInt(4, item.getEmployee().getId());
                ps.setInt(5, item.getShift().getId());
                ps.setInt(6, item.getUser().getId());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();

            if (ownsTransaction) {
                con.commit();
            }
            return true;
        } catch (Exception e) {
            try {
                if (ownsTransaction) {
                    con.rollback();
                }
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (ownsTransaction) {
                    con.setAutoCommit(oldAutoCommit);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
