package service;

import entity.Automat;
import entity.sequence.SequenceParserChars;
import entity.sequence.SequenceParserNumbers;

import java.io.File;
import java.util.*;

public class Main {
    private static final String PATH = "C:\\Users\\nyaan\\Downloads\\AutomatTask\\AutomatTask\\Task3";
    private static final String SEQ_PATH = "C:\\Users\\nyaan\\Downloads\\AutomatTask\\AutomatTask\\Task3\\sequence.txt";
    private static final String SEQ_AUTOMAT_PATH = "C:\\Temp\\automat.txt";
    private static final String SQUARE_AUTOMAT_PATH = "C:\\Temp\\square2.txt";


    public static void main(String[] args)
    {
        FileManager fileManager = new FileManager();
        AutomatService automatService = new AutomatService();
        SequenceChecker sequenceChecker = new SequenceChecker();
        //taskSquare(fileManager, automatService);
        //taskSequence(fileManager, automatService);
        lexicalAnalysis(fileManager, sequenceChecker);
    }

    //fun function = (id = 1e06, id < 1000 andalso (not(id - 5 = -366)), id *= 3.5)
    //id /= 56
    public static void lexicalAnalysis(FileManager fileManager, SequenceChecker sequenceChecker) {
        try {
            List<Automat> automats = getAutomatsForTask3(fileManager);
            String sequence = fileManager.getInputSequence(SEQ_PATH);

            int firstNum = 0;
            while (firstNum < sequence.length() - 1) {
                for (Automat automat : automats){
                    automat.setStart();
                }
                int[] counts = sequenceChecker.checkSequenceForTask3(sequence, automats, firstNum);

                generateOutputForTask3(fileManager, counts, sequence, automats, firstNum);

                int max = counts[AutomatUtils.getMaxNumberInArray(counts)];
                firstNum += (max > 0) ? max : 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateOutputForTask3(FileManager fileManager, int[] counts,
                                               String sequence, List<Automat> automats, int firstNum){

        int maxNumber = AutomatUtils.getMaxNumberInArray(counts);
        int max = counts[maxNumber];

        if (max > 0){
            String name = automats.get(maxNumber).getAutomatName();
            if (name.equals("WS")){
                fileManager.writeToFile(name + ": ");
                String seq = sequence.substring(firstNum, firstNum + max);
                for (char c : seq.toCharArray()){
                    if (c != ' ')
                        fileManager.writeToFile(automats.get(8).getSequenceParser().parseSymbol(c));
                    else
                        fileManager.writeToFile(Character.toString(c));
                }
                if (seq.charAt(seq.length() - 1) == ' ')
                    fileManager.writeToFile("|\n");
                else
                    fileManager.writeToFile("\n");
           }
            else
                fileManager.writeToFile(name + ": " + sequence.substring(firstNum, firstNum + max) + '\n');
        }
        else
            fileManager.writeToFile("Undefined: " + sequence.charAt(firstNum) +'\n');
    }


    private static List<Automat> getAutomatsForTask3(FileManager fileManager) throws Exception {
        String[] paths = new String[11];
        paths[0] = "\\KW.txt";
        paths[1] = "\\NUM.txt";
        paths[2] = "\\INT.txt";
        paths[3] = "\\LOG.txt";
        paths[4] = "\\OP.txt";
        paths[5] = "\\AS.txt";
        paths[6] = "\\LB.txt";
        paths[7] = "\\RB.txt";
        paths[8] = "\\WS.txt";
        paths[9] = "\\ID.txt";
        paths[10] = "\\C.txt";

        List<Automat> automats = new ArrayList<>();
        for (String path : paths){
            Automat automat = fileManager.getInputAutomat(PATH + path);
            automat.setAutomatName(path.substring(1, path.length() - 4));
            automats.add(automat);
        }

        automats.get(1).setSequenceParser(new SequenceParserNumbers());
        automats.get(2).setSequenceParser(new SequenceParserNumbers());
        //automats.get(8).setSequenceParser(new SequenceParserChars());
        automats.get(9).setSequenceParser(new SequenceParserChars());

        return automats;
    }





    //HHVVHV
    //RRDLLLLLLLLLLLL
    public static void taskSquare(FileManager fileManager, SequenceChecker sequenceChecker){
        try {
            Automat automat = fileManager.getInputAutomat(SQUARE_AUTOMAT_PATH);
            String sequence = fileManager.getInputSequence(SEQ_PATH);
            sequenceChecker.checkSequenceForTask1(sequence, automat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //12dd3.+a-55e4.7f56
    public static void taskSequence(FileManager fileManager, SequenceChecker sequenceChecker){
        try {
            Automat automat = fileManager.getInputAutomat(SEQ_AUTOMAT_PATH);
            String sequence = fileManager.getInputSequence(SEQ_PATH);
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
