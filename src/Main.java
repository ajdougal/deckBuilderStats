public class Main {

    public static void main(String[] args) {
	// write your code here
    // true means that the dubious player draws first
        System.out.println(percentSuccessDubious(200));
    }

    public Boolean isPerfectStart(Game game){
        return false;
    }

    public static String percentSuccessDubious(Integer numberOfTrials){
        Integer numberOfSuccess = 0;

        for (Integer index = 0; index < numberOfTrials; index++){
            Game game = new Game(true);
            if (game.dubiousSuccess){
                numberOfSuccess++;
            }
        }

        return "Ratio: "+numberOfSuccess.toString()+"/"+numberOfTrials;
    }
}
