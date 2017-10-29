package sokuban;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameBoardMovementTest {
    static String smallBoardMap[] = {
            " #####",
            "#*O.A#",
            "#...O#",
            "##..*#",
            " #####"
        };
 
    private GameBoard smallBoard;
 
    @Before
    public void setUp() {
        smallBoard = new GameBoard(smallBoardMap);
    }
}