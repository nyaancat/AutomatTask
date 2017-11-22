package service.Tasks;

import entity.Automat;
import entity.sequence.SequenceParserChars;
import entity.sequence.SequenceParserNumbers;
import service.AutomatUtils;
import service.FileManager;
import service.SequenceChecker;

import java.util.ArrayList;
import java.util.List;


public class Task3 {
    //fun function = (id = 1e06, id < 1000 andalso (not(id - 5 = -366)), id *= 3.5)
    //id /= 56
    public static void lexicalAnalysis(FileManager fileManager, SequenceChecker sequenceChecker,
                                       String pathToAutomats, String outputPath, String seqPath) {
        try {
            List<Automat> automats = getAutomatsForTask3(fileManager, pathToAutomats);
            String sequence = fileManager.getInputSequence(seqPath);

            int firstNum = 0;
            while (firstNum < sequence.length() - 1) {
                for (Automat automat : automats){
                    automat.setStart();
                }
                int[] counts = sequenceChecker.checkSequenceForTask3(sequence, automats, firstNum);

                generateOutputForTask3(fileManager, counts, sequence, automats, firstNum, outputPath);

                int max = counts[AutomatUtils.getMaxNumberInArray(counts)];
                firstNum += (max > 0) ? max : 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateOutputForTask3(FileManager fileManager, int[] counts,
                                               String sequence, List<Automat> automats, int firstNum,
                                               String outputFilePath){

        int maxNumber = AutomatUtils.getMaxNumberInArray(counts);
        int max = counts[maxNumber];

        if (max > 0){
            String name = automats.get(maxNumber).getAutomatName();
            if (name.equals("WS")){
                fileManager.writeToFile(name + ": ", outputFilePath);
                String seq = sequence.substring(firstNum, firstNum + max);
                for (char c : seq.toCharArray()){
                    if (c != ' ')
                        fileManager.writeToFile(AutomatUtils.parseSymbol(c), outputFilePath);
                    else
                        fileManager.writeToFile(Character.toString(c), outputFilePath);
                }
                if (seq.charAt(seq.length() - 1) == ' ')
                    fileManager.writeToFile("|\n", outputFilePath);
                else
                    fileManager.writeToFile("\n", outputFilePath);
            }
            else
                fileManager.writeToFile(name + ": " + sequence.substring(firstNum, firstNum + max) + '\n', outputFilePath);
        }
        else
            fileManager.writeToFile("Undefined: " + sequence.charAt(firstNum) +'\n', outputFilePath);
    }


    private static List<Automat> getAutomatsForTask3(FileManager fileManager, String pathToAutomats) throws Exception {
        String[] paths = new String[11];
        paths[0] = "\\KW.txt";
        paths[1] = "\\INT.txt";
        paths[2] = "\\NUM.txt";
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
            Automat automat = fileManager.getInputAutomat(pathToAutomats + path);
            automat.setAutomatName(path.substring(1, path.length() - 4));
            automats.add(automat);
        }

        return automats;
    }
}
