public class GameManager {
    private static GameHelper game;

    public static void setGame(GameHelper game) {
        GameManager.game = game;
    }

    public static GameHelper getGame() {
        return game;
    }
}
