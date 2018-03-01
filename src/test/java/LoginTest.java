import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends Fixture {
    private static final String USERNAME = "Luke";


    @Test
    public void shouldLoginAsAuthorizedUser() {
        //given
        cafe.loginPage.openPage();
        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("login"));

        //when
        cafe.loginPage.fillUsernameField(USERNAME);
        cafe.loginPage.fillPasswordfield("Skywalker");
        cafe.loginPage.pressLoginButton();

        //then
        wait.until(driver -> driver.getCurrentUrl().endsWith("employees"));
        assertTrue(cafe.loginPage.isUserLoggedIn(USERNAME));

        cafe.employeesListPage.logout();
        assertTrue(cafe.employeesListPage.isUserLoggedOut());
    }

    @Test(dataProvider = "getEmptyCredentials")
    public void shouldNotLoginWithMissingCredentials(String username, String password) {
        //given
        cafe.loginPage.openPage();
        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("login"));

        //when
        cafe.loginPage.fillUsernameField(username);
        cafe.loginPage.fillPasswordfield(password);
        cafe.loginPage.pressLoginButton();

        //then
        assertTrue(cafe.loginPage.isUserLoggedOut());
    }

    @Test(dataProvider = "getInvalidCredentials")
    public void shouldNotLoginWithInvalidCredentials(String username, String password) {
        //given
        cafe.loginPage.openPage();
        wait.until(webDriver -> webDriver.getCurrentUrl().endsWith("login"));

        //when
        cafe.loginPage.fillUsernameField(username);
        cafe.loginPage.fillPasswordfield(password);
        cafe.loginPage.pressLoginButton();

        //then
        assertTrue(cafe.loginPage.hasText(By.cssSelector("p.error-message"), "Invalid username or password!"));
        assertTrue(cafe.loginPage.isUserLoggedOut());
    }

    @DataProvider
    public Object[][] getEmptyCredentials() {
        return new Object[][]{{"Luke", ""}, {"", "Skywalker"}};
    }

    @DataProvider
    public Object[][] getInvalidCredentials() {
        return new Object[][]{{"abc", "ab123c"}, {"<script>alert('DPA);</script>", "<script>alert('DPA);</script>"}};
    }

}

