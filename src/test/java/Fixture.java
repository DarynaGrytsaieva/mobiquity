import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import page.Cafe;

import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Fixture {

    public static WebDriver driver;
    public static Wait<WebDriver> wait;
    public static Cafe cafe;

    @BeforeSuite(alwaysRun = true)
    public void setEnv() {
        System.setProperty(
                "webdriver.chrome.driver",
                "webdriver/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);

        cafe = new Cafe(driver);
    }

    @AfterSuite(alwaysRun = true)
    public void resetEnv() {
        driver.quit();
    }

    protected String randomizeName(String name) {
        return name + " " + new Random().nextInt();
    }
}
