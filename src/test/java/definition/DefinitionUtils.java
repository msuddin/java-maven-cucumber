package definition;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class DefinitionUtils {

    protected static WebDriver driver;
    protected static Properties prop = new Properties();
    protected static boolean printedColumns = false;

    protected void writeToFile(String myPackage, String trade, String commission) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("report.csv", true));
        if (!printedColumns) {
            writer.println("USERNAME" + "," + "MY PACKAGE" + "," + "COMMISSION" + "," + "TRADE");
            printedColumns = true;
        }
        writer.println(prop.getProperty("username") + "," + myPackage + "," + commission + "," + trade);
        writer.close();
    }

    protected String generateGoogleAuthenticatorCode() {
        String otpKeyStr = prop.getProperty("secret");
        Totp totp = new Totp(otpKeyStr);
        return totp.now();
    }

    protected void waitForElementTobeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (Exception e) {}
    }
}
