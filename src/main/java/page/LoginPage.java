package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {
    private static final String URL = "http://cafetownsend-angular-rails.herokuapp.com/login";
    private static final By USERNAME_INPUT = By.xpath("//*[contains(text(), 'Username*')]/../input");
    private static final By PASSWORD_INPUT = By.xpath("//*[contains(text(), 'Password*')]/../input");
    private static final By LOGIN_BUTTON = By.cssSelector("#login-form button[type='submit']");

    LoginPage(WebDriver driver) {
        super(URL, driver);
        this.driver = driver;
    }

    public void fillUsernameField(String value) {
        getElement(USERNAME_INPUT).sendKeys(value);
    }

    public void fillPasswordfield(String value) {
        getElement(PASSWORD_INPUT).sendKeys(value);
    }

    public void pressLoginButton() {
        getElement(LOGIN_BUTTON).click();
    }

}
