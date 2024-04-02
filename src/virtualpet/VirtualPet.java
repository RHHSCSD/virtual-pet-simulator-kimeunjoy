/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
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
        String correctUser = "snoopy";
        String correctPass = "toto";
        //create scanner and random
        Random r = new Random();
        Scanner kb = new Scanner(System.in);
        //print splash screen
        System.out.println("             ／^__/^");
        System.out.println("             |　_　_| ");
        System.out.println("            ／`ミ＿xノ ");
        System.out.println("           /　　　  |");
        System.out.println("          /　 ヽ　  ﾉ     ");
        System.out.println("          │　　|　|　");
        System.out.println("      ／￣|　 |　|　|   ");
        System.out.println("      (￣ヽ＿_ヽ_)__)   ");
        System.out.println("       ＼二) \n");
        System.out.println("\tDUCKCAT: a pet simulator game\n");
        //read in user & password
        System.out.println("INPUT USERNAME: "); 
        String username = kb.nextLine();
        System.out.println("INPUT PASSWORD: "); 
        String password = kb.nextLine();
        
        //if password and username correct, increase verification by 1
        if ((username.equals(correctUser)) && (password.equals(correctPass))){
        }
        else{
            System.exit(0);
        }
        //print start menu, take input
        System.out.println("WELCOME TO DUCKCAT, A PET SIMULATOR GAME! 1) START 2) INSTRUCTIONS 3) EXIT : ");
        String menu = kb.nextLine();
        //move to next screen depending on option chosen
        switch(menu){
            case "START":
            case "1": choosePet = 1;
            break;
            case "2": System.out.println("mewomeoewmeowMEWOEOWOWMEOW"); break;
            case "EXIT":
            case "3": System.exit(0); break;
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
        }
        
        
        //if verified, give naming options
        System.out.println("A) NAME PET YOURSELF B) RAN. GEN A NAME (INPUT LETTER): "); 
        String nameOption = kb.nextLine();
        nameOption = nameOption.toUpperCase();
        int vowel = 0;
            //read in name option; if wants to name by self, do so. if wants ran gen, random gen
        switch (nameOption){
            case "A": 
                name = kb.nextLine();
                System.out.println(name);
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
                default: System.exit(0); break;
                }
                    
        System.out.println(name);
                    
        int STARTPOOL = 20;
        int MAXHEALTH = r.nextInt(15,STARTPOOL)-10;
        STARTPOOL -= MAXHEALTH;
        int MAXFOOD = r.nextInt(7,STARTPOOL)-2; 
        STARTPOOL -= MAXHEALTH;
        int MAXENERGY = STARTPOOL;
            } 
                        
        }
                    





           