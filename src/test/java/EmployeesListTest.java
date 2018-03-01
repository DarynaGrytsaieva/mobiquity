import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class EmployeesListTest extends Fixture {

    private static final String USERNAME = "Luke";

    @BeforeMethod
    public void setUp() {
        cafe.loginPage.openPage();
        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("login"));

        cafe.loginPage.fillUsernameField("Luke");
        cafe.loginPage.fillPasswordfield("Skywalker");
        cafe.loginPage.pressLoginButton();

        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("employees"));
        assertTrue(cafe.createEmployeePage.isUserLoggedIn(USERNAME));
    }

    @Test
    public void shouldDisplayEmployeeList() {
        //given
        assertTrue(cafe.employeesListPage.isCreateButtonActive());
        assertFalse(cafe.employeesListPage.isEditButtonActive());
        assertFalse(cafe.employeesListPage.isDeleteButtonActive());

        //when
        cafe.employeesListPage.getFirstEmployee().click();

        //then
        assertTrue(cafe.employeesListPage.isCreateButtonActive());
        assertTrue(cafe.employeesListPage.isEditButtonActive());
        assertTrue(cafe.employeesListPage.isDeleteButtonActive());

    }

    @Test
    public void shouldDismissDeleteFromEmployeeList() {
        //given
        WebElement employee = cafe.employeesListPage.getFirstEmployee();
        employee.click();
        String name = employee.getText();

        //when
        cafe.employeesListPage.pressDeleteButton();
        assertTrue(cafe.employeesListPage.getAlertText().equals("Are you sure you want to delete " + name + "?"));
        cafe.employeesListPage.dismissAlert();

        //then
        assertTrue(cafe.employeesListPage.hasEmployee(name));

    }

    @Test
    public void shouldDeleteFromEmployeeList() {
        //given
        WebElement employee = cafe.employeesListPage.getFirstEmployee();
        employee.click();
        String name = employee.getText();

        //when
        cafe.employeesListPage.pressDeleteButton();
        assertTrue(cafe.employeesListPage.getAlertText().equals("Are you sure you want to delete " + name + "?"));
        cafe.employeesListPage.acceptAlert();

        //then
        assertTrue(cafe.employeesListPage.hasNoText(By.xpath("//*[@id='employee-list']/li[1]"), name));
    }

    @AfterMethod
    public static void tearDown() {
        cafe.employeesListPage.logout();
        assertTrue(cafe.employeesListPage.isUserLoggedOut());

    }

}
