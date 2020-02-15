package v2;

import java.io.*;
import java.net.Socket;

public class Task implements Runnable {
    private final Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            // 读取请求并解析
            Request request = Request.parse(is);
            System.out.println(request);
            // 处理业务
            if (request.path.equals("/hello")) {
                String body = "<h1>你好</h1>";
                byte[] bodyBytes = body.getBytes("UTF-8");

                // 拼接响应并发送
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                responseSB.append("Content-Type: text/plain;charset=UTF-8\r\n");
                responseSB.append("Content-Length: ");
                responseSB.append(bodyBytes.length);
                responseSB.append("\r\n");
                responseSB.append("\r\n");
                responseSB.append(body);
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.equals("/run")) {
                String body = "<script src='/joke.js'></script>";
                byte[] bodyBytes = body.getBytes("UTF-8");

                // 拼接响应并发送
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                responseSB.append("Content-Type: text/html;charset=UTF-8\r\n");
                responseSB.append("Content-Length: ");
                responseSB.append(bodyBytes.length);
                responseSB.append("\r\n");
                responseSB.append("\r\n");
                responseSB.append(body);
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.equals("/joke.js")) {
                String body = "alert('不给糖果就捣蛋');";
                byte[] bodyBytes = body.getBytes("UTF-8");

                // 拼接响应并发送
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                responseSB.append("Content-Type: application/javascript;charset=UTF-8\r\n");
                responseSB.append("Content-Length: ");
                responseSB.append(bodyBytes.length);
                responseSB.append("\r\n");
                responseSB.append("\r\n");
                responseSB.append(body);
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.equals("/move")) {
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 307 Temporary Redirect\r\n");
                responseSB.append("Location: https://www.baidu.com/\r\n");
                responseSB.append("\r\n");
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.equals("/set-cookie")) {
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 200 OK\r\n");
                responseSB.append("Set-Cookie: name=peixinchen; expires=Mon, 06-Apr-2020 03:41:01 GMT; Max-Age=8640000\r\n");
                responseSB.append("Set-Cookie: age=34; expires=Mon, 06-Apr-2020 03:41:01 GMT; Max-Age=8640000\r\n");
                responseSB.append("\r\n");
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            } else if (request.path.contains("/login")) {
                HttpServlet servlet = new LoginServlet();
                Response response = new Response();
                servlet.doGet(request, response);
                response.writeAndFlush(os);
            } else if (request.path.equals("/profile")) {
                // 获取 cookie 中的 sessionId
                String cookie = request.headers.get("Cookie");
                if (cookie == null) {
                    StringBuilder responseSB = new StringBuilder();
                    responseSB.append("HTTP/1.0 401 Unauthorized\r\n");
                    responseSB.append("\r\n");
                    // 发送响应
                    os.write(responseSB.toString().getBytes("UTF-8"));
                    os.flush();
                } else {
                    String sessionId = cookie.split("=")[1];

                    // 根据 session-id 获取用户信息
                    String filename = String.format("sessions/%s.session", sessionId);
                    File file = new File(filename);
                    if (file.exists()) {
                        System.out.println(filename);
                        InputStream fileOS = new FileInputStream(filename);
                        ObjectInputStream ois = new ObjectInputStream(fileOS);
                        User currentUser = (User)ois.readObject();
                        fileOS.close();


                        // 返回 response 个人信息 并且种 cookie
                        String body = String.format("<h1>%s</h1><img height='40' width='30' src='%s'><p>%s</p>",
                                currentUser.username,
                                currentUser.avatarUrl,
                                currentUser.show
                        );

                        StringBuilder responseSB = new StringBuilder();
                        responseSB.append("HTTP/1.0 200 OK\r\n");
                        responseSB.append("Content-Type: text/html;charset=UTF-8\r\n");
                        responseSB.append("Content-Length: ");
                        responseSB.append(body.getBytes("UTF-8").length);
                        responseSB.append("\r\n");
                        responseSB.append("Set-Cookie: session_id=");
                        responseSB.append(currentUser.id);
                        responseSB.append("\r\n");
                        responseSB.append("\r\n");
                        responseSB.append(body);
                        // 发送响应
                        os.write(responseSB.toString().getBytes("UTF-8"));
                        os.flush();
                    } else {
                        StringBuilder responseSB = new StringBuilder();
                        responseSB.append("HTTP/1.0 401 Unauthorized\r\n");
                        responseSB.append("\r\n");
                        // 发送响应
                        os.write(responseSB.toString().getBytes("UTF-8"));
                        os.flush();
                    }
                }
            } else {
                StringBuilder responseSB = new StringBuilder();
                responseSB.append("HTTP/1.0 404 Not Found\r\n");
                responseSB.append("\r\n");
                // 发送响应
                os.write(responseSB.toString().getBytes("UTF-8"));
                os.flush();
            }

            // 关闭 socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
