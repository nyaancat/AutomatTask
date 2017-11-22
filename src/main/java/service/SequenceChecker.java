package service;

import entity.Automat;

import java.util.List;

public class SequenceChecker {

    public int[] checkSequenceForTask3(String sequenceStr, List<Automat> automats, int firstNum) {
        int[] counts = new int[automats.size()];

        for (int j = firstNum; j < sequenceStr.length(); j++) {
            for (int i = 0; i < automats.size(); i++) {

                Automat automat = automats.get(i);
                String symbol = automat.getSequenceParser().parseSymbol(sequenceStr.charAt(j));

                automat.transit(symbol);

                if (automat.isFinishState())
                    counts[i] = j - firstNum + 1;
            }
        }

        return counts;
    }


    public int checkSequenceForTask2(String sequenceStr, Automat automat, int firstNum){
        int count = 0;
        List<String> sequence = automat.getSequenceParser().parseSequence(sequenceStr);
        for (int i = firstNum; i < sequence.size(); i++){
            automat.transit(sequence.get(i));

            if (automat.isFinishState())
                count = i - firstNum + 1;
            if (automat.isErrorState()){
                if (count == 0)
                    count = -1;
                break;
            }
        }
        return count;
    }

    public void checkSequenceForTask1(String sequence, Automat automat){
        for (int i = 0; i < sequence.length(); i++){
            System.out.print(sequence.charAt(i) + " " + automat.getCurrentStates().toString() + " -> ");
            automat.transit(String.valueOf(sequence.charAt(i)));
            System.out.println(automat.getCurrentStates().toString());

            if (automat.isErrorState()) {
                System.out.println("Incorrect sequence");
                return;
            }

            System.out.println("Correct sequence");
        }
    }
}
