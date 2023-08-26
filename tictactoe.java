package tictactoe;
import java.util.*;
import java.util.Deque;
import java.util.LinkedList;




public class tictactoe {
        Deque<Player> players;  
		public board gameBoard;		
			
	tictactoe(){
		initializeGame();
	}
	
	public void initializeGame() {
		
		players = new LinkedList<>();
		PlayingPieceX crossPiece = new PlayingPieceX();
		Player player1 = new Player("Player1",crossPiece);
		
		PlayingPieceO noughtsPiece = new PlayingPieceO();
		Player player2 = new Player("Player2",noughtsPiece);
		
		players.add(player1);
		players.add(player2);
		
		gameBoard = new board(3);
	}
	
	public String startGame() {
		
		boolean noWinner = true;
		while(noWinner)
		{
			
			Player playerturn = players.removeFirst();
			
			gameBoard.printBoard();
			List<Pair> freespaces = gameBoard.getFreeCells();
			if(freespaces.isEmpty()) {
				noWinner = false;
				continue;
			}
			
			System.out.print("Player:" + playerturn.name + "Enter row,column : ");
			Scanner scn = new Scanner(System.in);
            String s = scn.nextLine();
            String[] values = s.split(",");
            int inputrow = Integer.valueOf(values[0]);
            int inputcol = Integer.valueOf(values[1]);
            
            
            //place the piece 
            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputrow, inputcol, playerturn.p);
            if(!pieceAddedSuccessfully) {
            	System.out.print("Incorrect position chosen, try again");
            	players.addFirst(playerturn);
            	continue;
            }
            players.addLast(playerturn);
            
            boolean winner = isThereWinner(inputrow,inputcol,playerturn.p.pieceType);
            if(winner) {
            	 return playerturn.name;
            }
		}
		return "tie";
	} 
	 
	public boolean isThereWinner(int row, int col, PieceType pieceType ) {
		boolean rowMatch=true;
		boolean columnMatch = true;
		boolean diagonalMatch = true;
		boolean antiDiagonalMatch = true;
		
		for(int i=0;i<gameBoard.size;i++) {
			if(gameBoard.board[row][i]==null || gameBoard.board[row][i].pieceType!= pieceType){
				rowMatch = false;
			}
		}
		
		for(int i =0;i<gameBoard.size;i++) {
			if(gameBoard.board[i][col]==null || gameBoard.board[i][col].pieceType!= pieceType) {
				columnMatch = false;
			}
		}
		
		for(int i =0,j=0;i<gameBoard.size;i++,j++) {
			if(gameBoard.board[i][j]==null || gameBoard.board[i][j].pieceType!=pieceType) {
				diagonalMatch = false;
			}
		}
		
		 for(int i=0,j=gameBoard.size-1 ;i<gameBoard.size;i++,j--) {
			 
			 if(gameBoard.board[i][j]==null || gameBoard.board[i][j].pieceType != pieceType) {
				 antiDiagonalMatch = false;
			 }
		 }
		 
		 return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch; 		
	}
}
