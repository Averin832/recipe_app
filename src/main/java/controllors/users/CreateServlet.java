package controllors.users;

import java.io.IOException;
import java.util.List;

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
import utils.EncryptUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _token = request.getParameter("_token");

        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            User u = new User();

            String name = request.getParameter("name");
            u.setName(name);

            String mail = request.getParameter("mail");
            u.setMail(mail);

            String password = EncryptUtil.getPasswordEncrypt(
                    request.getParameter("password"),
                    (String)this.getServletContext().getAttribute("pepper")
                    );
            u.setPassword(password);

            List<String> errors = UserValidator.validate(u, true, true);
                if (errors.size() > 0) {

                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("user", u);
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
                    rd.forward(request, response);

                } else {

            em.persist(u);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "登録が完了しました");
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");

            }

        }

    }

}
