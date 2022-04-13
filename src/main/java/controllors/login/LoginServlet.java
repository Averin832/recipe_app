package controllors.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import services.UserService;
import utils.DBUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/.login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext application = this.getServletContext();
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        String mail = request.getParameter("mail");
        String plainPass = request.getParameter("password");
        String pepper = (String) application.getAttribute("pepper");
        String _token = request.getParameter("_token");
        UserService service = new UserService();

        Boolean isValidUser = service.validateLogin(mail, plainPass, pepper);

        if (isValidUser) {

            if (_token != null && _token.equals(request.getSession().getId())) {

                User u = service.findOne(mail, plainPass, pepper);
                request.getSession().setAttribute("login_user", u);

                em.close();

                response.sendRedirect(request.getContextPath() + "/index");

            } else {

                em.close();

                request.setAttribute("_token", _token);
                request.setAttribute("mail", mail);


                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
                rd.forward(request, response);
            }

        }



    }

}
