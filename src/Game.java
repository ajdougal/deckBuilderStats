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
    private Boolean startFirst = false;
    private Boolean isAllowMulligan = false;
    public Boolean dubiousSuccess = false;
    public Boolean evolutionSuccess = false;

    /** The following are for deck-testing purposes **/
    private Boolean isPerfectStart;


//    phases = ["startTurn", "endTurn"]

    private List<Card> deck = new ArrayList<Card>();
    private List<Card> hand = new ArrayList<Card>();
    private List<Card> inPlay = new ArrayList<Card>();
    private List<Card> graveyard = new ArrayList<Card>();
    private List<Card> exiled = new ArrayList<Card>();
    private List<Card> lands = new ArrayList<Card>();
    private List<Card> dubious = new ArrayList<Card>();
    private List<Card> evolution= new ArrayList<Card>();

    public List<Card> addCard(List<Card> collection, Card card, Integer number){
        for (int counter = 0; counter < number; counter++){
            collection.add(card);
        }
        return collection;
    }

    public void drawCard(String target){
        if (target.equals("hand")) {
            this.hand.add(deck.get(this.drawCounter));
            this.drawCounter++;
        }
        if (target.equals("dubious")){
            this.dubious.add(deck.get(this.drawCounter));
            this.drawCounter++;
        }
        if (target.equals("evolution")){
            this.evolution.add(deck.get(this.drawCounter));
            this.drawCounter++;
        }
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

    public Game(Boolean startFirst, Boolean isAllowMulligan){

        this.startFirst = startFirst;
        this.isAllowMulligan = isAllowMulligan;
        // Start of the game, build deck, shuffle, and draw hand

        constructDubiousEvolutionDeck(this.deck);
        shuffleDeck(this.deck);
        while (this.drawCounter < 7){
            drawCard("hand");
//            System.out.println("added card");
        }

        // If start first
        // Check if have necessary starting hand for dubious

        if (!startFirst){
            drawCard("hand");
        }
        Boolean hasFirstTurnDubious = hasFirstTurnDubious();
//        System.out.println(hasFirstTurnDubious);
        drawCard("hand");
        Boolean hasDubiousChallenge = hasDubiousChallenge();

//        System.out.println(hasDubiousChallenge);
        if (hasFirstTurnDubious && hasDubiousChallenge) {
            isDubiousChallengeSuccessful();
        }

        // Look for turn 3 evolution, keep things simple
        drawCard("hand");
        this.evolutionSuccess = isEvolutionSuccessful();

        if (!(evolutionSuccess || dubiousSuccess) && isAllowMulligan){
            performMulligan();
        }


    }

    public void performMulligan(){
        this.hand = new ArrayList<Card>();
        this.deck = new ArrayList<Card>();
        this.dubious = new ArrayList<Card>();
        this.evolution = new ArrayList<Card>();
        this.drawCounter = 0;

        constructDubiousEvolutionDeck(this.deck);
        shuffleDeck(this.deck);
        while (this.drawCounter < 7){
            drawCard("hand");
//            System.out.println("added card");
        }

        // If start first
        // Check if have necessary starting hand for dubious

        if (!this.startFirst){
            drawCard("hand");
        }
        Boolean hasFirstTurnDubious = hasFirstTurnDubious();
//        System.out.println(hasFirstTurnDubious);
        drawCard("hand");
        Boolean hasDubiousChallenge = hasDubiousChallenge();

//        System.out.println(hasDubiousChallenge);
        if (hasFirstTurnDubious && hasDubiousChallenge) {
            isDubiousChallengeSuccessful();
        }

        // Look for turn 3 evolution, keep things simple

        this.evolutionSuccess = isEvolutionSuccessful();
    }

    public Boolean isEvolutionSuccessful(){
        Boolean hasEvolution = false;
        Integer numberOfExtraGreens = 0;
        Boolean hasAllosaurusRider = false;

        for (Card card : this.hand){
            if (card.getTitle().equals("Allosaurus Rider Cheap Cost")){
                hasAllosaurusRider = true;
            }
            if (card.getTitle().equals("Eldritch Evolution")){
                hasEvolution = true;
            }
            if (hasEvolution && card.getTitle().equals("Eldritch Evolution")){
                numberOfExtraGreens++;
            }
            if (hasAllosaurusRider && card.getTitle().equals("Allosaurus Rider Cheap Cost")){
                numberOfExtraGreens++;
            }
            if (card.getTitle().equals("Dubious Challenge")){
                numberOfExtraGreens++;
            }
            if (card.getTitle().equals("Arbor Elf")){
                numberOfExtraGreens++;
            }
//            if (card.getTitle().equals("Generic Bomb")){
//                numberOfExtraGreens++;
//            }
        }

        if (numberOfExtraGreens > 1 && hasEvolution && hasAllosaurusRider){
            for (Integer l = 0; l < 9; l++){
                drawCard("evolution");
            }
            for (Card card : this.evolution){
                if (card.getTitle().equals("Generic Bomb")){return true;}
            }
        }
        return false;
    }

    public Boolean hasFirstTurnDubious(){
        Boolean hasGreenLand = false;
        Boolean hasArborElf = false;
        Boolean hasLandEnchantment = false;

        for (Card card : this.hand){
            if (card.getTitle().equals("greenLand")){
                hasGreenLand = true;
//                System.out.println("Has green land");
            }
            if (card.getTitle().equals("Utopia Sprawl")){
                hasLandEnchantment = true;
//                System.out.println("Has Utopia");
            }
            if (card.getTitle().equals("Arbor Elf")){
                hasArborElf = true;
//                System.out.println("Has Arbor Elf");
            }
        }
        if (hasGreenLand && hasArborElf && hasLandEnchantment){
            return true;
        }
        return false;
    }

    public Boolean hasDubiousChallenge(){
        for (Card card : this.hand){
            if (card.getTitle().equals("Dubious Challenge")){
                return true;
            }
        }
        return false;
    }

    public void isDubiousChallengeSuccessful(){
        for(Integer k = 0; k < 10; k++) {
            drawCard("dubious");
        }
        Boolean hasBlinkCreature = false;
        Boolean hasBomb = false;
        for (Card card : this.dubious){
//            System.out.println(card.getTitle());
            if (card.getTitle().equals("Generic Blink Creature")){
                hasBlinkCreature = true;
            }
            if (card.getTitle().equals("Generic Bomb")){
                hasBomb = true;
            }
        }
        if (hasBlinkCreature && hasBomb){
            this.dubiousSuccess = true;
        }
    }


}
