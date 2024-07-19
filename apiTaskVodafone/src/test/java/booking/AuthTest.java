package booking;

import com.vodafone.dataproviderobjects.excel.BooksData;
import com.vodafone.utilities.DataProviderSource;
import io.restassured.response.Response;
import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.vodafone.controller.BooksController.registerRequest;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class AuthTest {

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((BooksData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "test" : testCaseName);
    }

    @Test(alwaysRun = true, dataProvider = "BooksDataFeed", dataProviderClass = DataProviderSource.class)
    public void testAuthentication(BooksData data) {


        String username = "admin" ;
        String password = "password123";
        String requestBody = "{\n" +
                " \"username\" : \"" + username + "\",\n" +
                " \"password\" : \"" + password + "\"\n" +
                "}";

        Response registerResponse = registerRequest(requestBody);
        registerResponse.then().assertThat().statusCode(200);
        registerResponse.then().assertThat().body("token", not(emptyOrNullString()));

        registerResponse.prettyPrint();
    }
}
