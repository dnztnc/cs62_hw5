/*
 * File: WordleLogic.java
 * -----------------
 * This module is where the logic of the Wordle game lives.
 * You will need to implement most of this functionality yourself.
 */
package wordle;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class WordleLogic {

    /**
     * Enum representing the status of a letter in the Wordle game.
     *
     * @enum LetterStatus
     */
    public enum LetterStatus {
        /**
         * The letter is in the correct position in the target word.
         */
        CORRECT,

        /**
         * The letter is present in the target word but in the wrong position.
         */
        PRESENT,

        /**
         * The letter is not present in the target word.
         */
        MISSING
    }

    /**
     * Class representing the result of a player's guess, including the status of
     * each letter and the overall game outcome.
     */
    public class GuessResult {
        public LetterStatus[] letterStatuses;
        public boolean guessIsCorrect = false;
        public boolean isGameOver = false;
        public String message = "";
        public boolean guessIsInvalid = false;
        public GuessResult() {
            letterStatuses = new LetterStatus[WORD_LEN];
        }
    }

    /**
     * Static game configuration constants.
     */
    private final int MAX_GUESSES = WordleGWindow.N_ROWS;
    private final int WORD_LEN = WordleGWindow.N_COLS;

    /**
     * Dynamic game state variables.
     */

    // TODO: add your instance variables here to keep track of the Wordle game state
    public String hiddenWord;
    private int guessCount;
    private int[] guessStats = new int[6];

    private String getRandomWord() {
        int n = WordleDictionary.FIVE_LETTER_WORDS.length;
        int index = (int)(Math.random() * n);
        return WordleDictionary.FIVE_LETTER_WORDS[index];
    }

    public int[] getStats() {
        return guessStats;
    }

    /**
     * Constructor for WordleLogic. Initializes the game state and loads statistics
     * from file.
     */
    public WordleLogic() {
        loadStats();
    }

    /**
     * Resets the game state to a new game.
     */
    public void reset() {
        guessCount = 0;
        hiddenWord = getRandomWord(); /*WORD ANSWER HERE*/
    }

    private void saveStats() {
        try (PrintWriter out = new PrintWriter("wordle_stats.txt")) {
            for (int i = 0; i < 6; i++) {
                out.println(guessStats[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadStats() {
        try (Scanner in = new Scanner(new File("wordle_stats.txt"))) {
            for (int i = 0; i < 6; i++) {
                guessStats[i] = in.nextInt();
            }
        } catch (Exception e) {
        }
    }



    /**
     * Checks a player's guess against the current answer
     *
     * @param guess
     * @return a GuessResult object containing the status of each letter and game
     *         outcome
     */
    public GuessResult checkGuess(String guess) {
        // answer
        guess = guess.toLowerCase();
        boolean isValid = false;

        GuessResult result = new GuessResult();
        
        result.guessIsInvalid=true;

        for (String word: WordleDictionary.FIVE_LETTER_WORDS){
            if (word.equals(guess)){
                result.message="valid word";
                result.guessIsInvalid=false;
                isValid=true;
                break;
            }
            
        }
        if (isValid){
            guessCount++;

            ArrayList<Character> hiddenWordArr = new ArrayList<>();

            for (int i=0; i<guess.length(); i++){
                if (guess.charAt(i)== hiddenWord.charAt(i)){
                    result.letterStatuses[i]=LetterStatus.CORRECT;
                }
                else{
                    hiddenWordArr.add(hiddenWord.charAt(i));
                }
            }
            if (guess.equals(hiddenWord)){
                result.guessIsCorrect=true;
                result.isGameOver=true;
                result.message="correct answer!";
                guessStats[guessCount - 1]++;   // record guesses used
                saveStats();
                guessCount=0;
                return result;

            }

            for (int i=0; i<guess.length(); i++){
                if (result.letterStatuses[i]!= LetterStatus.CORRECT){
                    for (int j = 0; j < hiddenWordArr.size(); j++) {
                        if (guess.charAt(i) == hiddenWordArr.get(j)) {
                            hiddenWordArr.remove(j);
                            result.letterStatuses[i]=LetterStatus.PRESENT;
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < guess.length(); i++) {
                if (result.letterStatuses[i]== null){
                    result.letterStatuses[i]=LetterStatus.MISSING;
                }
            }

            if (guessCount>=MAX_GUESSES){
                result.isGameOver=true;
                result.message="game over!";
                guessCount=0;
                return result;
            }
            

        }

        if (!isValid){
            result.message="Not in word list";
        }

        return result;
    }

    public void setAnswer(String newAnswer) {
        // TODO: implement the logic to set a new answer for the game (we use this for
        // testing)
        hiddenWord=newAnswer;
    }
    public String getAnswer() {
        // TODO: implement the logic to return the current answer
        return hiddenWord;
    }


    public int getGuessCount() {
        // TODO: implement the logic to return the number of guesses made so far
        return guessCount;
    }

    public static void main(String[] args) {
        // did tests here, but then erased them to avoid having test code in final working game
    }

}
