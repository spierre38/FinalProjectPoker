import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int chips;
    private List<Card> hand;
    private int bet;
    private boolean folded;
    boolean isHuman;

    


    public Player(String name, int startingChips, boolean isHuman) {
        this.name = name;
        this.chips = startingChips;
        this.hand = new ArrayList<>();  // Initialize the hand
        this.isHuman = isHuman;
        this.folded = false; // Player starts the game not folded



    }

    public String getName() {
        return name;
    }
    
      public boolean isHuman() {
        return isHuman;
    }

    public int getChips() {
        return chips;
    }
    
       public void setChips(int chips) {
        this.chips = chips;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public void resetBet() {
        bet = 0;
    }

    public void clearHand() {
        hand.clear();
    }

    public boolean hasFolded() {
        return chips == 0;
    }

    public int getBet() {
        return bet;
    }

    public void clearBet() {
        bet = 0;
    }

    public void addToChips(int amount) {
        chips += amount;
    }

    public void bet(int amount) {
        if (amount > 0 && amount <= chips) {
            chips -= amount;
        } else {
            // Handle invalid bet
            System.out.println("Invalid bet amount");
        }
    }

    public void check() {
        System.out.println(name + " checks.");
    }


    public void call(int amount) {
        if (amount > 0 && amount <= chips) {
            chips -= amount;
        } else {
            System.out.println("Invalid call amount");
        }
    }

    public void raise(int amount) {
        if (amount > 0 && amount <= chips) {
            chips -= amount;
            System.out.println(name + " raises by " + amount);
        } else {
            System.out.println("Invalid raise amount");
        }
        
        
    }
    
        public boolean isFolded() {
        return folded;
    }

        public void fold() {
            folded = true;
        }
        
        public int decideBet() {
        int betAmount = (int) (Math.random() * 10) + 1;

        return betAmount;
    }
    

        
     public void makeDecision(GameHelper game) {
    }

}
