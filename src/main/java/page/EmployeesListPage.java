package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmployeesListPage extends Page {
    private static final By CREATE_BUTTON = By.id("bAdd");
    private static final By EDIT_BUTTON = By.id("bEdit");
    private static final By DELETE_BUTTON = By.id("bDelete");
    public static final By FIRST_EMPLOYEE = By.xpath("//*[@id='employee-list']/li[1]");


    public EmployeesListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void pressCreateButton() {
        getElement(CREATE_BUTTON).click();

    }

    public void pressEditButton() {
        getElement(EDIT_BUTTON).click();

    }

    public void pressDeleteButton() {
        getElement(DELETE_BUTTON).click();

    }

    public WebElement getFirstEmployee() {
        return getElement(FIRST_EMPLOYEE);

    }

    public WebElement getEmployee(String name) {
        return getElement(By.xpath("//*[@id='employee-list']/li[contains(text(), '" + name + "')]"));
    }


    public boolean hasEmployee(String name) {
        return isElementPresent(By.xpath("//*[@id='employee-list']/li[contains(text(), '" + name + "')]"));
    }

    public boolean hasNoEmployee(String name) {
        return isElementAbsent(By.xpath("//*[@id='employee-list']/li[contains(text(), '" + name + "')]"));
    }


    public boolean isCreateButtonActive() {
        return !getElement(CREATE_BUTTON).getAttribute("class").contains("disabled");
    }

    public boolean isEditButtonActive() {
        return !getElement(EDIT_BUTTON).getAttribute("class").contains("disabled");
    }

    public boolean isDeleteButtonActive() {
        return !getElement(DELETE_BUTTON).getAttribute("class").contains("disabled");
    }


}
