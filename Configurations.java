/**
 * This class implements the game board configurations and provides methods for
 * checking the winning shapes. 
 * @author chaejinhur
 *
 */
public class Configurations {
	
	
	int board_size; 
	int lengthToWin; 
	int max_levels;
		
	char[][] board; 		// instance variable to store the game board
	
    
	/**
	 * This constructor initializes the Configurations object with specific parameters.
	 * @param board_size
	 * @param lengthToWin
	 * @param max_levels
	 */
	public Configurations (int board_size, int lengthToWin, int max_levels) {
		this.board_size = board_size;
		this.lengthToWin = lengthToWin; 
		this.max_levels = max_levels; 
		this.board = new char[board_size][board_size]; 

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	/**
	 * creates an empty HashDictionary with a specified size of hash table
	 * @return An empty HashDictionary
	 */
	public HashDictionary createDictionary() {
		return new HashDictionary(7000); 
	}
	
	/**
	 * This method checks if the current board configurations is repeated in the given hash table.
	 * @param hashTable
	 * @return The Score
	 */
	public int repeatedConfiguration(HashDictionary hashTable) {
		// convert the board into a string representation 
		StringBuilder String = new StringBuilder(); 
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				String.append(board[i][j]);
			}
		}
		String boardKey = String.toString(); 
		int score = hashTable.get(boardKey); 
		
		// if String is in hashTable, return the score
		if (score != -1) {
			return score; 
			
		// if String isn't found: return -1
		} else {
			return -1; 			
		}
	
	}
	
	/**
	 * This method represents the content of board as a String, 
	 * then inserts this String and score in hashDictionary
	 * @param hashDictionary
	 * @param score
	 */
	public void addConfiguration(HashDictionary hashDictionary, int score) {
		StringBuilder String = new StringBuilder(); 

		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				String.append(board[i][j]);
			}
		}
		String boardKey = String.toString(); 
		
		Data record = new Data(boardKey, score); 
		
		try {
			hashDictionary.put(record);
			
		} catch (DictionaryException e) {
			System.out.println("Error while adding the configuration: " + e.getMessage());
		}

	} 
	
	/**
	 * This method stores the symbol in the board at the specified row and column
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void savePlay(int row, int col, char symbol) {
		board[row][col] = symbol; 
	}
	
	public boolean squareIsEmpty(int row, int col) {
		if (board[row][col] == ' ') {
			return true; 
		} else {
			return false; 
		}
	}
	
	public boolean wins(char symbol) {
		if (checkForXShape(symbol)) {
			return true; 
		}
		
		if (checkForPlusShape(symbol)) {
			return true; 
		}
		return false; 
	}
	
	private boolean checkForXShape(char symbol) {
		int k = lengthToWin; 		// define the length of shape to win the game
		
		// iterate through the board to check for x-shapes 
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == symbol) {
					// check for x-shape in horizontal direction 
					if (checkHorizontalX(i, j, symbol, k)) {
						return true; 
					} 
					if (checkVerticalX(i, j, symbol, k)) {
						return true; 
					}
					if (checkDiagonalX(i, j, symbol, k)) {
						return true; 
					}
				}
			}
		}
		return false; 
	}
	
	private boolean checkHorizontalX(int row, int col, char symbol, int k) {
		int count = 1; 		// initialize count to 1 for the current symbol 
		
		// check to the right of current position: 
		for (int j = col + 1; j < board[row].length; j++) {
			if (board[row][j] == symbol) {
				count++; 
			} else {
				break; 
			}
		}
		
		// check to the left of current position: 
		for (int j = col - 1; j >= 0; j--) {
			if (board[row][j] == symbol) {
				count++; 
			} else {
				break; 
			}
		}
		
		// check if the count is greater than or equal to k: 
		return count >= k; 
	}
	
	private boolean checkVerticalX(int row, int col, char symbol, int k) {
		int count = 1; 
		
		// check upwards from current position: 
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][col] == symbol) {
				count++; 
			} else {
				break; 
			}
		}
		
		// check downwards from current position: 
		for (int i = row + 1; i < board.length; i++) {
			if (board[i][col] == symbol) {
				count++; 
			} else {
				break; 
			}
		}	
		return count >= k; 
	
	}
	
	private boolean checkDiagonalX(int row, int col, char symbol, int k) {
		int count = 1; 
		
		// check  main diagonal from top-left to bottom-right from current position: 
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == symbol) {
				count++; 
			} else {
				break; 
			}
		}
		
		for (int i = row + 1, j = col + 1; i < board.length && j < board[row].length; i++, j++) {
			if (board[i][j] == symbol) {
				count++; 
			} else {
				break; 
			}
		}	
		
		// check if count is greater than or equal to k 
		if (count >= k) {
			return true; 
		}
		
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == symbol) {
				count++; 
			} else {
				break; 
			}
		}
		return count >= k; 
	}
	
	private boolean checkForPlusShape(char symbol) {
        int k = lengthToWin; // define the length to win the game

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == symbol) {
                    // Check for plus shape
                    if (checkPlusShape(i, j, symbol, k)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

	private boolean checkPlusShape(int row, int col, char symbol, int k) {
		int count = 1; 		
		
		// check upwards from the center: 
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][col] == symbol) {
				count++; 
			} else {
				break; 
			}
		}
		
		// check downwards from the center: 
		for (int i = row + 1; i < board_size; i++) {
			if (board[i][col] == symbol) {
				count++; 
			} else {
				break;
			}
		}
			
		// check to left from center: 
		for (int j = col - 1; j >= 0; j--) {
			if (board[row][j] == symbol) {
				count++; 
			} else {
				break; 
			}
		}
		
		// check to right from the center: 
		for (int j = col + 1; j < board[row].length; j++) {
			if (board[row][j] == symbol) {
				count++; 
			} else {
				break; 
			}
		}
		
		return count >= k; 
	}
	
	public boolean isDraw() {
		// check if there's any empty positions left: 
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == ' ') {
					return false; 
				}
			}
		}
		return true; 		// when no empty positions found
	}
	
	public int evalBoard() {
		if (wins('O')) {
			return 3; 		// computer wins 
		} else if (wins('X')) {
			return 0; 		// human wins 
		} else if (isDraw()) {
			return 2; 		// draw
		} else {
			return 1; 		// still undecided
		}
	}
} 

