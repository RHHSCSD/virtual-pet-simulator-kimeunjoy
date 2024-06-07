/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author joykim
 */
public class VP {
    
    
    //start of main
    public static void main(String[] args) {
        
        //initialize variables
        
        Random r = new Random();
        Scanner kb = new Scanner(System.in);
        
        String name = "";
        int choosePet = 0;
        int alive = 0;
        int coins = 0;
        String pet = "";
        int verification = 0;
        int petChosen = 0;
        String password = "";
        int[][] stats = new int [3][3];
        int[] statsStore = new int [2];
        
        //print splash screen
        System.out.println("             ／^__/^");
        System.out.println("             |　_　_ | ");
        System.out.println("            ／`ミ＿xノ ");
        System.out.println("           /　　　  |");
        System.out.println("          /　 ヽ　  ﾉ     ");
        System.out.println("          │　　|　|　");
        System.out.println("      ／￣|　 |　|　|   ");
        System.out.println("      (￣ヽ＿_ヽ_)__)   ");
        System.out.println("       ＼二) \n");
        System.out.println("\tDUCKCAT: a pet simulator game\n");
        
        //ask for username
        
        System.out.println("INPUT USERNAME: "); 
        String username = kb.nextLine();
        
        //see if save file with user exists
        
        File f = new File(username+".txt");
        try {
            //load stats in
                Scanner fileInitRead = new Scanner(f);
                String corrPassword = fileInitRead.nextLine();
                coins = fileInitRead.nextInt();

                for (int i = 0; i < stats.length; i++)
                    for (int j = 0; j < stats[i].length; j++){
                        (stats[i][j]) = fileInitRead.nextInt();
                }
                
                fileInitRead.nextLine();

                pet = fileInitRead.nextLine();

                //if password is guessed over three times, verify. if not, exit
                fileInitRead.close();
                for (int i = 0; i < 3; i++){
                    System.out.println("INPUT PASSWORD: "); 
                    password = kb.nextLine();
                    if (password.equals(corrPassword)){
                        verification++;
                        
                        break;
                    }
                }
                if (verification==0){
                    System.out.println("INCORRECT!");
                    System.exit(0);
                }
                
                //if no save file found, proceed with creating user
        }
        catch (FileNotFoundException ex) {
            
            //create new password, ask for pet
                System.out.println("INPUT NEW PASSWORD: "); 
                password = kb.nextLine();
            
                System.out.println("WELCOME TO DUCKCAT, A PET SIMULATOR GAME!");
                System.out.println("WHAT PET WILL YOU CHOOSE? 1) DUCK 2) CAT : "); 
            //read in pet wanted and assign based on choice
                    pet = kb.nextLine();
                    switch(pet){
                        case "DUCK":
                        case "1": pet = "DUCK"; break;
                        case "CAT":
                        case "2": pet = "CAT";break;
                        default: System.out.println("THAT IS NOT A PET OPTION."); System.exit(0); break;
                    }
                //print chosen pet
                System.out.println("YOU HAVE CHOSEN " + pet);
                petChosen++;
                
                if (petChosen == 1){
                    name = nameCreate();
                            }
                    
                    System.out.println("WELCOME " + name);
                    
                    //array 0 in primary array is health, 1 is food, 2 is energy
                //item 0 in each secondary array is max, 1 is current, 2 is amount of times said option has been picked
                    
                    int STARTPOOL = 20; 
                //max health
                    stats[0][0] = r.nextInt(15,STARTPOOL)-10;
                    stats[0][1] = stats[0][0];
                    stats[0][2] = 0;
                    STARTPOOL -= stats[0][0];
                //max food
                    stats[1][0] = r.nextInt(7,STARTPOOL)-2; 
                    stats[1][1] = stats[1][0];
                    stats[1][2] = 0;
                    STARTPOOL -= stats[0][1];
                //max energy
                    stats[2][0] = STARTPOOL;
                    stats[2][1] = stats[2][0];
                    stats[2][2] = 0;
                    
            }
        
        //start game loop
        alive++;            
                    
        while (alive == 1){
            //print out main pet menu
                System.out.println("WELCOME TO THE PET MENU! 1) PLAY/INTERACT 2) INSTRUCTIONS 3) EXIT : ");
                String petMenuResponse = kb.nextLine();
                petMenuResponse = petMenuResponse.toUpperCase();
                switch(petMenuResponse){
                    case "PLAY":
                    case "PLAY/INTERACT":
                    case "1": 
                    case "INTERACT":
                        //print number of coins, provide activity options to user
                        System.out.println("YOU HAVE " + coins + " COINS.");
                        System.out.println("DO YOU WANT TO: 1) MINIGAME 2) PLAY 3) FEED 4) GROOM ");
                        String interactResponse = kb.nextLine();
                        interactResponse = interactResponse.toUpperCase();
                        switch(interactResponse){
                            case "1":
                            case "MINIGAME":
                                //ask what wants to be played if minigame picked
                                System.out.println("WHAT DO YOU WANT TO PLAY: 1) NUMBER GUESSING GAME 2) MATCHING GAME:");
                                String gameChosen = kb.nextLine();
                                gameChosen = gameChosen.toUpperCase();
                                switch(gameChosen){
                                    case "1":
                                    case "NUMBER GUESSING GAME": 
                                        coins = guessingGame(coins);
                                        break;
                                    case "2":
                                    case "MATCHING GAME": 
                                        coins = matchingGame(coins);
                                        break;
                                    default: break;
                                }
                                break;
                            case "2":
                                //if play, feed, or groom picked, call according method and feed in stats. modify current stats via returned array
                            case "PLAY":
                                statsStore = playPet(coins, stats[2][1], stats[2][0]);
                                coins = statsStore[0];
                                stats[2][1] = statsStore[1];
                                stats[2][2]++;
                                break;
                            case "3":
                            case "FEED":
                                statsStore = feedPet(coins, stats[1][1], stats[1][0]);
                                coins = statsStore[0];
                                stats[1][1] = statsStore[1];
                                stats[1][2]++;
                                break;
                            case "4":
                            case "GROOM":
                                statsStore = groomPet(coins, stats[0][1], stats[0][0]);
                                coins = statsStore[0];
                                stats[0][1] = statsStore[1];
                                stats[0][2]++;
                                break;
                            default:
                                break;
                        }
                        break;
                    case "2":
                        System.out.println("INSTRUCTIONS.");
                        break;
                    case "EXIT":
                    case "3":
                        //call exit method
                        exitGame(username, password, coins, stats, pet);
                        break;
                        //remind user that choice is incorrect if inputs nonsense
                    default: System.out.println("that is not a valid choice.");break;
            }
        }
        //end of game loop
        
    }
    //end of main
    
    //start of matching game method
    public static int matchingGame(int coins){
        //scramble string with random
        String defaultString = "aabbccddee";
        String scrambledString = ""; 
        Random scrambler = new Random(); 
                Scanner kb = new Scanner(System.in);
                int randomPos = 0; 
                String referenceString = ""; 
                String showingString = "";
                String matchedPairs = "";
                int guessesMade = 0;
      
                while (defaultString.length() != 0) { 
                    if (defaultString.length() > 1) { 
                        randomPos = scrambler.nextInt(defaultString.length()-1); 
                        scrambledString += defaultString.charAt(randomPos); 
        
                        for (int i = 0; i < randomPos; i++) {
                            referenceString += defaultString.charAt(i); 
                        }
                        for (int i = randomPos + 1; i < defaultString.length(); i++) {
                            referenceString += defaultString.charAt(i); 
                        }
                        defaultString = referenceString; 
                        referenceString = ""; 
        //

                    } 
                    else {
                        scrambledString += defaultString; 
                        defaultString = ""; 
                    }
                }
                //while haven't matched all, ask user to guess positions
                while (matchedPairs.length() < 10){
                    System.out.println("GUESS TWO POSITIONS ON SEPARATE LINES");
                    int firstPos = kb.nextInt();
                    int secondPos = kb.nextInt();
                    //minus one from positions to align with java array logic, increase guesses made
                    firstPos--;
                    secondPos--;
                    guessesMade++;
                    //check to see if input is valid
                    if ((firstPos < 9) && (secondPos < 9) && (firstPos >= 0) && (secondPos >= 0)) {
                        if (firstPos == secondPos){
                            System.out.println("CHEATING CHEATER! THESE ARE THE SAME POSITION!");
                        }
                        else if (matchedPairs.contains(String.valueOf(scrambledString.charAt(firstPos)))){
                            System.out.println("SILLY YOU! YOU HAVE ALREADY MATCHED AT LEAST ONE OF THESE LETTERS!");
                        }
                        else if (scrambledString.charAt(firstPos) == scrambledString.charAt(secondPos)){
                            System.out.println("IT'S A MATCH!");
                            matchedPairs += "" + scrambledString.charAt(firstPos) + scrambledString.charAt(secondPos);
                        }
                        else{
                            System.out.println("THAT WAS NOT A MATCH!");
                        }
                    }
                    else{
                        System.out.println("HI! YOU DID NOT ENTER TWO VALID POSITIONS.");
                    }
      //print out each character
                for (int n = 0; n < 10; n++){
                    if (matchedPairs.contains(String.valueOf(scrambledString.charAt(n)))){
                        showingString += (scrambledString.charAt(n));
                    }
                    else{
                        showingString += '*';
                    }
                }
                System.out.println(showingString);
                showingString = "";
                
                //return coins based on guesses taken
      
                } 
                if (guessesMade < 5){
                    return (coins + 10);
                }
                else if (guessesMade < 8){
                    return (coins + 7);
                }
                else{
                    return (coins + 5);
                }
            }
            //end of matching game
    
    
    //start of guessing number game
    public static int guessingGame(int coins){
        //rangen a number, take input
                System.out.println("I'M THINKING OF A NUMBER BETWEEN 1-100... PLEASE GUESS! YOU HAVE 5 TRIES.");
                Scanner guessRead = new Scanner(System.in);
                Random numGen = new Random();
                int correctNumber = numGen.nextInt(100)+1;
                int guess;
                int coinsEarned = 10;
                int correct = 0;
                //allow five tries. deduct a coin for each incorrect guess
                for (int i = 1; i <= 5; i++){
                    guess = guessRead.nextInt();
                    if (guess == correctNumber){
                        //if guess correct, say so and end game
                        System.out.println("WOW! YOU GOT IT :D");
                        correct++;
                        break;
                        //if guess too low, say so
                    }
                    else if (guess < correctNumber){
                        System.out.println("TRY GUESSING HIGHER!");
                        coinsEarned--;
                    }
                    //if guess too high, say so
                    else{
                        System.out.println("TRY GUESSING LOWER!");
                        coinsEarned--;
                    }
                }
                //if not gotten, say so and print correct
                if (correct < 1){
                    System.out.println("DARN! YOU DIDN'T GET IT! THE NUMBER WAS " + correctNumber);
                }
                //return coins + those earned
                return (coins + coinsEarned);
            }
    //end of guessing number game
    
    //start of name creator function
    public static String nameCreate(){
            Scanner kb = new Scanner(System.in);
            Random r = new Random();
            String name = "";
            System.out.println("A) NAME PET YOURSELF B) RAN. GEN A NAME (INPUT LETTER): "); 
            String nameOption = kb.nextLine();
            nameOption = nameOption.toUpperCase();
            int vowel = 0;
    //read in name option; if wants to name by self, do so. if wants ran gen, random gen
            switch (nameOption){
                case "A": 
                    name = kb.nextLine();
                    break;
                case "B": 
                    String letters = "aeioubcdfghjklmnpqrstvwxyz";
                    String uppercase = "AEIOUBCDFGHJKLMNPQRSTVWXYZ";
                    String vowels = "aeiou";
                    String consonants = "bcdfghjklmnpqrstvwxyz";
                    int addLetters = r.nextInt(3,8);
                    int firstLetterPos = r.nextInt(0,26);
                    int letterPos = 1;
                    int dupe = 0;

                    if (firstLetterPos > 5){
                        vowel = 1;
                        }


                    char currentLetter = uppercase.charAt(firstLetterPos);
                    name += "" + currentLetter;

                    //name += "" + (letters.charAt(r.nextInt(5,26))) + (letters.charAt(r.nextInt(5))) + (letters.charAt(r.nextInt(5,26)));
                    for (int i = 0; i < addLetters; i++ ){
                        if (vowel == 1){
                            currentLetter = (vowels.charAt(r.nextInt(5)));
                            name += "" + currentLetter;
                            vowel = 0;
                            dupe = 0;
                            }
                        else{
                            if ((r.nextInt(4) == 1)&&(letterPos > 1)&&(dupe ==0)){
                                name += "" + currentLetter;
                                dupe = 1;
                                }
                            else{
                                currentLetter = (consonants.charAt(r.nextInt(21)));
                                name += "" + currentLetter;
                                vowel = 1; 
                                dupe = 0;
                                }
                        }
                        letterPos++; 
                        }
                    break;
                    default: break;
                    }
        return name;  
        } 
    //end of name creator function
    
    //energy/play
    public static int[] playPet(int coins, int energy, int maxEnergy){
            //create an array to store coins and food
            int[]coinsAndEnergy = new int[2];
            if (coins > 3){
                System.out.println("YIPPEE! YOU PLAYED WITH YOUR PET.");
                energy += 2;
                coins -= 3;
                if (energy > maxEnergy)
                    energy = maxEnergy;
                }
            else
                System.out.println("ARGHH!!! YOU DON'T HAVE ENOUGH MONEY.");
            coinsAndEnergy[0] = coins;
            coinsAndEnergy[1] = energy;
        return coinsAndEnergy;
        }
        
        //feed
        
    public static int[] feedPet(int coins, int food, int maxFood){
            //create an array to store coins and food
            int[]coinsAndFood = new int[2];
            if (coins > 3){
                System.out.println("YIPPEE! YOU FED YOUR PET.");
                food += 2;
                coins -= 3;
                if (food > maxFood)
                    food = maxFood;
                }
            else
                System.out.println("ARGHH!!! YOU DON'T HAVE ENOUGH MONEY.");
            coinsAndFood[0] = coins;
            coinsAndFood[1] = food;
        return coinsAndFood;
        }
        
        //health/groom
        
    public static int[] groomPet(int coins, int health, int maxHealth){
            //create an array to store coins and food
            int[]coinsAndHealth = new int[2];
            if (coins > 3){
                System.out.println("YIPPEE! YOU GROOMED YOUR PET.");
                health += 2;
                coins -= 3;
                if (health > maxHealth)
                    health = maxHealth;
                }
            else
                System.out.println("ARGHH!!! YOU DON'T HAVE ENOUGH MONEY.");
            coinsAndHealth[0] = coins;
            coinsAndHealth[1] = health;
        return coinsAndHealth;
        }
    
    public static void exitGame(String username, String password, int coins, int[][] stats,  String pet){
            
            PrintWriter output = null;
        try {
            //create file with username, create printwriter
            File userData = new File(username + ".txt");
            output = new PrintWriter(userData);
            //output password, coins, and statistics to file
            output.println(password);
            output.println(coins);
            for (int i = 0; i < stats.length; i++)
                for (int j = 0; j < stats[i].length; j++){
                    output.println(stats[i][j]);
                }
            output.println(pet);
            //print success message
            System.out.println("YOU HAVE GROOMED YOUR PET " + stats[0][2] + " TIMES!");
            if (stats[0][2] > 5)
                System.out.println("YOU HAVE ACHIEVED AVID BRUSHER!");
            System.out.println("YOU HAVE FED YOUR PET " + stats[1][2] + " TIMES!");
            if (stats[1][2] > 5)
                System.out.println("YOU HAVE ACHIEVED AVID FEEDER!");
            System.out.println("YOU HAVE PLAYED WITH YOUR PET " + stats[2][2] + " TIMES!");
            if (stats[2][2] > 5)
                System.out.println("YOU HAVE ACHIEVED AVID PLAYER!");
            System.out.println("GAME EXITED AND SAVED! GO HOME.");
            //if file nonexistent, print error message
        } catch (FileNotFoundException ex) {
            System.out.println("CAN'T WRITE TO FILE. YOUR PET WILL NOT BE SAVED.");
        } finally {
            //close file and exit program
            output.close();
            System.exit(0);
        }
        } 
    
}
//end of class
