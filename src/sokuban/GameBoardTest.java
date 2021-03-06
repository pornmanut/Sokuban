package sokuban;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {

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
	public void testLoadBoardProperties() {
		assertEquals(5,smallBoard.getHeight());
		assertEquals(6,smallBoard.getWidth());

		assertArrayEquals(new String[] {
				" #####",
				"#*...#",
				"#....#",
				"##..*#",
				" #####"
				},smallBoard.getBaseBoard());
	}
	
	@Test
	public void testLoadBoardPlayerPosition() {
		assertEquals(1,smallBoard.getPlayerRow());
		assertEquals(4,smallBoard.getPlayerCol());
	}
	@Test
	public void testLoadBoardNumBoxes() {
		assertEquals(2,smallBoard.getNumBoxes());
	}
	@Test
	public void testLoadBoardBoxPositions() {
		assertArrayEquals(new int[] {1,2},smallBoard.getBoxPosition(0));
		assertArrayEquals(new int[] {2,4},smallBoard.getBoxPosition(1));
	}
	@Test
	public void testPlayerPositionCheck() {
		assertFalse(smallBoard.hasPlayerAt(1, 2));
		assertTrue(smallBoard.hasPlayerAt(1, 4));
	}
	@Test
	public void testPlayerPositionCheckOutside() {
	    assertFalse(smallBoard.hasPlayerAt(-10, 1));
        assertFalse(smallBoard.hasPlayerAt(1, -10));
        assertFalse(smallBoard.hasPlayerAt(1, 100));
        assertFalse(smallBoard.hasPlayerAt(100, 1));
	}
	@Test
	public void testPlayerPositionCheckAfterChange() {
		smallBoard.setPlayerPosition(2, 3);
        assertFalse(smallBoard.hasPlayerAt(1,4));
        assertTrue(smallBoard.hasPlayerAt(2, 3));	
	}
	@Test
	public void testBoxPositionCheckAfterChange() {
		assertArrayEquals(new int[] {1,2},smallBoard.getBoxPosition(0));
		smallBoard.setBoxPosition(0, 3, 4);
		assertArrayEquals(new int[] {3,4},smallBoard.getBoxPosition(0));
	}
	@Test
	public void testExitPositionCheck() {
		assertFalse(smallBoard.hasExitAt(0, 0));
		assertFalse(smallBoard.hasExitAt(0, 3));
		assertFalse(smallBoard.hasExitAt(1, 2));
		assertFalse(smallBoard.hasExitAt(1, 4));
		assertFalse(smallBoard.hasExitAt(-30, 4));
		assertTrue(smallBoard.hasExitAt(1, 1));
		assertTrue(smallBoard.hasExitAt(3, 4));
	}
	@Test
	public void TestBoxPostionCheck() {
		assertFalse(smallBoard.hasBoxAt(0, 0));
		assertFalse(smallBoard.hasBoxAt(0, 3));
		assertFalse(smallBoard.hasBoxAt(1, 4));
		assertTrue(smallBoard.hasBoxAt(1, 2));
		assertTrue(smallBoard.hasBoxAt(2, 4));
		assertFalse(smallBoard.hasBoxAt(-50, 10));
	}
    @Test
    public void testToStringWithInitialPositions() {
        assertEquals(		
                " #####\n"+
                "#*O.A#\n"+
                "#...O#\n"+
                "##..*#\n"+
                " #####\n",
                smallBoard.toString());
        
        smallBoard.setPlayerPosition(2, 3);
        assertEquals(		
                " #####\n"+
                "#*O..#\n"+
                "#..AO#\n"+
                "##..*#\n"+
                " #####\n",
                smallBoard.toString());
        
        smallBoard.setBoxPosition(1,1, 4);
        assertEquals(		
                " #####\n"+
                "#*O.O#\n"+
                "#..A.#\n"+
                "##..*#\n"+
                " #####\n",
                smallBoard.toString());
    }

}
