package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;


import static org.testng.Assert.*;


public class CartTest extends BaseTest {
    final String goodsName = "Sauce Labs Onesie";

    @Epic("Тетстирование интернет-площадки")
    @Feature("Страница корзины")
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Gimranov Tagir @SilverWig")
    @TmsLink("Saus")
    @Issue("Auto_1")
    @Test
    public void checkGoodsAdded() {
        System.out.println("CartTest.incorrect !!!!! in thread: "+Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(user,password);
        assertEquals(productPage.checkTitleName(), "Products");

        productPage.addGoodsToCarts(goodsName);
        productPage.switchToCart();

        assertEquals(cartPage.checkTitleName(), "Your Cart");

        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 1);
        assertTrue(cartPage.getProductsNames().contains(goodsName));
    }
}
