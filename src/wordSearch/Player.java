package wordSearch;

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Player {
	String welcome = "";
	String [] key;
	String [][] board_Game;

	public Player(String welcome) {
		this.welcome = welcome;
	}

	public void Player (String [] key, String [][] board_Game) {


		this.key = key;
		this.board_Game = board_Game;

		String[] answers = new String [key.length - 2]; //this is a debugging solution, nothing to do with the game logic.
		for (int a = 0; a < answers.length; a++) {
			answers[a] = key[a];
		}

		//You can decomment this to acquire the key of the specific board.
		//System.out.print(Arrays.toString(answers));
		System.out.println();
		//this is the board design, it has some elements from my Battleship board design code, but is tailored to print out design for any dimensions.

		int x,y,z;
		int a,b;
		String border_stars = "+";
		String side_borderLine = "|";
		String dash_border = "---";
		String spacing_Cells = " ";

		for (y = 0; y < board_Game[0].length; y++) { //printing the initial stars and dash design +---
			System.out.print(border_stars + dash_border);
		}
		System.out.print(border_stars); //this prints one star at the end of +--- and the outcome is +---+ per cell
		System.out.println();
		for(z = 0; z < board_Game.length; z++) {
			System.out.print(side_borderLine);
			for(a = 0; a < board_Game[z].length; a++) {
				System.out.print(spacing_Cells); //this is the input in cells
				System.out.print(board_Game[z][a]); //this is the input in cells
				System.out.print(spacing_Cells + side_borderLine); //this prints an outcome of "   |" so it is the inside of the cell
			}
			System.out.println();
		System.out.print(border_stars); 

		for (b = 0; b < board_Game.length; b++){
			System.out.print(dash_border + border_stars);  //closing border print
		}
		System.out.println();
		}

		begin_Games(answers); //passes on key to begin_Games method
	}

	public void begin_Games(String[] answers) {
		//asking for user player num input

		while(true) {
			System.out.println();
			System.out.print("How many players will be joining us today?: ");
			Scanner p = new Scanner (System.in);
			int player_Num;
			player_Num = p.nextInt();
			if (player_Num <= 1) {
				System.out.println("Sorry! That is not a valid input. Please try again."); //there have to be minimum 2 players in the game for it to begin. user will be reprompted.
			}
			else {
				these_arePlayerNames(player_Num, answers);
				break;
			}
		}
	}

	public void these_arePlayerNames (int num_Players, String[] answer_Key) {
		String [] player_Name = new String[num_Players]; //making the number of players into an array
		for (int x = 0; x < player_Name.length; x++) {
			Scanner name = new Scanner(System.in);
			System.out.print("Please enter a name for Player " + (x+1) + " : ");
			String these_areNames = name.nextLine();
			player_Name[x] = these_areNames;
		}
		System.out.println("The players joining us today are: " + Arrays.toString(player_Name)); //announcing participants at the start of the game
		int random_Selection = 1 + (int)(Math.random()* num_Players); //randomizing player number between 1 and playernumber
		int player = (random_Selection-1); //we remove one because incase the player random num is 1, we need the index to represent 0 as that is player 1. I did not want the computer to generate player 0 as it is weird for the user.
		gameStart(player, player_Name, num_Players,answer_Key);
	}

	public void gameStart(int player, String [] player_Name, int num_Players, String[] answer_Key) { //pretty much the main game logic.
		Scanner input = new Scanner(System.in);
		System.out.print(player_Name[player] + " will be starting the game."); //randomly generated player begins the game.
		System.out.println();
		boolean over = false;
		String player_Guess = "";
		String player_CorrectGuess = "";

		while (over == false) {
			int wrong_SkipTurn = 0;
			System.out.print((player_Name[player]) + ", please enter a word: "); //user input
			String inputted_Word = input.nextLine();
			
			if ((inputted_Word.length() < 3) || (player_Guess.contains(inputted_Word) == true) || (inputted_Word.matches(".*[A-Z].*"))) { //data validation. incase inputted word is less than 3, or empty string, or uppercase, its a wrong input.
				wrong_SkipTurn++;
				System.out.println("Sorry " + player_Name[player] + " that is wrong input. Your turn will be skipped.");
			}

			if (inputted_Word.charAt(0) > 96 && inputted_Word.charAt(0) < 123) { //ensuring we proceed with correct input
				over = false;
			}

			if (over = false) {
				continue;
			}

			if (Arrays.binarySearch(answer_Key, inputted_Word) > -1) {//since this method returns a value in int of 0 or greater, we set the appropriate if condition.
				if (wrong_SkipTurn == 0) {
					System.out.print("You are correct! You get a point.");
					System.out.println();
					player_CorrectGuess = player_CorrectGuess + inputted_Word + ",";
				}
			}

			player_Guess = player_Guess + inputted_Word + ","; //adding all the correct guesses to one big string, this becomes an array towards the end for easy verification.

			if (player == (num_Players - 1)) { //this is for properly alternating turns b/w users. incase our user is the last one, we reset it.
				player = 0;
			}

			else if (player < num_Players) { //incase our user is the first player, we increment the value to keep the alternation going.
				player++;
			}

			String [] correct_answersGuessed = player_CorrectGuess.split(","); //we make a 1d array of the correct answers.
			if (correct_answersGuessed.length == answer_Key.length){ //verify against the answer key if it's all there
				System.out.println();
				System.out.print("The Game is OVER. All the hidden words were found. Thank you for playing!"); //game end detected, and it ends here.
				System.out.println();
				over = true;
			}

			wrong_SkipTurn = 0;		//reset for next player.
		}
	}
}