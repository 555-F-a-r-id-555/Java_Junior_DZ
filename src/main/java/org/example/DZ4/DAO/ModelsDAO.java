package org.example.DZ4.DAO;

import org.example.DZ4.Model.Post;
import org.example.DZ4.Model.PostComment;
import org.example.DZ4.Model.User;
import org.example.DZ4.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelsDAO {
    public void saveModels() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                for (int i = 0; i < 5; i++) {
                    User user = new User();
                    System.out.println("Создание пользователя с ID: " + user.getId() +
                            " и именем: " + user.getUserName());
                    session.save(user);
                }

                for (int i = 0; i < 5; i++) {
                    Post post = new Post();
                    System.out.println("Создание post с ID: " + post.getId() +
                            ", с title: " + post.getTitle() +
                            ", с userID: " + post.getUserId() +
                            " и date: " + post.getDate());
                    session.save(post);
                }

                for (int i = 0; i < 5; i++) {
                    PostComment postComment = new PostComment();
                    System.out.println("Создание postComment с ID: " + postComment.getId() +
                            ", с текстом: " + postComment.getText() +
                            ", с Post ID: " + postComment.getPostId() +
                            ", с user ID: " + postComment.getUserId() +
                            " и date: " + postComment.getDate());
                    session.save(postComment);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }


    public List<User> getUsers() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public List<Post> getPosts() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from Post", Post.class).list();
        }
    }

    public List<PostComment> getPostComments() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from PostComment ", PostComment.class).list();
        }
    }


    // 3.1 Загрузить все комментарии (post_comment) по определенной публикации (post)
    // Метод для получения всех комментариев определенного поста
    public List<PostComment> getCommentsForPost(Long postId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from PostComment where postId = :postId";
            return session.createQuery(hql, PostComment.class)
                    .setParameter("postId", postId)
                    .list();
        }
    }

    // 3.1.2 Вывести все комментарии (post_comment) для всех публикаций (post)
    public Map<Post, List<PostComment>> getAllPostsAndComments() {
        try (Session session = HibernateUtil.getSession()) {
            Map<Post, List<PostComment>> postCommentsMap = new HashMap<>();

            List<Post> posts = session.createQuery("from Post", Post.class).list();
            for (Post post : posts) {
                List<PostComment> comments = getCommentsForPost(post.getId());
                postCommentsMap.put(post, comments);
            }
            return postCommentsMap;
        }
    }
    //    3.2 Загрузить все публикации(post) по идентификатору юзера(user)
    public List<Post> getPostsForUser(Long userId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from Post where userId = :userId";
            return session.createQuery(hql, Post.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }

    // 3.3 ** Загрузить все комментарии(post_comment) по идентификатору юзера(user)
    // 3.3 Загрузить все комментарии (post_comment) по определенному пользователю (user)

    public List<PostComment> getCommentsForUser(Long userId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from PostComment where userId = :userId";
            return session.createQuery(hql, PostComment.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }

    //* 3.4 **** По идентификатору юзера(user) загрузить юзеров(users), под чьими публикациями он оставлял комменты.
    // userId -> List<User>

    public List<User> getUsersWithCommentsByUserId(Long userId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "select distinct u from PostComment pc " +
                    "join Post p on pc.postId = p.id " +
                    "join User u on p.userId = u.id " +
                    "where pc.userId = :userId";
            return session.createQuery(hql, User.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }
}
