package WitchHunt.Vue;

import WitchHunt.Module.Game;
import WitchHunt.Module.Players.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;


import javax.swing.*;

public class ChoosePlayer extends Observable {

	private JFrame frame;
	private ArrayList<JButton> buttons = new ArrayList<>();
	private ArrayList<Player> players = new ArrayList<>();
	private int choice = 1;
	private Game game;
	private int accuse = 0;

	/**
	 * Initialize the contents of the frame.
	 */
	public void init(Game game) {
		this.game = game;
//		this.players = game.getPlayerList();
		if (accuse == 1) {
			this.players = game.getPlayerList().get(0).getAvailableAccused();
		} else {
			this.players = game.getPlayerList().get(0).getAvailablePlayers();
		}

		this.addObserver(game.getMf());

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton p1 = new JButton(game.getPlayerList().get(1).getName());
		p1.setBounds(160, 30, 97, 23);
		frame.getContentPane().add(p1);
		buttons.add(p1);

		JButton p2 = new JButton(game.getPlayerList().get(2).getName());
		p2.setBounds(160, 60, 97, 23);
		frame.getContentPane().add(p2);
		buttons.add(p2);

		JButton p3 = new JButton("player 3");
		p3.setBounds(160, 90, 97, 23);
		frame.getContentPane().add(p3);
		buttons.add(p3);

		JButton p4 = new JButton("player 4");
		p4.setBounds(160, 120, 97, 23);
		frame.getContentPane().add(p4);
		buttons.add(p4);

		JButton p5 = new JButton("player 5");
		p5.setBounds(160, 150, 97, 23);
		frame.getContentPane().add(p5);
		buttons.add(p5);

		for (JButton jb : buttons) {
			jb.setVisible(false);
		}

		for (int i = 0 ; i < players.size(); i ++) {
			buttons.get(i).setVisible(true);
			buttons.get(i).setText(players.get(i).getName());
		}


		p1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 1;
				//game.getPlayerList().get(0).setChoice(choice);
				doChoice();
				frame.dispose();
			}
		});

		p2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 2;
				doChoice();
				frame.dispose();
			}
		});

		p3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 3;
				doChoice();
				frame.dispose();
			}
		});

		p4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 4;
				doChoice();
				frame.dispose();
			}
		});

		p5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 5;
				doChoice();
				frame.dispose();
			}
		});
	}

	public int getChoice() {
		return choice;
	}

	/**
	 * @param accuse Déterminez s'il faut utiliser accuser via les paramètres passés par MainFrame
	 */
	public void setAccuse(int accuse) {
		this.accuse = accuse;
	}

	/**
	 * Passer l'objet sélectionné à l'ordinateur central
	 */
	public void doChoice() {
		if (accuse == 1) {
			game.getPlayerList().get(0).accuse(players.get(choice - 1));
			accuse = 0;
		} else {
			setChanged();
			notifyObservers(choice);
		}
	}
}
