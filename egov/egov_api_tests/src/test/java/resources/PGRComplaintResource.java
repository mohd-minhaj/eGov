package resources;

import com.jayway.restassured.response.Response;
import utils.Properties;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class PGRComplaintResource {

    public Response createComplaint(String json) {

        new APILogger().log("Creating a PGR request  -- ");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.serverUrl + Properties.complaintUrl);

        return response;
    }

    public Response getPGRComplaint(String serviceRequestId) {

        new APILogger().log("Getting a PGR complaint request with -- " + serviceRequestId);

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("api_id", "org.egov.pgr")
                .header("ver", "1.0")
                .header("ts", "28-03-2016 10:22:33")
                .header("action", "GET")
                .header("did", "4354648646")
                .header("msg_id", "654654")
                .header("requester_id", "61")
                .header("auth_token", "null")
                .when()
                .get(Properties.serverUrl + Properties.getPGRComplaintUrl + serviceRequestId);

        return response;
    }
}
