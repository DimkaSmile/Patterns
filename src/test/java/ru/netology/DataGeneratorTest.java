package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DataGeneratorTest {

    @Test
    void shouldCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city']  input").setValue(DataGenerator.generateCity());
        $("[data-test-id='date']  input").doubleClick().sendKeys(Keys.BACK_SPACE);

        String planMeetingDate = DataGenerator.generateDate(3);

        $("[data-test-id='date']  input").setValue(planMeetingDate);
        $("[data-test-id='name']  input").setValue(DataGenerator.generateName());
        $("[data-test-id='phone']  input").setValue(DataGenerator.generatePhone());
        $("[data-test-id='agreement']").click();
        $(".grid-col button").click();

        $(withText("Успешно!")).shouldBe(visible);
        $(".notification__content").shouldBe(visible)
                .shouldHave(exactText("Встреча успешно запланирована на " + planMeetingDate));

        $("[data-test-id='date']  input").doubleClick().sendKeys(Keys.BACK_SPACE);

        String replanMeetingDate = DataGenerator.generateDate(4);

        $("[data-test-id='date']  input").setValue(replanMeetingDate);
        $(".grid-col button").click();

        $("[data-test-id='replan-notification'] .notification__content")
                .shouldBe(visible).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $(".notification_status_error .button").click();

        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible).shouldHave
                (exactText("Встреча успешно запланирована на " + replanMeetingDate));
    }
}
