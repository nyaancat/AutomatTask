package service.Tasks;

import entity.Automat;
import service.FileManager;
import service.SequenceChecker;

/**
 * Created by nyaan on 22.11.2017.
 */
public class Task2 {
    //12dd3.+a-55e4.7f56
    public static void taskSequence(FileManager fileManager, SequenceChecker sequenceChecker,
                                    String automatPath, String seqPath){
        try {
            Automat automat = fileManager.getInputAutomat(automatPath);
            String sequence = fileManager.getInputSequence(seqPath);
            int firstNum = 0;
            while (firstNum < sequence.length() - 1){
                automat.setStart();
                int count = sequenceChecker.checkSequenceForTask2(sequence, automat, firstNum);
//                System.out.print(firstNum + " ");
//                if (count < 0)
//                    System.out.println("<False, 0>");
//                else {
                //System.out.println("<True, " + count + ">");
                if (count > 0)
                    System.out.println(sequence.substring(firstNum, firstNum + count));
                //}
                firstNum += (count > 0) ? count : 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
