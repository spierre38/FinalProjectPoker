import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinnerWindow extends JFrame {

    public WinnerWindow(String winnerName, ActionListener playAgainListener) {
        initComponents(winnerName, playAgainListener);
    }

    private void initComponents(String winnerName, ActionListener playAgainListener) {
        JLabel winnerLabel = new JLabel("Winner: " + winnerName);
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton playAgainButton = new JButton("Play Again??");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the winner window
                dispose();

                playAgainListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        });

        JPanel panel = new JPanel(new BorderLayout());

        ImageIcon icon = new ImageIcon("C:/Users/ecloaner/Pictures/Poker_Titles.png");
        JLabel imageLabel = new JLabel(icon);
        panel.add(imageLabel, BorderLayout.NORTH);

        JPanel subPanel = new JPanel(new BorderLayout());
        subPanel.add(winnerLabel, BorderLayout.CENTER);
        subPanel.add(playAgainButton, BorderLayout.SOUTH);

        panel.add(subPanel, BorderLayout.CENTER);

        setContentPane(panel);
        setTitle("Winner");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
