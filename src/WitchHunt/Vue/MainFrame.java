package WitchHunt.Vue;

import WitchHunt.Module.Cards.RumorCards.RumorCard;
import WitchHunt.Module.Game;
import WitchHunt.Module.Players.Player;
import WitchHunt.Module.Players.VirtuelPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


public class MainFrame extends Game implements Observer {


	private final ArrayList<JButton> JButtons = new ArrayList<>();
	private final ArrayList<JTextPane> JTPanes = new ArrayList<>();
	private ArrayList<RumorCard> cards = new ArrayList<>();
	private Player me;
	public Game game;
	private HashMap<String,ImageIcon> map = new HashMap<>();
	private JLabel id;
	private int flag = 0;
	private int choice;
	private Player accuser;


	JFrame frame = new JFrame("WitchHunt");

	/**
	 * @param nb_players nb du joueurs robots contient
	 * @param i          Le personnage choisi par le joueur pour commencer le jeu
	 */
	public MainFrame(int nb_players, int i) {
		super(nb_players, i);
	}

	/**
	 * @param game game
	 * charger l'image, initialiser le jeu
	 */
	public void init(Game game) {

		ImageIcon angryMob = new ImageIcon("src/WitchHunt/pic/AngryMob.png");
		ImageIcon blackCat = new ImageIcon("src/WitchHunt/pic/BlackCat.png");
		ImageIcon broomStick = new ImageIcon("src/WitchHunt/pic/Broomstick.png");
		ImageIcon cauldron = new ImageIcon("src/WitchHunt/pic/Cauldron.png");
		ImageIcon duckingStool = new ImageIcon("src/WitchHunt/pic/DuckingStool.png");
		ImageIcon evilEye = new ImageIcon("src/WitchHunt/pic/EvilEye.png");
		ImageIcon hookedNose = new ImageIcon("src/WitchHunt/pic/HookedNose.png");
		ImageIcon petNewt = new ImageIcon("src/WitchHunt/pic/PetNewt.png");
		ImageIcon pointedHat = new ImageIcon("src/WitchHunt/pic/PointedHat.png");
		ImageIcon theInquisition = new ImageIcon("src/WitchHunt/pic/TheInquisition.png");
		ImageIcon toad = new ImageIcon("src/WitchHunt/pic/Toad.png");
		ImageIcon wart = new ImageIcon("src/WitchHunt/pic/Wart.png");
		ImageIcon back = new ImageIcon("src/WitchHunt/pic/Back.png");
		ImageIcon witch = new ImageIcon("src/WitchHunt/pic/Witch.png");
		ImageIcon villager = new ImageIcon("src/WitchHunt/pic/Villager.png");

		map.put("Angry Mob",angryMob);
		map.put("Black Cat",blackCat);
		map.put("Broom Stick",broomStick);
		map.put("Cauldron",cauldron);
		map.put("Ducking Stool",duckingStool);
		map.put("Evil Eye",evilEye);
		map.put("Hooked Nose",hookedNose);
		map.put("Pet Newt",petNewt);
		map.put("Pointed Hat",pointedHat);
		map.put("The Inquisition",theInquisition);
		map.put("Toad",toad);
		map.put("Wart",wart);
		map.put("Back",back);
		map.put("Witch",witch);
		map.put("Villager",villager);


		this.game = game;

		frame.setBounds(0, 0, 1280, 1080);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextPane c1 = new JTextPane();
		c1.setBounds(86,130,80,20);
		frame.getContentPane().add(c1);
		c1.setFont(new Font("Tahoma", Font.BOLD, 15));
		c1.setText("Usable");
		JTPanes.add(c1);

		JTextPane c2 = new JTextPane();
		c2.setBounds(313,130,80,20);
		frame.getContentPane().add(c2);
		c2.setFont(new Font("Tahoma", Font.BOLD, 15));
		c2.setText("Usable");
		JTPanes.add(c2);

		JTextPane c3 = new JTextPane();
		c3.setBounds(86,466,80,20);
		frame.getContentPane().add(c3);
		c3.setFont(new Font("Tahoma", Font.BOLD, 15));
		c3.setText("Usable");
		JTPanes.add(c3);

		JTextPane c4 = new JTextPane();
		c4.setBounds(313,466,80,20);
		frame.getContentPane().add(c4);
		c4.setFont(new Font("Tahoma", Font.BOLD, 15));
		c4.setText("Usable");
		JTPanes.add(c4);


		JButton card1 = new JButton("Card 1");
		card1.setBounds(56, 164, 140, 200);
		frame.getContentPane().add(card1);
		JButtons.add(card1);
		
		JButton card2 = new JButton("Card 2");
		card2.setBounds(283, 164, 140, 200);
		frame.getContentPane().add(card2);
		JButtons.add(card2);
		
		JButton card3 = new JButton("Card 3");
		card3.setBounds(56, 500, 140, 200);
		frame.getContentPane().add(card3);
		JButtons.add(card3);
		
		JButton card4 = new JButton("Card 4");
		card4.setBounds(283, 500, 140, 200);
		frame.getContentPane().add(card4);
		JButtons.add(card4);

		id = new JLabel("id");
		id.setBounds(800,300,140,200);
		frame.getContentPane().add(id);
		id.setIcon(map.get("Back"));

		for (JButton jb : JButtons) {
			jb.setVisible(false);
		}

		for (JTextPane jtp : JTPanes) {
			jtp.setVisible(false);
		}

		JButton start = new JButton("Prenez Carte");
		start.setBounds(524, 280, 123, 23);
		frame.getContentPane().add(start);

		JButton revealId = new JButton("Reveal Identity");
		revealId.setBounds(524, 327, 123, 23);
		frame.getContentPane().add(revealId);
		
		JButton accuse = new JButton("Accuse");
		accuse.setBounds(524, 383, 123, 23);
		frame.getContentPane().add(accuse);
		
		JButton hunt = new JButton("Hunt");
		hunt.setBounds(524, 441, 123, 23);
		frame.getContentPane().add(hunt);

		JTextArea text = new JTextArea("1 You need to press 'prenez carte' to start\n2 press hunt,choose player,choose card,than hunt\n3 when you are accused,\npress the card to defense or reveal identity");
		text.setBounds(800,100,600,100);
		text.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(text);

		card1.addActionListener(new ActionListener() {
			/**
			 * @param e Choisissez la première carte
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//先按hunt,
				//跳一个窗口出来，选择角色
				//角色选择好之后传回来
				useCard(0);
			}
		});

		card2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useCard(1);
			}
		});

		card3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useCard(2);
			}
		});

		card4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useCard(3);
			}
		});

		start.addActionListener(new ActionListener() {
			/**
			 * @param e commencer à traiter les cartes
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				initGame();
			}
		});

		revealId.addActionListener(new ActionListener() {
			/**
			 * @param e Choisissez de révéler votre carte d'identité
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				me.revealIdentity();
			}
		});

		accuse.addActionListener(new ActionListener() {
			/**
			 * @param e Sélectionnez accuser les autres, fenêtre de sélection contextuelle
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoosePlayer cp = new ChoosePlayer();
				cp.init(game);
				//1 means accuse
				cp.setAccuse(1);
			}
		});

		hunt.addActionListener(new ActionListener() {
			/**
			 * @param e Sélectionnez hunt les autres, fenêtre de sélection contextuelle
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				ChoosePlayer cp = new ChoosePlayer();
				cp.init(game);
			}
		});
	}


	/**
	 * Initialiser l'interface en fonction du nombre de cartes et l'identity
	 */
	public void initGame() {
		this.me = this.game.getPlayerList().get(0);

		this.game.startGame_G(this);

		this.cards = this.me.getCardsList();
		for (int i = 0; i < cards.size(); i++) {
			JButtons.get(i).setVisible(true);
			JTPanes.get(i).setVisible(true);
			JButtons.get(i).setIcon(map.get(cards.get(i).getName()));
		}
		id.setIcon(map.get(me.getIdentity().getIdentity().toString()));
	}

	/**
	 * @param p ajoutez mon propre objet
	 */
	public void setPlayer(Player p) {
		this.me = p;
	}

	/**
	 * @return propre carte
	 */
	public ArrayList<RumorCard> getCards() {
		return this.cards;
	}

	/**
	 * @param cards définir votre propre carte
	 */
	public void setCards(ArrayList<RumorCard> cards) {
		this.cards = cards;
	}

	/**
	 * @return choix dd joueur
	 */
	public int getChoice() {
		return choice;
	}

	/**
	 * @param choice obtenir le choix de ChoosePlayer
	 */
	public void setChoice(int choice) {
		this.choice = choice;
	}

	/**
	 * @param c Choisissez d'utiliser l'effet chasse ou sorcière de la carte
	 */
	public void useCard(int c) {
		Player nextPlayer;
		if (flag == 0) {
			nextPlayer = cards.get(c).takeEffectWitch(me,accuser);
			if (nextPlayer instanceof VirtuelPlayer) {
				nextPlayer.play();
			} else {
				System.out.println("Turn de Jouer");
			}
		} else {
			accuser = game.getPlayerList().get(choice);
			nextPlayer = cards.get(c).takeEffectHunt(me,accuser);
			if (nextPlayer instanceof VirtuelPlayer) {
				nextPlayer.play();
			} else {
				System.out.println("Turn de Jouer");
			}
			flag = 0;
		}
		JTPanes.get(c).setText("Unusable");
	}

	/**
	 * @param accuser enregistrer le joueur qui m'accuse
	 */
	public void setAccuser(Player accuser) {
		this.accuser = accuser;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.choice = Integer.parseInt(arg.toString());
	}


}
