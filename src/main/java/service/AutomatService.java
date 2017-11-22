package service;

import entity.Automat;
import entity.TransitionFunction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AutomatService {
//    public static Automat union(Automat automat1, Automat automat2){
//        Set<String> states = new HashSet<>();
//
//        Automat automat = new Automat();
//        return automat;
//    }

    private static void renameAutomats(Automat automat, int firstStateNumber){
        Set<String> states = automat.getStates();
        Map<String, String> oldNameToNewName = new HashMap<>();
        Set<String> newStates = new HashSet<>();
        Set<String> newCurrStates = new HashSet<>();
        Set<String> newFinishStates = new HashSet<>();
        Set<String> newStartStates = new HashSet<>();
        TransitionFunction newTransitionFunction;

        for (String state : states){
            String name = "STATE_" + firstStateNumber;
            oldNameToNewName.put(state, name);
            newStates.add(name);
            firstStateNumber++;
        }

        //automat.setStates(newStates);
        for (String state : automat.getCurrentStates()) {
            newCurrStates.add(oldNameToNewName.get(state));
        }

        for (String state : automat.getStartStates()) {
            newStartStates.add(oldNameToNewName.get(state));
        }

        for (String state : automat.getFinishStates()) {
            newFinishStates.add(oldNameToNewName.get(state));
        }
    }

}
