import logic.ServiceWeb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    BufferedReader in;
    BufferedWriter out;
    ServiceWeb serviceWeb;
    Socket socket;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        serviceWeb.myRequest(in);
        serviceWeb.myResponse(out);
        socket = new Socket("localhost", 2121);

    }
}
