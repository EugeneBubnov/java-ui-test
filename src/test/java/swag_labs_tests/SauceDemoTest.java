package swag_labs_tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import swag_labs.pages.LoginPage;

@ExtendWith(ScreenShooterExtension.class)
public class SauceDemoTest extends BaseTest {
    @Test
    @Epic("Sauce Demo")
    @Feature("Функционал покупки продуктов")
    @DisplayName("Купить один продукт")
    void testBuyOneProduct() {
        String caseCard = "Sauce Labs Onesie";

        new LoginPage().openPage()
                .setCredentials(0, user)
                .login(user)
                .clickOnCardTitle(caseCard)
                .checkProductCard(caseCard)
                .clickOnAddToCartBtn()
                .clickOnOpenCartBtn()
                .checkCardCounter(1)
                .validateProduct(caseCard)
                .clickOnCheckoutBtn()
                .completeTheForm(user)
                .clickOnContinueBtn()
                .verifyOrderSummary(caseCard)
                .clickOnFinishBtn()
                .assertOrderCompleted()
                .clickOnBackHomeBtn()
                .checkUrl();
    }
}
