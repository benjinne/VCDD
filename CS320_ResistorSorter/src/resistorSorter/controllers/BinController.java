package resistorSorter.controllers;
import java.util.List;

import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.IDatabase;

public class BinController {

	Bin model;
	IDatabase db;
	
	public BinController(Bin model)
	{
		this.model = model;
	}
	//Resistor related methods
	public BinController()
	{
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
		//Adding resistors
		//Must pass in instance of inventory class!
		public void addBin(int rack_id, int resistance, int count){
			db.insertBin(rack_id, resistance, count);
		}
		
		//Removing resistors
		//Must pass in an instance of inventory class!
		public void removeBin(int binID){
			db.removeBin(binID);
		}
		
		public List<Bin> displayBins(int rackID){
			return db.getAllBins(rackID);
		}
		
		public void addResistor(int bin_id, int count){
			db.addResistors(bin_id, count);
		}
		
		public void removeResistor(int bin_id, int count){
			db.removeResistors(bin_id, count);
		}
		public int getCount(int bin_id){
			return db.getCount(bin_id);
		}
}
