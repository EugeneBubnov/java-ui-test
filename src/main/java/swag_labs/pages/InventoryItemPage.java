package swag_labs.pages;

import com.codeborne.selenide.Condition;
import utils.ProductData;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryItemPage {
    public InventoryPage clickOnBackToProductsBtn() {
        step("Нажать кнопку: Back to products", () -> {
            $("#back-to-products").shouldBe(Condition.visible).click();
        });
        return page(InventoryPage.class);
    }

    public InventoryItemPage clickOnAddToCartBtn() {
        step("Нажать кнопку: Add to cart", () -> {
            $("#add-to-cart").shouldBe(Condition.visible).click();
        });
        return this;
    }

    public CartPage clickOnOpenCartBtn() {
        step("Открыть страницу корзины", () -> {
            $(".shopping_cart_link").shouldBe(Condition.visible).click();
        });
        return page(CartPage.class);
    }

    public InventoryItemPage checkProductCard(String cardName) {
        step(String.format("Проверить данные карточки: %s", cardName), () -> {
            Map<String, String> product = ProductData.getProductByName(cardName);

            assertAll("Данные карточки продукта",
                    () -> assertEquals(product.get("name"), $(".inventory_details_name").getText()),
                    () -> assertEquals(product.get("desc"), $(".inventory_details_desc").getText()),
                    () -> assertEquals(product.get("price"), $(".inventory_details_price").getText())
            );
        });
        return this;
    }
}
