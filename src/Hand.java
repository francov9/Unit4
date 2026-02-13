public class Hand {
    private static final int[] handCount = {0, 0, 0, 0, 0, 0, 0};
    private final int[] hand;
    private final int classification;
    private int jackClassification;
    private final int bet;
    private int rank;
    private static int total;
    public Hand(int[] hand, int bet){
        this.hand = hand.clone();
        setRank();
        classification = setClassification(hand);
        setJackClassification(jackCount());
        this.bet = bet;
    }

    private int jackCount(){
        int jacks = 0;
        for(int card : hand){
            if(card == 11){
                jacks++;
            }
        }
        return jacks;
    }

    private void setJackClassification(int jacks){
        if(jacks > 0){
            switch (classification){
                case 5, 6, 7 -> jackClassification = 7;
                case 4 -> jackClassification = 6;
                case 3 -> {
                    if(jacks == 2){
                        jackClassification = 6;
                    }else{
                        jackClassification = 5;
                    }
                }
                case 2 -> jackClassification = 4;
                case 1 -> jackClassification = 2;
            }
        }else{
            jackClassification = classification;
        }
    }

    private static int setClassification(int[] hand){
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

    public void setRank() {
        this.rank = total;
    }

    public int getBet() {
        return bet;
    }

    public int getRank(){
        return rank;
    }

    public int getClassification() {
        return classification;
    }

    public int getJackClassification() {
        return jackClassification;
    }

    public static void compareHands(Hand hand1, Hand hand2, int classification1, int classification2, boolean wild){
        int[] cards1 = hand1.hand;
        int[] cards2 = hand2.hand;
        if(wild){
            for (int i = 0; i < 5; i++) {
                if(cards1[i] == 11){
                    cards1[i] = 1;
                }
                if(cards2[i] == 11){
                    cards2[i] = 1;
                }
            }
        }
        if(classification1 > classification2){
            hand2.rank--;
        }else if(classification2 > classification1){
            hand1.rank--;
        }else{
            for(int i = 0; i < cards1.length; i++){
                if(cards1[i] > cards2[i]){
                    hand2.rank--;
                    return;
                }else if(cards1[i]< cards2[i]){
                    hand1.rank--;
                    return;
                }
            }
        }
    }
    public static String countOfHands() {
        return "Number of five of a kind hands: " + handCount[6] + "\n" +
                "Number of four of a kind hands: " + handCount[5] + "\n" +
                "Number of full house hands: " + handCount[4] + "\n" +
                "Number of three of a kind hands: " + handCount[3] + "\n" +
                "Number of two pair hands: " + handCount[2] + "\n" +
                "Number of one pair hands: " + handCount[1] + "\n" +
                "Number of high card hands: " + handCount[0];
    }
}