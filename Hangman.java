
//Christian Brannon, ID: 1593881
//ITSE 2317-5001, 02.25.18
//Assignment: Hangman

//Main File

/*
 *
 *
 *
 *
 */
 


import java.util.Scanner;
import java.lang.Character;
import java.lang.Integer;

public class Hangman
{
	private static int[] playerWins;
    private static int[] playerLosses;
	private static int playerTurn = 1;
	private static int guessCount = 0;
	private static String phrase = "";
	private static StringBuffer hidden;
	
    public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		int selection;
        String input;
		
        while(!exit)
        {
            System.out.print(
                    "\n------Main Menu------\n"+ 
                    "1: Start new Game\n"+
                    "2: Scores\n"+
                    "3: About Game\n"+
                    "4: Exit Game\n"+
                    "\n->");
            input = scanner.nextLine();
            if (tryParseInt(input))
            {
                selection = Integer.parseInt(input);
                switch (selection)
                { 
                    case 1:
                        game();
                        break;
                    case 2:
                        scores();
                        break;
                    case 3:
                        about();
                        break;
                    case 4:
                        System.out.print("\nGoodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.print("\nThat was an invalid selection....  Please select again\n");
                        break;
                }
            }
            else
                System.out.print("Invalid Input!");
        }
	}
	
	public static void about()
    {
		Scanner scanner = new Scanner(System.in);
        System.out.print("It's Hangman...");
        scanner.nextLine();
    }
	
	public static void scores()
    {
        System.out.print(
                "\nPlayer 1 Wins: " + playerWins[0] +
                "\nPlayer 1 Losses: " + playerLosses[0] + "\n");
		System.out.print(
                "\nPlayer 0 Wins: " + playerWins[1] +
                "\nPlayer 0 Losses: " + playerLosses[1] + "\n");
    }
    
	public static void game()
    {
        Scanner scanner = new Scanner(System.in);
		char guess;
		boolean exit = false;
		guessCount = 0;
		System.out.print("\nPlayer " + playerTurn + ", please enter a phrase for player " + (playerTurn ^ 1) + ".\n->");
		phrase = scanner.nextLine().toUpperCase();
		
		hidden = makeHidden(phrase);
		System.out.print("\nPlayer " + (playerTurn ^ 1) + ", time to start guessing!");
		hangmanImage();
		while(!exit)
		{
			if (phrase.equals(hidden.toString()))
			{
				playerWins[(playerTurn ^ 1)]++;
				playerLosses[playerTurn]++;
				playerTurn++;
			}
			else if (guessCount < 6 && !phrase.equals(hidden.toString()))
			{
				guess = Character.toUpperCase(scanner.next(".").charAt(0));
				if (phrase.indexOf(guess) >= 0)
				{
					for (int i = 0; i < phrase.length(); i++)
						if (phrase.charAt(i) == guess)
							hidden.setCharAt(i, guess);
				}
				else
					guessCount++;
				hangmanImage();
			}
			
			else
			{
				playerWins[playerTurn]++;
				playerLosses[(playerTurn ^ 1)]++;
			}
		}
	}
	
	public static StringBuffer makeHidden(String s)
    {
        StringBuffer hidden = new StringBuffer(s.length());
        for (int count=0; count < s.length(); count++)
            hidden.append('_');
        return hidden;
    }
	
	public static void hangmanImage() 
	{
		if (guessCount == 0)
		{
			System.out.println(
			"\n  ______     Guesses: " + guessCount +
			"\n  |    |     " +
			"\n  |          " +
			"\n  |          " +
			"\n  |          " +
			"\n_/|\\_       Phrase: " + hidden);
		}
		if (guessCount == 1)
		{
			System.out.println(
			"\n  ______     Guesses: " + guessCount +
			"\n  |    |     " +
			"\n  |    O     " +
			"\n  |          " +
			"\n  |          " +
			"\n_/|\\_       Phrase: " + hidden);
		}
		if (guessCount == 2)
		{
			System.out.println(
			"\n  ______     Guesses: " + guessCount +
			"\n  |    |     " +
			"\n  |    O     " +
			"\n  |    |     " +
			"\n  |          " +
			"\n_/|\\_       Phrase: " + hidden);
		}
		if (guessCount == 3)
		{
			System.out.println(
			"\n  ______     Guesses: " + guessCount +
			"\n  |    |     " +
			"\n  |    O     " +
			"\n  |    |\\   " +
			"\n  |          " +
			"\n_/|\\_       Phrase: " + hidden);
		}
		if (guessCount == 4)
		{
			System.out.println(
			"\n  ______     Guesses: " + guessCount +
			"\n  |    |     " +
			"\n  |    O     " +
			"\n  |   /|\\   " +
			"\n  |          " +
			"\n_/|\\_       Phrase: " + hidden);
		}
		if (guessCount == 5)
		{
			System.out.println(
			"\n  ______     Guesses: " + guessCount +
			"\n  |    |     " +
			"\n  |    O     " +
			"\n  |   /|\\   " +
			"\n  |   /      " +
			"\n_/|\\_       Phrase: " + hidden);
		}
		if (guessCount == 6)
		{
			System.out.println(
			"\n  ______     " +
			"\n  |    |     " +
			"\n  |    O     " +
			"\n  |   /|\\   " +
			"\n  |   / \\   " +
			"\n_/|\\_       Phrase: " + phrase);
		}
	}
	
	public static boolean tryParseInt(String value) 
    {  
        try 
        {  
            Integer.parseInt(value);  
            return true;  
         } 
        catch (NumberFormatException e) 
        {  
            return false;  
        }  
    }
 }