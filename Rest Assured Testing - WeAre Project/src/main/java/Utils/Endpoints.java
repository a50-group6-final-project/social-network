package Utils;

public class Endpoints {

    public static final String BASE_URL = "http://localhost:8081";

    public static final String USERS_ENDPOINT = "/api/users/";

    public static final String GET_USERS_BY_NAME = "/api/users";

    public static final String AUTHENTICATE_ENDPOINT = "/authenticate";
    public static final String PERSONAL_ENDPOINT = "/api/users/auth/{{userIdSender}}/personal";
    public static final String REQUEST_ENDPOINT = "/api/auth/request?principal={{firstNameSender}}";
    public static final String USER_REQUESTS_ENDPOINT = "/api/auth/users/{{userIdReceiver}}/request/";
    public static final String APPROVE_REQUEST_ENDPOINT = "/api/auth/users/{{userIdReceiver}}/request/approve?requestId={{requestId}}";



}
