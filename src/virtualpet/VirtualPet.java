/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.util.*;
/**
 *
 * @author michael.roy-diclemen
 */
public class VirtualPet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choosePet = 0;
        int verification = 0;
        String name = "";
        int firstMenu = 0;
        String correctUser = "snoopy";
        String correctPass = "toto";
        int petChosen = 0;
        int alive = 0;
        int coins = 0;
        int[][] stats = new int [3][3];
        int[] statsStore = new int [2];
        //create scanner and random
        Random r = new Random();
        Scanner kb = new Scanner(System.in);
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
        
        for (int i = 0; i < 3; i++){
            //read in user & password
            System.out.println("INPUT USERNAME: "); 
            String username = kb.nextLine();
            System.out.println("INPUT PASSWORD: "); 
            String password = kb.nextLine();
        
        //if password and username correct, increase verification by 1
            if ((username.equals(correctUser)) && (password.equals(correctPass))){
                verification++;
                i = 10;
            }
            else{
            }
        }
        
        if (verification < 1){ 
            System.exit(0);
            }

        //print start menu, take input
        while (firstMenu == 0){
            System.out.println("WELCOME TO DUCKCAT, A PET SIMULATOR GAME! 1) START 2) INSTRUCTIONS 3) EXIT : ");
            String menu = kb.nextLine();
            //move to next screen depending on option chosen
            switch(menu){
                case "START":
                case "1": choosePet = 1;
                firstMenu++;
                break;
                case "2": System.out.println("mewomeoewmeowMEWOEOWOWMEOW"); break;
                case "EXIT":
                case "3": 
                    exitGame(correctUser, correctPass, coins, stats);
                    break;
                default: System.out.println("that is not a valid choice.");break;
            }
        //if moved on to choosing pet, offer pet options
            if (choosePet == 1){
                System.out.println("WHAT PET WILL YOU CHOOSE? 1) DUCK 2) CAT : "); 
            //read in pet wanted and assign based on choice
                String pet = kb.nextLine();
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
                alive++;
                
            }
        
        
        //if verified, give naming options
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
        
        
            while ((firstMenu != 0)&&(alive == 1)){
                
                System.out.println("WELCOME TO THE PET MENU! 1) PLAY/INTERACT 2) INSTRUCTIONS 3) EXIT : ");
                String petMenuResponse = kb.nextLine();
                petMenuResponse = petMenuResponse.toUpperCase();
                switch(petMenuResponse){
                    case "PLAY":
                    case "PLAY/INTERACT":
                    case "1": 
                    case "INTERACT":
                        System.out.println("YOU HAVE " + coins + " COINS.");
                        System.out.println("DO YOU WANT TO: 1) MINIGAME 2) PLAY 3) FEED 4) GROOM ");
                        String interactResponse = kb.nextLine();
                        interactResponse = interactResponse.toUpperCase();
                        switch(interactResponse){
                            case "1":
                            case "MINIGAME":
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
                            case "PLAY":
                                statsStore = playPet(coins, stats[2][1], stats[2][0]);
                                coins = statsStore[0];
                                stats[2][1] = statsStore[1];
                                break;
                            case "3":
                            case "FEED":
                                statsStore = feedPet(coins, stats[1][1], stats[1][0]);
                                coins = statsStore[0];
                                stats[1][1] = statsStore[1];
                                break;
                            case "4":
                            case "GROOM":
                                statsStore = groomPet(coins, stats[0][1], stats[0][0]);
                                coins = statsStore[0];
                                stats[0][1] = statsStore[1];
                                break;
                            default:
                                break;
                        }
                        break;
                    case "2":
                        ;
                        break;
                    case "EXIT":
                    case "3":
                        exitGame(correctUser, correctPass, coins, stats);
                        break;
                    default: System.out.println("that is not a valid choice.");break;
                }
            }
        //end brackets
    }
    
            public static int matchingGame(int coins){
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
        //test lines
                    } 
                    else {
                        scrambledString += defaultString; 
                        defaultString = ""; 
                    }
                }
    //it works! string has been scrambled

                while (matchedPairs.length() < 10){
                    System.out.println("GUESS TWO POSITIONS ON SEPARATE LINES");
                    int firstPos = kb.nextInt();
                    int secondPos = kb.nextInt();
                    firstPos--;
                    secondPos--;
                    guessesMade++;
                    if ((firstPos < 10) && (secondPos < 10) && (firstPos >= 0) && (secondPos >= 0)) {
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
      //System.out.println(scrambledString.charAt(n));
      //System.out.println(matchedPairs);
                }
                System.out.println(showingString);
                showingString = "";
      
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
        
            public static int guessingGame(int coins){
                System.out.println("I'M THINKING OF A NUMBER BETWEEN 1-100... PLEASE GUESS! YOU HAVE 5 TRIES.");
                Scanner guessRead = new Scanner(System.in);
                Random numGen = new Random();
                int correctNumber = numGen.nextInt(100)+1;
                int guess;
                int coinsEarned = 10;
                int correct = 0;
                for (int i = 1; i <= 5; i++){
                    guess = guessRead.nextInt();
                    if (guess == correctNumber){
                        System.out.println("WOW! YOU GOT IT :D");
                        correct++;
                        break;
                    }
                    else if (guess < correctNumber){
                        System.out.println("TRY GUESSING HIGHER!");
                        coinsEarned--;
                    }
                    else{
                        System.out.println("TRY GUESSING LOWER!");
                        coinsEarned--;
                    }
                }
                if (correct < 1){
                    System.out.println("DARN! YOU DIDN'T GET IT! THE NUMBER WAS " + correctNumber);
                }
                return (coins + coinsEarned);
            }
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
        
        public static void exitGame(String username, String password, int coins, int[][] stats){
            
            PrintWriter output = null;
        try {
            File userData = new File(username + ".txt");
            output = new PrintWriter(userData);
            //
            output.println(password);
            output.println(coins);
            for (int i = 0; i < stats.length; i++)
                for (int j = 0; j < stats[i].length; j++){
                    output.println(stats[i][j]);
                }
            //
            System.out.println("GAME EXITED AND SAVED! GO HOME.");
        } catch (FileNotFoundException ex) {
            System.out.println("CAN'T WRITE TO FILE. YOUR PET WILL NOT BE SAVED.");
        } finally {
            output.close();
            System.exit(0);
        }
        } 
    }
                     
 




           