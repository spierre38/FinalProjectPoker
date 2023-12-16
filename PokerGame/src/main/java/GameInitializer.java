
import java.util.List;

public class GameInitializer {
    public static GameHelper initializeGame() {
        PlayerManager playerManager = PlayerManager.getInstance();

        List<Player> players = playerManager.getPlayers();
        players.clear(); // Clear any existing players

        players.add(new Player("Player1", 1000, true));
        for (int i = 1; i <= 3; i++) {
            NpcPlayer npcPlayer = new NpcPlayer(1000);
            players.add(npcPlayer);
        }

        return new GameHelper(players);
    }
}
