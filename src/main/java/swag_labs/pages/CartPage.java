package swag_labs.pages;

import com.codeborne.selenide.Condition;
import utils.ProductData;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {
    public CartPage checkCardCounter(int expCounter) {
        step("Проверить счётчик корзины: " + expCounter, () -> {
            int factCounter = Integer.parseInt($(".shopping_cart_badge").getText());
            assertEquals(expCounter, factCounter);
        });
        return this;
    }

    public CartPage validateProduct(String productName) {
        step("Проверить данные продукта: " + productName, () -> {
            Map<String, String> product = ProductData.getProductByName(productName);

            assertAll("Данные продукта в корзине",
                    () -> assertEquals(product.get("name"), $(".inventory_item_name").getText()),
                    () -> assertEquals(product.get("desc"), $(".inventory_item_desc").getText()),
                    () -> assertEquals(product.get("price"), $(".inventory_item_price").getText())
            );
        });
        return this;
    }

    public CheckoutStepper clickOnCheckoutBtn() {
        step("Нажать кнопку: Checkout", () -> $("#checkout").shouldBe(Condition.visible).click());
        return page(CheckoutStepper.class);
    }
}
