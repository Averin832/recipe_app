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

import models.Bookmark;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class BookmarkServlet
 */
@WebServlet("/bookmarks")
public class BookmarkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User login_user = (User) request.getSession().getAttribute("login_user");
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {

            }

            List<Bookmark> bookmarks = em.createNamedQuery("getAllMyBookmarks", Bookmark.class)
                    .setParameter("user", login_user)
                    .setFirstResult(10 * (page - 1))
                    .setMaxResults(10)
                    .getResultList();

            long myBookmarksCount = (long) em.createNamedQuery("countAllMyBookmarks", Long.class)
                    .setParameter("user", login_user)
                    .getSingleResult();

            em.close();

            request.setAttribute("bookmarks", bookmarks);
            request.setAttribute("myBookmarksCount", myBookmarksCount);
            request.setAttribute("page", page);
            request.setAttribute("maxRow", 10);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/recipe/bookmark.jsp");
            rd.forward(request, response);


    }

}
