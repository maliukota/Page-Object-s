package web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private SelenideElement firstCardBalance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCardBalance = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public ReplenishmentPage replenishFirstCard (){
        firstCardButton.click();
        return new ReplenishmentPage();
    }

    public ReplenishmentPage replenishSecondCard (){
        secondCardButton.click();
        return new ReplenishmentPage();
    }

    public void checkReturnToDashboardPage(){
        heading.shouldBe(Condition.visible);
        firstCardButton.shouldBe(Condition.visible);
        secondCardButton.shouldBe(Condition.visible);
    }

    public int getBalanceFirstCard () {
        String balanceText = firstCardBalance.getText();
        int startInd = balanceText.indexOf("баланс: ");
        int finishInd = balanceText.indexOf(" р.");
        String balance = balanceText.substring(startInd + 8, finishInd);
        return Integer.parseInt(balance)    ;
    }

    public int getBalanceSecondCard () {
        String balanceText = secondCardBalance.getText();
        int startInd = balanceText.indexOf("баланс: ");
        int finishInd = balanceText.indexOf(" р.");
        String balance = balanceText.substring(startInd + 8, finishInd);
        return Integer.parseInt(balance)    ;
    }
}
