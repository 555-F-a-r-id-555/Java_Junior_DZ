package org.example.DZ4;

/**
 * Используя hibernate, создать таблицы:
 * 1. Post (публикация) (id, title)
 * 2. PostComment (комментарий к публикации) (id, text, post_id)
 * <p>
 * Написать стандартные CRUD-методы: создание, загрузка, удаление.
 * <p>
 * Доп. задания:
 * 1. * В сущностях post и postComment добавить поля timestamp с датами.
 * 2. * Создать таблицу users(id, name) и в сущностях post и postComment добавить ссылку на юзера.
 * 3. * Реализовать методы:
 * 3.1 Загрузить все комментарии (post_comment)  публикации (post)
 * 3.2 Загрузить все публикации(post) по идентификатору юзера(user)
 * 3.3 ** Загрузить все комментарии(post_comment) по идентификатору юзера(user)
 * 3.4 **** По идентификатору юзера загрузить юзеров, под чьими публикациями он оставлял комменты.
 * // userId -> List<User>
 * <p>
 * <p>
 * Замечание:
 * 1. Можно использовать ЛЮБУЮ базу данных (например, h2)
 * 2. Если запутаетесь, приходите в группу в телеграме или пишите мне @inchestnov в личку.
 */


import org.example.DZ4.DAO.ModelsDAO;
import org.example.DZ4.Model.Post;
import org.example.DZ4.Model.PostComment;
import org.example.DZ4.Model.User;
import org.example.DZ4.Util.HibernateUtil;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("\u001B[34m");
        HibernateUtil.buildSessionFactory();
        ModelsDAO modelsDAO = new ModelsDAO();
        modelsDAO.saveModels();

        System.out.println("\u001B[32m");
        System.out.println("<=====================Вывод таблици User=======================================================================================================>");
        System.out.print("\u001B[33m");
        List<User> users = modelsDAO.getUsers();
        for (User user : users) {
            System.out.println("User ID: " + user.getId() +
                    ", Name: " + user.getUserName());
        }
        System.out.println("\u001B[32m");
        System.out.println("<=====================Вывод таблици Post=======================================================================================================>");
        System.out.print("\u001B[33m");
        List<Post> posts = modelsDAO.getPosts();
        for (Post post : posts) {
            System.out.println("Post_ID: " + post.getId() +
                    ", Title: " + post.getTitle() +
                    ", UserID: " + post.getUserId() +
                    " и Date: " + post.getDate());
        }
        System.out.println("\u001B[32m");
        System.out.println("<=====================Вывод таблици PostComment=======================================================================================================>");
        System.out.println("\u001B[33m");
        List<PostComment> postComments = modelsDAO.getPostComments();
        for (PostComment postComment : postComments) {
            System.out.println("PostComment_ID: " + postComment.getId() +
                    ", Text: " + postComment.getText() +
                    ", Post_ID: " + postComment.getPostId() +
                    ", User_ID: " + postComment.getUserId() +
                    " и Date: " + postComment.getDate());
        }

        // 3.1.1 Загрузить все комментарии (post_comment) по определенной публикации (post)
        // Получение и вывод всех комментариев для определенного поста
        System.out.println("\u001B[32m");
        System.out.println("<=====================3.1.1 Загрузить все комментарии (post_comment) по определенной публикации (post)=======================================================================================================>");
        System.out.println("\u001B[33m");
        Long postId = 1L; // пример ID поста
//        List<PostComment> comments = modelsDAO.getCommentsForPost(postId);
//        for (PostComment comment : comments) {
//            System.out.println("Comment_ID: " + comment.getId() +
//                    ", Text: " + comment.getText() +
//                    ", Post_ID: " + comment.getPostId() +
//                    ", User_ID: " + comment.getUserId() +
//                    ", Date: " + comment.getDate());
//        }
        modelsDAO.getCommentsForPost(postId).stream().forEach(comment -> System.out.println("Comment_ID: " + comment.getId() +
                ", Text: " + comment.getText() +
                ", Post_ID: " + comment.getPostId() +
                ", User_ID: " + comment.getUserId() +
                ", Date: " + comment.getDate()));


        // 3.1.2 Вывести все комментарии (post_comment) для всех публикаций (post)
        // Получение и вывод всех комментариев для всех публикаций
        System.out.println("\u001B[32m");
        System.out.println("<=====================3.1.2 Вывести все комментарии (post_comment) для всех публикаций (post)=======================================================================================================>");
        System.out.println("\u001B[33m");
        Map<Post, List<PostComment>> postCommentsMap = modelsDAO.getAllPostsAndComments();
//        for (Map.Entry<Post, List<PostComment>> entry : postCommentsMap.entrySet()) {
//            Post post = entry.getKey();
//            List<PostComment> comments2 = entry.getValue();
//            System.out.println("Post ID: " + post.getId() +
//                    ", Title: " + post.getTitle() +
//                    ", User ID: " + post.getUserId() +
//                    ", Date: " + post.getDate());
//            for (PostComment comment : comments2) {
//                System.out.println("  Comment ID: " + comment.getId() +
//                        ", Text: " + comment.getText() +
//                        ", Post ID: " + comment.getPostId() +
//                        ", User ID: " + comment.getUserId() +
//                        ", Date: " + comment.getDate());
//            }
//        }
        postCommentsMap.entrySet().stream().forEach(entry -> {
            Post post = entry.getKey();
            List<PostComment> comments3 = entry.getValue();

            System.out.println("Post ID: " + post.getId() +
                    ", Title: " + post.getTitle() +
                    ", User ID: " + post.getUserId() +
                    ", Date: " + post.getDate());

            comments3.forEach(comment -> {
                System.out.println("  Comment ID: " + comment.getId() +
                        ", Text: " + comment.getText() +
                        ", Post ID: " + comment.getPostId() +
                        ", User ID: " + comment.getUserId() +
                        ", Date: " + comment.getDate());
            });
        });

        // 3.2 Загрузить все публикации(post) по идентификатору юзера(user)
        System.out.println("\u001B[32m");
        System.out.println("<=====================3.2 Загрузить все публикации(post) по идентификатору юзера(user)=======================================================================================================>");
        System.out.println("\u001B[33m");
        Long userId = 4L;
//        List<Post> userPosts = modelsDAO.getPostsForUser(userId);
//        for (Post post : userPosts) {
//            System.out.println("Post_ID: " + post.getId() +
//                    ", Title: " + post.getTitle() +
//                    ", User_ID: " + post.getUserId() +
//                    " и Date: " + post.getDate());
//        }
        modelsDAO.getPostsForUser(userId).stream().forEach(post -> System.out.println("Post_ID: " + post.getId() +
                ", Title: " + post.getTitle() +
                ", User_ID: " + post.getUserId() +
                " и Date: " + post.getDate()));

        // 3.3 ** Загрузить все комментарии(post_comment) по идентификатору юзера(user)
        System.out.println("\u001B[32m");
        System.out.println("<=====================3.3 ** Загрузить все комментарии(post_comment) по идентификатору юзера(user)=======================================================================================================>");
        System.out.println("\u001B[33m");
//        List<PostComment> userComments = modelsDAO.getCommentsForUser(userId);
//        for (PostComment comment : userComments) {
//            System.out.println("Comment_ID: " + comment.getId() +
//                    ", Text: " + comment.getText() +
//                    ", Post_ID: " + comment.getPostId() +
//                    ", User_ID: " + comment.getUserId() +
//                    ", Date: " + comment.getDate());
//        }
        modelsDAO.getCommentsForUser(userId).stream().forEach(comment -> System.out.println("Comment_ID: " + comment.getId() +
                ", Text: " + comment.getText() +
                ", Post_ID: " + comment.getPostId() +
                ", User_ID: " + comment.getUserId() +
                ", Date: " + comment.getDate()));

        //* 3.4 **** По идентификатору юзера(user) загрузить юзеров(List<user>), под чьими публикациями он оставлял комменты.
        // userId -> List<User>
        // Получение и вывод пользователей, под чьими публикациями данный пользователь оставлял комментарии
        System.out.println("\u001B[32m");
        System.out.println("<=====================3.4 **** По идентификатору юзера(user) загрузить юзеров(List<user>), под чьими публикациями он оставлял комменты.=======================================================================================================>");
        System.out.println("\u001B[33m");
        List<User> usersWithComments = modelsDAO.getUsersWithCommentsByUserId(userId);
        System.out.println("Вывод пользователей, под чьими публикациями данный пользователь USER_ID, оставлял комментарии, USER_ID: " + userId);
//        for (User user : usersWithComments) {
//            System.out.println("User ID: " + user.getId() + ", Name: " + user.getUserName());
//        }
        usersWithComments.stream().forEach(user -> System.out.println("User ID: " + user.getId() + ", Name: " + user.getUserName()));


        HibernateUtil.shutdown();
    }
}
