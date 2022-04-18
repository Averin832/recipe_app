package controllors.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.UserValidator;
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

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        String mail = request.getParameter("mail");
        String plainPass = request.getParameter("password");
        String pepper = (String)this.getServletContext().getAttribute("pepper");
        String _token = request.getParameter("_token");

        Boolean isValidUser = UserValidator.validateLogin(mail, plainPass, pepper);

        if (isValidUser == true) {

            if (_token != null && _token.equals(request.getSession().getId())) {

                User u = UserValidator.findOne(mail, plainPass, pepper);
                request.getSession().setAttribute("login_user", u);
                request.getSession().setAttribute("user_mail", mail);
                request.getSession().setAttribute("flush", "ログインしました");

                em.close();

                response.sendRedirect(request.getContextPath() + "/index");

            } else {

                em.close();

                request.setAttribute("_token", _token);
                request.setAttribute("loginError", true);
                request.setAttribute("mail", mail);


                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
                rd.forward(request, response);
            }

        }

    }

}
