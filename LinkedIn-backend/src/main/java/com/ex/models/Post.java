package com.ex.models;

import javax.persistence.*;

@Entity
@Table(name = "posts", schema = "LinkedIn")

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

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="users", referencedColumnName="id", columnDefinition="INT")
    private User poster;



    public Post(){

    }


    public Post(String desc, Category postCat, User u) {

        this.desc = desc;
        this.postCat = postCat;
        this.poster = u;

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
                ", poster=" + poster +
                '}';
    }
}

