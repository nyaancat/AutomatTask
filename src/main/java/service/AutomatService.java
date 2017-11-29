package service;

import entity.Automat;
import entity.TransitionFunction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AutomatService {

    public TransitionFunctionService transitionFunctionService = new TransitionFunctionService();

    public Automat concat(Automat automat1, Automat automat2) {
        int stateNum = 0;
        renameAutomat(automat1, stateNum);
        renameAutomat(automat2, stateNum);

        Set<String> states = automat1.getStates();
        Set<String> finishStates = automat2.getFinishStates();
        Set<String> startStates = automat1.getStartStates();
        TransitionFunction transitionFunction = automat1.getTransitionFunction();
        Set<String> alphabet = automat1.getAlphabet();
        String automatName = automat1.getAutomatName();

        states.addAll(automat2.getStates());
        alphabet.addAll(automat2.getAlphabet());
        automatName += '&' + automat2.getAutomatName();
        transitionFunctionService.concat(transitionFunction, automat2.getTransitionFunction(), startStates, finishStates);

        if (hasEqualStates(finishStates, startStates)){
            finishStates.addAll(automat1.getFinishStates());
        }

        Automat automat = new Automat(states, alphabet, startStates, finishStates, transitionFunction, automatName);
        return automat;
    }

    public Automat union(Automat automat1, Automat automat2){
        int stateNum = 0;
        renameAutomat(automat1, stateNum);
        renameAutomat(automat2, stateNum);

        Set<String> states = automat1.getStates();
        Set<String> finishStates = automat1.getFinishStates();
        Set<String> startStates = automat1.getStartStates();
        TransitionFunction transitionFunction = automat1.getTransitionFunction();
        Set<String> alphabet = automat1.getAlphabet();
        String automatName = automat1.getAutomatName();

        states.addAll(automat2.getStates());
        finishStates.addAll(automat2.getFinishStates());
        startStates.addAll(automat2.getStartStates());
        alphabet.addAll(automat2.getAlphabet());
        automatName += '+' + automat2.getAutomatName();
        transitionFunctionService.union(transitionFunction, automat2.getTransitionFunction());

        Automat automat = new Automat(states, alphabet, startStates, finishStates, transitionFunction, automatName);
        return automat;
    }

    public Automat iterate(Automat automat) {
        int stateNum = 0;
        renameAutomat(automat, stateNum);
        transitionFunctionService.addTransitionFromFinishToStart(automat.getTransitionFunction(),
                automat.getStartStates(), automat.getFinishStates());
        automat.addState("STATE_" + stateNum);
        automat.addStartState("STATE_" + stateNum);
        automat.addFinishState("STATE_" + stateNum);
        return automat;
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

        newCurrStates = AutomatUtils.renameSet(automat.getCurrentStates(), oldNameToNewName);
        newStartStates = AutomatUtils.renameSet(automat.getStartStates(), oldNameToNewName);
        newFinishStates = AutomatUtils.renameSet(automat.getFinishStates(), oldNameToNewName);
        newTransitionFunction = transitionFunctionService.renameFunction(oldNameToNewName, automat.getTransitionFunction());

        automat.setStates(newStates);
        automat.setCurrentStates(newCurrStates);
        automat.setStartStates(newStartStates);
        automat.setFinishStates(newFinishStates);
        automat.setTransitionFunction(newTransitionFunction);

    }

    private boolean hasEqualStates(Set<String> states1, Set<String> states2){
        Set<String> diff = new HashSet<>(states1);
        diff.retainAll(states2);
        return !diff.isEmpty();
    }
}
