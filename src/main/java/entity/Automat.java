package entity;

import entity.sequence.SequenceParser;

import java.util.HashSet;
import java.util.Set;

public class Automat {

    private Set<String> states;
    private Set<String> alphabet;
    private Set<String> currentStates;
    private Set<String> finishStates;
    private Set<String> startStates;
    private TransitionFunction transitionFunction;
    private String automatName;
    private static final String ERROR_STATE_STR = "STATE_ERROR";
    private static final String EMPTY_STATE_STR = "STATE_EMPTY";


    public Automat(Set<String> states,
                   Set<String> alphabet,
                   Set<String> startStates,
                   Set<String> finishStates,
                   TransitionFunction transitionFunction){

        this.states = states;
        this.alphabet = alphabet;
        this.currentStates = startStates;
        this.startStates = startStates;
        this.finishStates = finishStates;
        this.transitionFunction = transitionFunction;
        states.add(ERROR_STATE_STR);
        //states.add(EMPTY_STATE_STR);
        //sequenceParser = new SequenceParser();
    }

    public Automat(Set<String> states,
                   Set<String> alphabet,
                   Set<String> startStates,
                   Set<String> finishStates,
                   TransitionFunction transitionFunction,
                   String automatName){

        this.automatName = automatName;
        this.states = states;
        this.alphabet = alphabet;
        this.currentStates = startStates;
        this.startStates = startStates;
        this.finishStates = finishStates;
        this.transitionFunction = transitionFunction;
        states.add(ERROR_STATE_STR);
       // states.add(EMPTY_STATE_STR);
    }

    public boolean transit(String symbol){
        try {
            currentStates = this.transitionFunction.transit(currentStates, symbol);
        } catch (Exception e) {
            Set<String> curr = new HashSet<String>();
            curr.add(ERROR_STATE_STR);
            setCurrentStates(curr);
            return false;
        }
        return true;
    }

    public boolean isErrorState(){
        return this.currentStates.contains(ERROR_STATE_STR);
    }

    public boolean isFinishState(){
        for (String state : finishStates){
            if (this.currentStates.contains(state))
                return true;
        }
        return false;
    }

    public void addState(String state){
        this.states.add(state);
    }

    public void addStartState(String state){
        this.startStates.add(state);
    }

    public void addFinishState(String state){
        this.finishStates.add(state);
    }
    public void setStart(){
        setCurrentStates(startStates);
    }

    public Set<String> getStates() {
        return states;
    }

    public void setCurrentStates(Set<String> currentStates) {
        this.currentStates = currentStates;
    }

    public void setStates(Set<String> states) {
        this.states = states;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Set<String> alphabet) {
        this.alphabet = alphabet;
    }

    public Set<String> getCurrentStates() {
        return currentStates;
    }

    public void setStartStates(Set<String> startStates) {
        this.currentStates = startStates;
    }

    public Set<String> getStartStates() {
        return startStates;
    }

    public Set<String> getFinishStates() {
        return finishStates;
    }

    public void setFinishStates(Set<String> finishStates) {
        this.finishStates = finishStates;
    }

    public TransitionFunction getTransitionFunction() {
        return transitionFunction;
    }

    public void setTransitionFunction(TransitionFunction transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    public String getAutomatName() {
        return automatName;
    }

    public void setAutomatName(String automatName) {
        this.automatName = automatName;
    }
}
