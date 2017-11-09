package entity;

import java.util.*;

public class TransitionFunction {
    private Map<String, Map<String, Set<String>>> table; //<startState, <symbol, List<finishState>>>

    TransitionFunction(Map<String, Map<String, Set<String>>> table){
        this.table = table;
    }

    public TransitionFunction() {
        table = new HashMap<String, Map<String, Set<String>>>();
    }

    protected Set<String> transit(Set<String> statesFrom, String symbol) throws Exception{
        Set <String> endStates = new HashSet<String>();
        try {
            for (String state : statesFrom) {
                endStates.addAll(table.get(state).get(symbol));
            }
        } catch (Exception e){
            //System.out.println("Illegal symbol");
            throw e;
        }
            return endStates;
    }

    public void addRow(String statefrom, String symbol, Set<String> result){
        if (!table.containsKey(statefrom)){
            Map<String, Set<String>> tmp = new HashMap<String, Set<String>>();
            tmp.put(symbol, result);
            table.put(statefrom, tmp);
        }
        else if(!table.get(statefrom).containsKey(symbol)){
            table.get(statefrom).put(symbol, result);
        }
    }
//    protected Map<String, Map<String, String>> getTable() {
//        return table;
//    }
//
//    protected void setTable(Map<String, Map<String, String>> table) {
//        this.table = table;
//    }



}
