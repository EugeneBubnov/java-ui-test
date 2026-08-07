package swag_labs_tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import swag_labs.pages.LoginPage;
import utils.User;

import java.util.Locale;

public class SauceDemoTest {
    User user;

    @BeforeEach
    void setUp() {
        Faker faker = new Faker(Locale.of("ru"));
        user = new User(faker.name().firstName(), faker.name().lastName());
        user.setPostalCode(faker.address().zipCode());
    }

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
