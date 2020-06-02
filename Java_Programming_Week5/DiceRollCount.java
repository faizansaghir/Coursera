import java.util.Random;
/**
 * Write a description of DiceRollCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DiceRollCount {
    private int rollDieOnce(){
        Random random=new Random();
        int dieRoll= random.nextInt(6)+1;//generates random integer number between 0 to 6(excluded) and then add 1 to it
        return dieRoll;
    }
    private int[] rollMultipleTimes(int rolls){
        int[] roll=new int[rolls];
        for(int i=1;i<=rolls;i++){
            roll[i-1]=rollDieOnce();
        }
        return roll;
    }
    public void rollDiceMultipleTimes(int dice,int times){
        int[][] rolls=new int[dice][times];
        for(int i=1;i<=dice;i++){
            rolls[i-1]=rollMultipleTimes(times);
        }
        String diePrint="";
        for(int i=1;i<=dice;i++){
            diePrint+="Die "+i+" \t ";
        }
        System.out.println(diePrint);
        for(int i=1;i<=times;i++){
            String toPrint="";
            for(int j=1;j<=dice;j++){
                toPrint+=rolls[j-1][i-1]+" \t ";
            }
            System.out.println(toPrint);
        }
    }
}
