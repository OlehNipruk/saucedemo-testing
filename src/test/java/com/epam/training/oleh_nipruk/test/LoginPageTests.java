package com.epam.training.oleh_nipruk.test;

import com.epam.training.oleh_nipruk.driver.WebDriverSingleton;
import com.epam.training.oleh_nipruk.page.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginPageTests {
    private static final Logger logger = LoggerFactory.getLogger(LoginPageTests.class);
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        driver = WebDriverSingleton.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        logger.info("Starting test with login page opened");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Cleaning up after test execution");
        WebDriverSingleton.quitDriver();
        logger.info("WebDriver instance quit and resources released");
        logger.info("Teardown complete");
    }

    @Test
    public void testLoginFormEmptyCredentials() {
        logger.info("Starting test for login with empty credentials");
        loginPage.enterUsername("any_username");
        logger.info("Entered username: any_username");
        loginPage.enterPassword("any_password");
        logger.info("Entered password");
        loginPage.clearFields();
        logger.info("Cleared both username and password fields");
        loginPage.clickLogin();
        logger.info("Login button clicked");
        assertEquals("Epic sadface: Username is required", loginPage.getErrorMessage());
        logger.info("Verified error message for missing username");
    }

    @Test
    public void testLoginFormMissingPassword() {
        logger.info("Starting test for login with missing password field");
        loginPage.enterUsername("any_username");
        logger.info("Entered username: any_username");
        loginPage.enterPassword("any_password");
        logger.info("Entered password and preparing to clear it");
        loginPage.clearPasswordField();
        logger.info("Password field cleared");
        loginPage.clickLogin();
        logger.info("Login button clicked");
        assertEquals("Epic sadface: Password is required", loginPage.getErrorMessage());
        logger.info("Login button clicked");
    }

    @ParameterizedTest
    @CsvSource({
            "standard_user",
            "locked_out_user",
            "problem_user",
            "performance_glitch_user",
            "error_user",
            "visual_user"
    })
    public void testLoginWithValidCredentials(String username) {
        logger.info("Starting test for login with valid credentials for user: {}", username);
        loginPage.enterUsername(username);
        logger.info("Entered username: {}", username);
        loginPage.enterPassword("secret_sauce");
        logger.info("Entered password");
        loginPage.clickLogin();
        logger.info("Login button clicked");
        assertEquals("Swag Labs", loginPage.getPageTitle());
        logger.info("Verified dashboard title for successful login");
    }
}
