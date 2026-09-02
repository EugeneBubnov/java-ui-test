package swag_labs_tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.User;

import java.util.Locale;

public class BaseTest {
    User user;
    Faker faker;

    @BeforeAll
    public static void preSetUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker(Locale.of("ru"));
        user = new User(
                faker.name().firstName(),
                faker.name().lastName()
        );
        user.setPostalCode(faker.address().zipCode());
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
