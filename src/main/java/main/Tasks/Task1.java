package main.Tasks;

import entity.Automat;
import service.FileManager;
import service.SequenceChecker;

public class Task1 {
    public static void taskSquare(FileManager fileManager, SequenceChecker sequenceChecker, String squarePath, String seqPath){
        try {
            Automat automat = fileManager.getInputAutomat(squarePath);
            String sequence = fileManager.getInputSequence(seqPath);
            sequenceChecker.checkSequenceForTask1(sequence, automat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
