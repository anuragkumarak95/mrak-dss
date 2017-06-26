package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wasdev.biz.conn.EntityFactory;
import wasdev.biz.conn.MyCloudantClient;
import wasdev.biz.model.Product;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "product", urlPatterns = { "/product" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyCloudantClient<Product> conn = EntityFactory.getProductInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _id = request.getParameter("_id");
		request.setAttribute(_id, conn.get(_id));
		request.getRequestDispatcher("Product.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product p = new Product();
		p.setDescription(request.getParameter("p_desc"));
		p.setName(request.getParameter("p_name"));
		p.setQuantity(Integer.parseInt(request.getParameter("p_quan")));
		p.setUnitCost(Float.parseFloat(request.getParameter("p_cost")));
		p.setImgUrl(request.getParameter("p_img"));
		
		//create a hidden item and add _id to it for product reference.
		request.setAttribute(request.getParameter("_id"), conn.update(request.getParameter("_id"), p));
		doGet(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product p = new Product();
		p.setDescription(request.getParameter("p_desc"));
		p.setName(request.getParameter("p_name"));
		p.setQuantity(Integer.parseInt(request.getParameter("p_quan")));
		p.setUnitCost(Float.parseFloat(request.getParameter("p_cost")));
		p.setImgUrl(request.getParameter("p_img"));
		//returns user data with _id and _rev values.
		p = conn.persist(p);
		request.setAttribute("new_product", p);
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conn.delete(request.getParameter("_id"));
		
	}

}
