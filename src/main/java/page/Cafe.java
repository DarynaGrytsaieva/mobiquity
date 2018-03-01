package page;

import org.openqa.selenium.WebDriver;

public class Cafe {
    public LoginPage loginPage;
    public EmployeesListPage employeesListPage;
    public CreateEmployeePage createEmployeePage;
    public EditEmployeePage editEmployeePage;

    public Cafe(WebDriver driver) {
        loginPage = new LoginPage(driver);
        employeesListPage = new EmployeesListPage(driver);
        createEmployeePage = new CreateEmployeePage(driver);
        editEmployeePage = new EditEmployeePage(driver);
    }
}
