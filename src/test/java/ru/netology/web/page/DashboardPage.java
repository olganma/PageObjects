package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        val text = cards.findBy(text(cardInfo.getNumber().substring(15))).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(text(cardInfo.getNumber().substring(15))).$("button").click();
        return new TransferPage();
    }
}