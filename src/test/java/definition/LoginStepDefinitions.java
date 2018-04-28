package definition;

import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;

public class LoginStepDefinitions extends DefinitionUtils {

    @Then("^I open a new tab for email$")
    public void openNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (prop.getProperty("username").equals("mohammedsuddin")) {
            js.executeScript("window.open('https://secure.ipage.com/secure/login.bml#webmail','_blank');");
        } else {
            js.executeScript("window.open('https://gmail.com','_blank');");
        }

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    @Then("^I enter email username$")
    public void enterEmailUsername() {
        if (prop.getProperty("username").equals("mohammedsuddin")) {
            waitForElementTobeVisible(By.id("address"));
            driver.findElement(By.id("address")).sendKeys(prop.getProperty("emailAdd"));
        } else {
            waitForElementTobeVisible(By.id("identifierId"));
            driver.findElement(By.id("identifierId")).sendKeys(prop.getProperty("emailAdd"));
            driver.findElement(By.id("identifierNext")).click();
        }
    }

    @Then("^I enter email password$")
    public void enterEmailPassword() {
        waitForElementTobeVisible(By.name("password"));
        driver.findElement(By.name("password")).sendKeys(prop.getProperty("emailPass"));
        if (prop.getProperty("username").equals("mohammedsuddin")) {

            WebElement element = driver.findElement(By.linkText("Not a customer? Sign up now!"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.perform();

            driver.findElement(By.id("webmailLogin")).click();
        } else {
            driver.findElement(By.id("passwordNext")).click();
        }
    }

    @Then("^I confirm I have logged in$")
    public void confirmEmailLogin() {
        if (prop.getProperty("username").equals("mohammedsuddin")) {
            waitForElementTobeVisible(By.className("username"));
            Assert.assertThat(driver.findElement(By.className("username")).getText(),
                    containsString(prop.getProperty("emailAdd")));
        } else {
            waitForElementTobeVisible(By.id("gba"));
            Assert.assertThat(driver.getTitle(), containsString(prop.getProperty("emailAdd")));
        }
    }
}
