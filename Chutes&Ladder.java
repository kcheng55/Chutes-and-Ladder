import java.util.Random;
import java.util.Scanner;
import java.io.*;
/**
 * The program keeps track of players location on a board game where players
 * move acording to the dice roll number, their position gets updated again by
 * hitting a chute or a ladder, winner reaches to 100 in the end.
 *
 * @author Kang Cheng
 * @version 1.0
 */
public class Chutes {
   /**
    * The main method contains file reading, array creation and storage and
    * actual game play
    *
    * @param args the string array containing the commond line arguments
    */
    public static void main(String[] args) throws IOException {
        // welcome message
        welcome();
        Scanner keyboard = new Scanner(System.in);
        char repeat;
        // read the file
        int[] board = getBoard();
        do {
            // getting player numbers
            int playerNum = getPlayerNum(keyboard);
            // get player name
            String [] player = getPlayerName(playerNum, keyboard);
            // game play
            gamePlay(player, board);
 
            System.out.print("\n" + "Do you want to play again? (y/n)" + "\n");
            repeat = keyboard.nextLine().charAt(0);
 
        } while (repeat == 'y' || repeat == 'Y');
 
        // goodbye message
        goodbye();
 
        keyboard.close();
    }

    /**
     * The method displays a welcome message
     */
    public static void welcome() {
        System.out.printf("%s%s%s%s", "\nWelcome to Chutes & Ladders!",
                      "You must land on 100 (without going past) to win!\n",
                      "You will play against the computer.\n\n",
                      "-------------------------------\n\n");
    }

    /**
     * This method obtains the number of players
     *
     * @param keyboard the scanner object
     * @return the number of players
     */
    public static int getPlayerNum(Scanner keyboard) {
        int playerNum; // holds player number
 
        do {
            System.out.print("How many players will play (between 2-6)? ");
            playerNum = keyboard.nextInt();
            keyboard.nextLine();
        } while(playerNum < 2 || playerNum > 6); // player number validation

    return playerNum;
    }

    /**
     * This method stores the player's name in a string array
     *
     * @param playerNum The number of players
     * @param keyboard The scanner object
     * @return the player's number
     */
    public static String[] getPlayerName(int playerNum, Scanner keyboard) {
        // create string array
        String[] playerName = new String[playerNum];
  
        // store names in array
        for (int i = 0; i < playerNum; i++) {
            System.out.print("Enter player " + (i + 1) + "'s name: ");
            playerName[i] = keyboard.nextLine();
            }
        System.out.println("\n" + "-----------------------------------" + "\n");
    
    return playerName;
    }

    /**
     * This method create a board array
     *
     * @return the board array
     */
    public static int[] getBoard() throws IOException {
        final String FILENAME  = "p3input.txt";
        final int BOARD_ARRAY_SIZE = 100;
    
        int[] board = new int[BOARD_ARRAY_SIZE];
        File myFile = new File(FILENAME);
        Scanner inputFile = new Scanner(myFile);
    
        while (inputFile.hasNext()) {
           int num1 = inputFile.nextInt();
           int  num2 = inputFile.nextInt();
           board[num1] = num2;
        }
    
        inputFile.close();
        return board;
    }

   /**
    * This method creats a random number generator
    *
    * @return the random dice number
    */
    public static int getDice() {
        Random rand = new Random();

        int dice = rand.nextInt(6) + 1;

    return dice;
    }

    /**
     * This method storges the players location in an array and checks if any
     * player's won
     *
     * @param playerName the name array
     * @param board the board array
     */
    public static void gamePlay(String[] playerName, int[] board) {
    
        boolean flag = true;
        int[] playerPos = new int[playerName.length];
        // loop until game over
        do {
            // loop for each player
            for (int i = 0; i < playerName.length; i++) {
    
                String name = playerName[i];
                int pos = playerPos[i];
                int dice = getDice();
                System.out.printf("%s, it's your turn. You are curretnly at space" +
                               " %d\n", name, pos);
                System.out.println("The spin was: " + dice);
                pos = getNewPos(pos, dice, board);
                playerPos[i] = pos;
                System.out.println("You are now at space " + pos);
                System.out.println();
                // check if anybody has won
                if (pos == 100) {
                    System.out.printf("%s, you have won the game!\n", name);
                    flag = false;
                    break;
                }
            }
        } while(flag);
    }
    /**
     * This method updates the player's location and checks for chutes and ladder
     *
     * @param pos the last player location
     * @param dice the spin number
     * @param board the board array
     * @return the updated location
     */
    public static int getNewPos(int pos, int dice, int[] board) {
        pos += dice;
        // if else for checking positin if over 100
        if (pos > 100) {
    
        pos -= dice;
        System.out.println("Sorry, no player can go over 100.");
        }
        else if (pos < 100) {
            int value = board[pos];
            // if else for checking chutes and ladders
            if (value < 0)
                System.out.printf("Sorry, that is a chute!" +
                                "You are sent back %d spaces.\n", -1*value);
            else if (value > 0)
                System.out.printf("Congrats, that is a ladder!" +
                                "You get to climb %d spaces.\n", value);
            pos += value;
        }
     
        return pos;
    }
    /**
     * The method displays a goodbye message
     */
    public static void goodbye() {
       System.out.println("Goodbye, and thanks for playing Chutes & Ladders!");
    }
}
                    