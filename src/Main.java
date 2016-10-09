public class Main {

    public static void main(String[] args) {
	// write your code here
    // true means that the dubious player draws first
        percentSuccessTotal(100000, false, true);
    }

    public static void percentSuccessTotal(Integer numberOfTrials, Boolean startFirst, Boolean isAllowMulligan){
        Integer numberOfDubiousSuccess = 0;
        Integer numberOfEvolutionSuccess = 0;

        for (Integer index = 0; index < numberOfTrials; index++){
            Game game = new Game(startFirst, isAllowMulligan);
            if (game.dubiousSuccess){
                numberOfDubiousSuccess++;
            }
            if (game.evolutionSuccess){
                numberOfEvolutionSuccess++;
            }
        }

        System.out.println("Ratio for Dubious: "+numberOfDubiousSuccess.toString()+"/"+numberOfTrials);
        System.out.println("Ratio for Evolution: "+numberOfEvolutionSuccess.toString()+"/"+numberOfTrials);
    }
}
