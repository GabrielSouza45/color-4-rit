package br.com.color4rit.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cria-usuario")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        super.doPost(request, response);

        String usuarioNome = request.getParameter("nome-usuario");
        String usuarioLogin = request.getParameter("login-usuario");
        String usuarioSenha = request.getParameter("senha-usuario");
        System.out.println(usuarioNome);
        System.out.println(usuarioLogin);
        System.out.println(usuarioSenha);

        request.getRequestDispatcher("index.html").forward(request,response);

    }


}
