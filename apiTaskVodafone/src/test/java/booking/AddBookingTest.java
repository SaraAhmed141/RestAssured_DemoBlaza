package booking;

import com.vodafone.dataproviderobjects.excel.BooksData;
import com.vodafone.utilities.DataProviderSource;
import io.restassured.response.Response;
import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.vodafone.controller.BooksController.addBookingRequest;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AddBookingTest {

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((BooksData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "test" : testCaseName);
    }

    @Test(alwaysRun = true, dataProvider = "BooksDataFeed", dataProviderClass = DataProviderSource.class)
    public void testAddBooking(BooksData data) {

        String requestBody = "{\n" +
                " \"firstname\" : \"Jim\",\n" +
                " \"lastname\" : \"Brown\",\n" +
                " \"totalprice\" : 111,\n" +
                " \"depositpaid\" : true,\n" +
                " \"bookingdates\" : {\n" +
                " \"checkin\" : \"2018-01-01\",\n" +
                " \"checkout\" : \"2019-01-01\"\n" +
                " },\n" +
                " \"additionalneeds\" : \"Breakfast\"\n" +
                "}";


        Response addBookingResponse = addBookingRequest(requestBody);

// Validate response
        addBookingResponse.then().assertThat().statusCode(Integer.parseInt(data.getStatusCode()))
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("Jim"))
                .body("booking.lastname", equalTo("Brown"))
                .body("booking.totalprice", equalTo(111))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                .body("booking.additionalneeds", equalTo("Breakfast"));


        addBookingResponse.prettyPrint();
    }


}
