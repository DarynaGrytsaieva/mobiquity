package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateEmployeePage extends Page {

    private static final By FIRST_NAME_INPUT = By.xpath("//*[contains(text(), 'First name')]/../input");
    private static final By LAST_NAME_INPUT = By.xpath("//*[contains(text(), 'Last name')]/../input");
    private static final By START_DATE_INPUT = By.xpath("//*[contains(text(), 'Start date')]/../input");
    private static final By EMAIL_INPUT = By.xpath("//*[contains(text(), 'Email')]/../input");
    private static final By CANCEL_BUTTON = By.className("bCancel");
    private static final By ADD_BUTTON = By.xpath("//*[@class='formFooter']/button[contains(text(), 'Add')]");


    public CreateEmployeePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void fillFirstNamefield(String value) {
        getElement(FIRST_NAME_INPUT).sendKeys(value);
    }

    public void fillLastNamefield(String value) {
        getElement(LAST_NAME_INPUT).sendKeys(value);

    }

    public void fillStartDatefield(String value) {
        getElement(START_DATE_INPUT).sendKeys(value);

    }

    public void fillEmailfield(String value) {
        getElement(EMAIL_INPUT).sendKeys(value);

    }

    public void pressCancelButton() {
        getElement(CANCEL_BUTTON).click();

    }

    public void pressAddButton() {
        getElement(ADD_BUTTON).click();

    }

}
