package dao;

import model.Shift;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShiftDAO extends DAO {
    public ShiftDAO() {
        super();
    }

    public ArrayList<Shift> getShiftNextWeek() {
        ArrayList<Shift> result = new ArrayList<Shift>();
        if (con == null) {
            return result;
        }
        String sql = "SELECT ID, date, shiftNumber, description, startDate, endDate "
                + "FROM tblShift "
                + "WHERE date >= DATE_ADD(CURDATE(), INTERVAL IF((9 - DAYOFWEEK(CURDATE())) % 7 = 0, 7, (9 - DAYOFWEEK(CURDATE())) % 7) DAY) "
                + "AND date < DATE_ADD(DATE_ADD(CURDATE(), INTERVAL IF((9 - DAYOFWEEK(CURDATE())) % 7 = 0, 7, (9 - DAYOFWEEK(CURDATE())) % 7) DAY), INTERVAL 7 DAY) "
                + "ORDER BY date, shiftNumber";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Shift shift = new Shift();
                shift.setId(rs.getInt("ID"));
                shift.setDate(rs.getDate("date"));
                shift.setShiftNumber(rs.getInt("shiftNumber"));
                shift.setDescription(rs.getString("description"));
                shift.setStartDate(rs.getTimestamp("startDate"));
                shift.setEndDate(rs.getTimestamp("endDate"));
                result.add(shift);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
