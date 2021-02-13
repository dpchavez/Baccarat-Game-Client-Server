
import java.util.HashMap;
import java.util.Vector;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class ClientGUI extends Application{

	
	TextField s1,s2,s3,s4, TextField1;
	Button serverChoice,clientChoice,Send;
	HashMap<String, Scene> sceneMap;
	GridPane grid;
	HBox buttonBox;
	Pane clientBox, gamePane;
	Scene startScene, gameScene;
	BorderPane startPane;
	BaccaratInfo gameInfo = new BaccaratInfo();
	Button plus1, plus5, plus10;
	Button min1, min5, min10;
	Button Player;
	Button Banker;
	Button Tie;
	Button Continue;
	String bankercrds = "";
	String playercrds = "";
	boolean flag = false;
	Button PlayAgain;
	Button Exit;
	double totalMoney = 0;;
	//Server serverConnection;
	static Client clientConnection;
	static ImageView one, two, three, four, five, six, seven, eight, nine;
	
	ListView<BaccaratInfo> listItems = new ListView<BaccaratInfo>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}


	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//TODO: Server and port... later....
		
		
		Continue = new Button("Continue...");
		Continue.setDisable(true);
		
		sceneMap = new HashMap<String, Scene>();
		
		
		
		
		Exit = new Button("Exit");
		
		Exit.setOnAction(E->{
			
			primaryStage.close();
		});
		
		
		
		Button Start = new Button("Connect to server");
		Start.setPrefWidth(150);
		Start.setPrefHeight(50);
		Start.setLayoutX(110);
		Start.setLayoutY(300);
		
		
		primaryStage.setTitle("Baccarat Game by Daniela Chavez & Jack Martin");
		
		
		PlayAgain = new Button("Play Again");
		
		PlayAgain.setOnAction(PA->
		{
			//Resetting all values to nothing...
			gameInfo = new BaccaratInfo();
			listItems = new ListView<BaccaratInfo>();
			clientConnection.gameInfo = new BaccaratInfo();
			
			
			primaryStage.setScene(createBidandWhoScene());
			primaryStage.show();
			
			
		});
		
		
		
		Start.setOnAction(e-> 
		{
			primaryStage.setScene(sceneMap.get("client"));
			primaryStage.setTitle("This is a client");
			clientConnection = new Client(data->
			{
				Platform.runLater(()->
				{
					listItems.getItems().add(gameInfo);
					flag = true;
				//ystem.out.print(gameInfo.who);
				});
			});
							
			clientConnection.start();
		});
		
		this.buttonBox = new HBox(400, Start);
		
		
		Pane startPane = new Pane();
		startPane.setPadding(new Insets(70));


		Image backGround = new Image("Background Baccarat.png");
		ImageView backgroundView = new ImageView(backGround);
		backgroundView.setFitHeight(492);
		backgroundView.setFitWidth(327);
		
		
		TextField1 = new TextField();
		
		Send = new Button("Send");

		

		
		Send.setOnAction(e->
		{
			gameInfo.bid = Integer.parseInt(TextField1.getText());
			clientConnection.send(gameInfo);
			
			gamePane = new Pane();
			

			Text playerCards = new Text();
			playerCards.setLayoutX(110); 
			playerCards.setLayoutY(150); 
			
			
			//the third card for player
			Text playerDrawCard = new Text();
			playerDrawCard.setLayoutX(110);
			playerDrawCard.setLayoutY(180);

			//the third card for banker
			Text bankDrawCard = new Text();
			bankDrawCard.setLayoutX(100);
			bankDrawCard.setLayoutY(300);
			
			
			Text bankCards = new Text();
			bankCards.setLayoutY(270); 
			bankCards.setLayoutX(100); 
			
			Text natWinText = new Text();
			natWinText.setLayoutY(340);
			natWinText.setLayoutX(155);
			
			PauseTransition playerCardPause = new PauseTransition(Duration.seconds(3));
			playerCardPause.setOnFinished(pc ->
			{
				playerCards.setText(clientConnection.gameInfo.playerHand.get(0) + " " 
			+ clientConnection.gameInfo.playerHand.get(1)); //+ " " + clientConnection.gameInfo.thirdPlayerCard);
			});
			playerCardPause.play();
			
			
			PauseTransition bankCardPause = new PauseTransition(Duration.seconds(4));
			bankCardPause.setOnFinished(bp->
			{
				bankCards.setText(clientConnection.gameInfo.dealerHand.get(0) + " "
						+ clientConnection.gameInfo.dealerHand.get(1));// + " " + clientConnection.gameInfo.thirdBankerCard);
			});
			bankCardPause.play();
			
			PauseTransition naturalPause = new PauseTransition(Duration.seconds(4.5));
			naturalPause.setOnFinished(np->
			{
				if(clientConnection.gameInfo.natural == true)
					natWinText.setText("There is a natural win!");
				else
					natWinText.setText("There is no natural win! :(");
			});
			naturalPause.play();
			//-------------------------------------------------------------------------
			
				PauseTransition thirdPlayerCardPause = new PauseTransition(Duration.seconds(5));
//				
				thirdPlayerCardPause.setOnFinished(pc->
				{
					playerDrawCard.setText(clientConnection.gameInfo.thirdPlayerCard);
				});
				thirdPlayerCardPause.play();
//			
//			
				PauseTransition thirdBankerCardPause = new PauseTransition(Duration.seconds(5.5));
//				
				thirdBankerCardPause.setOnFinished(bc->
				{
					bankDrawCard.setText(clientConnection.gameInfo.thirdBankerCard);
				});
				thirdBankerCardPause.play();
			
			//----------------------------------------------------------------------------

			
			
			//**************************************************************************

			
			Text theWinner = new Text();
			theWinner.setLayoutX(230);
			theWinner.setLayoutY(380);
			
			PauseTransition winnerPause = new PauseTransition(Duration.seconds(7));
			
			winnerPause.setOnFinished(wp->
			{

				theWinner.setText(clientConnection.gameInfo.winner);
				Continue.setDisable(false);

			});
			
			winnerPause.play();
			
			//***************************************************************************
			
			Continue.setLayoutX(110);
			Continue.setLayoutY(430);
			
			
			Image backGround2 = new Image("bg1.png");
			ImageView backgroundView2 = new ImageView(backGround2);
			backgroundView2.setFitHeight(507);
			backgroundView2.setFitWidth(331);
			
			
			gamePane.getChildren().addAll(backgroundView2, Continue, bankCards, playerCards, theWinner,natWinText, playerDrawCard, bankDrawCard);
			
			Scene scene1 = new Scene(gamePane, 331, 507);
			
			primaryStage.setScene(scene1);
			primaryStage.show();
			
		});
		
		
		Continue.setOnAction(C->{
			primaryStage.setScene(LastScene());
			primaryStage.show();
		});
		
		
		//-----------------------------------------------------------------------------------------------------------------------------------
		
		sceneMap.put("client",  createBidandWhoScene());
//		sceneMap.put("game", gameGUI());
		startPane.getChildren().addAll(backgroundView, buttonBox, Start);
		startScene = new Scene(startPane, 327,492);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		 
		
		primaryStage.setScene(startScene);
		primaryStage.show();
		
	}
	
	public Scene createServerGui() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		
		pane.setCenter(listItems);
	
		return new Scene(pane, 500, 400);
		
		
	}
	
	public Scene createBidandWhoScene() {
		
		
		
		
		clientBox = new Pane();
		clientBox.setPadding(new Insets(70));
		
		Send.setLayoutX(150);
		Send.setLayoutY(350);
		
		TextField1.setLayoutX(100);
		TextField1.setLayoutY(150);
		
		Player = new Button("Player");
		Player.setLayoutX(70);
		Player.setLayoutY(250);
		Player.setDisable(false);
		
		Banker = new Button("Banker");
		Banker.setLayoutX(210);
		Banker.setLayoutY(250);
		Banker.setDisable(false);
		
		Tie = new Button("Tie");
		Tie.setLayoutX(140);
		Tie.setLayoutY(250);
		Tie.setDisable(false);

		//Event handler for Player....
		Player.setOnAction(P -> 
		{
			gameInfo.who = "Player";
			Banker.setDisable(true);
			Tie.setDisable(true);
		});
		
		//Event handler for Draw...
		Banker.setOnAction(B ->
		{	
			gameInfo.who = "Banker";
			Player.setDisable(true);
			Tie.setDisable(true);
		});
		
		//Event Handler for Tie
		Tie.setOnAction(T ->
		{	
			gameInfo.who = "Tie";
			Player.setDisable(true);
			Banker.setDisable(true);
		});
		
		
		Image backGround0 = new Image("bg3.png");
		ImageView backgroundView0 = new ImageView(backGround0);
		backgroundView0.setFitHeight(444);
		backgroundView0.setFitWidth(344);
		

		clientBox.getChildren().addAll(backgroundView0,TextField1,Send, Player, Banker, Tie); //plus1,plus5,plus10,min1,min5,min10);
		  
		//clientBox.setStyle("-fx-background-color: green");
		return new Scene(clientBox, 344, 444);
		
	}

	
	
	public Scene LastScene() 
	{
		Pane newPane = new Pane();
		
		Text moneyWin = new Text();
		moneyWin.setLayoutY(130);
		moneyWin.setLayoutX(130);
		
		String whoOne = clientConnection.gameInfo.who;
		
		System.out.print(whoOne);
		
		totalMoney += clientConnection.gameInfo.total$;
		
		if (clientConnection.gameInfo.winner == gameInfo.who)
		
			moneyWin.setText("Congratulations! Your bet " 
					+clientConnection.gameInfo.who + " has won! \n"
					+ "Total earnings:  $" + totalMoney);
		
		
		else 
		
			moneyWin.setText("Sorry! your bet " + clientConnection.gameInfo.who + " has lost! \n"
					+ "Total earnings:  $ " + totalMoney);
		
		
		
		Image backGround3 = new Image("bg2.png");
		ImageView backgroundView3 = new ImageView(backGround3);
		backgroundView3.setFitHeight(278);
		backgroundView3.setFitWidth(500);
		
		PlayAgain.setLayoutX(200);
		PlayAgain.setLayoutY(200);
		
		Exit.setLayoutX(300);
		Exit.setLayoutY(200);
		
		
		newPane.getChildren().addAll(backgroundView3, moneyWin, PlayAgain, Exit);
		
		
		return new Scene(newPane, 500, 278);
	}

	

}
