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
	public View view;
       

	// initializing the reference of model class
	public void setModel(Model m) {
		this.model = m;
	}
	
	// function to request the model to update the board
	public void setRequest(ArrayList<Integer> position) {
		model.PlayMove(position.get(0), position.get(1));
	}
	
	// overloaded function to request model to reset
	public void setRequest() {
		model.ResetModel();
	}
        public void setRequest2() {
		model.EndGame();
	}
	 // Implementation of the actionPerformed method for the ActionListener interface
    public void actionPerformed(ActionEvent e) {
    		
    		if(view.isNewGame(e))
    			this.setRequest();
                else if(view.isEnd(e))
                    this.setRequest2();
    		else {
    			ArrayList<Integer> position = view.getPosition(e);
    			this.setRequest(position);
    		}
    }
}
