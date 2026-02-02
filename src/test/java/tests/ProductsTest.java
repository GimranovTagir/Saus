package tests;

import net.bytebuddy.implementation.EqualsMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {
    List<String> goodList = new ArrayList<>(
            List.of("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie", "Sauce Labs Fleece Jacket")
    );

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productPage.checkTitleName(), "Products");
        assertTrue(productPage.isTitleIsDisplayed());
        for (int i=0; i < goodList.size(); i++){
            productPage.addGoodsToCarts(goodList.get(i));
        }
        productPage.addGoodToCart(2);

        assertEquals(productPage.checkCounterValue(), "4");
        assertEquals(productPage.checkCounterColorValue(),"rgba(266,34,26,1)");
    }
}