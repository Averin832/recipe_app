package controllors;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Recipe;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user_page")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User loginuser = (User) request.getSession().getAttribute("login_user");
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {

            }

        List<Recipe> recipes = em.createNamedQuery("getAllMine", Recipe.class)
                .setParameter("user", loginuser)
                .setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .getResultList();

        long myRecipesCount = (long) em.createNamedQuery("countAllMine", Long.class)
                .setParameter("user", loginuser)
                .getSingleResult();

        em.close();

        request.setAttribute("recipes", recipes);
        request.setAttribute("myRecipesCount", myRecipesCount);
        request.setAttribute("page", page);
        request.setAttribute("maxRow", 10);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/recipe/user_page.jsp");
        rd.forward(request, response);

    }

}
