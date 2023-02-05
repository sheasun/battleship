/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ss1316.battleship;

import java.io.*;

public class App {

    private final Board<Character> theBoard;
    private final BoardTextView view;
    private final BufferedReader inputReader;
    private final PrintStream out;

    private final AbstractShipFactory<Character> shipFactory;

    // public String getGreeting() {
    //     return "Hello World!";
    // }
    public App(Board<Character> theBoard, Reader inputSource, PrintStream out) {
        this.theBoard = theBoard;
        this.view = new BoardTextView(theBoard);
        this.inputReader = new BufferedReader(inputSource);
        this.out = out;
        this.shipFactory = new V1ShipFactory();
    }

    public Placement readPlacement(String prompt) throws IOException {
        out.println(prompt);
        String s = inputReader.readLine();
        return new Placement(s);
    }

    public void doOnePlacement() throws IOException {
        String prompt = "Where would you like to put your ship?";
        Placement p = this.readPlacement(prompt);
        this.theBoard.tryAddShip(new RectangleShip<Character>(p.getCoordinate(), 's', '*'));
        out.println(this.view.displayMyOwnBoard());
    }

    public static void main(String[] args) throws IOException {
        Board<Character> board = new BattleShipBoard<>(10, 20);
        InputStreamReader in = new InputStreamReader(System.in);
        App app = new App(board, in, System.out);
        app.doOnePlacement();
    }
}
