package service;

import main.AutomatGenerator;

import java.util.*;

public class AutomatUtils {

    public static void generateAutomats(){
        AutomatGenerator generator = new AutomatGenerator();
        generator.generateIdAutomat();
        generator.generateIntAutomat();
        generator.generateNumAutomat();
    }

    public static int getMaxNumberInArray(int[] counts){
        int max = -1;
        int maxNumber = -1;
        for (int i = 0; i < counts.length; i++){
            if (counts[i] > max){
                max = counts[i];
                maxNumber = i;
            }
        }
        return maxNumber;
    }

    public static List<String> parseSequence(String sequence){
        List<String> resSeq = new ArrayList<String>();

        for (char c : sequence.toCharArray()){
            if(c == ' ')
                resSeq.add("SPACE");
            if(c == '\t')
                resSeq.add("\\t");
            if(c == '\r')
                resSeq.add("\\r");
            if(c == '\n')
                resSeq.add("\\n");
            else resSeq.add(Character.toString(c));
        }

        return resSeq;
    }


    public static String parseSymbol(char symbol){
        if(symbol == ' ')
            return "SPACE";
        if(symbol == '\r')
            return "\\r";
        if(symbol == '\t')
            return "\\t";
        if(symbol == '\n')
            return "\\n";
        return Character.toString(symbol);
    }

    public static Set<String> renameSet(Set<String> oldStates, Map<String, String> oldNameToNewName){
        Set<String> newStates = new HashSet<>();
        for (String state : oldStates) {
            newStates.add(oldNameToNewName.get(state));
        }
        return newStates;
    }
}
