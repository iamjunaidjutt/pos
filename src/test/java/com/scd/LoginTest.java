package com.scd;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.scd.Business.Login;

public class LoginTest {

    private Login login;

    @Before
    public void setUp() {
        login = new Login();
    }

    @Test
    public void testLoginValidCredentials() {
        assertTrue(login.login("admin1", "admin123123"));
    }

    @Test
    public void testLoginInvalidCredentials() {
        assertFalse(login.login("admin150", "123456"));
    }

    @Test
    public void testUserRoleValidCredentials() {
        assertEquals("MANAGER",login.userRole("admin1", "admin123123"));
    }

    @Test
    public void testUserRoleInvalidCredentials() {
        assertNotEquals("SALES_ASSISSTANT",login.userRole("admin1", "admin123123"));
    }
}

