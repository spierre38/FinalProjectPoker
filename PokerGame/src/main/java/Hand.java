import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {
    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
        //  the cards are sorted by rank for easier evaluation
        Collections.sort(this.cards, Collections.reverseOrder());
    }

    // Getter and setter methods
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    // Method to add a card to the hand
    public void addCard(Card card) {
        cards.add(card);
        //  the cards are sorted by rank for easier evaluation
        Collections.sort(cards, Collections.reverseOrder());
    }
    


    // Method to evaluate the strength of the hand
public int evaluate(GameHelper game) {
    if (game.getCurrentPlayer().isFolded()) {
        // Return a very low value if the player has folded
        return -1;
    }

    int bestHandStrength = evaluateBestHand(game);
    
    return bestHandStrength;
}

public int evaluateBestHand(GameHelper game) {
    int bestHandStrength = -1;

    // Check for different types of hands and determine their strength
    if (isStraightFlush()) {
        bestHandStrength = 8; // Straight Flush
    } else if (isFourOfAKind()) {
        bestHandStrength = 7; // Four of a Kind
    } else if (isFullHouse()) {
        bestHandStrength = 6; // Full House
    } else if (isFlush()) {
        bestHandStrength = 5; // Flush
    } else if (isStraight()) {
        bestHandStrength = 4; // Straight
    } else if (isThreeOfAKind()) {
        bestHandStrength = 3; // Three of a Kind
    } else if (isTwoPair()) {
        bestHandStrength = 2; // Two Pair
    } else if (isOnePair()) {
        bestHandStrength = 1; // One Pair
    } else {
        bestHandStrength = 0; // High Card
    }

    return bestHandStrength;
}


    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isFourOfAKind() {
        Map<Card.Rank, Long> rankCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        return rankCount.containsValue(4L);
    }

    private boolean isFullHouse() {
        Map<Card.Rank, Long> rankCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        return rankCount.containsValue(3L) && rankCount.containsValue(2L);
    }

    private boolean isFlush() {
        // Check if all cards have the same suit
        Card.Suit firstCardSuit = cards.get(0).getSuit();
        return cards.stream().allMatch(card -> card.getSuit() == firstCardSuit);
    }

    private boolean isStraight() {
        // Check if the cards form a five-card sequence
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank().ordinal() - cards.get(i + 1).getRank().ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind() {
        Map<Card.Rank, Long> rankCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        return rankCount.containsValue(3L);
    }

    private boolean isTwoPair() {
        Map<Card.Rank, Long> rankCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        long pairCount = rankCount.values().stream().filter(count -> count == 2L).count();
        return pairCount == 2;
    }

    private boolean isOnePair() {
        Map<Card.Rank, Long> rankCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        return rankCount.containsValue(2L);
    }
    
     public int getHandStrength(GameHelper game) {
        if (game.getCurrentPlayer().isFolded()) {
            // Return a very low value if the player has folded
            return -1;
        }
        if (isStraightFlush()) {
            return 8; // Straight Flush
        } else if (isFourOfAKind()) {
            return 7; // Four of a Kind
        } else if (isFullHouse()) {
            return 6; // Full House
        } else if (isFlush()) {
            return 5; // Flush
        } else if (isStraight()) {
            return 4; // Straight
        } else if (isThreeOfAKind()) {
            return 3; // Three of a Kind
        } else if (isTwoPair()) {
            return 2; // Two Pair
        } else if (isOnePair()) {
            return 1; // One Pair
        } else {
            return 0; // High Card
        }
    }

}
