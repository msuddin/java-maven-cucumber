package definition;

import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

public class WithdrawStepDefinition extends DefinitionUtils{

    @Then("^I navigate to withdraw$")
    public void openWithdraw() {
        clickOnSideMenu(By.className("active-nav"));
        clickOnSideMenu(By.linkText("Financial"));
        clickOnSideMenu(By.linkText("Request Withdraw"));
        waitForElementTobeVisible(By.id("solicitation-withdraw"));
    }

    @Then("^I set withdraw request$")
    public void setWithdrawRequest() {
        waitForElementTobeVisible(By.className("comission-bg"));
        String commission = driver.findElement(By.className("comission-bg"))
                .getText()
                .replace("Wallet", "")
                .replace("TCOIN", "")
                .replace("Commission", "")
                .replaceAll("\n", "")
                .replaceAll("\r", "");

        waitForElementTobeVisible(By.className("trade-bg"));
        String trade = driver.findElement(By.className("trade-bg"))
                .getText()
                .replace("Wallet", "")
                .replace("TCOIN", "")
                .replace("Trade", "")
                .replaceAll("\n", "")
                .replaceAll("\r", "");

        driver.findElement(By.id("input-wallet-comissions")).sendKeys(commission);
        driver.findElement(By.id("input-wallet-trade")).sendKeys(trade);
    }

    @Then("^I press request button$")
    public void openWithdrawWindow() {
        waitForElementTobeVisible(By.id("solicitation-withdraw"));
        driver.findElement(By.id("solicitation-withdraw")).click();
    }

    @Then("^I press token button$")
    public void pressTokenButton() {
        sleep(4);
        waitForElementTobeVisible(By.id("md-confirm-withdraw"));

        waitForElementTobeVisible(By.className("send-token"));
        driver.findElement(By.className("send-token")).click();
    }

    @Then("^I wait for token confirmation$")
    public void withdrawTokenConfirmation() {
        waitForElementTobeVisible(By.className("jquery-notific8-message"));
    }

    @Then("^I set financial password$")
    public void financialPassword() {
        waitForElementTobeVisible(By.name("easypass"));
        driver.findElement(By.name("easypass")).sendKeys(prop.getProperty("fPassword"));
    }

    @Then("^I set google authenticator value$")
    public void googleAuthenticatorWithdraw() {
        waitForElementTobeVisible(By.name("validateCodeGA"));
        driver.findElement(By.name("validateCodeGA")).sendKeys(generateGoogleAuthenticatorCode());
    }
}
