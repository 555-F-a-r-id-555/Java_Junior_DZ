package org.example.Test01;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static final int PORT = 12345;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новое подключение: " + clientSocket);
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String login;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            try {
                // Чтение логина
                this.login = in.readLine();
                System.out.println("Пользователь подключился: " + login);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Получено сообщение: " + inputLine);
                    Request request = objectMapper.readValue(inputLine, Request.class);
                    handleRequest(request);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleRequest(Request request) throws IOException {
            switch (request.getType()) {
                case "send_to_friend":
                    sendToFriend(request.getRecipient(), request.getMessage());
                    break;
                case "send_to_all":
                    sendToAll(request.getMessage());
                    break;
                case "get_clients":
                    sendClientsList();
                    break;
                default:
                    System.out.println("Неизвестный тип запроса: " + request.getType());
            }
        }

        private void sendToFriend(String recipient, String message) {
            for (ClientHandler client : clients) {
                if (client.login.equals(recipient)) {
                    client.out.println("Сообщение от " + login + ": " + message);
                    break;
                }
            }
        }

        private void sendToAll(String message) {
            for (ClientHandler client : clients) {
                client.out.println("Сообщение всем от " + login + ": " + message);
            }
        }

        private void sendClientsList() throws IOException {
            Set<String> clientLogins = new HashSet<>();
            for (ClientHandler client : clients) {
                clientLogins.add(client.login);
            }
            Response response = new Response(clientLogins);
            out.println(objectMapper.writeValueAsString(response));
        }
    }
}
