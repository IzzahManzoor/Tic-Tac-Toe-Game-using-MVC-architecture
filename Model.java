package tictactoe;

/** The model class is where the current state of the game
 * as well as the winning logic resides. The model class calls 
 * the view to update the interface according to the current state of 
 * the game.
 * */


public class Model {
	private View view;
	private int playerId;
	private int CountMoves;
	private char[][] GameBoard;
	private String message;
        private String Player1;
        private String Player2;

	// default constructor
	public Model() {
		GameBoard = new char[3][3];
		CountMoves = 9;
		playerId = 1;
	}
	
	// initializing the reference of view class
	public void registerView(View v) {
		view = v;
	}
    
	//setters 
        
        public void setPlayer1(String p1)
        {
            Player1=p1;
        }
        public void setPlayer2(String p2)
        {
            Player2=p2;
        }

	public char[][] getBoard() {
		return GameBoard;
	}

	public void setBoard(char[][] GameBoard) {
		this.GameBoard = GameBoard;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// function to update the GameBoard
	public void PlayMove(int x, int y) {
		
		if(CountMoves > 0){
			// mark the GameBoard with x or o depending on playerId
			if(playerId%2 == 0) 
				GameBoard[x][y] = 'X';
			else 
				GameBoard[x][y] = 'O';
			
			// reduce the count of moves left
			--CountMoves;
			
			//when moves have ended check if player has won or if game is tied
                        if(CountMoves == 0) {
                                if(CheckWinner()) {
                                     if(playerId%2 != 0)
				        setMessage(Player1+ " Won!");
                                     else
                                        setMessage(Player2+ " Won!");
                                }
                                else
                                {
				     setMessage("Game Draw");
                                }
				view.isWinner(x, y, GameBoard[x][y], getMessage());
                        }
                        //when moves are still left check if player has won
			else if(CheckWinner()) {
                                if(playerId%2 != 0)
				    setMessage(Player1+ " Won!");
                                else
                                    setMessage(Player2+ " Won!");
                                
				view.isWinner(x, y, GameBoard[x][y], getMessage());
			}
                        
			else {
				if(playerId%2 == 0) {
					// toggle the playerId
                                        playerId=1;
					setMessage("Turn: " + Player1);
				}
				else {
                                        playerId=2;
					setMessage("Turn: " + Player2);

				}
				// update the GameBoard with message for next player
				view.update(x, y, GameBoard[x][y], getMessage());
			}
			
		}
		
	}
	
	// function to check if there is a winner
        public boolean CheckWinner() {
        int x_sum_first_row, x_sum_second_row, x_sum_third_row,
            x_sum_first_column, x_sum_second_column, x_sum_third_column,
            x_sumDiagonalLR, x_sumDiagonalRL;
          
        
        x_sum_first_row = x_sum_second_row = x_sum_third_row
        = x_sum_first_column = x_sum_second_column = x_sum_third_column
        = x_sumDiagonalLR = x_sumDiagonalRL = 0;

        int o_sum_first_row, o_sum_second_row, o_sum_third_row,
            o_sum_first_column, o_sum_second_column, o_sum_third_column,
            o_sumDiagonalLR, o_sumDiagonalRL;

        o_sum_first_row = o_sum_second_row = o_sum_third_row
        = o_sum_first_column = o_sum_second_column = o_sum_third_column
        = o_sumDiagonalLR = o_sumDiagonalRL = 0;

        for (int i = 0; i < 3; i++) {
            if (GameBoard[0][i]== 'X')
                x_sum_first_row++;
            if (GameBoard[0][i] == 'O')
                o_sum_first_row++;
            if (GameBoard[1][i]== 'X')
                x_sum_second_row++;
            if (GameBoard[1][i]== 'O')
                o_sum_second_row++;
            if (GameBoard[2][i]== 'O')
                x_sum_third_row++;
            if (GameBoard[2][i]== 'O')
                o_sum_third_row++;
            if (GameBoard[i][0]== 'X')
                x_sum_first_column++;
            if (GameBoard[i][0]== 'O')
                o_sum_first_column++;
            if (GameBoard[i][1]== 'X')
                x_sum_second_column++;
            if (GameBoard[i][1]== 'O')
                o_sum_second_column++;
            if (GameBoard[i][2]== 'X')
                x_sum_third_column++;
            if (GameBoard[i][2]== 'O')
                o_sum_third_column++;
            if (GameBoard[i][i] == 'X')
                x_sumDiagonalLR++;
            if (GameBoard[i][i] == 'O')
                o_sumDiagonalLR++;
            if (GameBoard[GameBoard.length-1-i][i] == 'X')
                x_sumDiagonalRL++;
            if (GameBoard[GameBoard.length-1-i][i] == 'O')
                o_sumDiagonalRL++;
        }

        if (x_sum_first_row == 3 || x_sum_second_row == 3 || x_sum_third_row == 3 ||
            x_sum_first_column == 3 || x_sum_second_column == 3 || x_sum_third_column == 3 ||
            x_sumDiagonalLR == 3 || x_sumDiagonalRL == 3 ||
            o_sum_first_row == 3 || o_sum_second_row == 3 || o_sum_third_row == 3 ||
            o_sum_first_column == 3 || o_sum_second_column == 3 || o_sum_third_column == 3 ||
            o_sumDiagonalLR == 3 || o_sumDiagonalRL == 3)
           return true;

        return false;
    }
	
	// function to clear the model and reset it to initial state
	public void ResetModel() {
		CountMoves = 9;
		playerId=1;
		setMessage("");
		for(int q=0; q<GameBoard.length;q++) {
			for(int r=0; r<GameBoard.length;r++) {
				GameBoard[q][r] = '\0';
			}
		}
		view.NewGame();
		
	}
	public void EndGame()
        {
         view.Ending();

        }
        public void StartGame()
        {
            
         view.Starting();

        }
}
