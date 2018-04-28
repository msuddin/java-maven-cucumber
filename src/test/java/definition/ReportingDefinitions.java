package definition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

public class ReportingDefinitions extends DefinitionUtils {

    @Given("^I have deleted any previous reports$")
    public void deletePreviousReports() {
        File reportFile = new File(reportFileName);
        reportFile.delete();
    }

    @Then("^I capture current commission and trade amount$")
    public void commissionAndTradeWalletAmount() throws IOException {
        waitForElementTobeVisible(By.cssSelector(".comission-bg"));
        String commission = driver.findElement(By.cssSelector(".comission-bg .value-panel")).getText();

        waitForElementTobeVisible(By.cssSelector(".trade-bg"));
        String trade = driver.findElement(By.cssSelector(".trade-bg .value-panel")).getText();

        waitForElementTobeVisible(By.cssSelector(".box-mypackage"));
        String myPackage = driver.findElement(By.cssSelector(".box-mypackage .value-panel")).getText();

        myPackage = myPackage.replace("TCOIN", "");
        commission = commission.replace("TCOIN", "");
        trade = trade.replace("TCOIN", "");

        writeToFile(myPackage, trade, commission);
    }

}
