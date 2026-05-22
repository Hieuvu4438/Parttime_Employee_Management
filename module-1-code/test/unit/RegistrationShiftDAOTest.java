package test.unit;

import dao.DAO;
import dao.RegistrationShiftDAO;
import dao.ShiftDAO;
import dao.UserDAO;
import model.Employee;
import model.RegistrationShift;
import model.Shift;
import model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class RegistrationShiftDAOTest {
    @Test
    public void testGetRegistrationByEmployee() {
        Employee employee = new Employee();
        employee.setId(1);

        ArrayList<RegistrationShift> result = new RegistrationShiftDAO().getRegistrationByEmployee(employee);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 2);
        Assert.assertNotNull(result.get(0).getShift());
        Assert.assertNotNull(result.get(0).getUser());
    }

    @Test
    public void testSaveRegistrationWithRollback() throws Exception {
        new RegistrationShiftDAO();
        boolean oldAutoCommit = DAO.con.getAutoCommit();
        try {
            DAO.con.setAutoCommit(false);

            Employee employee = new Employee();
            employee.setId(2);
            User user = new User();
            user.setUsername("staff01");
            user.setPassword("123456");
            Assert.assertTrue(new UserDAO().checkLogin(user));

            Shift shift = findUnregisteredShift(employee);
            Assert.assertNotNull(shift);

            RegistrationShift registrationShift = new RegistrationShift();
            registrationShift.setRegisteredTime(new Date());
            registrationShift.setStatus("registered");
            registrationShift.setDescription("JUnit rollback test");
            registrationShift.setEmployee(employee);
            registrationShift.setShift(shift);
            registrationShift.setUser(user);

            ArrayList<RegistrationShift> list = new ArrayList<RegistrationShift>();
            list.add(registrationShift);

            Assert.assertTrue(new RegistrationShiftDAO().saveRegistration(list));

            ArrayList<RegistrationShift> savedList = new RegistrationShiftDAO().getRegistrationByEmployee(employee);
            boolean found = false;
            for (RegistrationShift item : savedList) {
                if (item.getShift() != null && item.getShift().getId() == shift.getId()) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        } finally {
            DAO.con.rollback();
            DAO.con.setAutoCommit(oldAutoCommit);
        }
    }

    private Shift findUnregisteredShift(Employee employee) {
        ArrayList<RegistrationShift> existingRegistrations = new RegistrationShiftDAO().getRegistrationByEmployee(employee);
        ArrayList<Shift> shifts = new ShiftDAO().getShiftNextWeek();
        for (Shift shift : shifts) {
            boolean registered = false;
            for (RegistrationShift registrationShift : existingRegistrations) {
                if (registrationShift.getShift() != null && registrationShift.getShift().getId() == shift.getId()) {
                    registered = true;
                    break;
                }
            }
            if (!registered) {
                return shift;
            }
        }
        return null;
    }
}
