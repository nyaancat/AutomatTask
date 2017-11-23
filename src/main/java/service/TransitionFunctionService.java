package service;

import entity.TransitionFunction;

import java.util.Map;

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




    public void union(TransitionFunction transitionFunction1, TransitionFunction transitionFunction2){
        transitionFunction1.getStartStateToSymbolAndFinishStatesMap().putAll(transitionFunction2.getStartStateToSymbolAndFinishStatesMap());
    }

    public void concat(TransitionFunction transitionFunction1, TransitionFunction transitionFunction2){

    }
}
