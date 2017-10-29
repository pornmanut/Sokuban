package sokuban;

public class GameBoard {
	private int height;
	private int width;
	private String[] baseBoard;
	
	private int playerRow;
	private int playerCol;
	
	private int numBoxes;
	private int[] boxRows;
	private int[] boxCols;
	
	private enum Direction{
		UP,RIGHT,DOWN,LEFT
	}
	private static String setCharAt(String str,int index,char c) {
		return str.substring(0,index)+c+str.substring(index+1);
	}
	

	private int getColDiff(Direction dir) {
		switch(dir) {
		case LEFT:
			return -1;
		case RIGHT:
			return 1;
		default:
			return 0;
		}
	}
	private int getRowDiff(Direction dir) {
		switch(dir) {
		case UP:
			return -1;
		case DOWN:
			return 1;
		default:
			return 0;
		}
	}
	
	public GameBoard(String[] map) {
		loadBoard(map);
	}
	
	public void loadBoard(String[] map) {
		height = map.length;
		width = map[0].length();
		numBoxes = 0;
		boxRows = new int[height*width];
		boxCols = new int[height*width];
		
		baseBoard = new String[height];
		for(int r=0;r<height;r++) {
			baseBoard[r] = "";
			for(int c=0;c<width;c++) {
				char mch = map[r].charAt(c);
				char sch = '.';
				switch(mch) {
				case 'A':
					playerRow = r;
					playerCol = c;
					break;
				case 'O':
					boxRows[numBoxes] = r;
					boxCols[numBoxes] = c;
					numBoxes++;
					break;
				default:
					sch = mch;
				}
				baseBoard[r] += sch;
			}
		}
	}
	public boolean canPlayerMove(Direction dir) {
		return false;
	}
	public void movePlayer(Direction dir) {
		
	}
	public String[] getBaseBoard() {
		return baseBoard;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getPlayerRow() {
		return playerRow;
	}
	public int getPlayerCol() {
		return playerCol;
	}
	public int getNumBoxes() {
		return numBoxes;
	}
	public int[] getBoxPosition(int i) {
		return new int[] {
				boxRows[i],
				boxCols[i]
		};
	}
	public boolean hasPlayerAt(int r,int c) {
		return (playerRow == r) && (playerCol == c);
	}
	public boolean hasBoxAt(int r,int c) {
		if((r >= 0 && r <= width-1) && (c >= 0 && c <= width-1))
			for(int i=0;i<numBoxes;i++) {
				if(boxRows[i] == r && boxCols[i] == c)
					return true;
			}
		return false;
	}
	public boolean hasExitAt(int r,int c) {
		if((r >= 0 && r <= width-1) && (c >= 0 && c <= width-1))
			return (baseBoard[r].charAt(c) == '*');
		return false;
	}

	
	public void setPlayerPosition(int r,int c) {
		playerRow = r;
		playerCol = c;
	}
	public void setBoxPosition(int i,int r,int c) {
		boxRows[i] = r;
		boxCols[i] = c;
	}

	public String toString() {
		
		//create return string;
		String map = "";
		//create a new board with original board and add \n;
		String[] sector = new String[height]; 
		for(int i=0;i<baseBoard.length;i++) {
			sector[i] = baseBoard[i]+"\n";
		}
		//add player position;
		sector[playerRow] = setCharAt(sector[playerRow],playerCol,'A');
		
		//add box position;
		for(int i=0;i<numBoxes;i++) {
			sector[boxRows[i]] = setCharAt(sector[boxRows[i]],boxCols[i],'O');
		}
		
		//combine sector to return
		for(int i=0;i<sector.length;i++) {
			map += sector[i];
		}
		return map;
	}
	
	
}

