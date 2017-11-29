package service;

import entity.TransitionFunction;

import java.util.Map;
import java.util.Set;

public class TransitionFunctionService {
    public TransitionFunction renameFunction(Map<String, String> oldNameToNewName, TransitionFunction transitionFunction) {
        TransitionFunction newTransitionFunction = new TransitionFunction();
        for (String startState : transitionFunction.getStartStateToSymbolAndFinishStatesMap().keySet()) {
            for (String symbol : transitionFunction.getStartStateToSymbolAndFinishStatesMap().get(startState).keySet()) {
                newTransitionFunction.addRow(oldNameToNewName.get(startState),
                        symbol,
                        AutomatUtils.renameSet(transitionFunction.getStartStateToSymbolAndFinishStatesMap().get(startState).get(symbol), oldNameToNewName));
            }
        }
        return newTransitionFunction;
    }

    private void addTransitionFromFinishToStart(TransitionFunction transitionFunction,
                                               Set<String> startStates, Set<String> finishStates){
        for (String startState : startStates){
            Map<String, Set<String>> symbolToDestinationState =
                    transitionFunction.getStartStateToSymbolAndFinishStatesMap().get(startState);
            for (String finishState : finishStates){
                for (String symbol : symbolToDestinationState.keySet()){
                    transitionFunction.addRow(finishState, symbol, symbolToDestinationState.get(symbol));
                }
            }
        }
    }

    public void union(TransitionFunction transitionFunction1, TransitionFunction transitionFunction2){
        transitionFunction1.getStartStateToSymbolAndFinishStatesMap().putAll(transitionFunction2.getStartStateToSymbolAndFinishStatesMap());
    }

    public void concat(TransitionFunction transitionFunction1, TransitionFunction transitionFunction2,
                       Set<String> startStates, Set<String> finishStates){
        union(transitionFunction1,transitionFunction2);
        addTransitionFromFinishToStart(transitionFunction1, startStates, finishStates);
    }
}
