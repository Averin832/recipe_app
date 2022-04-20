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
        String id = request.getParameter("recipe_id");

        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            Bookmark b = new Bookmark();

            //Recipe r = request.getSession().getAttribute(recipe);
            Recipe r = em.find(Recipe.class, Integer.parseInt(id));
            b.setRecipe(r);

            User u = (User) request.getSession().getAttribute("login_user");
            b.setUser(u);

            em.persist(b);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "ブックマークに登録しました");
            em.close();

            // response.sendRedirect(request.getContextPath() + "/recipe?id=${recipe.id }");
            response.sendRedirect(request.getContextPath() + "/index");

        }


    }

}
