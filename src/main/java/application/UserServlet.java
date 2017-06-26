package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wasdev.biz.conn.EntityFactory;
import wasdev.biz.conn.MyCloudantClient;
import wasdev.biz.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "user", urlPatterns = { "/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyCloudantClient<User> conn = EntityFactory.getUserInstance();
       
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
		String _id = request.getParameter("_id");
		
		request.setAttribute(_id, conn.get(_id));
		request.getRequestDispatcher("Account.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setAddress1(request.getParameter("u_addr1"));
		user.setAddress2(request.getParameter("u_addr2"));
		user.setCity(request.getParameter("u_city"));
		user.setCountry(request.getParameter("u_country"));
		user.setEmail(request.getParameter("u_email"));
		user.setfName(request.getParameter("u_fname"));
		user.setlName(request.getParameter("u_lname"));
		user.setPhone(request.getParameter("u_phone"));
		user.setPostalCode(request.getParameter("u_postal"));
		user.setProvince(request.getParameter("u_province"));
		user.setState(request.getParameter("u_state"));
		user.setUserId(request.getParameter("u_id"));
		user.setUserPass(request.getParameter("u_pass"));
		user.setLang(request.getParameter("u_lang"));
		
		request.setAttribute(request.getParameter("_id"), conn.update(request.getParameter("_id"), user));
		doGet(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setAddress1(request.getParameter("u_addr1"));
		user.setAddress2(request.getParameter("u_addr2"));
		user.setCity(request.getParameter("u_city"));
		user.setCountry(request.getParameter("u_country"));
		user.setEmail(request.getParameter("u_email"));
		user.setfName(request.getParameter("u_fname"));
		user.setlName(request.getParameter("u_lname"));
		user.setPhone(request.getParameter("u_phone"));
		user.setPostalCode(request.getParameter("u_postal"));
		user.setProvince(request.getParameter("u_province"));
		user.setState(request.getParameter("u_state"));
		user.setUserId(request.getParameter("u_id"));
		user.setUserPass(request.getParameter("u_pass"));
		user.setLang(request.getParameter("u_lang"));
		//returns user data with _id and _rev values.
		user = conn.persist(user);
		request.setAttribute("new_user", user);
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conn.delete(request.getParameter("_id"));
	}

}
