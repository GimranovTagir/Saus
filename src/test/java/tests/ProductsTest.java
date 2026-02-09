package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {
    List<String> goodList = new ArrayList<>(
            List.of("Test.allTheThings() T-Shirt (Red)",
                    "Sauce Labs Onesie", "Sauce Labs Fleece Jacket")
    );

    @Epic("Тетстирование интернет-площадки")
    @Feature("Страница товаров")
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Gimranov Tagir @SilverWig")
    @TmsLink("Saus")
    @Issue("Auto_1")
    @Test(description = "проверка добавления товатов в корзину")
    public void checkGoodsAdded() {
        System.out.println("ProductsTest.correct !!!!! in thread: " + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(user, password);

        assertEquals(productPage.checkTitleName(), PRODUCTS.getDisplayName());
        assertTrue(productPage.isTitleIsDisplayed());
        for (int i = 0; i < goodList.size(); i++) {
            productPage.addGoodsToCarts(goodList.get(i));
        }

        productPage.addGoodToCart(2);
        assertEquals(productPage.checkCounterValue(), "4");
        assertEquals(productPage.checkCounterColorValue(), "rgba(266,34,26,1)");
    }
}