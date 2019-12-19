package web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class ReplenishmentPage {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement fromCard = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DashboardPage transferAmountFromSecondCard (int transferAmount,
                                                       DataHelper.SecondCardInfo secondCardInfo){
        amount.setValue(String.valueOf(transferAmount));
        fromCard.setValue(secondCardInfo.getSecondCardNumber());
        transferButton.click();
        return new DashboardPage();
    }
    public DashboardPage transferAmountFromFirstCard (int transferAmount,
                                                       DataHelper.FirstCardInfo firstCardInfo){
        amount.setValue(String.valueOf(transferAmount));
        fromCard.setValue(firstCardInfo.getFirstCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void cleanFields (){
        amount.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        fromCard.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
    }
}
