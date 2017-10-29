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
    @Test
    public void testGetBoardNextItem() {
        assertEquals('#', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.UP));
        assertEquals('#', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.LEFT));
        assertEquals('#', smallBoard.getBoardNextItem(0, 0, GameBoard.Direction.LEFT));
        assertEquals('#', smallBoard.getBoardNextItem(0, 1, GameBoard.Direction.UP));
        assertEquals(' ', smallBoard.getBoardNextItem(0, 1, GameBoard.Direction.LEFT));
        assertEquals('#', smallBoard.getBoardNextItem(-50, -10, GameBoard.Direction.DOWN));
        assertEquals('.', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.DOWN));
        assertEquals('*', smallBoard.getBoardNextItem(1, 2, GameBoard.Direction.LEFT));
        assertEquals('O', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.RIGHT));
        assertEquals('O', smallBoard.getBoardNextItem(2, 3, GameBoard.Direction.RIGHT));
        assertEquals('A', smallBoard.getBoardNextItem(2, 4, GameBoard.Direction.UP));
    }
    @Test
    public void testGetBoardNextItemWithNewPostion() {
    	assertEquals('O', smallBoard.getBoardNextItem(2, 3, GameBoard.Direction.RIGHT));
    	smallBoard.setBoxPosition(1, 2, 2);
    	assertEquals('.', smallBoard.getBoardNextItem(2, 3, GameBoard.Direction.RIGHT));
    	assertEquals('O', smallBoard.getBoardNextItem(2, 1, GameBoard.Direction.RIGHT));
    	
    	assertEquals('A', smallBoard.getBoardNextItem(2, 4, GameBoard.Direction.UP));
    	smallBoard.setPlayerPosition(2, 3);
    	assertEquals('.', smallBoard.getBoardNextItem(2, 4, GameBoard.Direction.UP));
    	assertEquals('A', smallBoard.getBoardNextItem(1, 3, GameBoard.Direction.DOWN));
    }
}