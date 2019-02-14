package Database;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SpreadSheet {
	String[][] grid;
	ArrayList<String> data_type = new ArrayList();
	File dataType = new File("datatype.txt");
	File datas = new File("data.txt");

	public void load() {
		System.out.println("{{{{System loading database}}}}}");
		if (datas.exists()) { //Checks to see if file exist
			System.out.println("--_Data_Exist_--");
			try {
				Scanner inputDataType = new Scanner(dataType);
				while(inputDataType.hasNext()) {
					data_type.add(inputDataType.next());
				}
				
				Scanner inputRow = new Scanner(datas);
				int rows = 0;
				int columns = 0;
				while (inputRow.hasNextLine()) {// Gets rows in database
					inputRow.nextLine();
					rows++;
				}
				System.out.println("Rows in Database: " + rows);

				Scanner inputColumn = new Scanner(datas);//Finds amount of columns in database
				String[] contains = inputColumn.nextLine().split(",");
				columns = contains.length;
				System.out.println("Columns in Database: " + columns);

				this.grid = new String[rows][columns];
				Scanner inputData = new Scanner(datas);

				// Old method
				/*
				 * while(inputData.hasNextLine()) { String firstSentence = inputData.nextLine();
				 * String ID = firstSentence.substring(0, firstSentence.indexOf('|'));
				 * 
				 * String newSentence = firstSentence.substring(firstSentence.indexOf('|') + 1,
				 * firstSentence.length()); String NAME = newSentence.substring(0,
				 * newSentence.length()); System.out.println(NAME);
				 * 
				 * this.grid[r][c] = ID; this.grid[r][c+1] = NAME; r++; c++; }
				 */
				int r = 0;
				while (inputData.hasNextLine()) {
					String[] current = inputData.nextLine().split(","); // Captures 1 sentence. Splits each word in sentence into array
					for (int c = 0; c < current.length; c++) {
						this.grid[r][c] = current[c]; // Adds currentline to grid
					}
					r++;
				}
			}
			catch (Exception i) {
				System.out.println("--Error working with file--");
			}
		} 
		else {
			data_type.add("ID"); // Default column for [1][1]
			this.grid = new String[1][1];
			System.out.println("--New data base created--");
		}
	}

	public void edit(int row, int column, String add) {
		this.grid[row][column] = add;
	}

	public void addRow() {
		// System.out.println(this.grid[0].length);
		String[][] new_grid = new String[this.grid.length + 1][this.grid[0].length];
		for (int r = 0; r < grid.length; r++) { // Transferring content
			for (int c = 0; c < grid[r].length; c++) {
				new_grid[r][c] = grid[r][c];
			}
		}
		this.grid = new_grid;
		new_grid = null;
	}

	public void addColumn(String datatype) {
		// System.out.println(this.grid[0].length);
		data_type.add(datatype);
		String[][] new_grid = new String[this.grid.length][this.grid[0].length + 1];

		for (int r = 0; r < grid.length; r++) { // Transferring content
			for (int c = 0; c < grid[r].length; c++) {
				new_grid[r][c] = grid[r][c];
			}
		}
		this.grid = new_grid;
		new_grid = null;
	}

	public void printAllData() {
		for (int i = 0; i < data_type.size(); i++) { // Printing heading/labels/categories
			if (i != data_type.size() - 1)
				System.out.print(data_type.get(i) + " | ");
			else {
				System.out.println(data_type.get(i));
			}
		}

		for (int r = 0; r < grid.length; r++) { // Printing content
			for (int c = 0; c < grid[r].length; c++) {
				System.out.print(grid[r][c] + " | ");
			}
			System.out.println();
		}
	}

	public void search(int ID) {
		int holder = 0;
		for (int r = 0; r < grid.length; r++) { // Search for matching ID
			try {
				if (Integer.parseInt(grid[r][0]) == ID) {
					holder = r;
				}
			} catch (Exception i) {
				// System.out.println("Data was not convertible to string");
			}
		}

		for (int c = 0; c < data_type.size(); c++) { // Printing out based on category
			System.out.println(data_type.get(c) + ": " + grid[holder][c]);
		}
		System.out.println();
	}

	public void saveData() {
		try {
			PrintWriter output = new PrintWriter(datas);
			PrintWriter output2 = new PrintWriter(dataType);
			for (int r = 0; r < grid.length; r++) { // Writing each array into file
				for (int c = 0; c < grid[r].length; c++) {
					if (c != grid[r].length - 1) {
						output.print(grid[r][c] + ",");
					} else { // Last content will not get a ","
						output.print(grid[r][c]);
					}
				}
				output.println();
			}
			output.close();
			output.flush();
			for(int i = 0;i<data_type.size();i++) {
				output2.println(data_type.get(i));//Saves data type to ARRAYLIST
			}
			output2.close();
			output2.flush();
		}
		catch (Exception i) {
			System.out.println("---Error saving---");
		}
	}

}
