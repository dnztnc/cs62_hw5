/*
 * File: WordleLogic.java
 * -----------------
 * This module is where the logic of the Wordle game lives.
 * You will need to implement most of this functionality yourself.
 */
package wordle;

// TODO: import any additional packages you need here

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

    /**
     * Constructor for WordleLogic. Initializes the game state and loads statistics
     * from file.
     */
    public WordleLogic() {
        // TODO: initialize your instance variables here
    }

    /**
     * Resets the game state to a new game.
     */
    public void reset() {
        // TODO: implement the logic to reset the game state for a new game
    }

    /**
     * Checks a player's guess against the current answer
     *
     * @param guess
     * @return a GuessResult object containing the status of each letter and game
     *         outcome
     */
    public GuessResult checkGuess(String guess) {
        // TODO: implement the logic to check the player's guess against the current
        // answer
        return null;
    }

    public void setAnswer(String newAnswer) {
        // TODO: implement the logic to set a new answer for the game (we use this for
        // testing)
    }

    public int getGuessCount() {
        // TODO: implement the logic to return the number of guesses made so far
        return 0;
    }

    public static void main(String[] args) {
        // TODO: test just the logic here.
    }

}
