package WitchHunt.Vue;

import WitchHunt.Module.Cards.IdentityCards.Identity;
import WitchHunt.Module.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Interface extends JFrame {
    private JLabel lblNbIA;
    private int a=3;
    private Identity id;

    JFrame frame = new JFrame("witch hunt");


    public void init(){
        frame.setBounds(0, 0, 1280, 1080);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitre = new JLabel("Choix du nombre de joueurs");
        lblTitre.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setBounds(0, 40, 1266, 40);
        frame.getContentPane().add(lblTitre);

        JLabel lblNombreDeJoueurs = new JLabel("Nombre de joueurs :");
        lblNombreDeJoueurs.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombreDeJoueurs.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNombreDeJoueurs.setBounds(300, 200, 325, 78);
        frame.getContentPane().add(lblNombreDeJoueurs);

        JButton btnRedIA = new JButton("<");
        btnRedIA.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnRedIA.setBounds(720, 200, 60, 60);
        frame.getContentPane().add(btnRedIA);

        lblNbIA = new JLabel(String.valueOf(a));
        lblNbIA.setHorizontalAlignment(SwingConstants.CENTER);
        lblNbIA.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNbIA.setBounds(784, 200, 60, 50);
        frame.getContentPane().add(lblNbIA);

        JButton btnAugIA = new JButton(">");
        btnAugIA.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnAugIA.setBounds(850, 200, 60, 60);
        frame.getContentPane().add(btnAugIA);

        JButton witch = new JButton("Witch");
        witch.setFont(new Font("Tahoma", Font.BOLD, 30));
        witch.setBounds(420, 400, 200, 60);
        frame.getContentPane().add(witch);

        JButton villager = new JButton("Villager");
        villager.setFont(new Font("Tahoma", Font.BOLD, 30));
        villager.setBounds(670, 400, 200, 60);
        frame.getContentPane().add(villager);

        JButton start = new JButton("START");
        start.setFont(new Font("Tahoma", Font.BOLD, 25));
        start.setBounds(500, 560, 286, 78);
        frame.getContentPane().add(start);


        btnAugIA.addActionListener(new ActionListener() {
            /**
             * @param e augmenter le nombre de joueurs
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( a < 6 ) {
                    a++;
                    lblNbIA.setText(String.valueOf(a));
                }
            }
        });

        btnRedIA.addActionListener(new ActionListener() {
            /**
             * @param e réduire le nombre de joueurs
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a>3){
                    a--;
                    lblNbIA.setText(String.valueOf(a));
                }
            }
        });

        witch.addActionListener(new ActionListener() {
            /**
             * @param e choisir d'être Witch
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                id = Identity.Witch;
            }
        });

        villager.addActionListener(new ActionListener() {
            /**
             * @param e choisir d'être Villager
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                id = Identity.Villager;
            }
        });

        start.addActionListener(new ActionListener() {
            /**
             * @param e nombre du Joueur
             * Initialiser le jeu, afficher mf
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(a,0);
                game.getPlayerList().get(0).setIdentity(id);
                try {
                    new MainFrame(a,0).init(game);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
    }
}
