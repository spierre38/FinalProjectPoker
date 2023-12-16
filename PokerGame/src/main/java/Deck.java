import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    // Constructor to initialize a deck with 52 standard playing cards
    public Deck() {
        cards = new ArrayList<>();

        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    // Method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Method to deal a card from the deck
    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            return null; // Deck is empty
        }
    }
    
        public Card dealCommunityCard() {
        return dealCard();
    }

    // Method to check if the deck is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    

    
    

    // Example usage in a main method
    public static void main(String[] args) {
        // Create a new deck
        Deck deck = new Deck();

        // Shuffle the deck
        deck.shuffle();

        // Deal and print the first five cards from the deck
        for (int i = 0; i < 50; i++) {
            Card dealtCard = deck.dealCard();
            if (dealtCard != null) {
                System.out.println("Dealt Card: " + dealtCard);
            } else {
                System.out.println("Deck is empty.");
            }
        }
    }
}
