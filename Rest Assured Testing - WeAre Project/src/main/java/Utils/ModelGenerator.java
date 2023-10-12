package Utils;

import models.Category;
import models.PostModel;
import models.Skill;

public class ModelGenerator {


    public static Skill generateSkillModel(int categoryId) {
        Category category = new Category();
        category.id = categoryId;

        Skill skill = new Skill();
        skill.category = category;
        skill.skill = "Test Skill" + System.currentTimeMillis();
        skill.skillId = 0;
        return skill;
    }


    public static PostModel generatePostModel(String uniqueContent){
        PostModel createPost = new PostModel();
        createPost.content = uniqueContent;
        createPost.picture = "";
        createPost.mypublic = true;

        return createPost;
    }
}
