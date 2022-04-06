package controllors.users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.PropertyConst;
import models.User;
import utils.DBUtil;

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

            String password = request.getParameter("password");
            u.setPassword(password);

            String pepper = getContextScope(PropertyConst.PEPPER);

            // List<String> errors = service.create(u, pepper);

            em.persist(u);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");

        }
    }

}
