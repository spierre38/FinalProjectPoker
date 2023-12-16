
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static PlayerManager instance;
    private List<Player> players;

    private PlayerManager() {
        players = new ArrayList<>();
    }

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
