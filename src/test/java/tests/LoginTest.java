package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void firstLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("user-name")).sendKeys(Keys.CONTROL + "A");
        driver.findElement(By.id("user-name")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[name = 'login-button']")).click();
        boolean titleIsDisplayed = driver.findElement(By.cssSelector("[data-test='title']")).isDisplayed();
        assertTrue(titleIsDisplayed);
        String titleName = driver.findElement(By.cssSelector("[data-test='title']")).getText();
        assertEquals(titleName, "Products");
    }

    @Test
    public void incorrectLogin() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[name='login-button']")).click();
        boolean errorIsDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        assertTrue(errorIsDisplayed, "Нет сообщения об ошибке");
        String errorMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        assertEquals(errorMsg, "Epic sadface: Sorry, this user has been locked out.",
                "Не верный текст сообщенияя об ошибке");
    }
}