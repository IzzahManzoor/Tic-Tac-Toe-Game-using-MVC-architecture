package tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class View {
    private JFrame gameInterface;
    private JButton[][] boxes;
    private JButton NewGame;
    private JTextArea player;
    private JButton End;
    // default constructor to initialize the gui as JFrame
    public View() {
    		gameInterface = new JFrame("tic-tac-toe");
    		boxes = new JButton[3][3];
                
                End = new JButton("End Game");
                End.setBackground(Color.red);
                End.setForeground(Color.YELLOW);
                
    		NewGame = new JButton("New Game");
                NewGame.setBackground(Color.red);
                NewGame.setForeground(Color.YELLOW);
    		player = new JTextArea();
                player.setBackground(Color.cyan);
                player.setForeground(Color.MAGENTA);
    		// call the initialize method to set up the layout and initialize buttons
    		initialize();
    }
    
    // function to add action listeners to buttons
    public void setActionListener(Controller c) {
    		//  reference of view class is needed b controller
		c.view=this;
		for(int rows = 0; rows<3 ;rows++) {
	            for(int columns = 0; columns<3 ;columns++) {
	        		boxes[rows][columns].addActionListener(c);
	            }
		}
	       NewGame.addActionListener(c);
               End.addActionListener(c);
    }
    
    // function to initialize layout and buttons
    public void initialize () {
	    gameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gameInterface.setSize(new Dimension(350, 350));
	    gameInterface.setResizable(true);
	    JPanel gamePanel = new JPanel(new FlowLayout());
	    JPanel game = new JPanel(new GridLayout(3,3));
	    gamePanel.add(game, BorderLayout.CENTER);
	    JPanel options = new JPanel(new FlowLayout());
	    options.add(NewGame);
            options.add(End);
	    JPanel messages = new JPanel(new FlowLayout());
	    messages.setBackground(Color.cyan);
            gameInterface.add(gamePanel, BorderLayout.CENTER);
	    gameInterface.add(options, BorderLayout.SOUTH);
	    gameInterface.add(messages, BorderLayout.NORTH);
	    messages.add(player);
	    player.setText("Turn:Player 1 Symbol:'O'");
	    
	    for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	            boxes[row][column] = new JButton();
	            boxes[row][column].setPreferredSize(new Dimension(75,75));
	            boxes[row][column].setText("");
                    boxes[row][column].setBackground(Color.green);
	            game.add(boxes[row][column]);
		    }
		}
	    
	    // make the gui visible as the final step
	    gameInterface.setVisible(true);

    }
    
    // function to check if the action event was generated because of Newgame key
    public boolean isNewGame(ActionEvent e) {
    		if(e.getSource() == NewGame)
    			return true;
    		return false;
    }
    
     // function to check if the action event was generated because of end key
    public boolean isEnd(ActionEvent e) {
    		if(e.getSource() == End)
    			return true;
    		return false;
    }
    
    // function to find the x,y-coordinates of the pressed button
    public ArrayList<Integer> getPosition(ActionEvent e) {
    	ArrayList<Integer> position = new ArrayList<Integer>();
    	for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	        		if(e.getSource() == boxes[row][column]) {
	        			position.add(row);
	        			position.add(column);
	        		}
	        }
    		}
    		return position;
    }
    
    // function to update the view with the correct mark and message
    public void update(int row, int column, char symbol, String message) {
    		boxes[row][column].setText(Character.toString(symbol));
    		boxes[row][column].setEnabled(false);
    		player.setText(message);
    	
    }
    
    // function to freeze the view if there is a winner or game is tied
    public void isWinner(int row, int column, char symbol, String message) {
		boxes[row][column].setText(Character.toString(symbol));
		boxes[row][column].setEnabled(false);
		for(int i = 0; i<3 ;i++) {
	        for(int j = 0; j<3 ;j++) {
	        	boxes[i][j].setEnabled(false);
	        }
		}
		player.setText(message);

    }
    
    // function to clear the view and reset it for a new game
    public void NewGame() {
    	for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                boxes[row][column].setText("");
                boxes[row][column].setEnabled(true);
            }
        }
        player.setText("Turn:Player 1 Symbol:'O'");
    }
    public void Ending()
    {
        gameInterface.dispose();

    }
    // function for checking the value of a button on the grid
    public String getButtonText(int k, int l) {
    		return boxes[k][l].getText();
    }

}
