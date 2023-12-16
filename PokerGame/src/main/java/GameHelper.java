import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    private List<Player> players;
    private static int pot;
    private List<Card> communityCards = new ArrayList<>();
    private int currentRound;
    private Deck deck;
    private int roundBet;
    private Player currentPlayer;
    private final int minimumBet = 2;
    private static final int MAX_ROUNDS = 4; 
    static int money; 
    public GameHelper(List<Player> players) {
        this.players = players;
        this.pot = 0;
        this.communityCards = new ArrayList<>();
        this.currentRound = 0;
        this.deck = new Deck();
        this.roundBet = 0;
        this.currentPlayer = players.get(0);
        this.money = currentPlayer.getChips();
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void nextPlayer() {
    // Get the list of players
    List<Player> players = getPlayers();

    // Find the index of the current player
    int currentPlayerIndex = players.indexOf(getCurrentPlayer());

    // Determine the index of the next player
    int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();

    // Set the next player as the current player
    setCurrentPlayer(players.get(nextPlayerIndex));
}


    private void dealPlayerCards() {
        for (Player player : players) {
            List<Card> playerCards = new ArrayList<>();
            playerCards.add(deck.dealCard());
            playerCards.add(deck.dealCard());
            player.setHand(playerCards);
        }
    }
    
        public static int getMaxRounds() {
        return MAX_ROUNDS;
    }

    public void startRound() {
        for (Player player : players) {
            player.resetBet();
        }

        deck.shuffle();

        communityCards.clear();

        currentRound++;

        dealPlayerCards();
        
       
    }

    public void dealCommunityCards() {
        for (int i = 0; i < 5; i++) {
            communityCards.add(deck.dealCommunityCard());
        }
    }

    public void bet(int amount) {
        currentPlayer.bet(amount);
        roundBet += amount;
        pot += amount;
        this.money-=amount;
        
        

    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getPlayerBalance() {
        return currentPlayer.getChips();
    }
    
    

    public int getPlayerBet() {
        return currentPlayer.getBet();
    }

    public int getPot() {
        return pot;
    }

    public int getMinimumBet() {
        return minimumBet;
    }

    public int getRoundBet() {
        return roundBet;
        
    }
     public void increaseBalance(int amount) {

         currentPlayer.addToChips(amount);
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public int getCurrentRound() {
        return currentRound;
    }
    
    
    
    String getRoundName(int roundNumber) {
    switch (roundNumber) {
        case 0:
            return "Pre-Flop";
        case 1:
            return "Flop";
        case 2:
            return "Turn";
        case 3:
            return "River";
        default:
            return "Unknown Round";
    }
}

    
        public void incrementCurrentRound() {
        currentRound++;
    }


    public void playerFold() {
        currentPlayer.fold();
    }

    public void playerCall(int amount) {
        currentPlayer.call(amount);
        pot += amount;
    }

    public void playerRaise(int amount) {
        currentPlayer.raise(amount);
        pot += amount;
    }
    
    /**
     *
     * @return
     */
    public boolean checkEndOfGame() {

        return getCurrentRound() >= getMaxRounds(); 
}
    
    public boolean isGameOver() {
        return checkEndOfGame();
    }
    

    public int evaluateHandWithCommunityCards(Player player) {
        List<Card> playerHand = player.getHand();
        List<Card> communityCards = getCommunityCards();

        int score = 0;

        // Compare ranks
        for (Card playerCard : playerHand) {
            for (Card communityCard : communityCards) {
                if (playerCard.getRank() == communityCard.getRank()) {
                    score += 2;
                }
            }
        }

        // Compare suits
        for (Card playerCard : playerHand) {
            for (Card communityCard : communityCards) {
                if (playerCard.getSuit() == communityCard.getSuit()) {
                    score += 1;
                }
            }
        }

        return score;
    }


    void updatePot(int betAmount) {

        pot += betAmount;

        System.out.println("Pot updated. New pot amount: " + pot);
    }

}
