package controllors;

import java.io.IOException;
import java.time.LocalDate;
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
import models.validators.RecipeValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet(name = "CreateRecipeServlet", urlPatterns = { "/create_recipe" })
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

            Recipe r = new Recipe();

            String title = request.getParameter("title");
            r.setTitle(title);

            String ingredient = request.getParameter("ingredient");
            r.setIngredient(ingredient);

            String how_to = request.getParameter("how_to");
            r.setHowTo(how_to);

            String word = request.getParameter("word");
            r.setWord(word);

            LocalDate createdAt = LocalDate.now();
            r.setCreatedAt(createdAt);

            User u = (User) request.getSession().getAttribute("login_user");
            r.setUser(u);

            List<String> errors = RecipeValidator.validate(r);
                if (errors.size() > 0) {

                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("recipe", r);
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/recipe/new.jsp");
                    rd.forward(request, response);

                } else {

            em.persist(r);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "投稿しました");
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");

            }

        }

    }

}
