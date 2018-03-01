package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Page {

    private String page;
    public WebDriver driver;
    private Wait<WebDriver> wait;

    public Page(String page, WebDriver driver) {
        this.page = page;
        this.driver = driver;
        wait = new FluentWait<>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new FluentWait<>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public boolean openPage() {
        driver.get(page);
        return true;
    }

    public WebElement getElement(By selector) {
        return wait.until(driver -> driver.findElement(selector));
    }

    public boolean isElementPresent(By selector) {
        return wait.until(driver -> driver.findElement(selector).isDisplayed());
    }

    public boolean isElementAbsent(By selector) {
        return wait.until(driver -> driver.findElements(selector).isEmpty());
    }

    public boolean hasText(By selector, String text) {
        return wait.until(driver -> text.equals(driver.findElement(selector).getText()));
    }

    public boolean hasNoText(By selector, String text) {
        return wait.until(driver -> !text.equals(driver.findElement(selector).getText()));
    }

    public boolean isUserLoggedIn(String userName) {
        WebElement greetingsElement = getElement(By.cssSelector("div > p#greetings"));
        return greetingsElement.getText().contains("Hello " + userName);
    }

    public boolean isUserLoggedOut() {
        return isElementPresent(By.xpath("//button[contains(text(), 'Login')]"));
    }


    public void acceptAlert() {
        getAlert().accept();

    }

    public void dismissAlert() {
        getAlert().dismiss();
    }

    public String getAlertText() {
        return getAlert().getText();
    }

    private Alert getAlert() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoAlertPresentException.class);

        return wait.until(driver -> driver.switchTo().alert());
    }


    public void logout() {
        String cookieName = "_CafeTownsend-Angular-Rails_session";
        String loggedInCookie = driver.manage().getCookieNamed(cookieName).getValue();

        getElement(By.xpath("//*[contains(text(), 'Logout')]")).click();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS);

        wait.until(driver -> !driver.manage().getCookieNamed(cookieName).getValue().equals(loggedInCookie));
        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("login"));
        isUserLoggedOut();

    }
}