package controllors;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Bookmark;
import models.Recipe;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _token = request.getParameter("_token");
        String mail = request.getParameter("mail");

        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            Bookmark b = new Bookmark();

            Recipe r = (Recipe) request.getSession().getAttribute("recipe_id");
            b.setRecipe(r);

            User user_mail = (User) request.getSession().getAttribute(mail);
            b.setUser(user_mail);

            em.persist(b);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "ブックマークに登録しました");
            em.close();

            response.sendRedirect(request.getContextPath() + "/recipe?id=${recipe.id }");

        }


    }

}
