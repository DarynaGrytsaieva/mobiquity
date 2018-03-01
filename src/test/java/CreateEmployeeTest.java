import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CreateEmployeeTest extends Fixture {
    private static final String USERNAME = "Luke";

    @BeforeClass
    public void setUp() {
        cafe.loginPage.openPage();
        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("login"));
        cafe.loginPage.fillUsernameField("Luke");
        cafe.loginPage.fillPasswordfield("Skywalker");
        cafe.loginPage.pressLoginButton();
        assertTrue(cafe.createEmployeePage.isUserLoggedIn(USERNAME));
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees"));
    }

    @Test(dataProvider = "getValidData")
    public void shouldDismissCreateNewEmployee(String firstName, String lastName, String startDate, String email) {
        //given
        cafe.employeesListPage.pressCreateButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees/new"));
        cafe.createEmployeePage.fillFirstNamefield(firstName);
        cafe.createEmployeePage.fillLastNamefield(lastName);
        cafe.createEmployeePage.fillStartDatefield(startDate);
        cafe.createEmployeePage.fillEmailfield(email);

        //when
        cafe.createEmployeePage.pressCancelButton();

        //then
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees"));
        assertTrue(cafe.employeesListPage.hasNoEmployee(firstName + " " + lastName));

    }

    @Test(dataProvider = "getValidData")
    public void shouldCreateNewEmployee(String firstName, String lastName, String startDate, String email) {
        //given
        cafe.employeesListPage.pressCreateButton();
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees/new"));
        assertTrue(cafe.createEmployeePage.isUserLoggedIn(USERNAME));

        //when
        cafe.createEmployeePage.fillFirstNamefield(firstName);
        cafe.createEmployeePage.fillLastNamefield(lastName);
        cafe.createEmployeePage.fillStartDatefield(startDate);
        cafe.createEmployeePage.fillEmailfield(email);
        cafe.createEmployeePage.pressAddButton();

        //then
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees"));
        assertTrue(cafe.employeesListPage.hasEmployee(firstName + " " + lastName));


    }


    @Test(dataProvider = "getEmptyData")
    public void shouldNotCreateNewEmployeeWithMissingData(String firstName, String lastName, String startDate, String email) {
        //given
        cafe.employeesListPage.pressCreateButton();
        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("employees/new"));
        assertTrue(cafe.createEmployeePage.isUserLoggedIn(USERNAME));

        //when
        cafe.createEmployeePage.fillFirstNamefield(firstName);
        cafe.createEmployeePage.fillLastNamefield(lastName);
        cafe.createEmployeePage.fillStartDatefield(startDate);
        cafe.createEmployeePage.fillEmailfield(email);
        cafe.createEmployeePage.pressAddButton();

        //then
        assertTrue(driver.getCurrentUrl().endsWith("employees/new"));

        //cleanup
        cafe.createEmployeePage.pressCancelButton();

    }


    @Test(dataProvider = "getInvalidDate")
    public void shouldNotCreateNewEmployeeWithInvalidDate(String firstName, String lastName, String startDate, String email) {
        //given
        cafe.employeesListPage.pressCreateButton();
        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("employees/new"));
        assertTrue(cafe.createEmployeePage.isUserLoggedIn(USERNAME));

        //when
        cafe.createEmployeePage.fillFirstNamefield(firstName);
        cafe.createEmployeePage.fillLastNamefield(lastName);
        cafe.createEmployeePage.fillStartDatefield(startDate);
        cafe.createEmployeePage.fillEmailfield(email);
        cafe.createEmployeePage.pressAddButton();

        //then
        assertTrue(cafe.createEmployeePage.getAlertText().equals("Error trying to create a new employee: {\"start_date\":[\"can't be blank\"]})"));
        cafe.createEmployeePage.acceptAlert();
        assertTrue(driver.getCurrentUrl().endsWith("employees/new"));

        //cleanup
        cafe.createEmployeePage.pressCancelButton();

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

    @DataProvider
    public Object[][] getEmptyData() {
        return new Object[][]{
                {"", "Smith", "2000-10-10", "john_s@gmail.com"},
                {"John", "", "2000-10-10", "john_s@gmail.com"},
                {"John", "Smith", "", "john_s@gmail.com"},
                {"John", "Smith", "2000-10-10", ""}


        };

    }

    @DataProvider
    public Object[][] getInvalidDate() {
        return new Object[][]{
                {"John", "Smith", "123", "john_s@gmail.com"},
                {"John", "Smith", "1999-00-00", "john_s@gmail.com"}
        };

    }


}
