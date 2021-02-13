
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServerGUI extends Application{

	
	TextField s1,s2,s3,s4, c1;
	Button serverChoice,clientChoice,b1;
	HashMap<String, Scene> sceneMap;
	GridPane grid;
	HBox buttonBox;
	VBox clientBox;
	Scene startScene;
	BorderPane startPane;
	Server serverConnection;
	BaccaratInfo gameInfo ;
	Button Exit;
	//Client clientConnection;
	
	ListView<String> listItems, listItems2;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Baccarat Game by Daniela Chavez & Jack Martin");
		
		Button Start = new Button("Start");
		Start.setPrefWidth(100);
		Start.setPrefHeight(50);
		Start.setLayoutX(110);
		Start.setLayoutY(300);
		//this.serverChoice.setStyle("-fx-pref-height: 300px");
		
		
		Exit = new Button("Exit");
		
		Exit.setOnAction(E->{
			
			primaryStage.close();
		});
		
		
		Start.setOnAction(e->
		{
				primaryStage.setScene(sceneMap.get("server"));							
				primaryStage.setTitle("This is the Server");
				
				serverConnection = new Server(data -> 
				{
					Platform.runLater(()->
					{
						listItems.getItems().add(data.toString());
//						listItems.getItems().add(Integer.toString()gameInfo.bid);
					});

				});
											
		});
		

		
		this.buttonBox = new HBox(100, Start);
		
		
		Pane startPane = new Pane();
		startPane.setPadding(new Insets(70));
		//startPane.setCenter(buttonBox);
		
		
		Image backGround = new Image("Background Baccarat.png");
		ImageView backgroundView = new ImageView(backGround);
		
		backgroundView.setFitHeight(492);
		backgroundView.setFitWidth(327);
		
		startPane.getChildren().addAll(backgroundView, buttonBox, Start);
		
		startScene = new Scene(startPane, 327,492);
		
		listItems = new ListView<String>();
//		listItems2 = new ListView<String>();
		
		c1 = new TextField();
		b1 = new Button("Send");
//		b1.setOnAction(e->{clientConnection.send(c1.getText()); c1.clear();});
//		
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("server",  createServerGui());
//		sceneMap.put("client",  createClientGui());
		
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
		
		Pane pane = new Pane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: green");
		
		
		listItems.setLayoutX(50);
		
		Exit.setLayoutX(150);
		Exit.setLayoutY(440);
		
		pane.getChildren().addAll(listItems, Exit);
		
		return new Scene(pane, 350, 500);
		
		
	}
	


}
