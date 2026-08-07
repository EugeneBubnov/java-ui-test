package swag_labs.pages;

import com.codeborne.selenide.Condition;
import utils.User;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.*;

public class LoginPage {
    String pageTitle = "Страница авторизации";
    String pageUrl = "https://www.saucedemo.com/";

    public LoginPage openPage() {
        step("Открыть страницу: " + pageTitle, () -> open(pageUrl));
        return this;
    }

    /**
     * Установить креды для входа по номеру кейса
     *
     * @param cred_case 0 - обычный юзер; 1 - юзер из чс;
     * @return страница логина
     */
    public LoginPage setCredentials(int cred_case, User user) {
        step(String.format("Получить креды для входа (кейс: %d)", cred_case), () -> {
            String login = switch (cred_case) {
                case 0 -> "standard_user";
                case 1 -> "locked_out_user";
                default -> throw new RuntimeException(String.format("Invalid case: %d", cred_case));
            };

            user.setLogin(login);
            user.setPassword("secret_sauce");
        });
        return this;
    }

    public InventoryPage login(User user) {
        step("Войти в Swag Labs", () -> {
            $(".login_logo").shouldBe(Condition.visible).shouldHave(Condition.text("Swag Labs"));

            $("#user-name").sendKeys(user.getLogin());
            $("#password").sendKeys(user.getPassword());
            $("#login-button").click();
        });
        return page(InventoryPage.class);
    }
}
