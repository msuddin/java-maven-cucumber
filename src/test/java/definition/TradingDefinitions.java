package definition;

import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.Matchers.is;

public class TradingDefinitions extends DefinitionUtils {

    @Then("^I set trading to \"([^\"]*)\"$")
    public void setTrading(String tradeSetting) {
        waitForElementTobeVisible(By.id("trade-risk"));
        driver.findElement(By.id("trade-risk")).click();

        Select dropdown = new Select(driver.findElement(By.id("trade-risk")));
        dropdown.selectByVisibleText(tradeSetting);
    }

    @Then("^wait for trading confirmation$")
    public void tradingConfirmation() {
        waitForElementTobeVisible(By.className("jquery-notific8-message"));
    }

    @Then("^I check that trading is set to \"([^\"]*)\"$")
    public void checkWhatTradingIsSetTo(String tradingState) {
        waitForElementTobeVisible(By.id("trade-risk"));
        Select dropdown = new Select(driver.findElement(By.id("trade-risk")));
        Assert.assertThat(dropdown.getFirstSelectedOption().getText(), is(tradingState));
    }
}
