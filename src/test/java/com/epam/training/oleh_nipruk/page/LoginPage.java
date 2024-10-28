package com.epam.training.oleh_nipruk.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.xpath("//input[@id='user-name']");
    private By passwordField = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//input[@id='login-button']");
    private By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clearFields() {
       // driver.findElement(usernameField).clear();
       // driver.findElement(passwordField).clear();
        driver.findElement(usernameField).sendKeys(Keys.CONTROL+"A");
        driver.findElement(usernameField).sendKeys(Keys.BACK_SPACE);
        driver.findElement(passwordField).sendKeys(Keys.CONTROL+"A");
        driver.findElement(passwordField).sendKeys(Keys.BACK_SPACE);
    }
    public void clearPasswordField() {
        //driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(Keys.CONTROL+"A");
        driver.findElement(passwordField).sendKeys(Keys.BACK_SPACE);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed() ? errorElement.getText() : "";
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
