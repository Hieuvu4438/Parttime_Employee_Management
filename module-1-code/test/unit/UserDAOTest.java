package test.unit;

import dao.UserDAO;
import model.User;
import org.junit.Assert;
import org.junit.Test;

public class UserDAOTest {
    @Test
    public void testCheckLoginValid() {
        User user = new User();
        user.setUsername("staff01");
        user.setPassword("123456");

        boolean result = new UserDAO().checkLogin(user);

        Assert.assertTrue(result);
        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals("registration_staff", user.getRole());
    }

    @Test
    public void testCheckLoginInvalidPassword() {
        User user = new User();
        user.setUsername("staff01");
        user.setPassword("wrong");

        boolean result = new UserDAO().checkLogin(user);

        Assert.assertFalse(result);
    }

    @Test
    public void testCheckLoginUnknownUsername() {
        User user = new User();
        user.setUsername("unknown_user");
        user.setPassword("123456");

        boolean result = new UserDAO().checkLogin(user);

        Assert.assertFalse(result);
    }
}
