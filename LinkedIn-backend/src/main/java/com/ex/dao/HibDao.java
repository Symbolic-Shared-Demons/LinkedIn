package com.ex.dao;

import com.ex.models.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;


@Repository
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
        Transaction tx = session.beginTransaction();
        session.save(u);

        tx.commit();
        session.close();

        return u;
    }



    @Override
    public User getExistingUser(String username) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "from User where username = :u";
        Query query = session.createQuery(hql);
        query.setString("u", username);

        List<User> users = (List)query.list();
        tx.commit();
        session.close();

        for(User u: users){
            return u;
        }
        return null;
    }



    public List<Category> getAllCats(){

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "from Category";
        Query query = session.createQuery(hql);

        List<Category> cats = (List) query.list();
        tx.commit();
        session.close();

        return cats;
    }

    public List<Post> getAllPosts(){

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "from Post";
        Query query = session.createQuery(hql);

        List<Post> posts = (List) query.list();
        tx.commit();
        session.close();

        return posts;
    }

    public int deletePost(Post p){

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "delete Post where id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", p.getId());

        int r = query.executeUpdate();

        if(r == 1){
            tx.commit();
            session.close();
            return r;
        }
        else{
            tx.rollback();
            session.close();
            return r;
        }
    }

    public User userApply(String u, int p){

        Post post = getPostById(p);
        User user = getExistingUser(u);

        user.addAppliedPosts(post);
        updateUser(user);

        return user;

    }

    public User deleteApplied(User u, Post p){

        u.deleteApplied(p);

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.update(u);

        tx.commit();
        session.close();

        return getExistingUser(u.getUsername());
    }

    @Override
    public int deleteUser(String u){
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "delete User where username =:username";
        Query query = session.createQuery(hql);
        query.setParameter("username", u);

        int r = query.executeUpdate();

        if(r == 1){
            tx.commit();
            session.close();
            return r;
        }
        else{
            tx.rollback();
            session.close();
            return r;
        }
    }

    @Override
    public User updateUser(User u) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.update(u);

        tx.commit();

        return u;
    }

    @Override
    public boolean checkCreds(String username) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "from User where username = ?";
        Query query = session.createQuery(hql);
        query.setParameter(1,username);

        List user = query.list();

        tx.commit();
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
        Transaction tx = session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);

        List users = query.list();

        tx.commit();
        session.close();

        return users;

    }

    @Override
    public boolean checkCreds(String username, String password) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "from User where username = ? and password =?";
        Query query = session.createQuery(hql);
        query.setParameter(1,username);
        query.setParameter(2,password);

        List user = query.list();

        tx.commit();
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
        Criteria cr = session.createCriteria(Post.class);
        cr.add(Restrictions.eq("id", p));
        List<Post> posts = cr.list();
        if(posts.size() >= 1){
            return posts.get(0);
        }
        return null;
    }

    @Override
    public Category getCategoryById(int c) {

        Session session = this.sessionFactory.openSession();
        Criteria cr = session.createCriteria(Category.class);
        cr.add(Restrictions.eq("id", c));
        List<Category> cs = cr.list();
        if(cs.size() >= 1){
            return cs.get(0);
        }
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Category c) {
        Session session = this.sessionFactory.openSession();
        Criteria cr = session.createCriteria(Post.class);
        cr.add(Restrictions.eq("postCat", c));
        return cr.list();
    }

    @Override
    public User addCategoryForUser(Category c, User u) {
        u.addCat(c);
        return updateUser(u);
    }

    @Override
    public User addPostForUser(User u, Post p) {

        addNewPost(p);

        u.addPostedPost(p);

        return updateUser(u);
    }

    @Override
    public List<Post> getPostsByUser(User u) {
        Session session = this.sessionFactory.openSession();
        Criteria cr = session.createCriteria(Post.class);
        cr.add(Restrictions.eq("poster", u));
        return cr.list();
    }

    @Override
    public Set<Post> getPostsByApplied(User u) {

        Session session = this.sessionFactory.openSession();
        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("id", u.getId()));
        if(cr.list().size() >= 1){
            u = (User) cr.list().get(0);
            return u.getAppliedPosts();
        }
        else{
            return null;
        }
    }

    @Override
    public Category addNewCategory(Category c) {


        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(c);

        tx.commit();
        session.close();

        return c;

    }

    @Override
    public Post addNewPost(Post p) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(p);

        tx.commit();
        session.close();

        return p;

    }


}
