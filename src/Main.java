
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File f = new File("src/data");
            Scanner z = new Scanner(f);
            int fileLength = 0;
            while (z.hasNextLine()) {
                z.nextLine();
                fileLength++;
            }
            Scanner s = new Scanner(f);
            Hand.setTotal(fileLength);
            Hand[] hands = new Hand[fileLength];
            int[] bet = new int[fileLength];
            int i = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] hand = line.split(",");
                int[] numHand = new int[5];
                bet[i] = Integer.parseInt(hand[4].substring(hand[4].indexOf('|') + 1));
                hand[4] = hand[4].substring(0, hand[4].indexOf('|'));
                for (int n = 0; n < 5; n++) {
                    switch (hand[n]) {
                        case "Ace" -> numHand[n] = 14;
                        case "King" -> numHand[n] = 13;
                        case "Queen" -> numHand[n] = 12;
                        case "Jack" -> numHand[n] = 11;
                        default -> numHand[n] = Integer.parseInt(hand[n]);
                    }
                }
                hands[i] = new Hand(numHand, bet[i]);
                i++;
            }
            for (int a = 0; a < hands.length-1; a++) {
                for (int b = a + 1; b < hands.length; b++) {
                    Hand.compareHands(hands[a], hands[b], hands[a].getClassification(), hands[b].getClassification(), false);
                }
            }
            int totalBet = 0;
            for (Hand hand : hands) {
                totalBet += hand.getRank() * hand.getBet();
            }
            System.out.println(Hand.countOfHands());
            System.out.println("Total Bet: " + totalBet);
            for(Hand hand : hands){
                hand.setRank();
            }
            for (int a = 0; a < hands.length-1; a++) {
                for (int b = a + 1; b < hands.length; b++) {
                    Hand.compareHands(hands[a], hands[b], hands[a].getJackClassification(), hands[b].getJackClassification(), true);
                }
            }
            totalBet = 0;
            for (Hand hand : hands) {
                totalBet += hand.getRank() * hand.getBet();
            }
            System.out.println("Total Bet + Jacks: " + totalBet);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
