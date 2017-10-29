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
	

	private static String setCharAt(String str,int index,char c) {
		return str.substring(0,index)+c+str.substring(index+1);
	}
	
    public static boolean canPlayerStepOn(char item) {
        return (item == '.') || (item == '*') || (item == ' ');
    }
	
	public enum Direction{
		UP,RIGHT,DOWN,LEFT,STILL
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
	
	
	public String[] getBoard() {
		//create a new board with original board and add \n;
		String[] board = new String[height]; 
		for(int i=0;i<baseBoard.length;i++) {
			board[i] = baseBoard[i];
		}
		//add player position;
		board[playerRow] = setCharAt(board[playerRow],playerCol,'A');
		
		//add box position;
		for(int i=0;i<numBoxes;i++) {
			board[boxRows[i]] = setCharAt(board[boxRows[i]],boxCols[i],'O');
		}
		return board;
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
	public char getBoardNextItem(int r,int c,Direction dir) {
		int row = r+getRowDiff(dir);
		int col = c+getColDiff(dir);
		if((row >= 0 && row <= width-1)
				&& (col >= 0 && col <= width-1)) {
			char target = baseBoard[row].charAt(col);
			if(target == '.') {
				if(hasBoxAt(row,col)) return 'O';
				if(hasPlayerAt(row,col)) return 'A';
			}
			return target;
		}
		return '#';
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
	
	public boolean canPlayerMove(Direction dir) {
		char item = getBoardNextItem(playerRow,playerCol,dir);
		if(canPlayerStepOn(item))return true;
		if(item == 'O') {
			return canPlayerStepOn(getBoardNextItem(playerRow+getRowDiff(dir),
					playerCol+getColDiff(dir),dir));
		}
		return false;
	}
	public void movePlayer(Direction dir) {
		if(canPlayerMove(dir)) {
			setPlayerPosition(playerRow+getRowDiff(dir),playerCol+getColDiff(dir));
		}
	}

	public String toString() {
		String map = "";
		String[] board = getBoard();
		//combine board to return
		for(int i=0;i<board.length;i++) {
			map += board[i]+"\n";
		}
		return map;
	}

	
}


