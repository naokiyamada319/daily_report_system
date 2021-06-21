package controllers.follows;

import java.io.IOException;
import java.util.List;

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

		String followee_id = (String)request.getParameter("followee_id");

		Employee e = em.find(Employee.class, Integer.parseInt(followee_id));

		List<Follow> followeeList = em.createNamedQuery("getAllFollowee", Follow.class)
				                      .setParameter("followee", e)
				                      .setParameter("me", request.getSession().getAttribute("login_employee"))
				                      .getResultList();

		if(followeeList.size() > 0) {
			Follow f = (Follow) followeeList.get(0);
			em.getTransaction().begin();
			em.remove(f);
			em.getTransaction().commit();
		}
		em.close();
		response.sendRedirect(request.getContextPath() + "/reports/show?=" + followee_id);
	}

}
