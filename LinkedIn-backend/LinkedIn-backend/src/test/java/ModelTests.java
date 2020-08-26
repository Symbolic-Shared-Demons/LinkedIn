import com.ex.models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

public class ModelTests {

    private User testUser;
    private Category testCategory;
    private Post testPost;

    @Before
    public void init(){
        testUser = new User();
        testCategory = new Category();
        testPost = new Post();

    }

    @Test
    public void testSetAndGet(){
        testCategory.setId(1);
        testCategory.setTitle("Developer");
        HashSet cats = new HashSet<Category>();
        cats.add(testCategory);
        testPost.setPoster(testUser);
        testPost.setPostCat(testCategory);
        testPost.setDesc("Developer wanted");
        testPost.setId(1);
        testUser.setUserCats(cats);
        testUser.setEmail("example@gmail.com");
        testUser.setFirstName("tester");
        testUser.setLastName("user");
        testUser.setPassword("abc123");
        testUser.setUsername("code4life");
        testUser.setId(1);
        testPost.setPoster(testUser);
        HashSet appliedUsers = new HashSet<User>();
        appliedUsers.add(testUser);
        testPost.setAppliedUsers(appliedUsers);
        HashSet posts = new HashSet<Post>();
        posts.add(testPost);
        HashSet appliesPosts = new HashSet<Post>();
        appliesPosts.add(testPost);
        testUser.setAppliedPosts(appliesPosts);
        testUser.setUserPosts(posts);

        Assert.assertEquals("Category id was wrong", 1, testCategory.getId());
        Assert.assertEquals("Category title was wrong", "Developer", testCategory.getTitle());
        Assert.assertEquals("Post id was wrong", 1, testPost.getId());
        Assert.assertTrue("Post message was wrong", "Developer wanted".equals(testPost.getDesc()));
        Assert.assertEquals("Post poster was wrong", testUser, testPost.getPoster());
        Assert.assertTrue("Post applied was wrong", testPost.getAppliedUsers().contains(testUser));
        Assert.assertEquals("Post category was wrong", testCategory, testPost.getPostCat());
        Assert.assertEquals("User id was wrong", 1, testUser.getId());
        Assert.assertTrue("User name was wrong", (testUser.getFirstName()+ " " + testUser.getLastName()).equals("tester user"));
        Assert.assertTrue("User email was wrong", "example@gmail.com".equals(testUser.getEmail()));
        Assert.assertTrue("User password was wrong", "abc123".equals(testUser.getPassword()));
        Assert.assertTrue("User categories was wrong", testUser.getUserCats().contains(testCategory));
        Assert.assertTrue("User posts was wrong", testUser.getUserPosts().contains(testPost));
        Assert.assertTrue("User applied posts was wrong", testUser.getAppliedPosts().contains(testPost));
        Assert.assertTrue("User username was wrong", testUser.getUsername().equals("code4life"));


    }
}
