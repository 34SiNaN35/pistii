import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.imageio.ImageIO;

// Creating a class called Pishti for determining the values of movements and order of players.

//I gave the name "Player" to the person (in short, we are playing)-->
//-->who enters the input manually and plays this game against the artificial intelligence (computer).
public class Pishti {
    public static void main(String[] args) {
        System.out.println("Starting Pishti Game ...");
        generateDeckStorage();
        CardDealing(Board);
        for (int g = 1; g <= 6; g++) {
            System.out.println("_____________ ROUND " + g + " _____________");
            CardDealing(ComputerMachine);
            CardDealing(Player);
            for (int j = 0; j < 4; j++) {
                CardPutting(Player);
                CardPutting(ComputerMachine);
                // result();
            }
        }
        // result();

        PishtigameOver(); //Calling the function.
    }
    //---------------------------------------------------------------------------------------------------------
    public static final int Player=1;
    public static final int ComputerMachine=2;
    public static final int Board=3;
    public static final int RealPoint=1;
    public static final int PishtiPoint=10;
    public static final int JokerPoint=1;

// Creating card section to detect number of cards and also trying
//to define how many cards will be given to each player and will be put on board by using Arraylist .

    static ArrayList<String> DeckStorage = new ArrayList<String>(52);
    static String[] TypeofJokers = new String[4]; // Defining number of types of joker cards.

    static ArrayList NumberofCardsOnMachine = new ArrayList();
    static ArrayList NumberofCardsOnPlayer= new ArrayList();
    static ArrayList NumberofCardsOnBoard = new ArrayList();


    static String CardOnMachine;  // Defining two string variables which will be useful while playing the game.
    static String CardOnPlayer;

    static Scanner takeScanner = new Scanner(System.in);

    static boolean  machineJoker= false;
    static int machineJokerValue = 0;

    static boolean fccequal = false; //Card on floor
    static int fccequalOrder = 0;

    static boolean NonemachineJoker = false;
    static int NonemachineJokerValue = 0;

    static int Chosencardindexnumber;
    static int Machine_Score = 0;
    static int Player_score = 0;


    private static void generateDeckStorage(){
        System.out.println("Cards are in dealing process : ");
        int i=0;
        while(i<52){

            if(i>=0 && i <13){
                DeckStorage.add("Spades ♠ - " + (i%13+1));
            }
            else if(i>=13 && i<26){
                DeckStorage.add("Hearts ♥ - " + (i%13+1));
            }
            else if(i>=26 && i<39){
                DeckStorage.add("Clubs ♣ - " + (i%13+1));
            }
            else{
                DeckStorage.add("Diamonds ♦ - " + (i%13+1));
            }
            i++;

        }
// Ordering process of the elements of the deck I defined upwards in "while" loop ,
        TypeofJokers[0]=("Spades -11");
        TypeofJokers[1]=("Hearts -11");
        TypeofJokers[2]=("Clubs -11");
        TypeofJokers[3]=("Diamonds -11");
// Then we randomly shuffle the cards in the deck with the "shuffle" command from the Collections we imported above.
        Collections.shuffle(DeckStorage);
    }

    public static void result() {
        System.out.println("_________________________");
        System.out.println("Number of all cards : " + DeckStorage);
        System.out.println("Cards on board : " + NumberofCardsOnBoard);
        System.out.println("Cards on player :  " + NumberofCardsOnPlayer);
        System.out.println("Cards on machine " + NumberofCardsOnMachine);

        System.out.println("-----------********----********-----------");
    }

    public static void PishtigameOver() {
        System.out.println("______**** ___0---0___ **** ______");
        System.out.println("Game is over ! ");
        System.out.println("Resulting Criteria : ");
        System.out.println("Machine Points(Score) = " + Machine_Score );
        System.out.println("Player Points(Score) = " + Player_score);

        if(Player_score<Machine_Score){
            System.out.println("Machine Won!");
        }
        else if(Machine_Score<Player_score){
            System.out.println("You Won!");
        }
        else{
            System.out.println("Both players have equal points. Pishti game ended in a draw:");
        }

        System.out.println("Thank you so much for playing this Pishti game.");
        System.out.println("-----------********----********-----------");
    }
//******************************************************************************************

    public static void CardDealing(int fff1) {
        for (int i = 0; i < 4; i++) {
            if (fff1 == Board){
                NumberofCardsOnBoard.add(DeckStorage.get(0));
            }
            else if (fff1 == ComputerMachine){
                NumberofCardsOnMachine.add(DeckStorage.get(0));
            }
            else if (fff1 == Player){
                NumberofCardsOnPlayer.add(DeckStorage.get(0));
            }

            DeckStorage.remove(0);
        }

        if (fff1 == Board){
            System.out.println(" The last card put on the table is : " + NumberofCardsOnBoard.get(NumberofCardsOnBoard.size() - 1));
        }
        if (fff1 == Player){
            System.out.println(" Cards in your hand : " + NumberofCardsOnPlayer);
        }
    }
    //**************************************************************************************************** */
    public static void GameManager(String PickedCard, int kplyr) {
        String boardcardd="";
        if (NumberofCardsOnBoard.isEmpty()) {
            NumberofCardsOnBoard.add(PickedCard);
        }
        else {
            boardcardd = (String) NumberofCardsOnBoard.get(NumberofCardsOnBoard.size() - 1);
            if (PickedCard.substring(1, PickedCard.length()).equals(boardcardd.substring(1, boardcardd.length()))) {
                if (NumberofCardsOnBoard.size() == 1) {
                    if (kplyr == ComputerMachine)
                        Machine_Score = Machine_Score + PishtiPoint;
                    else if (kplyr == Player)
                        Player_score = Player_score + PishtiPoint;

                    NumberofCardsOnBoard.removeAll(NumberofCardsOnBoard);
                }
                else {
                    if (kplyr == ComputerMachine)
                        Machine_Score = Machine_Score + NumberofCardsOnBoard.size() + RealPoint;
                    else
                        Player_score = Player_score + NumberofCardsOnBoard.size() + RealPoint;

                    NumberofCardsOnBoard.removeAll(NumberofCardsOnBoard);
                }
            } else if (PickedCard.equals(TypeofJokers[0]) || PickedCard.equals(TypeofJokers[1]) || PickedCard.equals(TypeofJokers[2])
                    || PickedCard.equals(TypeofJokers[3])) {
                if (kplyr == ComputerMachine)
                    Machine_Score = Machine_Score + NumberofCardsOnBoard.size() + JokerPoint;
                else
                    Player_score = Player_score + NumberofCardsOnBoard.size() + JokerPoint;

                NumberofCardsOnBoard.removeAll(NumberofCardsOnBoard);
            }
            else
                NumberofCardsOnBoard.add(PickedCard);
        }
    }
    //******************************************************************************************************************* */
    public static void CardPutting(int kplyr) {
        String boardcardd = "";
        if (!NumberofCardsOnBoard.isEmpty()) {
            boardcardd = (String) NumberofCardsOnBoard.get(NumberofCardsOnBoard.size() - 1);
        }
        if (kplyr == Player) {
            System.out.print("Enter the order of the card you will play: ");
            if (!NumberofCardsOnPlayer.isEmpty()) {

                int selected_number = (takeScanner.nextInt());

                while (NumberofCardsOnPlayer.size() < selected_number || 1 > selected_number){
                    System.out.println("Enter the card number you want to enter between 1 and " + NumberofCardsOnPlayer.size());
                    System.out.print("Enter the number of the Card you will play: ");
                    selected_number = (takeScanner.nextInt());
                }

                GameManager(NumberofCardsOnPlayer.get(selected_number - 1).toString(),Player);
                System.out.println("You have played " + NumberofCardsOnPlayer.get(selected_number - 1).toString());
                NumberofCardsOnPlayer.remove(selected_number - 1);
                System.out.println("Cards in your hand " + NumberofCardsOnPlayer);
            }
        } else if (kplyr == ComputerMachine) {
            if (!NumberofCardsOnBoard.isEmpty()) {
                for (int i = 0; i < NumberofCardsOnMachine.size(); i++) {
                    CardOnMachine = NumberofCardsOnMachine.get(i).toString();
                    if (CardOnMachine.substring(1, CardOnMachine.length()).equals(
                            boardcardd.substring(1, boardcardd.length()))) {
                        fccequal = true;
                        fccequalOrder = i;
                    }
                }
                if (fccequal) {
                    GameManager(NumberofCardsOnMachine.get(fccequalOrder).toString(), ComputerMachine);
                    System.out.println(" The last card put on the table is : " + NumberofCardsOnMachine.get(fccequalOrder).toString());
                    NumberofCardsOnMachine.remove(fccequalOrder);
                    fccequal = false;
                    fccequalOrder = 0;
                }
                else {
                    for (int z = 0; z < NumberofCardsOnMachine.size(); z++) {
                        for (int  h= 0; h < 4; h++) {
                            if (NumberofCardsOnMachine.get(z).toString().equals(TypeofJokers[h])) {
                                machineJoker = true;
                                machineJokerValue = z;
                            }
                        }
                    }
                    if (machineJoker) {
                        GameManager(NumberofCardsOnMachine.get(machineJokerValue).toString(), ComputerMachine);
                        System.out.println("The last card put on table" + NumberofCardsOnMachine.get(machineJokerValue).toString());
                        NumberofCardsOnMachine.remove(machineJokerValue);
                        machineJoker = false;
                        machineJokerValue = 0;
                    }
                    else {
                        int rand = (int) (Math.random()*NumberofCardsOnMachine.size());
                        GameManager(NumberofCardsOnMachine.get(rand).toString(), ComputerMachine);
                        System.out.println("The last card put on table is  " + NumberofCardsOnMachine.get(rand).toString());
                        NumberofCardsOnMachine.remove(rand);
                    }
                }
            }
            else {
                if (NumberofCardsOnMachine.size() == 1) {
                    GameManager(NumberofCardsOnMachine.get(0).toString(), 1);
                    System.out.println("The last card put on table is " + NumberofCardsOnBoard.get(0).toString());
                    NumberofCardsOnMachine.removeAll(NumberofCardsOnMachine);
                }
                else {
                    for (int lc = 0; lc < NumberofCardsOnMachine.size(); lc++) {
                        for (int orp = 0; orp < 4; orp++) {
                            if (!(NumberofCardsOnMachine.get(lc).toString().equals(TypeofJokers[orp]))) {
                                NonemachineJoker = true;
                                NonemachineJokerValue = lc;
                            }
                        }
                    }
                    if (NonemachineJoker) {
                        GameManager(NumberofCardsOnMachine.get(NonemachineJokerValue).toString(), ComputerMachine);
                        System.out.println("The last card put on table is " + NumberofCardsOnMachine.get(NonemachineJokerValue).toString());
                        NumberofCardsOnMachine.remove(NonemachineJokerValue);
                        NonemachineJoker = false;
                        NonemachineJokerValue = 0;
                    }
                    else {
                        int randnum = (int) (Math.random()*NumberofCardsOnMachine.size());
                        GameManager(NumberofCardsOnMachine.get(randnum).toString(), 1);
                        System.out.println("The last card put on table is " + NumberofCardsOnMachine.get(randnum).toString());
                        NumberofCardsOnMachine.remove(randnum);
                    }
                }
            }
        }
    }
}

