/**
 * Representation of a Baccarat Shoe.
 *
 * @author Ali Osman
 */

import java.util.Collections;

public class Shoe extends CardCollection{

    /**
     * creates a shoe with int decks number of decks in it
     * @param decks which is the number if decks
     * @throws CardException when it is not 6 or 8 decks
     */
    public Shoe(int decks){
        super();
        if(decks != 6 && decks != 8){
            throw new CardException("Invalid number of decks:" +
                    " number of decks in shoe must be 6 or 8");
        }
        //loops over every card in each deck and creates a BaccaratCard object
        for(int i=0;i<decks;i++){
            for (BaccaratCard.Suit suit : BaccaratCard.Suit.values()) {
                for (BaccaratCard.Rank rank : BaccaratCard.Rank.values()) {
                    cards.add(new BaccaratCard(rank, suit));
                }
            }

        }
    }

    /**
     * shuffles the shoe using CardCollections shuffle method
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }

    /**
     * deals a card from the shoe
     * @throws CardException when the shoe is empty
     */
    public Card deal(){
        if(cards.isEmpty()){
            throw new CardException("shoe is empty : can not deal from empty shoe");
        }
        Card card = cards.getFirst();
        cards.removeFirst();
        return card;
    }
}