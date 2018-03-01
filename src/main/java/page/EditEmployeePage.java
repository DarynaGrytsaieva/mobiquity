package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditEmployeePage extends Page {

    private static final By FIRST_NAME_INPUT = By.xpath("//*[contains(text(), 'First name')]/../input");
    private static final By LAST_NAME_INPUT = By.xpath("//*[contains(text(), 'Last name')]/../input");
    private static final By START_DATE_INPUT = By.xpath("//*[contains(text(), 'Start date')]/../input");
    private static final By EMAIL_INPUT = By.xpath("//*[contains(text(), 'Email')]/../input");

    private static final By BACK_BUTTON = By.className("bBack");
    private static final By UPDATE_BUTTON = By.xpath("//*[@class='formFooter']/button[contains(text(), 'Update')]");
    private static final By DELETE_BUTTON = By.xpath("//*[@class='formFooter']/*[contains(text(), 'Delete')]");


    public EditEmployeePage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    public String getStartDate() {
        return getElement(START_DATE_INPUT).getAttribute("value");
    }

    public String getEmail() {
        return getElement(EMAIL_INPUT).getAttribute("value");
    }

    public void updateFirstNamefield(String value) {
        WebElement input = getElement(FIRST_NAME_INPUT);
        input.clear();
        input.sendKeys(value);
    }

    public void updateLastNamefield(String value) {
        WebElement input = getElement(LAST_NAME_INPUT);
        input.clear();
        input.sendKeys(value);

    }

    public void updateStartDatefield(String value) {
        WebElement input = getElement(START_DATE_INPUT);
        input.clear();
        input.sendKeys(value);

    }

    public void updateEmailfield(String value) {
        WebElement input = getElement(EMAIL_INPUT);
        input.clear();
        input.sendKeys(value);

    }

    public void pressBackButton() {
        getElement(BACK_BUTTON).click();

    }

    public void pressUpdateButton() {
        getElement(UPDATE_BUTTON).click();

    }

    public void pressDeleteButton() {
        getElement(DELETE_BUTTON).click();

    }

}


