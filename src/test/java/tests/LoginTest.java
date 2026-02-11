package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test()
    public void correctLogin() {
        System.out.println("LoginTest.correct !!!!! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(productPage.isTitleIsDisplayed(), "Заголовок не виден");
        assertEquals(productPage.checkTitleName(), PRODUCTS.getDisplayName(), "Не верный заголовок");
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Standard_user", "secret_sauce",
                        "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Epic("Тетстирование интернет-площадки")
    @Feature("Проверка расчета скидки")
    @Story("FFFg")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Gimranov Tagir @SilverWig")
    @TmsLink("Saus")
    @Issue("Auto_1")
    @Test(dataProvider = "incorrectLoginData", description = "тест проверяет авторизацию заюлокированного пользователя")
    public void incorrectLogin(String user, String password, String errorMsg) {
        System.out.println("LoginTest.incorrect !!!!! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        //AllureUtils.takeScreenshot(driver);
        assertEquals(loginPage.getErrorText(), errorMsg, "Не верный текст сообщенияя об ошибке");

    }
}