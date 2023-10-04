package Utils;

public class JSONRequests {
    public static String searchName="John";
    public static String updatedName = "Maria";
    public static int userID = 144;

    public static final String CONTENT_POST = "I am looking for a painter";

    public static final String REGISTRATION_BODY = "{\n" +
            "    \"category\": {\n" +
            "        \"id\": 100,\n" +
            "        \"name\": \"All\"\n" +
            "    },\n" +
            "    \"confirmPassword\": \"Project.10\",\n" +
            "    \"email\": \"test.account.24@abv.bg\",\n" +
            "    \"password\": \"Project.10\",\n" +
            "    \"username\": \"Grandmama\"\n" +
            "}";
    public static final String GET_USER_BY_NAME_BODY = "{\n" +
            "  \"index\": 0,\n" +
            "  \"next\": true,\n" +
            "  \"searchParam1\": \"\",\n" +
            "  \"searchParam2\": \"" + searchName + "\",\n" +
            "  \"size\": 1\n" +
            "}";





    public static final String UPGRADE_USER_PERSONAL_PROFILE_BODY = "{\n" +
            "    \"id\": " + userID + ",\n" +
            "    \"firstName\": \"" + updatedName + "\",\n" +
            "    \"lastName\": \"Ivanova\",\n" +
            "    \"gender\": \"MALE\",\n" +
            "    \"birthYear\": \"1955-12-09\",\n" +
            "    \"personalReview\": null,\n" +
            "    \"location\": {\n" +
            "        \"city\": {\n" +
            "            \"country\": {},\n" +
            "            \"id\": 23\n" +
            "        }\n" +
            "    }\n" +
            "}";


    public static final String CREATE_POST_BODY = "{\n" +
            "  \"content\": \"" + CONTENT_POST + "\",\n" +
            "  \"picture\": \"Test\",\n" +
            "  \"public\": true\n" +
            "}";

}

