package wordSearch;


import java.io.*;
import java.util.Scanner;
import java.io.IOException; 
import java.util.Arrays;

public class wordSearch {
	public void randomization()throws IOException{
		int randomize_Files = 1 + (int)(Math.random()* 12); //generate random number between 1 and 12
		
		String file_Num = ""; //empty string to add file name
	
		switch(randomize_Files) {							//switch statement to link name to random number.
			case 1: file_Num = "board_1.csv"; break; 		//this is file 1	
			case 2: file_Num = "board_2.csv"; break;		//this is file2
			case 3: file_Num = "board_3.csv"; break;		//this is file3 and so on
			case 4: file_Num = "board_4.csv"; break;
			case 5: file_Num = "board_5.csv"; break;
			case 6: file_Num = "board_6.csv"; break;
			case 7: file_Num = "board_7.csv"; break;
			case 8: file_Num = "board_8.csv"; break;
			case 9: file_Num = "board_9.csv"; break;
			case 10: file_Num = "board_10.csv"; break;
			case 11: file_Num = "board_11.csv"; break;
			case 12: file_Num = "board_12.csv"; break;
		}
		try {
			System.out.println("We are using " + file_Num);
			int b = 0; //counter for while loop
			Scanner input = new Scanner(new File(file_Num)); 
			input.useDelimiter(",");							
			System.out.println();
			String dimensions = input.nextLine();				//reads file data
			String [] this_isTheKey = dimensions.split(",");	//this creates an array for our key so we can pass it on to other important methods

			int these_areRows, these_areCols;
			these_areRows = Integer.parseInt(this_isTheKey[0]); //we create an array from the file input
			these_areCols = Integer.parseInt(this_isTheKey[1]);

			int position_toRemove = 0;
			for (int a = position_toRemove; a < this_isTheKey.length-1; a++) { //this removes the dimensions from the key array 
				this_isTheKey[a] = this_isTheKey[a+1]; //makes a new array with only the correct answers.
			}

			int position_toremove = 0;
			for (int p = position_toremove; p < this_isTheKey.length-1; p++) { // this is because I was facing issues with reading the key, it kept printing the last vlaue thrice. I had to debug this.
				this_isTheKey[p] = this_isTheKey[p+1];
			}

			

			String [][] this_isWordSearch = new String[these_areRows][these_areCols]; //generate a 2d array with the dimensions from the file
			String line = "";

			while (input.hasNext()) { //will loop till file has data to read
				String individual_Line = input.nextLine();
				String [] individual_letters = individual_Line.split(","); //creates an array for the individual characters

				for (int a = 0; a < individual_letters.length; a++) { 
					this_isWordSearch[b][a] = individual_letters[a]; //adds the individual characters to the string 2d array one by one.
				}
				b++;
			}
			System.out.println();
			Player games_begin = new Player(".");
			games_begin.Player(this_isTheKey, this_isWordSearch); //we pass this information on to the next file with class Player.
			input.close();
		}

		catch (IOException e) {
			e.printStackTrace();  
		}
	}
}