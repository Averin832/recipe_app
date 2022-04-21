package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import models.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {

    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String contextPath = ((HttpServletRequest) request).getContextPath();
        String uri = ((HttpServletRequest) request).getRequestURI();
        String servletPath = ((HttpServletRequest) request).getServletPath();

        if (servletPath.matches("/css.*")) {

            // pass the request along the filter chain
            chain.doFilter(request, response);

        } else {

            HttpSession session = ((HttpServletRequest)request).getSession();
            User u = (User) session.getAttribute("login_user");

            if (u == null) {

                if (    uri.equals(contextPath + "/new_recipe") ||
                        uri.equals(contextPath + "/bookmarks")
                        ) {

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
                    rd.forward(request, response);

                    return;

                }

            } else {

                if (uri.equals(contextPath + "/login")) {

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/recipe/index.jsp");
                    rd.forward(request, response);

                    return;

                }  else {

                }
            }

            chain.doFilter(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
