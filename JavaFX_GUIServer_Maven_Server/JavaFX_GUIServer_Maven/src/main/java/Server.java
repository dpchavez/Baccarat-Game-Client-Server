import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{

	BaccaratDealer Dealer = new BaccaratDealer();
	BaccaratGame Game = new BaccaratGame();
	BaccaratGameLogic gameLogic = new BaccaratGameLogic();
	
	
	ArrayList<Card> tempPlayer = new ArrayList<Card>();
	ArrayList<Card> tempBanker = new ArrayList<Card>();
	
	
	ArrayList <String> PlayerCards = new ArrayList<String>();
	ArrayList <String> BankerCards = new ArrayList<String>();
	
	Card temp;
	Card playerDraw;
	Card bankerDraw;
	
	BaccaratInfo gameInfo;
	double money;
	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	
	
	
	Server(Consumer<Serializable> call){
	
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(5555);){
				callback.accept("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
		
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			//updates number of clients in server
			public void updateClients(BaccaratInfo message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
			}
			
			
			public void logic(BaccaratInfo gameInfo) 
			
			{
				
				
				//Deleting all Items after function is done... 
				Dealer = new BaccaratDealer();
				Game = new BaccaratGame();
				//gameLogic = new BaccaratGameLogic();
					
					
				tempPlayer.clear();
				tempBanker.clear();
					
					
				PlayerCards.clear();
				BankerCards.clear();
					
				temp = null;
				playerDraw = null;
				bankerDraw = null;
				
				
				
				
				// 1) We are generating a deck....
				Dealer.generateDeck();
				// 2) Now shuffling the deck...
				Dealer.shuffleDeck();
				callback.accept("client #" + count + " deck is being suffled!");
				
				
				callback.accept("client #" + count + " bidded " + gameInfo.bid);
				
				
				//3) The player is going to extract 2 cards from the top of the shuffled deck...
				tempPlayer = Dealer.dealHand(); //array  of cards
				
				//4) The banker is going to extract 2 cards from the shuffled deck...
				tempBanker = Dealer.dealHand(); //array of cards
				
				//Now we are going to store those strings into new arrays that are only going to be strings so client can display them...
				 String Temp1 = tempPlayer.get(0).suite + " " + Integer.toString(tempPlayer.get(0).value);
				 String Temp2 = tempPlayer.get(1).suite + " " + Integer.toString(tempPlayer.get(1).value);
				 
				 PlayerCards.add(Temp1); //string array
				 PlayerCards.add(Temp2);
				 
				 //Same thing but for the Banker...
				 String Temp3 = tempBanker.get(0).suite + " " + Integer.toString(tempBanker.get(0).value);
				 String Temp4 = tempBanker.get(1).suite + " " + Integer.toString(tempBanker.get(1).value);
				 
				 BankerCards.add(Temp3); //string array
				 BankerCards.add(Temp4);
				
				 //5) Sending info back to Baccarat Info...
				 callback.accept("client #" + count + " has bet for " + gameInfo.who);
				 gameInfo.playerHand = PlayerCards;
				 gameInfo.dealerHand = BankerCards;
				 
				 callback.accept("client #" + count + " player cards are " + gameInfo.playerHand);
				 callback.accept("client #" + count + " banker cards are " + gameInfo.dealerHand);
				 
				 //System.out.print("In server.java -> " + PlayerCards);
				 
				 //6) Determine if there is a natural win or tie
				 int playerHandTotal = BaccaratGameLogic.handTotal(tempPlayer);
				 int bankerHandTotal = BaccaratGameLogic.handTotal(tempBanker);
				 
				 if((playerHandTotal == 9 || playerHandTotal == 8) && bankerHandTotal != playerHandTotal)
				 {
					 gameInfo.winner = "Player"; //natural win for player btw winner member is who won
					 Game.playerHand = tempPlayer;
					 Game.bankerHand = tempBanker;
					 Game.info = gameInfo; 
					 Game.totalWinnings = gameInfo.total$;
					 Game.currentBet = gameInfo.bid;
					 gameInfo.natural = true;
					 money += Game.evaluateWinnings();
					 gameInfo.total$ = money;
//					 System.out.println("Server side of player winnings is");
					 //UPDATE WINNINGS AND STUFF
				 }
				 else if((bankerHandTotal == 9 || bankerHandTotal == 8) && bankerHandTotal != playerHandTotal)
				 {
					 gameInfo.winner = "Banker"; //natural win for banker
					 Game.playerHand = tempPlayer;
					 Game.bankerHand = tempBanker;
					 Game.info = gameInfo; 
					 Game.totalWinnings = gameInfo.total$;
					 Game.currentBet = gameInfo.bid;
					 gameInfo.natural = true;
					 money = Game.evaluateWinnings();
					 gameInfo.total$ = money;
					 
					 //UPDATE WINNINGS AND STUFF
				 }
				 else //no natural win
				 {
				 
					 //7) now we determine if player and/or banker draws a third card since there is no natural win
					 		 
					 if(BaccaratGameLogic.evaluatePlayerDraw(tempPlayer))
					 {
						 temp = Dealer.drawOne();
						 //System.out.println("The card drawn is " + temp.suite + " of " + temp.value);
						 tempPlayer.add(temp);
						 String Temp5 = tempPlayer.get(2).suite + " " + Integer.toString(tempPlayer.get(2).value);
						 PlayerCards.add(Temp5);
						 gameInfo.playerHand = PlayerCards;
						
					
					 }
					 //based on both what player draw and banker hand total
					  if(BaccaratGameLogic.evaluateBankerDraw(tempBanker, temp))
					 {
						 temp = Dealer.drawOne();
						 tempBanker.add(temp);//adds to banker array if they meet condition
						 String Temp6 = tempBanker.get(2).suite + " " + Integer.toString(tempBanker.get(2).value);
						 BankerCards.add(Temp6);
						 gameInfo.dealerHand = BankerCards;
						 
					 }
					 
					 //8) based on new hands, who won the game
					 gameInfo.winner = gameLogic.whoWon(tempPlayer, tempBanker);
					 
					 //9)updates winnings if they win bet
					 if(gameInfo.winner == gameInfo.who)
					 {
						 gameInfo.winner = gameInfo.who;
						 Game.playerHand = tempPlayer; //fills object with hands and total winnings and current bet
						 Game.bankerHand = tempBanker;
						 Game.totalWinnings = gameInfo.total$;
						 Game.currentBet = gameInfo.bid;
						 Game.info = gameInfo;
							 	
						 money = Game.evaluateWinnings();
						 gameInfo.total$ += money;
						 
						 callback.accept("Client # " + count + " has won the bet!");
						 callback.accept("client #" + count + " won" + gameInfo.total$);
//						 System.out.println("Server side for money is: " + money);
						 	
					}
					else
					{//10) updates winnings if they lose bet
						 Game.playerHand = tempPlayer;
						 Game.bankerHand = tempBanker;
						 Game.totalWinnings = gameInfo.total$;
						 Game.currentBet = gameInfo.bid;
						 Game.info = gameInfo;
						 
						 money = Game.evaluateWinnings();
						 gameInfo.total$ += money;
						 callback.accept("Client # " + count + " has lost the bet!");
//						 System.out.println("Server side for money is: " + money);						 
					 }
					 
				 }
				 
				 
				
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}

				 while(true) {
					    try {
					    	gameInfo = (BaccaratInfo) in.readObject();
					    	
					    	callback.accept("client #" + count + " has started the game!");
					    	logic(gameInfo);
					    	
					    	out.writeObject(gameInfo);
					    	updateClients(gameInfo);
					    	
					    	
					    	}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
//					    	updateClients("Client #"+count+" has left the server!");

					    	//Deleting all Items after function is done... 
							Dealer = new BaccaratDealer();
							Game = new BaccaratGame();
							gameLogic = new BaccaratGameLogic();
								
								
							tempPlayer.clear();
							tempBanker.clear();
								
								
							PlayerCards.clear();
							BankerCards.clear();
								
							temp = null;
							playerDraw = null;
							bankerDraw = null;
							
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}


	