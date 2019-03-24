package tictactoe;

/** The Controller class is responsible for requesting the model
 * to update its state whenever there is an event on a button on 
 * the game board.
 * */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Controller implements ActionListener{
	private Model model;
	private View view;
        

	// initializing the reference of model class
	public void setModel(Model m) {
		model = m;
	}
	
        // initializing the reference of view class
	public void setView(View v) {
		view = v;
	}
        //setting names of players used by model class
        public void SetPlayerNames(String p1,String p2)
        {
            model.setPlayer1(p1);
            model.setPlayer2(p2);
        }
        
	// function to request the model to update the board
	public void setRequest(ArrayList<Integer> position) {
		model.PlayMove(position.get(0), position.get(1));
	}
	
	
	public void setRequest1() {
		model.ResetModel();
	}
        public void setRequest2() {
		model.EndGame();
	}
        public void setRequest3() {
		model.StartGame();
	}
	 // Implementation of the actionPerformed method for the ActionListener interface
    public void actionPerformed(ActionEvent e) {
    		
    		if(view.isNewGame(e))
    			setRequest1();
                else if(view.isEnd(e))
                        setRequest2();
                else if(view.isStartGame(e))
                        setRequest3();
    		else {
    			ArrayList<Integer> position = view.getPosition(e);
    			setRequest(position);
    		}
    }
}
