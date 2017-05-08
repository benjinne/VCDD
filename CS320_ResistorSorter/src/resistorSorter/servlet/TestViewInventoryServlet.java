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
import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;

public class TestViewInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InventoryController inventoryController;
	private InventoryTransactionController inventoryTransactionController;
	private RackController rackController;
	private BinController binController;
	private LoginController loginController;
	private String email;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//Test if session exists
		String user = (String) req.getSession().getAttribute("user");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		//Setup controllers
		inventoryController = new InventoryController("inventory");
		rackController = new RackController("inventory");
		binController = new BinController("inventory");
		loginController = new LoginController("inventory");

		//pass info to jsp
		displayInventories(req);
		displayRacks(req);
		displayBins(req);
		
		req.getRequestDispatcher("/_view/TestViewInventory.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//initialize inventoryTransactionController
		inventoryTransactionController = new InventoryTransactionController("inventory");
		
		//initialize error to null
		String error = null;
		
		//get email from session
		email = (String) req.getSession().getAttribute("user");
		

		//if logout is pressed
		if (req.getParameter("logout") != null ) {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		//if initializeInventory is pressed
		else if (req.getParameter("addInventory") != null) {
			//get create inventory parameters
			int binCapacity = getInteger(req, "binCapacity");
			int userRemoveLimit = getInteger(req, "userRemoveLimit");
			String inventoryName = (String) req.getParameter("inventoryName");
			error = inventoryController.addInventory(binCapacity, userRemoveLimit, inventoryName, email);
		}
		
		//delete an inventory
		else if (req.getParameter("deleteInventory") != null) {
			int deleteInventoryID = getInteger(req, "deleteInventory");
			error = inventoryController.removeInventory(deleteInventoryID, email);
		}
		
		//add a Rack
		else if (req.getParameter("addRack") != null) {
			//get create rack parameters
			int inventory_id = getInteger(req, "inventory_id");
			Float tolerance = getFloat(req, "tolerance");
			Float power = getFloat(req, "power");
			error = rackController.addRack(tolerance, power, inventory_id, email);
		}
		
		//delete a rack
		else if (req.getParameter("deleteRack") != null) {
			int deleteRackID = getInteger(req, "deleteRack");
			error = rackController.removeRack(deleteRackID, email);
		}
		
		//add a bin
		else if (req.getParameter("addBin") != null) {
			//get create bin parameters
			int rack_id = getInteger(req, "rack_id");
			int resistance = getInteger(req, "resistance");
			int count = getInteger(req, "count");
			error = binController.addBin(rack_id, resistance, count, email);	
		}
		
		//delete a bin
		else if (req.getParameter("deleteBin") != null) {
			int deleteBinID = getInteger(req, "deleteBin");
			error = binController.removeBin(deleteBinID, email);
		}
		
		//addResistors
		else if (req.getParameter("addResistors") != null) {
			int bin_id = getInteger(req, "bin_id");
			int countChange = getInteger(req, "countChange");
			error = binController.addResistor(bin_id, countChange);
			if(error == null){
				inventoryTransactionController.addTransaction(email, bin_id, countChange, true);
			}
		}
		
		//removeResistors
		else if (req.getParameter("removeResistors") != null) {
			int bin_id = getInteger(req, "bin_id");
			int countChange = getInteger(req, "countChange");
			error = binController.removeResistor(bin_id, countChange);
			if(error == null){
				inventoryTransactionController.addTransaction(email, bin_id, countChange, false);
			}
		}
		
		//send info to jsp
		displayInventories(req);
		displayRacks(req);
		displayBins(req);
		
		//set attributes for jsp
		req.setAttribute("errorMessage", error);
		req.getRequestDispatcher("/_view/TestViewInventory.jsp").forward(req, resp);
	}
	////////////////////////////METHODS USED FOR GET AND POST////////////////////////////////////////////////
	private void displayInventories(HttpServletRequest req){
		//display inventories
		List<Inventory> inventories = inventoryController.displayInventories();
		req.setAttribute("inventories", inventories);
	}
	
	private void displayRacks(HttpServletRequest req){
		//display inventories
		//-1 used to display all racks regardless of inventory_id
		List<Rack> racks = rackController.displayRacks(-1);
		req.setAttribute("racks", racks);
		
	}
	
	private void displayBins(HttpServletRequest req){
		//display bins
		//-1 used to display all bins regardless of rackID
		List<Bin> bins = binController.displayBins(-1);
		req.setAttribute("bins", bins);
	}

	private int getInteger(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			try{
				return Integer.parseInt(req.getParameter(name));
			}catch(NumberFormatException e){
				return 0;
			}
		}
		else{
			return 0;
		}
	}
	
	private float getFloat(HttpServletRequest req, String name) {
		if(req.getParameter(name) != ""){
			try{
				return Float.parseFloat(req.getParameter(name));
			}catch(NumberFormatException e){
				return 0;
			}
		}
		else{
			return 0;
		}
	}
	
	
	
}