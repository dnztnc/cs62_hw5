/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */
package wordle;

import wordle.WordleLogic.GuessResult;

// TODO: You will need to add imports here

public class Wordle {

    private WordleLogic logic;
    private WordleGWindow gw;
    private boolean showingStats = false;


    private void startNewGame() {
            gw.setCurrentRow(0);
            for (int r=0; r<WordleGWindow.N_ROWS; r++){
                for (int c=0; c<WordleGWindow.N_COLS; c++){
                    gw.setSquareLetter(r,c,"");
                    gw.setSquareColor(r,c,WordleGWindow.DEFAULT_SQUARE_COLOR);
                }
            }
            for (char ch = 'A'; ch <= 'Z'; ch++){
                gw.setKeyColor(Character.toString(ch), WordleGWindow.DEFAULT_KEY_COLOR);
            }
            logic.reset();


    }
    private void showStatsScreen(int[] stats) {
        gw.showMessage("Press ENTER to play again");

        // clear board
        for (int r = 0; r < WordleGWindow.N_ROWS; r++) {
            for (int c = 0; c < WordleGWindow.N_COLS; c++) {
                gw.setSquareLetter(r, c, "");
                gw.setSquareColor(r, c, WordleGWindow.DEFAULT_SQUARE_COLOR);
            }
        }

        // draw rows 1..6 on left + counts on right
        for (int r = 0; r < 6; r++) {
            gw.setSquareLetter(r, 0, Integer.toString(r + 1));
            gw.setSquareColor(r, 0, WordleGWindow.CORRECT_COLOR); 

            String count = Integer.toString(stats[r]); 
            if (count.length() == 1) {
                gw.setSquareLetter(r, 4, count);         
            } else {
                gw.setSquareLetter(r, 3, count.substring(0, 1));
                gw.setSquareLetter(r, 4, count.substring(1, 2));
            }
        }

        }


    /*
     * Called when the user hits the RETURN key or clicks the ENTER button,
     * passing in the string of characters on the current row.
     */
    public void enterAction(String s) {
        
        if (showingStats) {
            showingStats = false;
            startNewGame();
            return;
        }


        GuessResult result = logic.checkGuess(s);
        gw.showMessage(result.message);
                if (result.isGameOver){
                javax.swing.Timer t =new javax.swing.Timer(2000, e -> {
                    showStatsScreen(logic.getStats());
                    showingStats = true;
                });
                t.setRepeats(false);
                t.start();
        }

        if (!result.guessIsInvalid){
            for (int i=0; i<result.letterStatuses.length; i++){
                if (result.letterStatuses[i]== WordleLogic.LetterStatus.CORRECT){
                    gw.setSquareColor(gw.getCurrentRow(),i,WordleGWindow.CORRECT_COLOR);
                    gw.setKeyColor(Character.toString(s.charAt(i)), WordleGWindow.CORRECT_COLOR);
                }
                else if (result.letterStatuses[i]== WordleLogic.LetterStatus.PRESENT){
                    gw.setSquareColor(gw.getCurrentRow(),i,WordleGWindow.PRESENT_COLOR);
                    if(gw.getKeyColor(Character.toString(s.charAt(i)))!= WordleGWindow.CORRECT_COLOR)
                    {gw.setKeyColor(Character.toString(s.charAt(i)), WordleGWindow.PRESENT_COLOR);}
                }
                else{
                    gw.setSquareColor(gw.getCurrentRow(),i,WordleGWindow.MISSING_COLOR);
                    gw.setKeyColor(Character.toString(s.charAt(i)), WordleGWindow.MISSING_COLOR);
                }
            }
            

            if (result.guessIsCorrect){
                gw.showMessage("You win!");
            }
            else if (result.isGameOver){
                gw.showMessage("Game over! The word was: " + logic.getAnswer());
            }
                else{
                    gw.showMessage("Try again!");
                    gw.setCurrentRow(gw.getCurrentRow()+1);
        }
        }}

    public void run() {
        logic = new WordleLogic();
        gw = new WordleGWindow();
        startNewGame();
        gw.addEnterListener((s) -> enterAction(s));

    }

    public static void main(String[] args) {
        new Wordle().run();
    }

}
