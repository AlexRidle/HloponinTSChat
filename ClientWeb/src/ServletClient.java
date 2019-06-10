import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;

//чуть что добавить аннотацию
//@WebServlet("/servletClient")
public class ServletClient extends HttpServlet {
    BufferedReader in;
    BufferedWriter out;
    ServiceWeb serviceWeb;
    Socket socket;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("UTF-8");
        out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));

        String name = request.getParameter("nameClient");
        String type = request.getParameter("typeClient");

        out.write(name + "\n");
        out.flush();
        out.write(type + "\n");
        out.flush();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        serviceWeb.myRequest(socket, in);
        serviceWeb.myResponse(socket, out);
        socket = new Socket("localhost", 2121);


    }
}
