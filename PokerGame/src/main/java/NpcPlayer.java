import java.util.List;

public class NpcPlayer extends Player {
    private static final List<String> RANDOM_NAMES = List.of(
            "Alpha", "Bravo", "Charlie", "Delta", "Echo",
            // ... (existing names)
            "Whiskey", "X-Ray", "Yankee", "Zulu",
            "Ace", "King", "Queen", "Jack");

    private static int npcCount = 0;

    public NpcPlayer(int initialChips) {
        super(generateNpcName(), initialChips, false); // Set isNpc to false
    }

    private static String generateNpcName() {
        int index = npcCount % RANDOM_NAMES.size();
        npcCount++;
        return RANDOM_NAMES.get(index);
    }

    @Override
    public boolean isHuman() {
        return false; 
    }

    @Override

    public void makeDecision(GameHelper game) {
        if (game.getCurrentRound() == 0) {
            
            
            int minimumBet = 2; // Minimum bet for preflop
            int randomRaiseThreshold = 80; // Percentage chance to randomly raise

            if (Math.random() * 100 < randomRaiseThreshold) {
                int raiseAmount = minimumBet + (int) (Math.random() * 5); // Random raise amount
                game.playerRaise(raiseAmount);
                game.updatePot(raiseAmount); // Update the pot
            } else {
                game.playerCall(minimumBet);
                game.updatePot(minimumBet); // Update the pot
            }
        } else {
            // Postflop logic
            int callThreshold = 20; // Percentage chance to call
            int raiseThreshold = 70; // Percentage chance to raise
            int foldThreshold = 20; // Percentage chance to fold if the hand is weak and balance is low
            int lowBalanceThreshold = 200; // Threshold for low balance
            int handStrength = decideBet(); // Evaluate the strength of the hand

            if (handStrength >= 5) {
                if (Math.random() * 100 < raiseThreshold) {
                    int raiseAmount = game.getMinimumBet(); // Raise the minimum bet
                    game.playerRaise(raiseAmount);
                    game.updatePot(raiseAmount); // Update the pot
                } else {
                    game.playerCall(game.getMinimumBet());
                    game.updatePot(game.getMinimumBet()); 
                }
            } else {
                if (Math.random() * 100 < foldThreshold && game.getPlayerBalance() < lowBalanceThreshold) {
                    game.playerFold();
                } else {
                    if (Math.random() * 100 < callThreshold) {
                        game.playerCall(game.getMinimumBet());
                        game.updatePot(game.getMinimumBet()); // Update the pot
                    } else {
                        game.playerFold();
                    }
                }
            }
        }
    }
}
