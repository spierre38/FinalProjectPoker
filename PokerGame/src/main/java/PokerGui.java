import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public final class PokerGui extends javax.swing.JFrame {
    
    GameHelper game;
    private int selectedBetAmount = 0;  // Add this variable to store the selected bet amount
    private Player currentPlayer;





    /**
     * Creates new form PokerGui
     */
    public PokerGui() {
        this.game = GameInitializer.initializeGame();
        GameManager.setGame(game);
        initComponents();
        game.startRound(); // This method deals cards to players and community cards
        initializeGame();
        updateGUI();
        updateNPCNames();
        dealCommunityCardsAndUpdate();

     
       // GameHelper retrievedGame = GameManager.getGame();

        //updateNPCCards();
        
        

    }
    
    
    private void initializeGame() {

        // Create the game instance

        jbtnDealer.setText("Yes");
        jbtnDealer2.setText("No");

        // Set text for jtxtDealer
        jtxtDealer.setText("Are you ready?");

    }
    
    
    
        private void updateGUI() {
        // Update GUI elements based on the game state
        jtxtBalance.setText("$" + game.money);
        jtxtYourBet.setText("$" + game.getPlayerBet());
        jtxtPot.setText("$" + game.getPot());
        jtxtRoundBet.setText("$" + game.getRoundBet());
     // Assuming you have a method to get or construct the console log content
    String consoleLogContent = constructConsoleLogContent();

    // Set the text of the JtextAreaConsoleLog
    jtxtAreaConsoleLog.setText(consoleLogContent);
        // Update other relevant GUI elements
    }
        
private String constructConsoleLogContent() {
    // Customize this method based on how you want to update the console log
    String logContent = "Round: " + game.getCurrentRound() + "\n";
    logContent += "Pot: $" + game.getPot() + "\n";

    // Add information about the current player's turn
    Player currentPlayer = game.getCurrentPlayer();
    logContent += "Current Turn: " + currentPlayer.getName() + "\n";

    // Check if the current player has folded
    if (currentPlayer.isFolded()) {
        logContent += currentPlayer.getName() + " has folded.\n";
    }


    return logContent;
}

        // Update this method to set the currentPlayer when needed
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
        this.game.setCurrentPlayer(player);
    }
    
    private boolean isPlayerTurn() {
    return currentPlayer instanceof Player;
}
    

   
        



private void updatePlayerCards() {
    Player player = GameManager.getGame().getPlayers().get(0); // Assuming player1 is at index 0
    List<Card> playerCards = player.getHand();

    // Check if the player has cards in hand
    if (!playerCards.isEmpty()) {
        // Assuming your Card class has getImageUrl method
        String card1ImagePath = playerCards.get(0).getImg();
        ImageIcon card1Icon = new ImageIcon(card1ImagePath);

        // Check if the player has at least two cards in hand
        if (playerCards.size() >= 2) {
            String card2ImagePath = playerCards.get(1).getImg();
            ImageIcon card2Icon = new ImageIcon(card2ImagePath);

            // Set icons for player1Card1 and player1Card2
            player1Card1.setIcon(card1Icon);
            player1Card2.setIcon(card2Icon);
        }
    }
    
    
}



private void updateFlopCards(List<Card> communityCards) {
    if (communityCards.size() >= 3) {
        flop1Card.setIcon(new ImageIcon(communityCards.get(0).getImg()));
        flop2Card.setIcon(new ImageIcon(communityCards.get(1).getImg()));
        flop3Card.setIcon(new ImageIcon(communityCards.get(2).getImg()));
    }
}

private void updateTurnCard(List<Card> communityCards) {
    if (communityCards.size() >= 4) {
        turnCard.setIcon(new ImageIcon(communityCards.get(3).getImg()));
    }
}

private void updateRiverCard(List<Card> communityCards) {
    if (communityCards.size() == 5) {
        riverCard.setIcon(new ImageIcon(communityCards.get(4).getImg()));
    }
}

private void updateCommunityCards(List<Card> communityCards) {
    updateFlopCards(communityCards);
    updateTurnCard(communityCards);
    updateRiverCard(communityCards);
}

private void dealCommunityCardsAndUpdate() {
    GameManager.getGame().dealCommunityCards();
    List<Card> communityCards = GameManager.getGame().getCommunityCards();
    updateCommunityCards(communityCards);
}







 private void updateNPCCards() {
    for (int i = 1; i < game.getPlayers().size(); i++) {
        Player npcPlayer = game.getPlayers().get(i);
        List<Card> npcCards = npcPlayer.getHand();

        // Check if the NPC player has cards in hand
        if (!npcCards.isEmpty()) {

            String npcCard1ImagePath = npcCards.get(0).getImg();
            ImageIcon npcCard1Icon = new ImageIcon(npcCard1ImagePath);

            // Check if the NPC player has at least two cards in hand
            if (npcCards.size() >= 2) {
                String npcCard2ImagePath = npcCards.get(1).getImg();
                ImageIcon npcCard2Icon = new ImageIcon(npcCard2ImagePath);

                // Set icons for NPC cards based on their index
                switch (i) {
                    case 1:
                        player2Card1.setIcon(npcCard1Icon);
                        player2Card2.setIcon(npcCard2Icon);
                        break;
                    case 2:
                        player3Card1.setIcon(npcCard1Icon);
                        player3Card2.setIcon(npcCard2Icon);
                        break;
                    case 3:
                        player4Card1.setIcon(npcCard1Icon);
                        player4Card2.setIcon(npcCard2Icon);
                        break;


                    default:
                        break;
                }
            }
        }
    }
}







    public void updateNPCNames() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);

            // Assuming NPC players start from index 1 in the players list
            if (i > 0 && i <= 3) {
                String npcName = player.getName();
                updateNPCLabel(i, npcName);
            }
        }
    }

    private void updateNPCLabel(int npcIndex, String npcName) {
        // Update the corresponding JLabel components with NPC names
        switch (npcIndex) {
            case 1:
                nameNPC4.setText(npcName);
                break;
            case 2:
                nameNPC2.setText(npcName);
                break;
            case 3:
                nameNPC3.setText(npcName);
                break;


            default:
                break;
        }
    }

//////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////
    //////GAME INTERFACEEE
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        player3Card2 = new javax.swing.JLabel();
        player2Card2 = new javax.swing.JLabel();
        player2Card1 = new javax.swing.JLabel();
        player3Card1 = new javax.swing.JLabel();
        player1Card1 = new javax.swing.JLabel();
        player1Card2 = new javax.swing.JLabel();
        player4Card1 = new javax.swing.JLabel();
        player4Card2 = new javax.swing.JLabel();
        jbtn5 = new javax.swing.JButton();
        jbtn10 = new javax.swing.JButton();
        jbtn25 = new javax.swing.JButton();
        jbtn100 = new javax.swing.JButton();
        jbtn250 = new javax.swing.JButton();
        jbtn500 = new javax.swing.JButton();
        jbtn2 = new javax.swing.JButton();
        jbtnclear = new javax.swing.JButton();
        turnCard = new javax.swing.JLabel();
        flop2Card = new javax.swing.JLabel();
        flop1Card = new javax.swing.JLabel();
        riverCard = new javax.swing.JLabel();
        flop3Card = new javax.swing.JLabel();
        jbtnBet = new javax.swing.JButton();
        jbtnRaise = new javax.swing.JButton();
        jbtnFold = new javax.swing.JButton();
        jtxtBalance = new javax.swing.JTextField();
        jtxtYourBet = new javax.swing.JTextField();
        jtxtPot = new javax.swing.JTextField();
        jtxtRoundBet = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtDealer = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nameNPC4 = new javax.swing.JLabel();
        nameNPC3 = new javax.swing.JLabel();
        nameNPC2 = new javax.swing.JLabel();
        jlblDealer = new javax.swing.JLabel();
        jbtnDealer = new javax.swing.JButton();
        jlblConsole = new javax.swing.JLabel();
        jbtnDealer2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtAreaConsoleLog = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        jLabel12.setText("jLabel12");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POKER TABLE");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 0, 610, 712));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMaximumSize(new java.awt.Dimension(712, 610));
        setMinimumSize(new java.awt.Dimension(712, 610));
        setPreferredSize(new java.awt.Dimension(712, 610));
        setResizable(false);
        setSize(new java.awt.Dimension(712, 610));
        getContentPane().setLayout(null);

        player3Card2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        player3Card2.setText("CARD");
        getContentPane().add(player3Card2);
        player3Card2.setBounds(340, 30, 56, 92);

        player2Card2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        player2Card2.setText("CARD");
        getContentPane().add(player2Card2);
        player2Card2.setBounds(630, 180, 60, 60);

        player2Card1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        player2Card1.setText("CARD");
        getContentPane().add(player2Card1);
        player2Card1.setBounds(580, 180, 70, 60);

        player3Card1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        player3Card1.setText("CARD");
        getContentPane().add(player3Card1);
        player3Card1.setBounds(390, 30, 60, 92);

        player1Card1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        getContentPane().add(player1Card1);
        player1Card1.setBounds(350, 450, 60, 92);

        player1Card2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        player1Card2.setText("CARD");
        getContentPane().add(player1Card2);
        player1Card2.setBounds(400, 450, 60, 92);

        player4Card1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        player4Card1.setText("CARD");
        getContentPane().add(player4Card1);
        player4Card1.setBounds(0, 170, 70, 60);

        player4Card2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        player4Card2.setText("CARD");
        getContentPane().add(player4Card2);
        player4Card2.setBounds(50, 170, 70, 60);

        jbtn5.setText("5");
        jbtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn5ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtn5);
        jbtn5.setBounds(210, 550, 40, 23);

        jbtn10.setText("10");
        jbtn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn10ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtn10);
        jbtn10.setBounds(260, 550, 50, 23);

        jbtn25.setText("25");
        jbtn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn25ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtn25);
        jbtn25.setBounds(324, 550, 50, 23);

        jbtn100.setText("100");
        jbtn100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn100ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtn100);
        jbtn100.setBounds(380, 550, 58, 23);

        jbtn250.setText("250");
        jbtn250.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn250ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtn250);
        jbtn250.setBounds(450, 550, 62, 23);

        jbtn500.setText("500");
        jbtn500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn500ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtn500);
        jbtn500.setBounds(520, 550, 56, 23);

        jbtn2.setText("2");
        jbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn2ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtn2);
        jbtn2.setBounds(160, 550, 40, 23);

        jbtnclear.setText("Clear");
        jbtnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnclearActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnclear);
        jbtnclear.setBounds(580, 550, 72, 23);

        turnCard.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        turnCard.setText("CARD");
        getContentPane().add(turnCard);
        turnCard.setBounds(380, 160, 60, 92);

        flop2Card.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        flop2Card.setText("CARD");
        getContentPane().add(flop2Card);
        flop2Card.setBounds(280, 160, 60, 92);

        flop1Card.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        flop1Card.setText("CARD");
        getContentPane().add(flop1Card);
        flop1Card.setBounds(230, 160, 60, 92);

        riverCard.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        riverCard.setText("CARD");
        getContentPane().add(riverCard);
        riverCard.setBounds(430, 160, 60, 92);

        flop3Card.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\card_back.png")); // NOI18N
        flop3Card.setText("CARD");
        getContentPane().add(flop3Card);
        flop3Card.setBounds(330, 160, 60, 92);

        jbtnBet.setText("Bet");
        jbtnBet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBetActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnBet);
        jbtnBet.setBounds(380, 430, 72, 23);

        jbtnRaise.setText("Raise");
        jbtnRaise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRaiseActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnRaise);
        jbtnRaise.setBounds(294, 430, 80, 23);

        jbtnFold.setText("Fold");
        jbtnFold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFoldActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnFold);
        jbtnFold.setBounds(460, 430, 72, 23);

        jtxtBalance.setEditable(false);
        jtxtBalance.setText("$");
        jtxtBalance.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(jtxtBalance);
        jtxtBalance.setBounds(430, 300, 60, 22);

        jtxtYourBet.setEditable(false);
        jtxtYourBet.setText("$");
        jtxtYourBet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtYourBetActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtYourBet);
        jtxtYourBet.setBounds(430, 330, 60, 22);

        jtxtPot.setEditable(false);
        jtxtPot.setText("$");
        getContentPane().add(jtxtPot);
        jtxtPot.setBounds(428, 358, 60, 22);

        jtxtRoundBet.setEditable(false);
        jtxtRoundBet.setText("$");
        jtxtRoundBet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtRoundBetActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtRoundBet);
        jtxtRoundBet.setBounds(428, 386, 59, 22);

        jLabel1.setText("Balance: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(370, 300, 63, 16);

        jLabel2.setText("Your Bet:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(370, 330, 70, 16);

        jLabel3.setText("Pot: ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(390, 360, 41, 16);

        jLabel4.setText("Round Bet :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(358, 389, 64, 16);

        jtxtDealer.setEditable(false);
        jtxtDealer.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtxtDealer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDealerActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtDealer);
        jtxtDealer.setBounds(68, 340, 270, 30);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 10, 43, 0);

        nameNPC4.setText("Name:");
        nameNPC4.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(nameNPC4);
        nameNPC4.setBounds(10, 140, 41, 18);

        nameNPC3.setText("Name:");
        nameNPC3.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(nameNPC3);
        nameNPC3.setBounds(430, 20, 90, 18);

        nameNPC2.setText("Name:");
        nameNPC2.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(nameNPC2);
        nameNPC2.setBounds(650, 150, 41, 18);

        jlblDealer.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\Dealer.idle.png")); // NOI18N
        jlblDealer.setText("Dealer");
        getContentPane().add(jlblDealer);
        jlblDealer.setBounds(210, 270, 90, 80);

        jbtnDealer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDealerActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnDealer);
        jbtnDealer.setBounds(100, 370, 60, 20);

        jlblConsole.setText("Game Log!");
        getContentPane().add(jlblConsole);
        jlblConsole.setBounds(70, 440, 80, 16);

        jbtnDealer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDealer2ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnDealer2);
        jbtnDealer2.setBounds(250, 370, 60, 20);

        jtxtAreaConsoleLog.setEditable(false);
        jtxtAreaConsoleLog.setColumns(20);
        jtxtAreaConsoleLog.setRows(5);
        jScrollPane1.setViewportView(jtxtAreaConsoleLog);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 440, 130, 150);

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\ecloaner\\Pictures\\pokertable.png")); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 0, 720, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void startBettingRound(String roundName) {

    List<Player> players = game.getPlayers();

    for (Player player : players) {
        if (!player.isHuman()) {
            System.out.println("NPC's turn");

            NpcPlayer npcPlayer = (NpcPlayer) player;
            npcPlayer.makeDecision(game);
            updateGUI();
        } else {
            System.out.println("Player's turn");

            if (player.equals(game.getCurrentPlayer())) {

                jtxtDealer.setText("It's your turn...use chips..to bet or....raise...");
                // Implement your logic here
            } else {
                // Handle the case when it's not the turn of the human player
                jtxtDealer.setText("Waiting for other players...");
               
            }
        }

        // Update the current player for the next turn
        game.nextPlayer();
    }

}





    private void jbtnBetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBetActionPerformed

    handleGameEnd();

    }//GEN-LAST:event_jbtnBetActionPerformed


    private void jbtnRaiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRaiseActionPerformed
    handleGameEnd();

    }//GEN-LAST:event_jbtnRaiseActionPerformed

private int getSelectedBetAmount() {
    try {
        return Integer.parseInt(jtxtYourBet.getText().replace("$", ""));
    } catch (NumberFormatException e) {
        // Handle the case when the text in jtxtYourBet is not a valid integer
        return 0;
    }
}

private void clearSelectedBetAmount() {
    jtxtYourBet.setText("");
}

    private void jbtn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn10ActionPerformed
    // Check if it's the player's turn
    if (game.getCurrentPlayer().isHuman()) {
        // Player's turn logic for the Raise button
        selectedBetAmount += 10;
        jtxtYourBet.setText("$" + selectedBetAmount);
        jtxtDealer.setText("5*2 Nice");

    } else {
        // It's not the player's turn
        jtxtDealer.setText("Waiting for other players...");
    }
    }//GEN-LAST:event_jbtn10ActionPerformed

    private void jbtnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnclearActionPerformed

    jtxtDealer.setText("Are You Sure?");

    
    selectedBetAmount = 0;
    jtxtYourBet.setText("");
    


   // updatePlayerCards(); // Update the GUI with the dealt cards for player 4
   // updateP3CardBack();
    }//GEN-LAST:event_jbtnclearActionPerformed

    private void jbtnFoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFoldActionPerformed
    // Set the player's state to "folded"
   // game.getCurrentPlayer().fold();

    // Update GUI to reflect the fold
   // updateGUI();

    // Check for the end of the round or game
    //if (checkEndOfRound()) {
        // Proceed to the next round or end the game
     //   startNextRoundOrEndGame();
    //} else {
        // Continue with the next player's turn
    //    game.nextPlayer();
    //    updateGUI();  // Update GUI for the next player
   // }          dispose();
   
        dispose();

        LoadingScreen loadingScreen = new LoadingScreen();  // Instantiate the LoadingScreen class
        loadingScreen.reloadLoadingScreen();

        game.startRound(); // This method deals cards to players and community cards
    
    

    }//GEN-LAST:event_jbtnFoldActionPerformed
//private boolean checkEndOfRound() {
    // Count the number of players who have not folded
 //   long activePlayerCount = game.getPlayers().stream()
 //           .filter(player -> !player.isFolded())
 //           .count();

    // The round should end if there's only one active player remaining
 //   return activePlayerCount == 1;
//}


//private void startNextRoundOrEndGame() {
 //   if (game.getCurrentRound() < game.getMaxRounds()) {
        // Start the next round
 //       game.incrementCurrentRound();

        // Implement logic to update GUI and start the appropriate round
  //      updateGUI();
  //      startBettingRound(game.getRoundName(game.getCurrentRound()));

        // Check for special conditions or other game-specific logic
        // (You may need to customize this part based on your game rules)
   //     if (game.checkEndOfGame()) {
            // End the game and declare a winner
    //        determineWinner();
            // Optionally, reset the game or perform other end-game actions
        //game.startRound(); // This method deals cards to players and community cards
   //     }
//    } else {
        // The game has reached its maximum number of rounds
        // You might want to declare a winner or perform other end-game actions
   //     determineWinner();
        // Optionally, reset the game or perform other end-game actions
        //game.startRound(); // This method deals cards to players and community cards
//    }
//}

    private void jbtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn5ActionPerformed
    // Check if it's the player's turn
    if (game.getCurrentPlayer().isHuman()) {
        // Player's turn logic for the Raise button
        selectedBetAmount += 5;
        jtxtYourBet.setText("$" + selectedBetAmount);
        jtxtDealer.setText(".........");

    } else {
        // It's not the player's turn
        jtxtDealer.setText("Waiting for other players...");
    }

    }//GEN-LAST:event_jbtn5ActionPerformed

    private void jtxtYourBetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtYourBetActionPerformed
        // TODO add your handling code
    }//GEN-LAST:event_jtxtYourBetActionPerformed

    private void jtxtRoundBetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtRoundBetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtRoundBetActionPerformed

    private void jbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn2ActionPerformed
    // Check if it's the player's turn
    if (game.getCurrentPlayer().isHuman()) {
        // Player's turn logic for the Raise button
        selectedBetAmount += 2;
        jtxtYourBet.setText("$" + selectedBetAmount);
        jtxtDealer.setText("........");
    } else {
        // It's not the player's turn
        jtxtDealer.setText("Waiting for other players...");
    }

    }//GEN-LAST:event_jbtn2ActionPerformed

    private void jbtn500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn500ActionPerformed
    // Check if it's the player's turn
    if (game.getCurrentPlayer().isHuman()) {
        // Player's turn logic for the Raise button
        selectedBetAmount += 500;
        jtxtYourBet.setText("$" + selectedBetAmount);
        jtxtDealer.setText("GOLLY Chuck Norris??");
    } else {
        // It's not the player's turn
        jtxtDealer.setText("Waiting for other players...");
    }

    }//GEN-LAST:event_jbtn500ActionPerformed

    private void jbtn250ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn250ActionPerformed
    // Check if it's the player's turn
    if (game.getCurrentPlayer().isHuman()) {
        // Player's turn logic for the Raise button
        selectedBetAmount += 250;
        jtxtYourBet.setText("$" + selectedBetAmount);
        jtxtDealer.setText("NICEEEE");
    } else {
        // It's not the player's turn
        jtxtDealer.setText("Waiting for other players...");
    }

    }//GEN-LAST:event_jbtn250ActionPerformed

    private void jbtn100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn100ActionPerformed
    // Check if it's the player's turn
    if (game.getCurrentPlayer().isHuman()) {
        // Player's turn logic for the Raise button
        selectedBetAmount += 100;
        jtxtYourBet.setText("$" + selectedBetAmount);
        jtxtDealer.setText("are you sure??");
    } else {
        // It's not the player's turn
        jtxtDealer.setText("Waiting for other players...");
    }

    }//GEN-LAST:event_jbtn100ActionPerformed

    private void jbtn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn25ActionPerformed
     
    // Check if it's the player's turn


    if (game.getCurrentPlayer().isHuman()) {
        // Player's turn logic for the Raise button
        selectedBetAmount += 25;
        jtxtYourBet.setText("$" + selectedBetAmount);
        jtxtDealer.setText("are you sure??");
    } else {
        // It's not the player's turn
        jtxtDealer.setText("Waiting for other players...");
    }

    
    }//GEN-LAST:event_jbtn25ActionPerformed

    private void jtxtDealerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDealerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDealerActionPerformed

    private void jbtnDealerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDealerActionPerformed
            // Assuming you have unique ActionCommands or some other way to identify buttons
       handleDealerButtonClick(true);
         jtxtDealer.setText(".........");
         dealCommunityCardsAndUpdate();

        startBettingRound("Preflop");
        
        if (game.getCurrentRound() == 4) {
                jbtnDealer.setVisible(false);

        }
         
    }//GEN-LAST:event_jbtnDealerActionPerformed

    private void jbtnDealer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDealer2ActionPerformed
        handleDealerButtonClick(false) ;
        jtxtDealer.setText(".........");
        
                if (game.getCurrentRound() == 4) {
                jbtnDealer.setVisible(false);

        }

    }//GEN-LAST:event_jbtnDealer2ActionPerformed
private void handleDealerButtonClick(boolean isReady) {
    // Hide buttons after one is pressed
    jbtnDealer.setVisible(false);
    jbtnDealer2.setVisible(false);

    // Handle the response
    if (isReady) {
        // If Yes is pressed, update player cards
        updatePlayerCards();

        // You can add additional logic here if needed
    } else {
        // If No is pressed, set balance to 0 and close the panel
        dispose();
    }
 
    
//???WDIIUBIWBDIWVVDWVYVIUDW gotta make the other buttons hiddden after isReady call doo later


}


// Placeholder method for hand strength (replace with your logic)


    /**
     * @param args the command line arguments
     */

private void handleGameEnd() {
    if (game.isGameOver()) {
        // Determine the winner
        List<Player> players = game.getPlayers();
        Player winner = null;
        int highestScore = -1;

        for (Player player : players) {
            if (!player.isFolded()) {
                int handScore = game.evaluateHandWithCommunityCards(player);

                if (handScore > highestScore) {
                    highestScore = handScore;
                    winner = player;
                }
            }
        }

        // Display the winner
         updateNPCCards();

        jtxtDealer.setText("Winner: " + winner.getName());
        String winnerName = winner != null ? winner.getName() : "No Winner";
        showWinnerWindow(winnerName);

    } else {
        // Continue with the game logic
        if (game.getCurrentPlayer().isHuman()) {
            // Check if a bet amount is selected
            if (selectedBetAmount > 0 ) {
                // Check if the player has enough funds
                if (game.getPlayerBalance() >= selectedBetAmount) {
                    game.bet(selectedBetAmount);

                    updateGUI();

                    // Clear the selected bet amount
                    selectedBetAmount = 0;
                    jtxtYourBet.setText("");

                    // Check if it's the end of the current round
                    if (game.getCurrentRound() == 0 && game.getCurrentPlayer().equals(game.getPlayers().get(0))) {
                        // It's the end of the preflop round, advance to the next round
                        game.incrementCurrentRound();
                        startBettingRound("Pre-Flop");
                        jtxtDealer.setText("Pre-Flop Round");
                        jlblDealer.setIcon(new ImageIcon("C:/Users/ecloaner/Pictures/Dealertalking.png"));
                    } else if (game.getCurrentRound() == 1 && game.getCurrentPlayer().equals(game.getPlayers().get(0))) {
                        // It's the end of the flop round, advance to the next round
                        game.incrementCurrentRound();
                        startBettingRound("Flop");
                        jtxtDealer.setText("Flop Round");
                    } else if (game.getCurrentRound() == 2 && game.getCurrentPlayer().equals(game.getPlayers().get(0))) {
                        // It's the end of the turn round, advance to the next round
                        game.incrementCurrentRound();
                        startBettingRound("Turn");
                        jtxtDealer.setText("Turn Round");
                    } else if (game.getCurrentRound() == 3 && game.getCurrentPlayer().equals(game.getPlayers().get(0))) {
                        // It's the end of the turn round, advance to the next round
                        game.incrementCurrentRound();
                        startBettingRound("River");
                        jtxtDealer.setText("River Round");
                    } else if (game.getCurrentRound() == 4) {
                        // It's the end of the game, handle endgame logic
                        List<Player> players = game.getPlayers();
                        Player winner = null;
                        int highestScore = -1;

                        for (Player player : players) {
                            if (!player.isFolded()) {
                                int handScore = game.evaluateHandWithCommunityCards(player);

                                if (handScore > highestScore) {
                                    highestScore = handScore;
                                    winner = player;
                                int potAmount = game.getPot();
                                winner.addToChips(potAmount);
                                updateNPCCards();


                                }
                            }
                        }

                        System.out.println("Winner: " + winner.getName());
                         updateNPCCards();

                    } else {
                        // It's not the end of any round, proceed to the next player
                        game.nextPlayer();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds! You cannot bet $" + selectedBetAmount,
                            "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                    jtxtDealer.setText("You need more Pesos!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a bet amount first.",
                        "No Bet Amount Selected", JOptionPane.ERROR_MESSAGE);
                jtxtDealer.setText("Pot: " + game.getPot() + " " + "Make a bet less CANNOT be 0!.... or Raise!");
            }
        } else {
            String message = "It's not your turn.";
            JOptionPane.showMessageDialog(null, message, "Turn Information", JOptionPane.INFORMATION_MESSAGE);
            jtxtDealer.setText("Waiting for other players...");
        }
    }
    
}

private void showWinnerWindow(String winnerName) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            ActionListener playAgainListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Close the winner window
                    dispose();
                    
                    SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new PokerGui().setVisible(true);
                    }
                });
                    
                    
                }
            };

            WinnerWindow winnerWindow = new WinnerWindow(winnerName, playAgainListener);
            winnerWindow.setVisible(true);
        }
    });
}

public static void main(String args[]) {

    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new PokerGui().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel flop1Card;
    private javax.swing.JLabel flop2Card;
    private javax.swing.JLabel flop3Card;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtn10;
    private javax.swing.JButton jbtn100;
    private javax.swing.JButton jbtn2;
    private javax.swing.JButton jbtn25;
    private javax.swing.JButton jbtn250;
    private javax.swing.JButton jbtn5;
    private javax.swing.JButton jbtn500;
    private javax.swing.JButton jbtnBet;
    private javax.swing.JButton jbtnDealer;
    private javax.swing.JButton jbtnDealer2;
    private javax.swing.JButton jbtnFold;
    private javax.swing.JButton jbtnRaise;
    private javax.swing.JButton jbtnclear;
    private javax.swing.JLabel jlblConsole;
    private javax.swing.JLabel jlblDealer;
    private javax.swing.JTextArea jtxtAreaConsoleLog;
    private javax.swing.JTextField jtxtBalance;
    private javax.swing.JTextField jtxtDealer;
    private javax.swing.JTextField jtxtPot;
    private javax.swing.JTextField jtxtRoundBet;
    private javax.swing.JTextField jtxtYourBet;
    private javax.swing.JLabel nameNPC2;
    private javax.swing.JLabel nameNPC3;
    private javax.swing.JLabel nameNPC4;
    private javax.swing.JLabel player1Card1;
    private javax.swing.JLabel player1Card2;
    private javax.swing.JLabel player2Card1;
    private javax.swing.JLabel player2Card2;
    private javax.swing.JLabel player3Card1;
    private javax.swing.JLabel player3Card2;
    private javax.swing.JLabel player4Card1;
    private javax.swing.JLabel player4Card2;
    private javax.swing.JLabel riverCard;
    private javax.swing.JLabel turnCard;
    // End of variables declaration//GEN-END:variables
}
