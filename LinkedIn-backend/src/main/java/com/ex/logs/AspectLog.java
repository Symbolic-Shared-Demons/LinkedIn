package com.ex.logs;

import com.ex.dao.Dao;
import com.ex.dao.HibDao;
import com.ex.models.Category;
import com.ex.models.Post;
import com.ex.models.User;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

public class AspectLog {

    Dao dao;

    @Autowired
    public AspectLog(HibDao dao){
        this.dao = dao;

    }

    public void aroundLogIn(ProceedingJoinPoint pjp){

        String m = null;
        String username = (String)pjp.getArgs()[0];
//        m = username + " tried to log in";
//        dao.addLog(m);

        try{
            Object user = pjp.proceed();

            if(user == null){
//                dao.addLog(username + " failed to log in");
            }
            else{
//                dao.addLog(username + " successfully logged in");

            }

        }catch(Throwable throwable){
            throwable.printStackTrace();
        }
    }

    public void afterCreateAcc(User user){

        if(user.getUsername() == null){
            dao.addLog("user failed to create an account");
        }else{
            dao.addLog("user made new account: " + user.getUsername());
        }
    }

    public void aroundPost(ProceedingJoinPoint pjp){

        String username = (String)pjp.getArgs()[0];
        Post p = (Post)pjp.getArgs()[1];

        try{
            User u = (User) pjp.proceed();
            for(Post post: u.getUserPosts()){
                if(post.getId() == p.getId()){
                    dao.addLog(username + " posted a post with an id: " + p.getId());
                }
            }
        }catch(Throwable throwable){
            throwable.printStackTrace();
        }

    }

    public void aroundApply(ProceedingJoinPoint pjp){

        String username = (String)pjp.getArgs()[0];
        int p = (int)pjp.getArgs()[1];

        try{
            User u = (User) pjp.proceed();

            for(Post post: u.getAppliedPosts()){
                if(p == post.getId()){
                    dao.addLog(username + " applied to a post with an id: " + p);
                }
            }

        }catch(Throwable throwable){
            throwable.printStackTrace();
        }
    }

    public void aroundDeleteApply(ProceedingJoinPoint pjp){

        String username = (String)pjp.getArgs()[0];
        Post p = (Post)pjp.getArgs()[1];
        boolean delete = true;

        try{
            User u = (User) pjp.proceed();

            for(Post post: u.getAppliedPosts()){
                if(p.getId() == post.getId()){
                    delete = false;
                }
            }

        }catch(Throwable throwable){
            throwable.printStackTrace();
        }

        if(delete){
            dao.addLog(username + " deleted application to a post with an id: " + p.getId());
        }
    }

    public void aroundAddCategory(ProceedingJoinPoint pjp){

        String username = (String)pjp.getArgs()[1];
        int id = (int)pjp.getArgs()[1];

        try{
            User u = (User) pjp.proceed();

            for(Category c : u.getUserCats()){
                if(c.getId() == id){
                    dao.addLog(username + " added " + c.getTitle() + " to their categories");
                }
            }

        }catch(Throwable throwable){
            throwable.printStackTrace();
        }
    }

}
