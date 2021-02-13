import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

/* Project 3:
 * Students: Daniela Chavez & Jack Martin
 * NetID's: dchave29 & jmart303
 * Class: CS342
 * Fall 2020 (Corona time)
 */

public class Client extends Thread{

	BaccaratInfo gameInfo;
	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call){
	
		callback = call;
	}
	
	public void run() {
		
		try {
		socketClient= new Socket("127.0.0.1",5555);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		
		while(true) {
			 
			try {
			gameInfo = (BaccaratInfo) in.readObject();
			
			callback.accept(gameInfo);
			//System.out.print("In client.java -> " +gameInfo.playerHand);
			}
			catch(Exception e) {}
		}
	
    }
	
	public void send(BaccaratInfo B) {
		
		try {
			out.writeObject(B);
			//System.out.println("Client side: " + B.bid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
