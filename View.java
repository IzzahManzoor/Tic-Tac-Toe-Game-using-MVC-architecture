package tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class View {
    private Controller controller;
    private JFrame HomeScreen;
    private JFrame gameInterface;
    private JButton[][] boxes;
    private JButton NewGame;
    private JTextArea player;
    private JButton End;
    private JButton StartGame;
    private JLabel Player1Name;
    private JLabel Player2Name;
    private JTextField PlayerName1;
    private JTextField PlayerName2;
    private JLabel sym1;
    private JOptionPane result;
    // default constructor 
    public View() {
        //    JOptionPane.showMessageDialog(null, "Username or password invalid", "Login Failed", 2);

                //homescreen initialization
                HomeScreen = new JFrame("tic-tac-toe");
                Player1Name= new JLabel("Enter Player 1 name:  ");
                Player2Name= new JLabel("Enter Player 2 name:  ");
                StartGame = new JButton("Start Game");
                StartGame.setBackground(Color.red);
                StartGame.setForeground(Color.YELLOW);
                PlayerName1 =new JTextField("Player1");
                PlayerName2 =new JTextField("Player2");
                
                //game screen initialization
    		gameInterface = new JFrame("tic-tac-toe");
    		boxes = new JButton[3][3];
                sym1=new JLabel();
                //end button
                End = new JButton("End Game");
                End.setBackground(Color.red);
                End.setForeground(Color.YELLOW);
                //newgame button
    		NewGame = new JButton("New Game");
                NewGame.setBackground(Color.red);
                NewGame.setForeground(Color.YELLOW);
                //text area
    		player = new JTextArea();
                player.setBackground(Color.cyan);
                player.setForeground(Color.MAGENTA);
                
                result = new JOptionPane();
    		// call the initialize method 
    		initialize();
    }
    //setting controller reference
    public void setController(Controller c)
    {
        controller=c;
        setActionListener(controller);
    }
    // function to add action listeners to buttons
    public void setActionListener(Controller c) {
    	
		for(int rows = 0; rows<3 ;rows++) {
	            for(int columns = 0; columns<3 ;columns++) {
	        		boxes[rows][columns].addActionListener(c);
	            }
		}
	       NewGame.addActionListener(c);
               End.addActionListener(c);
               StartGame.addActionListener(c);
    }
    
    // function to initialize layout and buttons
    public void initialize () {
            
            HomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    HomeScreen.setSize(new Dimension(350, 350));
	    HomeScreen.setResizable(true);      
            JPanel P1 = new JPanel(new FlowLayout());
            P1.add(Player1Name);
            P1.add(PlayerName1);
            JPanel P2 = new JPanel(new FlowLayout());
            P2.add(Player2Name);
            P2.add(PlayerName2);
            JPanel ButtonStart = new JPanel(new FlowLayout());
            ButtonStart.add(StartGame);
            HomeScreen.add(P1, BorderLayout.NORTH);
	    HomeScreen.add(ButtonStart, BorderLayout.SOUTH);
	    HomeScreen.add(P2, BorderLayout.CENTER);
            
            
        
	    gameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gameInterface.setSize(new Dimension(500, 350));
	    gameInterface.setResizable(true);
	    JPanel gamePanel = new JPanel(new FlowLayout());
	    JPanel game = new JPanel(new GridLayout(3,3));
	    gamePanel.add(game, BorderLayout.CENTER);
	    JPanel options = new JPanel(new FlowLayout());
	    options.add(NewGame);
            options.add(End);
            JPanel options2 = new JPanel(new FlowLayout());
            options2.add(sym1);
            
	    JPanel messages = new JPanel(new FlowLayout());
	    messages.setBackground(Color.cyan);
            gameInterface.add(gamePanel, BorderLayout.CENTER);
	    gameInterface.add(options, BorderLayout.SOUTH);
	    gameInterface.add(messages, BorderLayout.NORTH);
            gameInterface.add(options2, BorderLayout.WEST);
            
	    messages.add(player);
	    //player.setText("Turn: "+PlayerName1.getText());
	    
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
            HomeScreen.setVisible(true);
	    

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
     // function to check if the action event was generated because of start key
    public boolean isStartGame(ActionEvent e) {
    		if(e.getSource() == StartGame)
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
		
               result.showMessageDialog(null, message, " Game Result", 2);
               

    }
    
    // function to clear the view and reset it for a new game
    public void NewGame() {
    	for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                boxes[row][column].setText("");
                boxes[row][column].setEnabled(true);
            }
        }
        player.setText("Turn: "+PlayerName1.getText());
    }
    public void Ending()
    {
        gameInterface.dispose();
        HomeScreen.dispose();

    }
    public void Starting()
    {
         controller.SetPlayerNames(PlayerName1.getText(), PlayerName2.getText());
         sym1.setText(PlayerName1.getText()+ "= 'O' "+PlayerName2.getText()+ "= 'X'");
         player.setText("Turn: "+PlayerName1.getText());
         HomeScreen.setVisible(false);
         gameInterface.setVisible(true);

    }
    // function for checking the value of a button on the grid
    public String getButtonText(int k, int l) {
    		return boxes[k][l].getText();
    }

}
