package test.unit;

import dao.EmployeeDAO;
import model.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class EmployeeDAOTest {
    @Test
    public void testSearchEmployeeByCode() {
        ArrayList<Employee> result = new EmployeeDAO().searchEmployee("E001");

        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 1);
        Assert.assertEquals("E001", result.get(0).getCode());
    }

    @Test
    public void testSearchEmployeeByName() {
        ArrayList<Employee> result = new EmployeeDAO().searchEmployee("Nguyen");

        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 1);
        Assert.assertTrue(result.get(0).getFullName().contains("Nguyen"));
    }

    @Test
    public void testSearchEmployeeNoResult() {
        ArrayList<Employee> result = new EmployeeDAO().searchEmployee("not-exist-employee-key");

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
