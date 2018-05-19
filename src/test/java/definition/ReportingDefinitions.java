package definition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportingDefinitions extends DefinitionUtils {

    private static boolean reportDeleteDone = false;

    @Given("^I have deleted any previous reports$")
    public void deletePreviousReports() {
        if (!reportDeleteDone) {
            File reportFile = new File(reportFileName);
            reportFile.delete();
            reportDeleteDone = true;
        }
    }

    @Then("^I capture details$")
    public void captureDashBoard() throws IOException {
        waitForElementTobeVisible(By.cssSelector(".comission-bg"));
        String commission = driver.findElement(By.cssSelector(".comission-bg .value-panel")).getText();

        waitForElementTobeVisible(By.cssSelector(".trade-bg"));
        String trade = driver.findElement(By.cssSelector(".trade-bg .value-panel")).getText();

        waitForElementTobeVisible(By.cssSelector(".box-mypackage"));
        String myPackage = driver.findElement(By.cssSelector(".box-mypackage .value-panel")).getText();

        waitForElementTobeVisible(By.className("info-monitoring"));
        List<WebElement> tradingDays = driver.findElements(By.className("info-monitoring"));
        List<String> tradingDaysText = new ArrayList<String>();
        for (int i = 0; i < tradingDays.size(); i++) {
            tradingDaysText.add(tradingDays.get(i).getText());
        }

        myPackage = myPackage.replace("TCOIN", "");
        commission = commission.replace("TCOIN", "");
        trade = trade.replace("TCOIN", "");

        clickOnSideMenu(By.className("active-nav"));
        clickOnSideMenu(By.linkText("My Network"));
        clickOnSideMenu(By.linkText("Binary Network"));

        waitForElementTobeVisible(By.id("formSendFindMember"));
        List<String> binaryPointsTextValue = new ArrayList<String>();
        List<WebElement> binaryPoints = driver.findElements(By.cssSelector(".table-bordered tbody tr td"));

        for (int i = 0; i < binaryPoints.size(); i++) {
            binaryPointsTextValue.add(binaryPoints.get(i).getText());
        }

        clickOnSideMenu(By.className("active-nav"));
        clickOnSideMenu(By.linkText("Financial"));
        clickOnSideMenu(By.linkText("Financial Extract"));

        waitForElementTobeVisible(By.className("table-responsive"));
        List<WebElement> tables = driver.findElements(By.className("table-responsive"));
        List<WebElement> earningSections = tables.get(1).findElements(By.tagName("td"));
        double totalTradeEarning = Double.parseDouble(earningSections.get(earningSections.size() - 2).getText());

        writeToFile(myPackage, trade, commission, binaryPointsTextValue, tradingDaysText, totalTradeEarning);
    }

}
