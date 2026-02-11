package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {

    private final By loginInput = By.id("user-name");
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginButton = By.cssSelector("[name = 'login-button']");
    private final By errorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Логинимся под кредами пользователя")
    public void login(String user, String password) {
        fillLoginField(user);
        fillPasswordField(password);
        driver.findElement(loginButton).click();
    }

    @Step
    public void login(User user) {
        fillLoginField(user.getEmail());
        fillPasswordField(user.getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Ввводим логин {user}")
    public void fillLoginField(String user) {
        driver.findElement(loginInput).sendKeys(user);
    }

    @Step("Ввводим пароль {password}")
    public void fillPasswordField(String user) {
        driver.findElement(passwordInput).sendKeys(user);
    }

    @Step("Пороверяем, видимость уведомления об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    @Step("Пороверяем, текст ошибки")
    public String getErrorText() {
        return driver.findElement(errorMsg).getText();
    }
}