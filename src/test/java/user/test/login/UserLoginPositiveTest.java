package user.test.login;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import com.stellarburger.user.Credentials;
import com.stellarburger.user.User;
import com.stellarburger.user.UserClient;
import com.stellarburger.user.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserLoginPositiveTest {
    private UserClient userClient;
    private User user;
    private String accessToken;


    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.createDefaultUser();
    }

    @After
    public void cleanUp() {
        userClient.delete(accessToken);
    }

    @Test
    @DisplayName("Check response when user is logged in")
    public void userCanBeLoginAndCheckResponse() {
        ValidatableResponse responseCreate = userClient.create(user);
        ValidatableResponse responseLogin = userClient.login(Credentials.from(user));
        int actualSC = responseLogin.extract().statusCode();
        boolean isUserLoggedIn = responseCreate.extract().path("success");
        accessToken = responseLogin.extract().path("accessToken");
        assertEquals("Status Code incorrect", actualSC, SC_OK);
        assertTrue("Expected true", isUserLoggedIn);
    }
}
