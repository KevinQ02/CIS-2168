import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {

    public static void main(String[] args){
        boolean gameGoing = true;

        while(gameGoing) {
            HashMap<String, Integer> words = new HashMap<>();
            String fileName = "words.txt";
            int size = 0;
            int wrongGuesses = 0;
            Set<String> wordList;
            HashSet<Character> guessedLetters = new HashSet<>();


            try { //add words from file
                Scanner scanner = new Scanner(new File(fileName));
                while (scanner.hasNextLine()) {
                    String word = scanner.nextLine().toLowerCase();
                    words.put(word, word.length());
                }
                scanner.close();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            Scanner scan = new Scanner(System.in);
            // add size of word
            while (true) {
                System.out.println("Enter length of word:");
                size = scan.nextInt();
                scan.nextLine();
                if (words.containsValue(size)) break;
                System.out.println("Invalid size");
            }
            //add amt of guesses
            System.out.println("Enter max number of wrong guesses allowed:");
            wrongGuesses = scan.nextInt();
            scan.nextLine();

            words.values().retainAll(Collections.singleton(size));

            wordList = words.keySet();
            //Game loop
            while (true) {
                System.out.println("Guessed letters: " + guessedLetters);
                System.out.println("Guesses Remaining: " + wrongGuesses);
                char guess;
                while (true) {
                    System.out.println("Enter next guess");
                    guess = scan.nextLine().toLowerCase().charAt(0);
                    if (Character.isLetter(guess)) {
                        if (!guessedLetters.contains(guess)) {
                            guessedLetters.add(guess);
                            break;
                        }
                        System.out.println("Letter already guessed");
                    } else {
                        System.out.println("Error: Letter not entered");
                    }
                }

                HashMap<String, Set<String>> wordConfigurations = new HashMap<>();
                for (String word : wordList) {
                    String configuration = "";
                    for (Character c : word.toCharArray()) {
                        if (c == guess) configuration += c;
                        else configuration += "-";
                    }
                    if (!wordConfigurations.containsKey(configuration)) wordConfigurations.put(configuration, new HashSet<>());
                    wordConfigurations.get(configuration).add(word);
                }
                HashSet<String> largestSet = new HashSet<>();
                String letterConfiguration = "";
                for (String config : wordConfigurations.keySet()) {
                    if (wordConfigurations.get(config).size() > largestSet.size()) {
                        letterConfiguration = config;
                        largestSet = (HashSet<String>) wordConfigurations.get(config);
                    }
                }
                wordList = largestSet;
                if (letterConfiguration.contains("" + guess)) {
                    System.out.println("Correct guess");
                }
                else {
                    System.out.println("Wrong guess");
                    wrongGuesses--;
                }
                boolean wordGuessed = true;
                if (wordList.size() != 1) wordGuessed = false;
                else {
                    for (char c: wordList.toArray()[0].toString().toCharArray()) {
                        if (!guessedLetters.contains(c)) {
                            wordGuessed = false;
                            break;
                        }
                    }
                }
                //Game over
                if (wrongGuesses == 0 || wordGuessed) {
                    if (wrongGuesses == 0) {
                        System.out.println("Sorry you're out of guesses");
                    }
                    else {
                        System.out.println("You win");
                    }
                    System.out.println("The word was " + wordList.toArray()[0]);
                    System.out.println("Y to play again");
                    char c = scan.nextLine().toLowerCase().charAt(0);
                    if (c != 'y') gameGoing = false;
                    break;
                }
                else {
                    String progress = "";
                    for (char c: ((String) wordList.toArray()[0]).toCharArray()) {
                        if (guessedLetters.contains(c)) progress += c;
                        else progress += "_";
                    }
                    System.out.println("Current Progress: " + progress);

                    //hidden word list
                    System.out.println("Hidden Words " + wordList.size());
                    System.out.println("Hidden Words " + wordList);
                }
            }
        }

    }
}

/*
sources: Youtube videos professor Rosen, GitHub, StackOverflow, ChatGpt
 */