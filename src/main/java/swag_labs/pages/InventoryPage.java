package swag_labs.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryPage {
    String pageTitle = "Страница инвентаря";
    String pageUrl = "https://www.saucedemo.com/inventory.html";

    public InventoryPage openPage() {
        step("Открыть страницу: " + pageTitle, () -> open(pageUrl));
        return this;
    }

    public InventoryItemPage clickOnCardTitle(String cardTitle) {
        step(String.format("Открыть карточку: %s", cardTitle), () -> {
            SelenideElement title = $x("//div[@class='inventory_item_name ' and .='" + cardTitle + "']");
            title.shouldBe(Condition.visible).click();
        });
        return page(InventoryItemPage.class);
    }

    public InventoryPage checkUrl() {
        step("Убедиться, что текущий url: " + pageUrl, () -> {
            String currentUrl = Selenide.webdriver().driver().getCurrentFrameUrl();
            assertEquals(currentUrl, pageUrl);
        });
        return this;
    }
}
