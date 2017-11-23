package service;

import entity.Automat;
import entity.TransitionFunction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AutomatService {

    public void union(Automat automat1, Automat automat2){
        Set<String> states = new HashSet<>();

        //Automat automat = new Automat();
        //return automat;
    }

    private void renameAutomat(Automat automat, int firstStateNumber){
        Map<String, String> oldNameToNewName = new HashMap<>();
        Set<String> newStates = new HashSet<>();
        Set<String> newCurrStates;
        Set<String> newFinishStates;
        Set<String> newStartStates;
        TransitionFunction newTransitionFunction = new TransitionFunction();

        for (String state : automat.getStates()){
            String name = "STATE_" + firstStateNumber;
            oldNameToNewName.put(state, name);
            newStates.add(name);
            firstStateNumber++;
        }

        newCurrStates = renameSet(automat.getCurrentStates(), oldNameToNewName);
        newStartStates = renameSet(automat.getStartStates(), oldNameToNewName);
        newFinishStates = renameSet(automat.getFinishStates(), oldNameToNewName);

        Map<String, Map<String, Set<String>>> startStateToSymbolAndFinishStatesMap =
                automat.getTransitionFunction().getStartStateToSymbolAndFinishStatesMap();

        for (String startState : startStateToSymbolAndFinishStatesMap.keySet()){
            for (String symbol : startStateToSymbolAndFinishStatesMap.get(startState).keySet()) {
                newTransitionFunction.addRow(oldNameToNewName.get(startState),
                        symbol,
                renameSet(startStateToSymbolAndFinishStatesMap.get(startState).get(symbol), oldNameToNewName));
            }
        }

        automat.setStates(newStates);
        automat.setCurrentStates(newCurrStates);
        automat.setStartStates(newStartStates);
        automat.setFinishStates(newFinishStates);
        automat.setTransitionFunction(newTransitionFunction);

    }

    private Set<String> renameSet(Set<String> oldStates, Map<String, String> oldNameToNewName){
        Set<String> newStates = new HashSet<>();
        for (String state : oldStates) {
            newStates.add(oldNameToNewName.get(state));
        }
        return newStates;
    }
}
