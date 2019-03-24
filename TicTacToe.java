
package tictactoe;



public class TicTacToe {                                                              
                                                                                 
    public static void main(String[] args) {                                   
        // Create the components     
        
    	Controller controller = new Controller();  
    	View view = new View();   
        Model model = new Model();   
        
        // Use aggregation to put the components together
        model.registerView(view);
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);
    }                                                                            
} 
