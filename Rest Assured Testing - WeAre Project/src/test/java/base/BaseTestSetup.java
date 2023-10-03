package base;

import io.restassured.http.Cookies;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class BaseTestSetup {

    public static Cookies cookies;


    public static void AssertResponse(Response response) {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, "Incorrect status code.");
        if (statusCode == SC_OK) {
            System.out.println("Test passed.");
        }
    }

}
