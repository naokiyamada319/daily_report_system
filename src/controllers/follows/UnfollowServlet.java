package controllers.follows;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class UnfollowServlet
 */
@WebServlet("/unfollow")
public class UnfollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnfollowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		EntityManager em = DBUtil.createEntityManager();

		Follow f = new Follow();

		String followee_id = (String)request.getParameter("followee_id");

		Employee e = em.find(Employee.class, Integer.parseInt(followee_id));

		f.setFollowee(e);
		f.setFollower((Employee)request.getSession().getAttribute("login_employee"));

		request.setAttribute("followee_id", f);

		em.getTransaction().begin();
		em.remove(f);
		em.getTransaction().commit();
		em.close();
		response.sendRedirect(request.getContextPath() + "/reports/show?=" + followee_id);
	}

}
