package com.ex.dao;

import com.ex.models.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
@Transactional
public class HibDao implements Dao {
    private SessionFactory sessionFactory;

    public HibDao(){

    }

    @Autowired
    public HibDao(SessionFactory sf){
        this.sessionFactory = sf;

    }


    public User addNewUser(User u){

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(u);

        session.getTransaction().commit();
        session.close();


        return u;
    }



    @Override
    @Transactional
    public User getExistingUser(String username) {

        Session session = this.sessionFactory.openSession();


        String hql = "from User where username = :u";
        Query query = session.createQuery(hql);
        query.setString("u", username);

        List<User> users = (List)query.list();


        for(User u: users){
            session.close();
            return getUserById(u.getId());
        }

        session.close();
        return null;

    }



    public List<Category> getAllCats(){

        Session session = this.sessionFactory.openSession();

        session.beginTransaction();

        String hql = "from Category";
        Query query = session.createQuery(hql);

        List<Category> cats = (List) query.list();

        session.getTransaction().commit();
        session.close();

        return cats;
    }

    public List<Post> getAllPosts(){

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Post";
        Query query = session.createQuery(hql);

        List<Post> posts = (List) query.list();

        session.getTransaction().commit();
        session.close();

        return posts;
    }

    public int deletePost(Post p){

        p.setPostCat(null);
        Session sess1 = this.sessionFactory.openSession();
        for(User u: p.getAppliedUsers()){
            u.getAppliedPosts().remove(p);
            sess1.beginTransaction();
            sess1.update(u);
            sess1.getTransaction().commit();
        }

        p.setPoster(null);
        Session sess = this.sessionFactory.openSession();
        sess.beginTransaction();

        sess.update(p);

        sess.getTransaction().commit();
        sess.close();

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();


        session.delete(p);

        session.getTransaction().commit();
        session.close();

        return 1;
    }

    public User userApply(User u, Post p){

        u.addAppliedPosts(p);

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(u);
        session.getTransaction().commit();
        session.close();
        return u;

    }

    public User deleteApplied(User u, Post p){

        u.deleteApplied(p);

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        session.update(u);

        session.getTransaction().commit();
        session.close();
        return getExistingUser(u.getUsername());
    }

    @Override
    public void deleteUser(int id){

        User user = getUserById(id);

        user.getAppliedPosts().clear();

        Session sess1 = this.sessionFactory.openSession();
        sess1.beginTransaction();

        sess1.update(user);

        sess1.getTransaction().commit();
        sess1.close();


        deleteAllPostsForUser(user);

        user = getUserById(id);

        user.getUserCats().clear();


        Session sess = this.sessionFactory.openSession();
        sess.beginTransaction();

        sess.update(user);

        sess.getTransaction().commit();
        sess.close();

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();




        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }



    @Override
    public boolean checkCreds(String username) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from User where username = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,username);

        List user = query.list();

        session.getTransaction().commit();
        session.close();


        if(user.size() >= 1){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public List<User> getAllUsers() {

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);

        List users = query.list();

        session.getTransaction().commit();
        session.close();

        return users;

    }

    @Override
    public boolean checkCreds(String username, String password) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from User where username = ? and password =?";
        Query query = session.createQuery(hql);
        query.setParameter(0,username);
        query.setParameter(1,password);

        List user = query.list();

        session.getTransaction().commit();
        session.close();


        if(user.size() >= 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Post getPostById(int p) {

        Session session = this.sessionFactory.openSession();
        Post post = session.get(Post.class,p);
        session.close();
        return post;
    }

    @Override
    public Category getCategoryById(int c) {

        Session session = this.sessionFactory.openSession();
        Category cat = session.get(Category.class, c);
        session.close();
        return cat;
    }

    @Override
    public List<Post> getPostsByCategory(Category c) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(Post.class);
        cr.add(Restrictions.eq("postCat", c));
        List list = cr.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public User addCategoryForUser(Category c, User u) {
        u.addCat(c);

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(u);
        session.getTransaction().commit();
        session.close();
        return u;
    }

    @Override
    public User addPostForUser(User u, Post p) {

        p.setPoster(u);
        Session session = this.sessionFactory.openSession();

        addNewPost(p,session);

        session.close();

        u.addPostedPost(p);

        return u;
    }

    @Override
    public List<Post> getPostsByUser(User u) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(Post.class);
        cr.add(Restrictions.eq("poster", u));
        List list = cr.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public Set<Post> getPostsByApplied(User u) {

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("id", u.getId()));
        if(cr.list().size() >= 1){
            u = (User) cr.list().get(0);
            session.getTransaction().commit();
            session.close();
            return u.getAppliedPosts();
        }
        else{
            session.getTransaction().commit();
            session.close();
            return null;
        }
    }



    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.openSession();

        User u = session.get(User.class,id);

        session.close();

        return u;
    }

    @Override
    public Post addNewPost(Post p, Session session) {


        session.save(p);


        return p;

    }

    @Override
    public void addLog(String m) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("insert into linkedin.logs(message) values (" + m +")");
        q.executeUpdate();

        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void deleteAllPostsForUser(User u) {
         for(Post p : u.getUserPosts()){
            deletePost(p);
        }
    }


}
