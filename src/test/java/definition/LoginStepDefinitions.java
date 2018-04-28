package definition;

import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;

public class LoginStepDefinitions extends DefinitionUtils {

    @Then("^I open a new tab for email$")
    public void openNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://gmail.com','_blank');");

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    @Then("^I enter email username$")
    public void enterEmailUsername() {
        waitForElementTobeVisible(By.id("identifierId"));
        driver.findElement(By.id("identifierId")).sendKeys(prop.getProperty("emailAdd"));
        driver.findElement(By.id("identifierNext")).click();
    }

    @Then("^I enter email password$")
    public void enterEmailPassword() {
        waitForElementTobeVisible(By.name("password"));
        driver.findElement(By.name("password")).sendKeys(prop.getProperty("emailPass"));
        driver.findElement(By.id("passwordNext")).click();
    }

    @Then("^I confirm I have logged in$")
    public void confirmEmailLogin() {
        waitForElementTobeVisible(By.id("gba"));
        Assert.assertThat(driver.getTitle(), containsString(prop.getProperty("emailAdd")));
    }
}
