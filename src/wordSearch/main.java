package wordSearch;


import java.io.*;
import java.util.Scanner;

public class main{
	public static void main(String[]args)throws Exception{
		
		Player games_begin = new Player("Hello!");
		System.out.println(games_begin.welcome);
		wordSearch game = new wordSearch();
		game.randomization();
	}
}