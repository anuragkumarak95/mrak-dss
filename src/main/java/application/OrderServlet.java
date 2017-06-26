package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wasdev.biz.conn.EntityFactory;
import wasdev.biz.conn.MyCloudantClient;
import wasdev.biz.model.Order;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "order", urlPatterns = { "/order" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyCloudantClient<Order> conn = EntityFactory.getOrderInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _id = request.getParameter("_id");
		request.setAttribute(_id, conn.get(_id));
		request.getRequestDispatcher("checkout.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order o = new Order();
		o.setBillAddress1(request.getParameter("o_b_add1"));
		o.setBillAddress2(request.getParameter("o_b_add2"));
		o.setBillCity(request.getParameter("o_b_city"));
		o.setBillCountry(request.getParameter("o_b_country"));
		o.setBillFName(request.getParameter("o_b_fname"));
		o.setBillLName(request.getParameter("o_b_lname"));
		o.setBillPostalCode(request.getParameter("o_b_postal"));
		o.setBillProvince(request.getParameter("o_b_province"));
		o.setBillState(request.getParameter("o_b_state"));
		o.setShipAddress1(request.getParameter("o_s_add1"));
		o.setShipAddress2(request.getParameter("o_s_add2"));
		o.setShipCity(request.getParameter("o_s_city"));
		o.setShipCountry(request.getParameter("o_s_country"));
		o.setShipFName(request.getParameter("o_s_fname"));
		o.setShipLName(request.getParameter("o_s_lname"));
		o.setShipPostalCode(request.getParameter("o_s_postal"));
		o.setShipProvince(request.getParameter("o_s_province"));
		o.setShipState(request.getParameter("o_s_state"));
		o.setUserId(request.getParameter("o_uid"));

		request.setAttribute(request.getParameter("_id"), conn.update(request.getParameter("_id"), o));
		doGet(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order o = new Order();
		o.setBillAddress1(request.getParameter("o_b_add1"));
		o.setBillAddress2(request.getParameter("o_b_add2"));
		o.setBillCity(request.getParameter("o_b_city"));
		o.setBillCountry(request.getParameter("o_b_country"));
		o.setBillFName(request.getParameter("o_b_fname"));
		o.setBillLName(request.getParameter("o_b_lname"));
		o.setBillPostalCode(request.getParameter("o_b_postal"));
		o.setBillProvince(request.getParameter("o_b_province"));
		o.setBillState(request.getParameter("o_b_state"));
		o.setShipAddress1(request.getParameter("o_s_add1"));
		o.setShipAddress2(request.getParameter("o_s_add2"));
		o.setShipCity(request.getParameter("o_s_city"));
		o.setShipCountry(request.getParameter("o_s_country"));
		o.setShipFName(request.getParameter("o_s_fname"));
		o.setShipLName(request.getParameter("o_s_lname"));
		o.setShipPostalCode(request.getParameter("o_s_postal"));
		o.setShipProvince(request.getParameter("o_s_province"));
		o.setShipState(request.getParameter("o_s_state"));
		o.setUserId(request.getParameter("o_uid"));
		//returns user data with _id and _rev values.
		request.setAttribute("new_order", conn.persist(o));
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conn.delete(request.getParameter("_id"));
	}

}
