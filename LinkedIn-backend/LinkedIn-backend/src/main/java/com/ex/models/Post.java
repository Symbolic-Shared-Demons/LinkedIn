package com.ex.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts", schema = "linkedin")
@Proxy(lazy = false)

@NamedNativeQueries(
        value={
                @NamedNativeQuery(
                        name = "getAllPosts",
                        query = "select * from posts",
                        resultClass = Post.class
                ),
                @NamedNativeQuery(
                        name = "getPostsByCat",
                        query = "select * from posts where id = :cat_id",
                        resultClass = Post.class
                ),
                @NamedNativeQuery(
                        name = "getPostFromPoster",
                        query = "select * from posts where poster = :poster_id",
                        resultClass = Post.class
                ),
                @NamedNativeQuery(
                        name = "getUsersAppliedOnPost",
                        query = "select users.id, users.username, users.hash, users.first_name, users.last_name," +
                                "users.email from posts inner join applied on (posts.id = applied.post_id) inner " +
                                "join users on (users.id = applied.user_id) where posts.id = :post_id",
                        resultClass = User.class
                )
        }
)

public class Post {

    @Id
    @Column(name="id", columnDefinition ="serial primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="description")
    private String desc;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="categories", referencedColumnName="id", columnDefinition="INT")

    private Category postCat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poster")
    @JsonIgnore
    private User poster;

    @ManyToMany(mappedBy = "appliedPosts", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> appliedUsers;



    public Post(){
        appliedUsers = new HashSet<User>();
    }


    public Post(String desc, Category postCat, User u, Set applied) {

        this.desc = desc;
        this.postCat = postCat;
        this.poster = u;
        this.appliedUsers = applied;

    }

    public Set<User> getAppliedUsers() {
        return appliedUsers;
    }

    public void setAppliedUsers(Set<User> appliedUsers) {
        this.appliedUsers = appliedUsers;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Category getPostCat() {
        return postCat;
    }

    public void setPostCat(Category postCat) {
        this.postCat = postCat;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", postCat=" + postCat +
                ", poster=" + poster.getId() +
                '}';
    }

    public void addApplied(User u) {
        appliedUsers.add(u);
    }
}

