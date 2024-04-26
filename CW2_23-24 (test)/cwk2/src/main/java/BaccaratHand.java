/**
 * Representation of a Baccarat Hand.
 *
 * @author Ali Osman
 */
public class BaccaratHand extends CardCollection{

    public BaccaratHand (){
        super();
    }

    /**
     * overrides the toString method and formats the string
     * @return formatOutput
     */
    @Override
    public String toString() {
        String formatOutput = "";
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            formatOutput = formatOutput + String.format("%c%c", card.getRank().getSymbol(),
                    card.getSuit().getSymbol());
            if (i < cards.size() - 1) {formatOutput = formatOutput + " ";}
        }
        return formatOutput;
    }

    /**
     * overides value and calculates the value of a baccarat hand
     * @return sum%10 which is the value
     */
    @Override
    public int value(){
        int sum = 0;
        for (Card card: cards) {
                sum += card.value();
            }
        return sum % 10;
    }

        /**
        * @return true if the hand is natural
        */
        public boolean isNatural(){
        return cards.size() == 2 && (value() == 9 || value()== 8);
    }
}
