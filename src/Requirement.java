public class Requirement {

    /** The purpose of this class is to build requirements which get checked at certain times during the build.
        For example, you can have a 2 mana by turn 2 requirement and select the cards that meet the requirement.

        Another requirement is the combo play. The following example requirement objects highlight how to use this class.
        In this example, we are setting up a rather dubious challenge which will be presented to our opponent. It has a
        rather sneaky spice - It builds and simmers over time.

        Sample Requirements:
        {
            title: "Dubious Spice-Bomb Turn 1",
            startTurn: 0,
            endTurn: 1,
            requiredStartingCardsInHand: [greenLand, arborElf, thatLandEnchantment],
            requiredEndingCardsInHand: [],
            requiredStartingCardsInPlay: [],
            requiredEndingCardsInPlay: [greenLand, arborElf, thatLandEnchantment]
        },
        {
            title: "Dubious Spice-Bomb Turn 2"
            startTurn: 1,
            endTurn: 2,
            requiredStartingCardsInPlay: [greenLand, arborElf, thatLandEnchantment],
            requiredEndingCardsInHand: [],
            requiredStartingCardsInHand: [dubiousChallenge],
            requiredEndingCardsInPlay: []


     **/

    private String title;
    private Integer startTurn;
    private Integer endTurn;
    private Card[] requiredStartingCardsInHand;
    private Card[] requiredStartingCardsInPlay;
    private Card[] requiredEndingCardsInHand;
    private Card[] requiredEndingCardsInPlay;

}
