import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/**
 * A Baccarat game simulation class .
 * @author Ali Osman
 */
public class BaccaratGame {

    private Shoe shoe;
    private BaccaratHand banker;
    private BaccaratHand player;
    //statistics printed at the end
    private int playerWins;
    private int bankerWins;
    private int ties;
    private int counter;

    /**
     * BaccaratGame constructor that starts the game
     * @param args which reprsents the command line arguments
     * */
    public BaccaratGame(String[] args) {
        //making the symbols appear in windows setting powershell to utf8
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        //checks in which mode it was run
        if(args.length != 0 && modeChecker(args[0])){
        shoeCreater();
        }
        else{
            //creates a shoe of size 6 decks if ran in non-interactive mode
            this.shoe=new Shoe(6);
            this.shoe.shuffle();
        }
        counter = 1;
        while (shoe.size() > 5) {
            System.out.printf("Round %d%n", counter);
            round();
            if (args.length == 1 ) {
                if (modeChecker(args[0]) && !anotherRound()) {
                    counter++;
                    break;
                }
            }
            counter++;
        }
        printStats();
    }

    /**
     * shoeCreator creates a shoe and shuffles it depending on the mode of the game
     * */
    private void shoeCreater(){
        System.out.print("please choose size of shoe either 6 or 8 decks: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        try{
            this.shoe=new Shoe(size);
        }
        catch(CardException e){
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        this.shoe.shuffle();
    }

    /**
     * dealCard deals a card to the hand it handles the CardException thrown by deal()
     * @param hand which reprsents the hand the card is going to be dealt to
     * */
    private void dealCard(BaccaratHand hand) {
        try {
            hand.add(shoe.deal());
        } catch(CardException e){
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * playerDraw checks whether the player should draw according to punto banco drawing rules
     * @param playerValue which reprsents the value of the player's hand
     * */
    private boolean playerDraw(int playerValue) {
        return playerValue < 6;
    }

    /**
     * bankerDraw checks whether the player should draw according to punto banco drawing rules
     * @param playerValue which represents the value of the player's hand
     * @param bankerValue which represents the value of the banker's hand
     * */
    private boolean bankerDraw(int playerValue, int bankerValue) {
        return switch (bankerValue) {
            case 3 -> playerValue != 8;
            case 4 -> playerValue >= 2 && playerValue <= 7;
            case 5 -> playerValue >= 4 && playerValue <= 7;
            case 6 -> playerValue == 6 || playerValue == 7;
            default -> true;
        };
    }

    /**
     * round represents one round of punto banco
     * */
    public void round() {
        banker = new BaccaratHand();
        player = new BaccaratHand();
        for (int i = 0; i < 2; i++) {
            dealCard(player);
            dealCard(banker);
        }
        printResult();
        thirdCardRule();
        winnerCheck();
    }

    /**
     * thirdCardRule checks which hand should get a third card
     * */
    private void thirdCardRule() {
        if (!player.isNatural() && !banker.isNatural()) {
            if (playerDraw(player.value())) {
                dealCard(player);
                System.out.println("Dealing third card to player...");
                printResult();
                if (bankerDraw(player.value(), banker.value())) {
                    dealCard(banker);
                    System.out.println("Dealing third card to banker...");
                    printResult();
                }
            }
            else if (playerDraw(banker.value())) {
                dealCard(banker);
                System.out.println("Dealing third card to banker...");
                printResult();
            }
        }
    }

    /**
     * winnerCheck checks who the winner is and increments its counter
     * for statistics
     * */
    private void winnerCheck() {
        if (banker.value() > player.value()) {
            System.out.println("Banker win!");
            bankerWins++;
        }
        else if (banker.value() < player.value()) {
            playerWins++;
            System.out.println("Player win!");
        }
        else {
            ties++;
            System.out.println("Tie");
        }
    }

    /**
     * printResult prints the hand of the player and banker
     * */
    private void printResult() {
        System.out.printf("Player: %s = %d%nBanker: %s = %d%n",
                player, player.value(), banker, banker.value());
    }

    /**
     * anotherRound prompts the user to check if he wants to
     * play again it is only code in interactive mode
     * */
    public boolean anotherRound() {
        System.out.print("Another round? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.isEmpty()) {
            return false; // if input is empty return false
        }
        char firstChar = line.charAt(0);
        if (firstChar == 'y'|| firstChar == 'Y') {
            return true;
        }
        return false;
    }

    /**
     * modeChecker checks if the mode is interactive or non-interactive
     * @param args is the command line arguments
     * */
    public boolean modeChecker(String args) {
        return args.equals("-i") || args.equals("--interactive");
    }

    /**
     * printStats prints the statistics of the game at the end
     * */
    public void printStats(){
        System.out.printf("%d rounds played%n", counter - 1);
        System.out.printf("%d player wins%n", playerWins);
        System.out.printf("%d banker wins%n", bankerWins);
        System.out.printf("%d ties%n", ties);}
}
