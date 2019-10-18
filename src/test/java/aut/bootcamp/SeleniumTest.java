package aut.bootcamp;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * SeleniumTest
 */
public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchInGoogle() {
        driver.get("https://google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("GUI Automation" + Keys.ENTER);
    }

    @Test
    public void testLoginEva() {
        driver.get("http://web.evace-uat.172.26.12.17.nip.io/login");
        driver.findElement(By.className("mat-button-wrapper")).click();
        WebElement email = driver.findElement(By.name("identifier"));
        email.sendKeys("sampleTestasdfasdil@.com");
        driver.findElement(By.xpath("//span[text()='Siguiente']")).click();
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("GQ8Pzc")));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Ingresa un número de teléfono o correo electrónico válidos";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}