package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement pageHeading = $(byText("Пополнение карты"));
    private final SelenideElement amountInput = $("[data-test-id=amount] input");
    private final SelenideElement fromInput = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public TransferPage() {
        pageHeading.shouldBe(visible);
    }

    public void transfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getNumber());
        transferButton.click();
    }

    public DashboardPage validTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        transfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void showError(String expectedText){
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

}
