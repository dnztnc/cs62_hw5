/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */
package wordle;

// TODO: You will need to add imports here

public class Wordle {

    private WordleLogic logic;
    private WordleGWindow gw;

    // TODO: You will need to add additional instance variables here

    // TODO: You will need to add additional methods here

    /*
     * Called when the user hits the RETURN key or clicks the ENTER button,
     * passing in the string of characters on the current row.
     */
    public void enterAction(String s) {
        gw.showMessage("You have to implement this method.");
    }

    public void run() {
        logic = new WordleLogic();
        gw = new WordleGWindow();
        gw.addEnterListener((s) -> enterAction(s));
    }

    public static void main(String[] args) {
        new Wordle().run();
    }

}
