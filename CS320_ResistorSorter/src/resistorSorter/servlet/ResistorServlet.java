package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.BinController;

public class ResistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BinController binController;
	private int bin_id;
	private int count;
	String error;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/Resistor.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		binController = new BinController("inventory");
		
		//get parameters from jsp
		bin_id = getInteger(req, "bin_id");
		
		if (req.getParameter("addResistors") != null) {
			error = binController.addResistor(bin_id, getInteger(req, "countChange"));
			
		}
		else if (req.getParameter("removeResistors") != null) {
			error = binController.removeResistor(bin_id, getInteger(req, "countChange"));
		}
		
		//getting updated count based on bin_id
		count = binController.getCount(bin_id);
		
		//sending info back to jsp
		req.setAttribute("errorMessage", error);
		req.setAttribute("bin_id", bin_id);
		req.setAttribute("count", count);
		
		req.getRequestDispatcher("/_view/Resistor.jsp").forward(req, resp);
	}
	
	private int getInteger(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			return Integer.parseInt(req.getParameter(name));
		}
		else{
			return 0;
		}
	}

}
