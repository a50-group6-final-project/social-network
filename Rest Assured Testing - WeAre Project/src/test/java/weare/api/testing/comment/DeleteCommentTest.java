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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DeleteCommentTest extends BaseTestSetup {
    String uniqueContent;
    @BeforeClass
    public void setup() {

        if(!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
        }

        if(isDeletedPost){
            uniqueContent = DataGenerator.generateUniqueContentPost();
            createPost = ModelGenerator.generatePostModel(uniqueContent);
            Response response = PostController.createPost(cookies, createPost);

            createdPost = response.as(PostModel.class);
            assertEquals(createdPost.content, uniqueContent, "Content does not match.");
            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }
        if(isCommentDeleted){
            createComment = ModelGenerator.generateCommentModel(DataGenerator.generateUniqueContentPost(), postId, userId);

            Response response = CommentController.createComment(cookies, createComment);
            isResponse200(response);

            createdComment = response.as(CommentModel.class);
            System.out.println("Successfully created a new comment with Id" + " " + createdComment.commentId);
            isCommentDeleted = false;
        }
    }

    @Test
    public void deleteComment_Successful() throws InterruptedException {
        Response response = CommentController.deleteComment(cookies, createdComment.commentId);
        isResponse200(response);
        System.out.println("Successfully deleted comment with Id" + " " + commentId + " " + "successfully.");
        isCommentDeleted = true;
        Thread.sleep(1000);
    }
}
