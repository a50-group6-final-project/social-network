package Utils;

public class JSONRequests{
    public static final String CONTENT_POST = "I am looking for a painter";
    public static final String CREATE_POST_BODY = "{\n" +
            "  \"content\": \"" + CONTENT_POST + "\",\n" +
            "  \"picture\": \"Test\",\n" +
            "  \"public\": true\n" +
            "}";
    public static final String UPDATED_CONTENT = "I am looking for a plump";
    public static final String EDIT_POST_BODY = "{\n" +
            "  \"content\": \"" + UPDATED_CONTENT + "\",\n" +
            "  \"picture\": \"Test\",\n" +
            "  \"public\": true\n" +
            "}";
    public static String searchName = "John Atanasov";
    public static final String GET_USER_BY_NAME_BODY = "{\n" +
            "  \"index\": 0,\n" +
            "  \"next\": true,\n" +
            "  \"searchParam1\": \"\",\n" +
            "  \"searchParam2\": \"" + searchName + "\",\n" +
            "  \"size\": 1\n" +
            "}";
    public static String updatedName = "Maria";
    public static String UPGRADE_USER_PERSONAL_PROFILE_BODY(int currentUserId){
        return String.format("{\n" +
                "    \"id\": %d,\n" +
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
                "}", currentUserId);
    }


    public static String REGISTRATION_BODY(String username, String email) {
        return String.format("{\n" +
                "    \"category\": {\n" +
                "        \"id\": 100,\n" +
                "        \"name\": \"All\"\n" +
                "    },\n" +
                "    \"confirmPassword\": \"Project.10\",\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"Project.10\",\n" +
                "    \"username\": \"%s\"\n" +
                "}", email, username);
    }

}

