import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class EditEmployeeTest extends Fixture {
    private static final String USERNAME = "Luke";


    @BeforeClass
    public void setUp() {
        cafe.loginPage.openPage();

        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("login"));
        cafe.loginPage.fillUsernameField("Luke");
        cafe.loginPage.fillPasswordfield("Skywalker");
        cafe.loginPage.pressLoginButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees"));
        assertTrue(cafe.createEmployeePage.isUserLoggedIn(USERNAME));

    }

    @Test
    public void shouldDismissEdit() {
        //given
        WebElement employee = cafe.employeesListPage.getFirstEmployee();
        employee.click();
        String name = employee.getText();
        cafe.employeesListPage.pressEditButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("/edit"));

        //when
        cafe.editEmployeePage.pressBackButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees"));

        //then
        assertTrue(cafe.employeesListPage.hasEmployee(name));


    }

    @Test(dataProvider = "getValidData")
    public void shouldEditEmployee(String newFirstName, String newLastName, String newStartDate, String newEmail) {
        //given
        cafe.employeesListPage.getFirstEmployee().click();
        cafe.employeesListPage.pressEditButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("/edit"));

        //when
        cafe.editEmployeePage.updateFirstNamefield(newFirstName);
        cafe.editEmployeePage.updateLastNamefield(newLastName);
        cafe.editEmployeePage.updateStartDatefield(newStartDate);
        cafe.editEmployeePage.updateEmailfield(newEmail);

        cafe.editEmployeePage.pressUpdateButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees"));

        //then
        assertTrue(cafe.employeesListPage.hasEmployee(newFirstName + " " + newLastName));

        cafe.employeesListPage.getEmployee(newFirstName + " " + newLastName).click();
        cafe.employeesListPage.pressEditButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("/edit"));

        assertTrue(cafe.editEmployeePage.getStartDate().equals(newStartDate));
        assertTrue(cafe.editEmployeePage.getEmail().equals(newEmail));

        //cleanup
        cafe.editEmployeePage.pressBackButton();


    }

    @Test
    public void shouldPartiallyEditEmployeeWithData() {
        //given
        cafe.employeesListPage.getFirstEmployee().click();
        cafe.employeesListPage.pressEditButton();
        String newName = randomizeName("Peter");
        String newLastName = "Pan";

        //when
        cafe.editEmployeePage.updateFirstNamefield(newName);
        cafe.editEmployeePage.updateLastNamefield(newLastName);
        cafe.editEmployeePage.pressUpdateButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees"));

        //then
        assertTrue(cafe.employeesListPage.hasEmployee(newName + " " + newLastName));


    }

    @Test
    public void shouldDeleteEmployee() {
        //given
        WebElement employee = cafe.employeesListPage.getFirstEmployee();
        employee.click();
        String name = employee.getText();
        cafe.employeesListPage.pressEditButton();

        //when
        cafe.editEmployeePage.pressDeleteButton();

        //then
        assertTrue(cafe.employeesListPage.getAlertText().equals("Are you sure you want to delete " + name + "?"));
        cafe.editEmployeePage.acceptAlert();
        WebElement newFirst = cafe.employeesListPage.getFirstEmployee();
        assertFalse(newFirst.getText().equals(name));
    }

    @AfterClass
    public static void tearDown() {
        cafe.employeesListPage.logout();
        assertTrue(cafe.employeesListPage.isUserLoggedOut());

    }


    @DataProvider
    public Object[][] getValidData() {
        return new Object[][]{{randomizeName("John"), "Lennon", "2000-10-10", "john_l@gmail.com"}};

    }


}
