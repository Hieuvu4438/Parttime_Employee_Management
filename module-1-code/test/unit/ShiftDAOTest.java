package test.unit;

import dao.ShiftDAO;
import model.Shift;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ShiftDAOTest {
    @Test
    public void testGetShiftNextWeek() {
        ArrayList<Shift> result = new ShiftDAO().getShiftNextWeek();

        Assert.assertNotNull(result);
        Assert.assertEquals(14, result.size());
    }

    @Test
    public void testShiftDataIsValid() {
        ArrayList<Shift> result = new ShiftDAO().getShiftNextWeek();

        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);
        for (Shift shift : result) {
            Assert.assertTrue(shift.getId() > 0);
            Assert.assertNotNull(shift.getDate());
            Assert.assertNotNull(shift.getStartDate());
            Assert.assertNotNull(shift.getEndDate());
            Assert.assertTrue(shift.getShiftNumber() == 1 || shift.getShiftNumber() == 2);
        }
    }

    @Test
    public void testShiftOrder() {
        ArrayList<Shift> result = new ShiftDAO().getShiftNextWeek();

        Assert.assertNotNull(result);
        for (int i = 1; i < result.size(); i++) {
            Shift previous = result.get(i - 1);
            Shift current = result.get(i);
            int dateCompare = previous.getDate().compareTo(current.getDate());
            Assert.assertTrue(dateCompare <= 0);
            if (dateCompare == 0) {
                Assert.assertTrue(previous.getShiftNumber() <= current.getShiftNumber());
            }
        }
    }
}
