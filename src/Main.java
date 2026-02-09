void main() {
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
                    case "Ace" -> numHand[n] = 1;
                    case "King" -> numHand[n] = 13;
                    case "Queen" -> numHand[n] = 12;
                    case "Jack" -> numHand[n] = 11;
                    default -> numHand[n] = Integer.parseInt(hand[n]);
                }
            }
            hands[i] = new Hand(numHand, bet[i]);
            i++;
        }
        for (int a = 0; a < hands.length; a++) {
            for (int b = a + 1; b < hands.length; b++) {
                Hand.compareHands(hands[a], hands[b]);
            }
        }
        int totalBet = 0;
        for (Hand hand : hands) {
            totalBet += hand.getRank() * hand.getBet();
        }
        IO.println(hands[0]);
        IO.println("Total Bet: " + totalBet);
    } catch (FileNotFoundException e) {
        IO.println("File not found");
    }
}