package Utils;

public class JSONRequests {

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
    public static final String GET_USER_BY_USERNAME_BODY = "{\n" +
            "  \"index\": 0,\n" +
            "  \"next\": true,\n" +
            "  \"searchParam1\": \"\",\n" +
            "  \"searchParam2\": \"\",\n" +
            "  \"size\": 1\n" +
            "}";

}