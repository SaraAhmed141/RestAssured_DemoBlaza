package booking;

import com.vodafone.dataproviderobjects.excel.BooksData;
import com.vodafone.utilities.DataProviderSource;
import io.restassured.response.Response;
import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.vodafone.controller.BooksController.getBookingsRequest;
import static org.hamcrest.Matchers.greaterThan;

public class GetAllBookingsTest  {
    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((BooksData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "test" : testCaseName);
    }

    @Test(alwaysRun = true, dataProvider = "BooksDataFeed", dataProviderClass = DataProviderSource.class)
    public void testGetAllBookings(BooksData data) {


        Response getBookingsResponse = getBookingsRequest();

// Validate response
        getBookingsResponse.then().assertThat().statusCode(200)
                .body("$.size()", greaterThan(0));
        System.out.println("Response Body: " + getBookingsResponse.body().asString());
    }

}
