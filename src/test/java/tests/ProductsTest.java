package tests;

import net.bytebuddy.implementation.EqualsMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productPage.isTitleIsDisplayed();
        productPage.addGoodsToCarts("Sauce Labs Onesie");
        productPage.addGoodsToCarts("Sauce Labs Fleece Jacket");
        productPage.addGoodsToCarts("Test.allTheThings() T-Shirt (Red)");
        assertEquals(productPage.checkCounterValue(), "3");
        assertEquals(productPage.checkCounterColorValue(),"rgba(226, 35, 26, 1)");
    }
}

