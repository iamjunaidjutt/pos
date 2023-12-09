package com.scd;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.scd.Business.Register;
import com.scd.Data.UserDAO;
import com.scd.Models.User;

public class RegitserTest {

    private Register reg;
    private UserDAO userDAO;

    @Before
    public void setUp() {
        reg = new Register();
        userDAO=new UserDAO();
    }

    @Test
    public void testRegister() {
        User user=new User();
        user.setUsername("Hamid");
        user.setPassword("147147");

        assertTrue(reg.register(user));
        assertFalse(reg.register(user));

        userDAO.delete(user.getId());
    }

    @Test
    public void testCheckUsername() {
        assertTrue(userDAO.checkUsername("admin1"));
    }

    @Test
    public void testCheckUsernameInvalid() {
        assertFalse(userDAO.checkUsername("Ali"));
    }
}
