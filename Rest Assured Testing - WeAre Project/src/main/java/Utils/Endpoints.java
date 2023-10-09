package Utils;

public class Endpoints {

    public static final String BASE_URL = "http://localhost:8081";

    public static final String USERS_ENDPOINT = "/api/users/";

    public static final String GET_ALL_POSTS_ENDPOINT = "/api/post/";

    public static final String CREATЕ_POST_ENDPOINT = "/api/post/auth/creator";

    public static final String GET_USERS_BY_NAME_ENDPOINT = "/api/users";

    public static final String AUTHENTICATE_ENDPOINT = "/authenticate";
    public static final String EDIT_POST_ENDPOINT = "/api/post/auth/editor?postId=";

    public static final String UPDATE_ENDPOINT = "/api/users/auth/";
    public static final String PERSONAL_ENDPOINT = "/api/users/auth/{{userIdSender}}/personal";
    public static final String REQUEST_ENDPOINT = "/api/auth/request?principal={{firstNameSender}}";
    public static final String USER_REQUESTS_ENDPOINT = "/api/auth/users/{{userIdReceiver}}/request/";
    public static final String APPROVE_REQUEST_ENDPOINT = "/api/auth/users/{{userIdReceiver}}/request/approve?requestId={{requestId}}";
    public static final String SKILL_ENDPOINT = "/api/skill";
    public static final String SKILL_CREATE_ENDPOINT = SKILL_ENDPOINT + "/create";
    public static final String SKILL_GET_ONE_ENDPOINT = SKILL_ENDPOINT + "/getOne";
    public static final String SKILL_UPDATE_ENDPOINT = SKILL_ENDPOINT + "/edit";
    public static final String SKILL_DELETE_ENDPOINT = SKILL_ENDPOINT + "/delete";


}
