package swag_labs.pages;

import com.codeborne.selenide.Condition;
import utils.ProductData;
import utils.User;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

public class CheckoutStepper {
    public CheckoutStepper completeTheForm(User user) {
        step("Заполнить форму данным пользователя", () -> {
            $("#first-name").sendKeys(user.getFirstName());
            $("#last-name").sendKeys(user.getLastName());
            $("#postal-code").sendKeys(user.getPostalCode());
        });
        return this;
    }

    public CheckoutStepper clickOnContinueBtn() {
        step("Нажать кнопку: Continue", () -> $("#continue").click());
        return this;
    }

    public CheckoutStepper verifyOrderSummary(String productName) {
        step("Проверить данные для подтверждения заказа: " + productName, () -> {
            Map<String, String> product = ProductData.getProductByName(productName);

            assertAll("Данные из карточки продукта",
                    () -> assertEquals(product.get("name"), $(".inventory_item_name").getText()),
                    () -> assertEquals(product.get("desc"), $(".inventory_item_desc").getText()),
                    () -> assertEquals(product.get("price"), $(".inventory_item_price").getText())
            );
            assertAll("Информация о платеже",
                    () -> assertEquals(
                            $(".summary_info_label:first-child").getText(),
                            "Payment Information:"
                    ),
                    () -> assertTrue($(".summary_value_label:nth-child(2)").getText().contains("SauceCard #"))
            );
            assertAll("Информация о доставке",
                    () -> assertEquals(
                            $(".summary_info_label:nth-child(3)").getText(),
                            "Shipping Information:"
                    ),
                    () -> assertEquals(
                            $(".summary_value_label:nth-child(4)").getText(),
                            "Free Pony Express Delivery!"
                    )
            );

            float tax = 0.64f;
            float productPrice = Float.parseFloat(product.get("price").replace("$", ""));
            float totalPrice = productPrice + tax;

            assertAll("Итоговая цена",
                    () -> assertEquals($(".summary_info_label:nth-child(5)").getText(), "Price Total"),
                    () -> assertEquals($(".summary_subtotal_label").getText(), "Item total: $" + productPrice),
                    () -> assertEquals($(".summary_tax_label").getText(), "Tax: $" + tax),
                    () -> assertEquals($(".summary_total_label").getText(), "Total: $" + totalPrice)
            );
        });
        return this;
    }

    public CheckoutStepper clickOnFinishBtn() {
        step("Нажать кнопку: Finish", () -> $("#finish").click());
        return this;
    }

    public CheckoutStepper assertOrderCompleted() {
        step("Проверить сообщение об успешном оформлении заказа", () -> {
            $("[src='/assets/checkmark-VLWQafip.png']").shouldBe(Condition.visible);
            $(".complete-header").shouldBe(Condition.visible).shouldHave(Condition.text(
                    "Thank you for your order!"
            ));
            $(".complete-text").shouldBe(Condition.visible).shouldHave(Condition.text(
                    "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
            ));
        });
        return this;
    }

    public InventoryPage clickOnBackHomeBtn() {
        step("Нажать кнопку: Back Home", () -> $("#back-to-products").click());
        return page(InventoryPage.class);
    }
}
