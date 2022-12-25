package WitchHunt.Module;

import WitchHunt.Vue.Interface;

import java.awt.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Voulez-vous lancer le jeu en :\n0) Console.\n1) Interface Graphique\n");
        Scanner scannerc = new Scanner(System.in);
        int choice = scannerc.nextInt();
        if (choice == 0) {
            System.out.println("Importer le nombre de joueurs (3 - 6) : ");
            Scanner scanner = new Scanner(System.in);
            int nb = scanner.nextInt();
            if (nb >= 3 && nb <= 6){
                Game game = Game.getInstance(nb);
                game.startGame();
            }else {
                System.out.println("Vous devez commencer une partie avec 3 à 6 joueurs");
            }
        } else {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Interface i = new Interface();
                        i.init();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
