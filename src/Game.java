import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Game {
    private Integer turn;
    private String title;
    private Integer playerLife;
    private Integer opponentLife;
    private Integer redMana, blueMana, greenMana, blackMana, whiteMana, uncoloredMana;
    private String phase;
    private Integer drawCounter = 0;

    /** The following are for deck-testing purposes **/
    private Boolean isPerfectStart;


//    phases = ["startTurn", "endTurn"]

    private List<Card> deck = new ArrayList<Card>();
    private List<Card> hand = new ArrayList<Card>();
    private List<Card> inPlay = new ArrayList<Card>();
    private List<Card> graveyard = new ArrayList<Card>();
    private List<Card> exiled = new ArrayList<Card>();
    private List<Card> lands = new ArrayList<Card>();

    public List<Card> addCard(List<Card> collection, Card card, Integer number){
        for (int counter = 0; counter < number; counter++){
            collection.add(card);
        }
        return collection;
    }

    public void drawCard(){
        this.hand.add(deck.get(this.drawCounter));
        this.drawCounter++;
    }

    public void constructDubiousEvolutionDeck(List<Card> deck){
        List<String> colorsGreen = new ArrayList<String>();
        colorsGreen.add("green");
        List<String> colorless = new ArrayList<String>();
        colorless.add("colorless");
        this.deck = addCard(this.deck, new Card("sorcery", "Dubious Challenge", colorsGreen, 4), 4);
        this.deck = addCard(this.deck, new Card("creature", "Allosaurus Rider Cheap Cost", colorsGreen, 2), 4);
        this.deck = addCard(this.deck, new Card("sorcery", "Eldritch Evolution", colorsGreen, 3), 4);
        this.deck = addCard(this.deck, new Card("creature", "Arbor Elf", colorsGreen, 1), 4);
        this.deck = addCard(this.deck, new Card("enchantment", "Utopia Sprawl", colorsGreen, 1), 4);
        this.deck = addCard(this.deck, new Card("land", "greenLand", colorless, 0), 23);
        this.deck = addCard(this.deck, new Card("creature", "Generic Bomb", colorless, 0), 7);
        this.deck = addCard(this.deck, new Card("creature", "Generic Blink Creature", colorless, 0), 10);
    }

    public void shuffleDeck(List<Card> deck){
        Collections.shuffle(deck);
    }

    public Game(){
        constructDubiousEvolutionDeck(this.deck);
        shuffleDeck(this.deck);
        while (this.drawCounter < 7){
            drawCard();
            System.out.println("added card");
        }
        for (Card card : this.hand){
            System.out.println(card.getTitle());
        }
    }




}
