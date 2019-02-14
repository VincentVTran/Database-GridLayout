package Database;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
//Vincent Tran
public class myMain {
	static SpreadSheet database;
	public static void main(String[] args) {
		myMain invoke = new myMain();
		invoke.mains();
	}
	
	public void mains() {
		database = new SpreadSheet();
		database.load();
		//Do whatever you want to do:
		//Options = insertName, addRow
		function();
		//END
		database.saveData();
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy" + " | " + "hh:mm:ss aa").format(new Date());
		System.out.println("Edited: "+timeStamp);
		
	}
	
	public void function() {
		boolean run = true;
		Scanner input = new Scanner(System.in);
		System.out.println();
		System.out.println("________Editing Options________");
		System.out.println("1. Add Row");
		System.out.println("2. Add Column (Data Type)");
		System.out.println("3. Edit Name (Row, Column, String)");
		System.out.println("________Options________");
		System.out.println("4. Search Info (ID#)");
		System.out.println("5. Print Grid");
		//System.out.println("6. Add everything");
		System.out.println("99. Save");
		System.out.println();
		while(run == true) {
			System.out.print("Current selection: ");
			int response = input.nextInt();
			switch(response) {
				case 1: 
					database.addRow();
					break;
				case 2:
					System.out.print("What category of data will this be?: ");
					String reply = input.next();
					database.addColumn(reply);
					break;
				case 3: 
					System.out.print("What row would you like to edit?: ");
					int row = input.nextInt()-1;
					System.out.print("What column would you like to edit?: ");
					int column = input.nextInt()-1;
					System.out.print("What string would you like to insert?: ");
					String data = input.next();
					
					try {
						database.edit(row, column, data);
					}
					catch(Exception i) {
						System.out.println("-----Row/Column error-----" + "\n");
					}
					break;
				case 4:
					System.out.print("Please enter in the ID: ");
					int ID = input.nextInt();
					System.out.println();
					database.search(ID);
					break;
				case 5:
					System.out.println("------Printing out database------");
					database.printAllData();
					break;
				case 99:
					return;
				default: 
					System.out.println("--Function not found--");
					break;
			}
		}
	}
}
