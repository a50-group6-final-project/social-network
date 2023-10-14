package weare.api.testing.comment;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.CommentController;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.CommentModel;
import models.PostModel;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class FindAllCommentsOfAPost extends BaseTestSetup {
    String uniqueContent;

    @BeforeClass
    public void setup() {

        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
        }

        if (isDeletedPost) {
            uniqueContent = DataGenerator.generateUniqueContentPost();
            createPost = ModelGenerator.generatePostModel(uniqueContent);
            Response response = PostController.createPost(cookies, createPost);

            createdPost = response.as(PostModel.class);
            assertEquals(createdPost.content, uniqueContent, "Content does not match.");
            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }
        if (isCommentDeleted) {
            createComment = ModelGenerator.generateCommentModel(DataGenerator.generateUniqueContentPost(), postId, userId);

            Response response = CommentController.createComment(cookies, createComment);
            isResponse200(response);

            createdComment = response.as(CommentModel.class);
            System.out.println("Successfully created a new comment with Id" + " " + createdComment.commentId);
            isCommentDeleted = false;
        }
    }


    @Test
    public void findAllCommentsOfAPost_Successful() {
        Response response = CommentController.findAllCommentsOfAPost(cookies, postId);

        System.out.println(response.asString());
        isResponse200(response);

        CommentModel[] commentsList = response.as(CommentModel[].class);
        Assert.assertTrue(commentsList.length >= 1, "The array size is more than or equal to 1");
        Assert.assertNotNull(commentsList[0].content, "Content is null");
        System.out.println("Successfully fetched all comments of post with Id" + " " + postId + " " + "successfully.");
    }

    @AfterClass
    public void tearDown() {
        if (!isDeletedPost){
            PostController.deletePost(cookies, createdPost.postId);
            System.out.println("Successfully delete a post with Id" + " " + createdPost.postId);
            isDeletedPost = true;
        }
        if(!isCommentDeleted){
            CommentController.deleteComment(cookies, createdComment.commentId);
            System.out.println("Successfully delete a comment with Id" + " " + createdPost.postId);
            isCommentDeleted = true;
        }
    }
}
