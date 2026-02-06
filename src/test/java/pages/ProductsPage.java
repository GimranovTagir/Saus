package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private final By title = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By cartCounter = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleIsDisplayed() {
        return driver.findElement(title).isDisplayed();
    }

    @Step("Находит товар по названию")
    public void addGoodsToCarts(String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCart).click();
    }
    @Step("Добавляет товар по названию")
    public void addGoodToCart(int goodsIndex) {
        driver.findElements(By.xpath("//*[text()='Add to cart']")).get(goodsIndex).click();
    }
    @Step("Проверяет сколько товаров по кнопке корзины")
    public String checkCounterValue() {
        return driver.findElement(cartCounter).getText();
    }

    @Step("Проверяет цвет конопки корзины")
    public String checkCounterColorValue() {
        return driver.findElement(cartCounter).getCssValue("background-color");
    }

    @Step("Переходит на страницу корзины")
    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}