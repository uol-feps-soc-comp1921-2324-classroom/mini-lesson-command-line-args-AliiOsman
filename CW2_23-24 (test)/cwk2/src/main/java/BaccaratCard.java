/**
 * Representation of a Baccarat card.
 *
 * @author Ali Osman
 */

public class BaccaratCard extends Card {

    public BaccaratCard(Rank r, Suit s) {
        super(r, s);
    }

    /**
     * Computes the value of this card.
     *
     * <p>Value is based on rank and disregards suit. Aces score 1
     * and picture cards and Ten all score 0.</p>
     *
     * @return Card value
     */
    @Override
    public int value(){
        //overides the value method because in punto
        // banco these cards are 0 not 10
        return switch (getRank()) {
            case JACK -> 0;
            case QUEEN -> 0;
            case KING -> 0;
            case TEN -> 0;
            default -> getRank().ordinal() + 1;
        };
        }
    }