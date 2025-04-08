import configs.TestConfig;
import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static constants.Constants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTests {
    //TestConfig config = new TestConfig();
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get(config.getBaseUrl());
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void openHomePageTest(){
        String actualTitle = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);

    }

    @Test
    void loginFormTest(){
        WebElement loginFormLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 7.\")]/../a[contains (@href, 'login-form')]"));
        new Actions(driver).moveToElement(loginFormLink).perform();
        loginFormLink.click();
        String loginFormUrl = "login-form.html";
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(config.getBaseUrl() + loginFormUrl, currentUrl);
        assertEquals("Login form", title.getText());
    }

    @Test
    void signInTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
        WebElement loginField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        loginField.sendKeys(config.getUsername());
        passwordField.sendKeys(config.getPassword());
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        WebElement message = driver.findElement(By.className("alert"));

        assertEquals("Login successful", message.getText());
    }


}