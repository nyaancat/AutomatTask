package service;

import entity.Automat;
import entity.TransitionFunction;
import sun.awt.TracedEventQueue;

import java.io.*;
import java.util.*;

public class FileManager {
    private final static String OUTPUT_FILE_PATH = "C:\\Users\\nyaan\\Downloads\\AutomatTask\\AutomatTask\\Task3\\output.txt";

    /**
     * Input File Format:
     * State1, State2, State3...
     * Symbol1, Symbol2...
     * startState
     * finishState1, finishState2...
     * State1, Symbol1, resultState
     * State1, Symbol2, resultState
     * State2, Symbol1, resultState
     * etc
     */

    public FileManager(){
        try {
            File file = new File(OUTPUT_FILE_PATH);
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Automat getInputAutomat(String automatPath) throws Exception{
        try (BufferedReader in = new BufferedReader(new FileReader(automatPath))){
            Set<String> states = getElements(in.readLine());
            Set<String> symbols = getElements(in.readLine());
            Set<String> startState = getElements(in.readLine());
            Set<String> finishStates = getElements(in.readLine());

            TransitionFunction transitionFunction = new TransitionFunction();
            String line;
            while ((line = in.readLine()) != null){
                List <String> elements = new ArrayList<String>(Arrays.asList(line.split(" ")));
                transitionFunction.addRow(elements.get(0), elements.get(1), new HashSet<String>(elements.subList(2, elements.size())));
            }

            return new Automat(new HashSet<String>(states),
                    new HashSet<String>(symbols), startState,
                    new HashSet<String>(finishStates), transitionFunction);

        } catch (Exception e) {
            throw e;
        }
    }

    public String getInputSequence(String seqPath) throws Exception{
        try (BufferedReader in = new BufferedReader(new FileReader(seqPath))){
            String line;
            StringBuilder res = new StringBuilder();
            while ((line = in.readLine()) != null)
                res.append(line + '\n');
            return res.toString().substring(0, res.length() - 1);
        } catch (Exception e) {
            throw e;
        }
    }

//    public BufferedWriter getOut() {
//        return out;
//    }

    public void deleteFile(String path){
        try {
            File file = new File(path);
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String str, String pathToFile){
        try ( BufferedWriter out = new BufferedWriter(new FileWriter(pathToFile, true))){
            out.write(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<String> getElements(String s){
        return new HashSet<String>(Arrays.asList(s.split(" ")));
    }
}
