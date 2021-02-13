import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


//These are my test cases for my Server ...

class MyTest {
	
//----------------------------------------------------------------
	//card constructor test
	@Test
	void cardTest1() {
		Card test1 = new Card ("Test1", 100);
		assertEquals("Test1", test1.suite, "Wrong Value...");
	}
//-----------------------------------------------------------------
	//BaccaratDealer TESTS 
	
	//tests constructor
	@Test
	void DealerTest()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
		int answer = theDealer.deck.size();
		
		assertEquals(0, answer, "Wrong");
		
		theDealer.generateDeck();
		answer = theDealer.deck.size();
		assertEquals(52, answer, "Wrong");
	}
	
	//tests it makes 52 cards
	@Test 
	void generateDeckTest1()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
//		
		theDealer.generateDeck(); 
		ArrayList<Card> myDeck = new ArrayList<Card>();
		myDeck = theDealer.deck;
		
		int answer = 52;
		assertEquals(answer, myDeck.size(), "Wrong");
	}
	
	//tests it generates the right suites and values
	@Test
	void generateDeckTest2()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
		
		theDealer.generateDeck(); 
		ArrayList<Card> myDeck = new ArrayList<Card>();
		myDeck = theDealer.deck;
		
		Card secondCard = new Card ("Spades", 2);
		
		Card eightDiamonds = new Card ("Diamonds", 8);
		
		assertEquals(secondCard.suite, myDeck.get(1).suite, "Wrong");
		assertEquals(secondCard.value, myDeck.get(1).value, "Wrong");
		
		assertEquals(eightDiamonds.suite, myDeck.get(33).suite, "Wrong");
		assertEquals(eightDiamonds.value, myDeck.get(33).value, "Wrong");
	}
	//tests it gives 2 cards
	@Test
	void dealHandTest1()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
//		
		theDealer.generateDeck(); 
		ArrayList<Card> myDeck = new ArrayList<Card>();
		myDeck = theDealer.deck;
		ArrayList<Card> playerHand = new ArrayList<Card>();
		
		playerHand = theDealer.dealHand();
		assertEquals(2, playerHand.size(), "Wrong");
	}
	
	//tests it removes the cards dealt from the deck
	@Test
	void dealHandTest2()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
//		
		theDealer.generateDeck(); 
	//	ArrayList<Card> myDeck = new ArrayList<Card>();
//		myDeck = theDealer.deck;
		ArrayList<Card> playerHand = new ArrayList<Card>();
		
		Card thirdCard = new Card("Spades", 3);
		
		playerHand = theDealer.dealHand();
		
		assertEquals(thirdCard.suite, theDealer.deck.get(0).suite, "Wrong");
		assertEquals(thirdCard.value, theDealer.deck.get(0).value, "Wrong");
	}
	
	//tests the card it draws removevs from the deck
	@Test
	void drawOneTest1()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
		
		theDealer.generateDeck();
		Card firstCard = theDealer.drawOne();
		Card secondCard = new Card("Spades", 2);
		
		assertEquals(secondCard.suite, theDealer.deck.get(0).suite, "Wrong");
		assertEquals(secondCard.value, theDealer.deck.get(0).value, "Wrong");
	}
	
	//tests the drawOne draws from top of deck
	@Test
	void drawOneTest2()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
		
		theDealer.generateDeck();
		
		Card firstCard = theDealer.drawOne();
//		Card secondCard = new Card("Spades", 2);
		
		assertEquals(firstCard.suite, "Spades", "Wrong");
		assertEquals(firstCard.value, 1, "Wrong");
	}
	
	//-----------------------------------------------------------------------------------------
	
	
	//tests that the shuffled deck is not the same as the generated deck
	//might fail but would require crazy odds
	@Test
	void shuffleTest1()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
		BaccaratDealer shuffleDealer = new BaccaratDealer();
		theDealer.generateDeck();
		
		shuffleDealer.generateDeck();
		shuffleDealer.shuffleDeck();
		Boolean answer = false;
		
		if(shuffleDealer != theDealer)
			answer = true;
		
		assertEquals(true, answer, "Wrong");
	}
	
	//checks to see if it shuffles a different order for different deck objects 
	@Test
	void shuffleTest2()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
		BaccaratDealer shuffleDealer = new BaccaratDealer();
		theDealer.generateDeck();
		theDealer.shuffleDeck();
		
		shuffleDealer.generateDeck();
		shuffleDealer.shuffleDeck();
		
		
		Boolean answer = false;
		
		if (theDealer != shuffleDealer)
			answer = true;
		
		assertEquals(true, answer, "Wrong");
	}
	//-------------------------------
	//tests that subtracting from deck affects size
	@Test
	void deckSizeTest1()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
		
		theDealer.generateDeck();
		
		theDealer.dealHand();
		
		assertEquals(50, theDealer.deck.size(), "Wrong");
	}
	
	//tests that subtracting from one deck doesn't subtract from another
	@Test
	void deckSizeTest2()
	{
		BaccaratDealer theDealer = new BaccaratDealer();
		
		theDealer.generateDeck();
		
		theDealer.dealHand();
		
		assertEquals(50, theDealer.deck.size(), "Wrong");
		
		BaccaratDealer secondDealer = new BaccaratDealer();
		
		secondDealer.generateDeck();
		secondDealer.dealHand();
		secondDealer.drawOne();
		
		assertEquals(49, secondDealer.deck.size(), "Wrong");
		assertEquals(50, theDealer.deck.size(), "Wrong");
	}
	
	//--------------------------------------------------------------------------------------------------
	//BaccaratGame TEST TODO constructor and evalWinnings
	//tests that the winnings get added if player wins
	
	//tests constructor
	@Test
	void BaccaratGameConstructorTest()
	{
		BaccaratGame game = new BaccaratGame();
		
		assertEquals(0, game.currentBet, "Wrong");
		assertEquals(0, game.totalWinnings, "Wrong");
		assertEquals(null, game.playerHand, "Wrong");
		assertEquals(null, game.bankerHand, "Wrong");
		
	}
	//tests that winnings get added if player wins
	@Test
	void evalWinningsTest1()
	{
		
//		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		BaccaratInfo info = new BaccaratInfo();
		ArrayList<Card> playerHand = new ArrayList<Card> ();
		BaccaratDealer myDealer = new BaccaratDealer();
		Card firstCard = new Card("Spades", 9);
		Card secondCard = new Card("Diamonds", 9);
		playerHand.add(firstCard);
		playerHand.add(secondCard);
		
		ArrayList<Card> bankHand = new ArrayList<Card> ();
		Card BfirstCard = new Card("Spades", 12);
		Card BsecondCard = new Card("Diamonds", 6);
		bankHand.add(BfirstCard);
		bankHand.add(BsecondCard);
		
		info.who = "Player";
		
		
		BaccaratGame game = new BaccaratGame();
		game.currentBet = 500;
		game.playerHand = playerHand;
		game.bankerHand = bankHand;
		game.totalWinnings = 30;
		game.theDealer = myDealer;
		game.info = info;
		double answer = game.evaluateWinnings();
//		System.out.println(answer);
		assertEquals(530, answer, "Wrong");
	}
	
	//tests that the winnings get added if theres a tie
	@Test
	void evalWinningsTest2()
	{
		
//		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		BaccaratInfo info = new BaccaratInfo();
		info.who = "Draw";
		ArrayList<Card> playerHand = new ArrayList<Card> ();
		BaccaratDealer myDealer = new BaccaratDealer();
		Card firstCard = new Card("Spades", 8);
		Card secondCard = new Card("Diamonds", 1);
		playerHand.add(firstCard);
		playerHand.add(secondCard);
		
		//player hand is 9
		
		ArrayList<Card> bankHand = new ArrayList<Card> ();
		Card BfirstCard = new Card("Spades", 9);
		Card BsecondCard = new Card("Diamonds", 10);
		bankHand.add(BfirstCard);
		bankHand.add(BsecondCard);
		
		//bank hand is 9
		
		BaccaratGame game = new BaccaratGame();
		game.currentBet = 500;
		game.playerHand = playerHand;
		game.bankerHand = bankHand;
		game.totalWinnings = 30;
		game.theDealer = myDealer;
		game.info = info;
		
		double answer = game.evaluateWinnings();
//		System.out.println(answer);
		assertEquals(4030, answer, "Wrong");
	}
	
	//---------------------------------------------------------------------------------------------------
	//GameLogic TEST TODO constructor
	
	
	@Test
	void gameLogicTest()
	{
		//IDK HOW TO TEST THIS WITH NO DATA MEMBERS @daniela pls fix
		
		//Update: I tried to make a test case out of this and I have no idea how to do it. It is randomized.
		BaccaratGameLogic logic = new BaccaratGameLogic();
		
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		test.shuffleDeck();
		
		ArrayList<Card>testing1 = test.dealHand();
		ArrayList<Card>testing2 = test.dealHand();
		
		String whoWon = logic.whoWon(testing1, testing2);
		
		//assertEquals("Banker", whoWon, "wrong");
		
		System.out.print(whoWon);

	}
	//tests they total the right thing with face cards
	
	
	@Test
	void handTotalTest1()
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> myHand = new ArrayList<Card> ();
		Card firstCard = new Card("Spades", 12);
		Card secondCard = new Card("Diamonds", 6);
		myHand.add(firstCard);
		myHand.add(secondCard);
		int answer = BaccaratGameLogic.handTotal(myHand);
		
		assertEquals(6, answer, "Wrong");
	}
	//tests they total the right thing with a total above 10
	@Test
	void handTotalTest2()
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> myHand = new ArrayList<Card> ();
		Card firstCard = new Card("Spades", 9);
		Card secondCard = new Card("Diamonds", 9);
		myHand.add(firstCard);
		myHand.add(secondCard);
		int answer = BaccaratGameLogic.handTotal(myHand);
		
		assertEquals(8, answer, "Wrong");
	}
	
	//tests if player won
	@Test
	void whoWonTest1()
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<Card> ();
		Card firstCard = new Card("Spades", 9);
		Card secondCard = new Card("Diamonds", 9);
		playerHand.add(firstCard);
		playerHand.add(secondCard);
		
		ArrayList<Card> bankHand = new ArrayList<Card> ();
		Card BfirstCard = new Card("Spades", 12);
		Card BsecondCard = new Card("Diamonds", 6);
		bankHand.add(BfirstCard);
		bankHand.add(BsecondCard);
		
		String answer = gameLogic.whoWon(playerHand, bankHand);
		assertEquals("Player", answer, "Wrong");
	}
	
	@Test
	//tests that there was a tie
	void whoWonTest2()
	{
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<Card> ();
		Card firstCard = new Card("Spades", 9);
		Card secondCard = new Card("Diamonds", 9);
		playerHand.add(firstCard);
		playerHand.add(secondCard);
		
		ArrayList<Card> bankHand = new ArrayList<Card> ();
		Card BfirstCard = new Card("Spades", 9);
		Card BsecondCard = new Card("Diamonds", 9);
		bankHand.add(BfirstCard);
		bankHand.add(BsecondCard);
		
		String answer = gameLogic.whoWon(playerHand, bankHand);
		assertEquals("Draw", answer, "Wrong");
	}
	//tests the check for player to draw is false
	@Test
	void evalPlayerDrawTest1()
	{
//		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<Card> ();
		Card firstCard = new Card("Spades", 9);
		Card secondCard = new Card("Diamonds", 9);
		playerHand.add(firstCard);
		playerHand.add(secondCard);
		
		Boolean answer = BaccaratGameLogic.evaluatePlayerDraw(playerHand);
		
		assertEquals(false, answer, "Wrong");
	}
	//tests the check for player to draw is true
	@Test
	void evalPlayerDrawTest2()
	{
//		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<Card> ();
		Card firstCard = new Card("Spades", 2);
		Card secondCard = new Card("Diamonds", 11);
		playerHand.add(firstCard);
		playerHand.add(secondCard);
		
		Boolean answer = BaccaratGameLogic.evaluatePlayerDraw(playerHand);
		
		assertEquals(true, answer, "Wrong");
	}
	
	//checks for bank to draw and is false
	@Test
	void evalBankerDrawTest1()
	{
		ArrayList<Card> bankHand = new ArrayList<Card> ();
		Card BfirstCard = new Card("Spades", 9);
		Card BsecondCard = new Card("Diamonds", 9);
		Card PfirstCard = new Card("Spades", 2);
		bankHand.add(BfirstCard);
		bankHand.add(BsecondCard);
		
		Boolean answer = BaccaratGameLogic.evaluateBankerDraw(bankHand, PfirstCard);
		
		assertEquals(false, answer, "Wrong");
	}
	//checks for bank to draw and is true
	@Test
	void evalBankerDrawTest2()
	{
		ArrayList<Card> bankHand = new ArrayList<Card> ();
		Card BfirstCard = new Card("Spades", 1);
		Card BsecondCard = new Card("Diamonds", 2);
		Card PfirstCard = new Card("Spades", 2);
		bankHand.add(BfirstCard);
		bankHand.add(BsecondCard);
		
		Boolean answer = BaccaratGameLogic.evaluateBankerDraw(bankHand, PfirstCard);
		
		assertEquals(true, answer, "Wrong");
	}
	
	
	@Test
	void BaccaratInfoConstructorTest()
    {
        BaccaratInfo info = new BaccaratInfo();
        
        assertEquals(0, info.clientNumber);
        assertEquals(0, info.bid);
        assertEquals("",info.who);
        assertEquals("",info.winner);
        assertEquals(0,info.howMuch$);

        assertEquals(0, info.total$);
        assertEquals(false, info.natural);
        assertEquals(false, info.again);
        assertEquals("No card was drawn", info.thirdPlayerCard);
        assertEquals("No card was drawn", info.thirdBankerCard);
        
    } 
	
	
	//-------------------------------------------------------------------------
}
