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
    @Test
    public void testPlayerCanMove() {
    	assertFalse(smallBoard.canPlayerMove(GameBoard.Direction.UP));
    	assertFalse(smallBoard.canPlayerMove(GameBoard.Direction.RIGHT));
    	assertTrue(smallBoard.canPlayerMove(GameBoard.Direction.DOWN));
    	assertTrue(smallBoard.canPlayerMove(GameBoard.Direction.LEFT));
    	smallBoard.setPlayerPosition(3, 3);
    	assertTrue(smallBoard.canPlayerMove(GameBoard.Direction.UP));
    	assertTrue(smallBoard.canPlayerMove(GameBoard.Direction.RIGHT));
    	assertFalse(smallBoard.canPlayerMove(GameBoard.Direction.DOWN));
    	assertTrue(smallBoard.canPlayerMove(GameBoard.Direction.LEFT));
    }
    //@Test
    public void testPlayerMove() {
    	assertTrue(smallBoard.hasPlayerAt(1, 4));
    	smallBoard.movePlayer(GameBoard.Direction.DOWN);
    	assertTrue(smallBoard.hasPlayerAt(1, 4));
    	smallBoard.movePlayer(GameBoard.Direction.LEFT);
    	assertFalse(smallBoard.hasPlayerAt(1, 4));
    	assertTrue(smallBoard.hasPlayerAt(1, 3));
    	smallBoard.movePlayer(GameBoard.Direction.UP);
    	assertTrue(smallBoard.hasPlayerAt(1, 3));
    	smallBoard.movePlayer(GameBoard.Direction.DOWN);
    	assertFalse(smallBoard.hasPlayerAt(1, 3));
    	assertTrue(smallBoard.hasPlayerAt(2, 3));
    }
    @Test
    public void testPlayerCanMoveBlock() {
    	assertTrue(smallBoard.canPlayerMove(GameBoard.Direction.DOWN));
    	smallBoard.setPlayerPosition(2, 3);
    	assertFalse(smallBoard.canPlayerMove(GameBoard.Direction.RIGHT));
    	smallBoard.setPlayerPosition(1, 4);
    	smallBoard.setBoxPosition(1, 1, 3);
    	assertFalse(smallBoard.canPlayerMove(GameBoard.Direction.LEFT));
    }
    	
    	
}