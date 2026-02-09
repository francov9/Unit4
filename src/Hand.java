public class Hand {
    private static int[] handCount = {0, 0, 0, 0, 0, 0, 0};
    private final int[] hand;
    private final int classification;
    private final int bet;
    private int rank;
    private static int total;
    public Hand(int[] hand, int bet){
        this.hand = hand.clone();
        rank = total;
        classification = setClassification(hand);
        this.bet = bet;
    }

    public static int setClassification(int[] hand){
        int[] handClone = hand.clone();
        int[][] classifying = new int[5][2];
        for(int i = 0; i < 5; i++){
            classifying[i][0] = handClone[i];
            classifying[i][1]++;
            handClone[i] = 0;
            for(int n = i; n < 5; n++){
                if(classifying[i][0] == handClone[n]){
                    classifying[i][1]++;
                    handClone[n] = 0;
                }
            }
        }
        int elements = 0;
        for (int[] ints : classifying) {
            if (ints[0] != 0) {
                elements++;
            }
        }
        switch (elements){
            case 1 -> {handCount[6]++; return 7;}
            case 2 -> {
                for (int[] ints : classifying) {
                    if (ints[1] == 4) {
                        handCount[5]++;
                        return 6;
                    } else if(ints[1] == 3){
                        handCount[4]++;
                        return 5;
                    }
                }
                handCount[4]++;
                return 5;
            }
            case 3 -> {
                for (int[] ints : classifying) {
                    if (ints[1] == 3) {
                        handCount[3]++;
                        return 4;
                    } else if (ints[1] == 2) {
                        handCount[2]++;
                        return 3;
                    }
                }
            }
            case 4 -> {handCount[1]++; return 2;}
            case 5 -> {handCount[0]++; return 1;}
        }
        handCount[0]++;
        return 1;
    }

    public static void setTotal(int i){
        total = i;
    }

    public int[] getHand(){
        return hand;
    }

    public int getBet() {
        return bet;
    }

    public int getRank(){
        return rank;
    }

    public static void compareHands(Hand hand1, Hand hand2){
        int[] cards1 = hand1.hand;
        int[] cards2 = hand2.hand;
        if(hand1.classification > hand2.classification){
            hand2.rank--;
        }else if(hand2.classification > hand1.classification){
            hand1.rank--;
        }else{
            for(int i = 0; i < cards1.length; i++){
                if(cards1[i] < cards2[i]){
                    hand1.rank--;
                    break;
                }else if (cards1[i] > cards2[i]){
                    hand2.rank--;
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Number of five of a kind hands: " + handCount[6] + "\n" +
                "Number of four of a kind hands: " + handCount[5] + "\n" +
                "Number of full house hands: " + handCount[4] + "\n" +
                "Number of three of a kind hands: " + handCount[3] + "\n" +
                "Number of two pair hands: " + handCount[2] + "\n" +
                "Number of one pair hands: " + handCount[1] + "\n" +
                "Number of high card hands: " + handCount[0];
    }
}