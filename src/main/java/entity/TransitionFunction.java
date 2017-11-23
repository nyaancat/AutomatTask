package entity;

import service.AutomatUtils;

import java.util.*;

public class TransitionFunction {
    private Map<String, Map<String, Set<String>>> startStateToSymbolAndFinishStates; //<startState, <symbol, List<finishState>>>

    TransitionFunction(Map<String, Map<String, Set<String>>> startStateToSymbolAndFinishStates){
        this.startStateToSymbolAndFinishStates = startStateToSymbolAndFinishStates;
    }

    public TransitionFunction() {
        startStateToSymbolAndFinishStates = new HashMap<String, Map<String, Set<String>>>();
    }

    protected Set<String> transit(Set<String> statesFrom, String symbol) throws Exception{
        Set <String> endStates = new HashSet<String>();
        try {
            for (String state : statesFrom) {
                endStates.addAll(startStateToSymbolAndFinishStates.get(state).get(symbol));
            }
        } catch (Exception e){
            //System.out.println("Illegal symbol");
            throw e;
        }
            return endStates;
    }

    public void addRow(String statefrom, String symbol, Set<String> result){
        if (!startStateToSymbolAndFinishStates.containsKey(statefrom)){
            Map<String, Set<String>> tmp = new HashMap<String, Set<String>>();
            tmp.put(symbol, result);
            startStateToSymbolAndFinishStates.put(statefrom, tmp);
        }
        else if(!startStateToSymbolAndFinishStates.get(statefrom).containsKey(symbol)){
            startStateToSymbolAndFinishStates.get(statefrom).put(symbol, result);
        }
    }

//    public void union(TransitionFunction transitionFunction){
//        this.startStateToSymbolAndFinishStates.putAll(transitionFunction.startStateToSymbolAndFinishStates);
//    }

//    public void rename(Map<String, String> oldNameToNewName){
//        //TransitionFunction newTransitionFunction = new TransitionFunction();
//        clearStartStateToSymbolAndFinishStatesMap();
//        for (String startState : startStateToSymbolAndFinishStates.keySet()){
//            for (String symbol : startStateToSymbolAndFinishStates.get(startState).keySet()) {
//                this.addRow(oldNameToNewName.get(startState),
//                        symbol,
//                        AutomatUtils.renameSet(startStateToSymbolAndFinishStates.get(startState).get(symbol), oldNameToNewName));
//            }
//        }
//    }
//
//    private void clearStartStateToSymbolAndFinishStatesMap(){
//        this.startStateToSymbolAndFinishStates = new HashMap<String, Map<String, Set<String>>>();
//    }

    @Deprecated
    public Map<String, Map<String, Set<String>>> getStartStateToSymbolAndFinishStatesMap() {
        return startStateToSymbolAndFinishStates;
    }

//    public void setStartStateToSymbolAndFinishStatesMap(Map<String, Map<String, Set<String>>> startStateToSymbolAndFinishStates) {
//        this.startStateToSymbolAndFinishStates = startStateToSymbolAndFinishStates;
//    }



}
