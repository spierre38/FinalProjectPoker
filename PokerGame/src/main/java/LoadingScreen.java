import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingScreen extends JFrame {

    public LoadingScreen() {
        initComponents();
    }

    private void initComponents() {
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                ImageIcon icon = new ImageIcon("C:/Users/ecloaner/Pictures/Poker_Titles.png");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new FlowLayout());
        panel.add(startButton);

        setContentPane(panel);

        setTitle("Loading Screen");
        setSize(712, 610);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
        public void reloadLoadingScreen() {
        // Close the current LoadingScreen
        dispose();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoadingScreen().setVisible(true);
            }
        });
    }

    private void startGame() {
        // Close the loading screen
        dispose();

        GameHelper game = GameInitializer.initializeGame();
        GameManager.setGame(game);
        


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PokerGui pokerGui = new PokerGui();
                pokerGui.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoadingScreen().setVisible(true);
            }
        });
    }
}
