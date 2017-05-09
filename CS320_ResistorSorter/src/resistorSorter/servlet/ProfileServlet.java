package resistorSorter.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.BinController;
import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.InventoryTransactionController;
import resistorSorter.controllers.LoginController;
import resistorSorter.controllers.RackController;
import resistorSorter.model.InventoryTransaction;
import resistorSorter.persist.DerbyDatabase;



public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InventoryTransactionController inventoryTransactionController;
	private LoginController loginController;
	private InventoryController inventoryController;
	private RackController rackController;
	private BinController binController;
	String username;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String error = null;
		//initialize controllers
		inventoryTransactionController = new InventoryTransactionController("inventory");
		loginController = new LoginController("inventory");
		inventoryController = new InventoryController("inventory");
		rackController = new RackController("inventory");
		binController = new BinController("inventory");
		
		
		//check to see if user is logged in
		String email = (String) req.getSession().getAttribute("user");
		if (email == null) {			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		username = email.substring(0, email.indexOf('@'));
		
		//send info to jsp
		req.setAttribute("username", username);
		req.setAttribute("errorMessage", error);
		displayUserTransactions(req);
		displayUserStatus(req);
		spamFilter(req);
		req.getRequestDispatcher("/_view/Profile.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//initialize error to null
		String error = null;
		
		//initialize controllers
		inventoryTransactionController = new InventoryTransactionController("inventory");
		
		//get info from jsp
		String email = (String) req.getSession().getAttribute("user");
		username = email.substring(0, email.indexOf('@'));
		
		//if reset inventory is pressed
		if (req.getParameter("resetInventory") != null) {
			req.getSession().invalidate();
			DerbyDatabase.deleteDataBase();
			DerbyDatabase.loadDataBase();
			loginController.insertNewUser("blinne@ycp.edu");
			loginController.insertNewUser("dwebb4@ycp.edu");
			loginController.updateAdminFlag("blinne@ycp.edu", true);
			loginController.updateAdminFlag("dwebb4@ycp.edu", true);
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
			//old code used if button is displayed to user
			/*if(!loginController.getAdminFlag(email)){
				error = "Only admistrators can do that";
			}
			else{
				DerbyDatabase.deleteDataBase();
				DerbyDatabase.loadDataBase();
			}*/
		}
		//if populate tables is pressed
		else if(req.getParameter("populateTables") != null){
			if(!loginController.getAdminFlag(email)){
				error = "Only admistrators can do that";
			}
			else if(inventoryController.getCountOfInventories() > 0){
				error = "Cannot populate already existing inventory";
			}
			else{
				populateTables((String) req.getSession().getAttribute("user"));
			}
		}
		else if (req.getParameter("logout") != null) {
			System.out.println("logout");
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		else if (req.getParameter("requestAdmin") != null && (!loginController.checkRequest(email))) {
			loginController.userRequestAdmin(email);
			loginController.updateRequest(email, true);
		}
		//Send to transactions
		else if(req.getParameter("viewTransactions") != null){
			resp.sendRedirect(req.getContextPath() + "/AllTransactions");
			return;
		}
		
		//send info to jsp
		req.setAttribute("username", username);
		displayUserTransactions(req);
		displayUserStatus(req);
		spamFilter(req);
		req.setAttribute("errorMessage", error);
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Profile.jsp").forward(req, resp);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void displayUserTransactions(HttpServletRequest req){
		//display transactions
		String username = (String) req.getSession().getAttribute("user");
		List<InventoryTransaction> transactions = inventoryTransactionController.displayUserInventoryTransactions(username);
		req.setAttribute("transactions", transactions);
	}
	
	private void displayUserStatus(HttpServletRequest req){
		//Showing user status based on adminReq
		//Also to allow admin to view all transactions or not
		if(loginController.getAdminFlag((String)req.getSession().getAttribute("user"))){
			req.setAttribute("adminFlag", "Administrator");
			req.setAttribute("admin", true);
		}
		else{
			req.setAttribute("adminFlag", "User");
			req.setAttribute("admin", false);
		}
	}
	private void spamFilter(HttpServletRequest req){
		if(!loginController.checkRequest((String)req.getSession().getAttribute("user"))){
			//System.out.println("The user has not submitted a request yet");
			req.setAttribute("request", true);
		}
		else{
			//System.out.println("The user has already submitted a request");
			req.setAttribute("request", false);
		}
	}
	
	private void populateTables(String email){
		inventoryController.addInventory(1000, 150,"KEC123",email);
		inventoryController.addInventory(1000, 150,"KEC124",email);
		inventoryController.addInventory(1000, 150,"KEC125",email);
		inventoryController.addInventory(500, 100,"KEC130",email);
		inventoryController.addInventory(10000, 1000,"STOCK",email);
		
		rackController.addRack(5, (float) 0.5, 1,email);
		rackController.addRack(10, (float)0.25, 1,email);
		rackController.addRack(5, (float) 0.5, 2,email);
		rackController.addRack(10, (float)0.25, 2,email);
		rackController.addRack(5, (float) 0.5, 3,email);
		rackController.addRack(10, (float)0.25, 3,email);
		rackController.addRack(5, (float) 0.5, 4,email);
		rackController.addRack(10, (float)0.25, 5,email);
		rackController.addRack(20, (float) 0.3, 4,email);
		rackController.addRack(1, (float)0.25, 5,email);
		
		binController.addBin(1, 500, 500,email);
		binController.addBin(1, 220, 1000,email);
		binController.addBin(2, 1000, 65,email);
		binController.addBin(2, 7200, 56,email);
		binController.addBin(3, 1500, 22,email);
		binController.addBin(3, 100, 86,email);
		binController.addBin(4, 600, 100,email);
		binController.addBin(4, 2000, 56,email);
		binController.addBin(5, 500, 95,email);
		binController.addBin(5, 1000, 333,email);
		binController.addBin(6, 500, 100,email);
		binController.addBin(6, 7200, 96,email);
		binController.addBin(7, 150, 95,email);
		binController.addBin(7, 60, 333,email);
		binController.addBin(8, 78, 100,email);
		binController.addBin(8, 650, 96,email);
		binController.addBin(7, 720, 95,email);
		binController.addBin(7, 630, 333,email);
		binController.addBin(8, 50000, 65,email);
		binController.addBin(7, 7200, 96,email);
		binController.addBin(8, 25000, 1500,email);
		binController.addBin(8, 80, 6000,email);
		binController.addBin(8, 70, 5000,email);
		binController.addBin(8, 32, 1000,email);
		binController.addBin(8, 56, 9800,email);
		binController.addBin(8, 1600, 8000,email);
		binController.addBin(8, 5400, 7500,email);
	}
	
}
