package org.example.DZ4V2.Dao;

import org.example.DZ4V2.Models.Post;
import org.example.DZ4V2.Models.PostComment;
import org.example.DZ4V2.Models.User;
import org.example.DZ4V2.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelsDao {
    public void saveModels() {
        HibernateUtil.buildSessionFactory();
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                System.out.println("<=====================Создание таблицы User=======================================================================================================>");
                for (int i = 0; i < 5; i++) {
                    User user = new User();
                    System.out.println("Создание пользователя с ID: " + user.getId() +
                            " и именем: " + user.getUserName());
                    session.save(user);
                }

                System.out.println("<=====================Создание таблицы Post=======================================================================================================>");
                List<User> users = getUsers();
                for (int i = 0; i < 5; i++) {
                    Post post = new Post();
                    post.setUser(users.get(i % users.size())); // Установка пользователя для поста
                    System.out.println("Создание post с ID: " + post.getId() +
                            ", с title: " + post.getTitle() +
                            ", с userID: " + post.getUser().getId() +
                            " и date: " + post.getDate());
                    session.save(post);
                }

                System.out.println("<=====================Создание таблицы PostComment=======================================================================================================>");
                List<Post> posts = getPosts();
                for (int i = 0; i < 5; i++) {
                    PostComment postComment = new PostComment();
                    postComment.setPost(posts.get(i % posts.size())); // Установка поста для комментария
                    postComment.setUser(users.get(i % users.size())); // Установка пользователя для комментария
                    System.out.println("Создание postComment с ID: " + postComment.getId() +
                            ", с текстом: " + postComment.getText() +
                            ", с Post ID: " + postComment.getPost().getId() +
                            ", с user ID: " + postComment.getUser().getId() +
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
            return session.createQuery("from PostComment", PostComment.class).list();
        }
    }

    // Метод для получения всех комментариев определенного поста
    public List<PostComment> getCommentsForPost(Long postId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from PostComment where post.id = :postId";
            return session.createQuery(hql, PostComment.class)
                    .setParameter("postId", postId)
                    .list();
        }
    }

    // Вывести все комментарии для всех публикаций
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

    // Загрузить все публикации по идентификатору юзера
    public List<Post> getPostsForUser(Long userId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from Post where user.id = :userId";
            return session.createQuery(hql, Post.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }

    // Загрузить все комментарии по идентификатору юзера
    public List<PostComment> getCommentsForUser(Long userId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from PostComment where user.id = :userId";
            return session.createQuery(hql, PostComment.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }

    // По идентификатору юзера загрузить юзеров, под чьими публикациями он оставлял комменты
    public List<User> getUsersWithCommentsByUserId(Long userId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "select distinct u from PostComment pc " +
                    "join pc.post p " +
                    "join p.user u " +
                    "where pc.user.id = :userId";
            return session.createQuery(hql, User.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }

    public void dropTables() {
        HibernateUtil.buildSessionFactory();
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.createNativeQuery("DROP TABLE IF EXISTS post_comment CASCADE").executeUpdate();
                session.createNativeQuery("DROP TABLE IF EXISTS post CASCADE").executeUpdate();
                session.createNativeQuery("DROP TABLE IF EXISTS users CASCADE").executeUpdate();
                transaction.commit();
                System.out.println("Базы данных удалены");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
                HibernateUtil.shutdown();
            }
        }
    }
}
