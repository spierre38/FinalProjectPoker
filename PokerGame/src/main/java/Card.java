
public class Card {
    // Enumerations for the suit and rank of the card
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    private Suit suit;
    private Rank rank;
    private String img; 

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.img = getImageUrl();  // Fixed: Call the getImageUrl method
    }

    // Getter methods to retrieve the suit and rank of the card
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    public String getImg() {
        return img;
    }

    private String getImageUrl() {
        switch (this.getSuit()) {
            case CLUBS:
                switch (this.getRank()) {
                    case TWO:return "C:/Users/ecloaner/Pictures/card_clubs_two.png"  ;
                    case THREE:return "C:/Users/ecloaner/Pictures/card_clubs_three.png" ;
                    case FOUR:return "C:/Users/ecloaner/Pictures/card_clubs_four.png" ;
                    case FIVE:return "C:/Users/ecloaner/Pictures/card_clubs_five.png" ;
                    case SIX:return "C:/Users/ecloaner/Pictures/card_clubs_six.png" ;
                    case SEVEN:return "C:/Users/ecloaner/Pictures/card_clubs_seven.png" ;
                    case EIGHT:return "C:/Users/ecloaner/Pictures/card_clubs_eight.png" ;
                    case NINE:return "C:/Users/ecloaner/Pictures/card_clubs_nine.png" ;
                    case TEN:return "C:/Users/ecloaner/Pictures/card_clubs_ten.png" ;
                    case JACK:return "C:/Users/ecloaner/Pictures/card_clubs_jack.png" ;
                    case QUEEN:return "C:/Users/ecloaner/Pictures/card_clubs_queen.png" ;
                    case KING:return "C:/Users/ecloaner/Pictures/card_clubs_king.png" ;
                    case ACE:return "C:/Users/ecloaner/Pictures/card_clubs_ace.png" ;
                }
                break;
            case DIAMONDS:
                switch (this.getRank()) {
                    case TWO:return "C:/Users/ecloaner/Pictures/card_diamonds_two.png" ;
                    case THREE:return "C:/Users/ecloaner/Pictures/card_diamonds_three.png" ;
                    case FOUR:return "C:/Users/ecloaner/Pictures/card_diamonds_four.png" ;
                    case FIVE:return "C:/Users/ecloaner/Pictures/card_diamonds_five.png" ;
                    case SIX:return "C:/Users/ecloaner/Pictures/card_diamonds_six.png" ;
                    case SEVEN:return "C:/Users/ecloaner/Pictures/card_diamonds_seven.png" ;
                    case EIGHT:return "C:/Users/ecloaner/Pictures/card_diamonds_eight.png" ;
                    case NINE:return "C:/Users/ecloaner/Pictures/card_diamonds_nine.png" ;
                    case TEN:return "C:/Users/ecloaner/Pictures/card_diamonds_ten.png" ;
                    case JACK:return "C:/Users/ecloaner/Pictures/card_diamonds_jack.png" ;
                    case QUEEN:return "C:/Users/ecloaner/Pictures/card_diamonds_queen.png" ;
                    case KING:return "C:/Users/ecloaner/Pictures/card_diamonds_king.png" ;
                    case ACE:return "C:/Users/ecloaner/Pictures/card_diamonds_ace.png" ;
                }
                break;

            case SPADES:
                switch (this.getRank()) {
                    case TWO:return "C:/Users/ecloaner/Pictures/card_hearts_two.png"  ;
                    case THREE:return "C:/Users/ecloaner/Pictures/card_hearts_three.png" ;
                    case FOUR:return "C:/Users/ecloaner/Pictures/card_hearts_four.png" ;
                    case FIVE:return "C:/Users/ecloaner/Pictures/card_hearts_five.png" ;
                    case SIX:return "C:/Users/ecloaner/Pictures/card_hearts_six.png" ;
                    case SEVEN:return "C:/Users/ecloaner/Pictures/card_hearts_seven.png" ;
                    case EIGHT:return "C:/Users/ecloaner/Pictures/card_hearts_eight.png" ;
                    case NINE:return "C:/Users/ecloaner/Pictures/card_hearts_nine.png" ;
                    case TEN:return "C:/Users/ecloaner/Pictures/card_hearts_ten.png" ;
                    case JACK:return "C:/Users/ecloaner/Pictures/card_hearts_jack.png" ;
                    case QUEEN:return "C:/Users/ecloaner/Pictures/card_hearts_queen.png" ;
                    case KING:return "C:/Users/ecloaner/Pictures/card_hearts_king.png" ;
                    case ACE:return "C:/Users/ecloaner/Pictures/card_hearts_ace.png" ;
                }
                break;
                
                
                
                        
            case HEARTS:
                switch (this.getRank()) {
                    case TWO: return "C:/Users/ecloaner/Pictures/card_spades_two.png"  ;
                    case THREE:return "C:/Users/ecloaner/Pictures/card_spades_three.png" ;
                    case FOUR:return "C:/Users/ecloaner/Pictures/card_spades_four.png" ;
                    case FIVE:return "C:/Users/ecloaner/Pictures/card_spades_five.png" ;
                    case SIX:return "C:/Users/ecloaner/Pictures/card_spades_six.png" ;
                    case SEVEN:return "C:/Users/ecloaner/Pictures/card_spades_seven.png" ;
                    case EIGHT:return "C:/Users/ecloaner/Pictures/card_spades_eight.png" ;
                    case NINE:return "C:/Users/ecloaner/Pictures/card_spades_nine.png" ;
                    case TEN:return "C:/Users/ecloaner/Pictures/card_spades_ten.png" ;
                    case JACK:return "C:/Users/ecloaner/Pictures/card_spades_jack.png" ;
                    case QUEEN:return "C:/Users/ecloaner/Pictures/card_spades_queen.png" ;
                    case KING:return "C:/Users/ecloaner/Pictures/card_spades_king.png" ;
                    case ACE:return "C:/Users/ecloaner/Pictures/card_spades_ace.png" ;
                }
                break;        }

        // Default case (should not reach here)
        return "";
    }
}
