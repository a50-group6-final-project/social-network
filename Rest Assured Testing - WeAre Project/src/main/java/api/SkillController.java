package api;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Skill;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.SKILL_CREATE_ENDPOINT;

public class SkillController {

public static Response createSkillRequest(Skill skill) {
    String bodySkillString = Serializer.convertObjectToJsonString(skill);
    Response response = RestAssured.given().baseUri(BASE_URL)
            .contentType("application/json")
            .body(bodySkillString)
            .when()
            .post(SKILL_CREATE_ENDPOINT)
            .then().log().body().extract().response();
    return null;
}
}
