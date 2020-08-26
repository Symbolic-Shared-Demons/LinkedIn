
import com.ex.ORMConfig;
import com.ex.dao.HibDao;

import com.ex.models.Category;
import com.ex.models.Post;
import com.ex.models.User;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-context.xml")
public class DaoTests implements ApplicationContextAware {

    @Autowired
    private HibDao dao;

    private User u;

    private Post testPost;


    @Before
    public void init(){

        u = new User();

        u.setPassword("abc123");
        u.setLastName("junit");
        u.setEmail("junit@gmail.com");
        u.setFirstName("test");
        u.setUsername("help-me");

        testPost = new Post();

        testPost.setDesc("Developer wanted");
        testPost.setId(1);

    }

    @Test
    public void testAllGets(){

        Assert.assertNotNull(dao.getExistingUser("sanbornshane9"));
        Assert.assertNotNull(dao.getCategoryById(3));

        Assert.assertNotNull(dao.getAllCats());
        Assert.assertNotNull(dao.getAllPosts());
        Assert.assertNotNull(dao.getAllUsers());

        dao.addNewUser(u);

        Assert.assertEquals("user was not added to the database", u.getLastName(), dao.getExistingUser(u.getUsername()).getLastName());

        Category c = dao.getCategoryById(3);

        testPost.setPostCat(c);

        u = dao.addPostForUser(u,testPost);

        int id = 0;

        for(Post p: u.getUserPosts()){
            id = p.getId();
            Assert.assertNotNull("post was added to user", dao.getPostById(p.getId()));
        }

        u = dao.addCategoryForUser(c,u);
        u = dao.userApply(dao.getUserById(u.getId()),dao.getPostById(id));

        u = dao.getUserById(u.getId());

        for(Post p: u.getAppliedPosts()){
            Assert.assertTrue("user didn't apply to post", p.getAppliedUsers().contains(u));
        }

        Assert.assertTrue("post was not added to the user", u.getUserPosts().size() == 1);



        System.out.println("hello");
        Assert.assertTrue("category was not added to user", u.getUserCats().size() == 1);

        dao.deleteUser(u.getId());

        Assert.assertNull("user was not deleted", dao.getUserById(u.getId()));


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
