
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NumberGame extends Application {
	
	
	public void start(Stage primaryStage) throws Exception {

		//Set title of the window
		primaryStage.setTitle("Guessing Game");

		//Create a flexible grid of rows and columns in which to lay out controls. Include some padding between rows/columns
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		//Format the game title
		Text gameTitle = new Text("Guessing Game");
		gameTitle.setFont(Font.font("Palatino Linotype", 20));
		gameTitle.setFill(Color.BLUE);
		grid.add(gameTitle,0,0);
		
		//generate a random number
		int randomNumber = (int)((Math.random()*100)+1);

		//Prompt user to enter a number using a text field
		Label numberLabel = new Label("Guess a number between 1-100:");
		numberLabel.setFont(Font.font("Palatino Linotyle", FontPosture.ITALIC, 12));
		grid.add(numberLabel, 0, 1);
		TextField userGuess = new TextField();
		grid.add(userGuess, 0, 2);

		//Button to submit user guess
		Button btn = new Button();
		btn.setText("Enter Guess");
		grid.add(btn, 0, 3);

		//Set output message (according to conditions of the game as per below)
		Text outputMessage = new Text();
		grid.add(outputMessage, 0, 4);
		
		//Text to display the number of guesses remaining
		Text noOfGuesses = new Text();
		grid.add(noOfGuesses, 0, 5);

		//When the button is pressed, run the below method
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			//Initize the count for the number of times user has guessed a number. 
			int count = 0;
			
			public void handle(ActionEvent e) {
				//Count is increased each time the button is pressed
				count++;
				try {
					//Change the user input from text field from a string into an integer
					int guess = Integer.parseInt(userGuess.getText());

					//print out the random generated number to the console (for testing only)
					System.out.println(randomNumber);

					if (!(count >= 10)) {
						//if the number is out of range, print a message
						if(guess < 0 || guess > 100) {
							userGuess.setText("");
							outputMessage.setText("Please enter a number between 1-100");
							noOfGuesses.setText("You have "+ (10-count) + " guess(es) left");
						}
						//if the user guess is equal to the number print out a win message
						if((guess==randomNumber)) {
							userGuess.setText("");
							outputMessage.setText(guess+" is correct. Congratulations you win!");
							outputMessage.setFill(Color.GREEN);
							noOfGuesses.setText("");
						}
						//if the user guess is lower than the number print out a message 
						else if((guess < randomNumber)) {
							userGuess.setText("");
							outputMessage.setText("Your guess of "+guess+" is too low, try again!");
							outputMessage.setFill(Color.RED);
							noOfGuesses.setText("You have "+ (10-count) + " guess(es) left");
						}
						//if the user guess is higher than the number print out a message
						else if((guess > randomNumber)) {
							userGuess.setText("");
							outputMessage.setText("Your guess of "+guess+" is too high, try again!");
							outputMessage.setFill(Color.RED);
							noOfGuesses.setText("You have "+ (10-count) + " guess(es) left");
						}
					}
					//if the user has guessed more than 10 times, they lose.
					else {
						userGuess.setText("");
						outputMessage.setText("Too Many turns, you lose");
						outputMessage.setFill(Color.RED);
						noOfGuesses.setText("");
					}
				}
				//if the user does not enter a number, prompt to enter again
				catch(NumberFormatException ex){
					userGuess.setText("");
					outputMessage.setText("Please enter a valid number");
					noOfGuesses.setText("You have "+ (10-count) + " guess(es) left");
				}
			}
		});
		//set scene and show window
		Scene scene = new Scene(grid,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
