package web.test;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import web.data.DataHelper;
import web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferTest {
    private static final String WEBSITE ="http://localhost:9999";

    @ParameterizedTest
    @CsvFileSource(resources = "/TransferAmountData.csv", numLinesToSkip = 1)
    void shouldTransferMoneyBetweenOwnCards(int transferAmount){
        open(WEBSITE);
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);

        val initialBalanceOfFirstCard = dashboardPage.getBalanceFirstCard();
        val replenishmentPageOfFirstCard = dashboardPage.replenishFirstCard();
        val secondCardInfo = DataHelper.getSecondCardInfo();
        replenishmentPageOfFirstCard.transferAmountFromSecondCard(transferAmount, secondCardInfo);
        val finalCardFirstBalance = dashboardPage.getBalanceFirstCard();
        assertEquals(initialBalanceOfFirstCard + transferAmount, finalCardFirstBalance);

        dashboardPage.checkReturnToDashboardPage();

        val initialBalanceOfSecondCard = dashboardPage.getBalanceSecondCard();
        val replenishmentPageOfSecondCard = dashboardPage.replenishSecondCard();
        replenishmentPageOfSecondCard.cleanFields();
        val firstCardInfo = DataHelper.getFirstCardInfo();
        replenishmentPageOfSecondCard.transferAmountFromFirstCard(transferAmount, firstCardInfo);
        val finalSecondCardBalance = dashboardPage.getBalanceSecondCard();
        assertEquals(initialBalanceOfSecondCard + transferAmount, finalSecondCardBalance);

        dashboardPage.checkReturnToDashboardPage();
    }
}
