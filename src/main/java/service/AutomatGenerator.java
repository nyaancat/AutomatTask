package service;

import service.FileManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AutomatGenerator {
    private static final String ID_AUTOMAT_PATH = "C:\\Projects\\AutomatTask\\AutomatTask\\Task3\\ID.txt";
    private static final String INT_AUTOMAT_PATH = "C:\\Projects\\AutomatTask\\AutomatTask\\Task3\\INT.txt";
    private static final String NUM_AUTOMAT_PATH = "C:\\Projects\\AutomatTask\\AutomatTask\\Task3\\NUM.txt";


    private static final String LETTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String NUMBERS = "1234567890";
    private static final String SIGNUM = "+-";
    private static final String E = "eE";


    public void generateIdAutomat(){
        final String states = "STATE_LETTER STATE_NUMBER STATE_UNDER STATE_EMPTY";
        FileManager fileManager = new FileManager();
        fileManager.deleteFile(ID_AUTOMAT_PATH);
        fileManager.writeToFile(states + '\n', ID_AUTOMAT_PATH);

        for (char c : LETTERS.toCharArray()){
            fileManager.writeToFile(c + " ", ID_AUTOMAT_PATH);
        }
        for (char c : NUMBERS.toCharArray()){
            fileManager.writeToFile(c + " ", ID_AUTOMAT_PATH);
        }

        fileManager.writeToFile("_\n", ID_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_EMPTY\n" +
                "STATE_LETTER STATE_NUMBER STATE_UNDER\n", ID_AUTOMAT_PATH);

        for (char c : LETTERS.toCharArray()){
            fileManager.writeToFile("STATE_EMPTY " + c + " STATE_LETTER\n", ID_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_LETTER " + c + " STATE_LETTER\n", ID_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_NUMBER " + c + " STATE_LETTER\n", ID_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_UNDER " + c + " STATE_LETTER\n", ID_AUTOMAT_PATH);
        }


        for (char c : NUMBERS.toCharArray()){
            fileManager.writeToFile("STATE_LETTER " + c + " STATE_NUMBER\n", ID_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_NUMBER " + c + " STATE_NUMBER\n", ID_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_UNDER " + c + " STATE_NUMBER\n", ID_AUTOMAT_PATH);
        }
        fileManager.writeToFile("STATE_NUMBER " + '_' + " STATE_UNDER\n", ID_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_LETTER " + '_' + " STATE_UNDER\n", ID_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_UNDER " + '_' + " STATE_UNDER\n", ID_AUTOMAT_PATH);

    }

    //todo:refactor signum
    public void generateIntAutomat(){
        FileManager fileManager = new FileManager();
        fileManager.deleteFile(INT_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_EMPTY STATE_ERROR STATE_SIGNUM STATE_NUMBER" + '\n', INT_AUTOMAT_PATH);

        for (char c : NUMBERS.toCharArray()){
            fileManager.writeToFile(c + " ", INT_AUTOMAT_PATH);
        }

        fileManager.writeToFile("+ -\n", INT_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_EMPTY\n" +
                "STATE_NUMBER\n", INT_AUTOMAT_PATH);


        for (char c : NUMBERS.toCharArray()){
            fileManager.writeToFile("STATE_EMPTY " + c + " STATE_NUMBER\n", INT_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_NUMBER " + c + " STATE_NUMBER\n", INT_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_SIGNUM " + c + " STATE_NUMBER\n", INT_AUTOMAT_PATH);
        }
        fileManager.writeToFile("STATE_EMPTY " + '+' + " STATE_SIGNUM\n", INT_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_EMPTY " + '-' + " STATE_SIGNUM\n", INT_AUTOMAT_PATH);

    }

    public void generateNumAutomat(){
        FileManager fileManager = new FileManager();
        fileManager.deleteFile(NUM_AUTOMAT_PATH);
        String states = "STATE_EMPTY STATE_ERROR STATE_1 STATE_2 STATE_3 STATE_4 STATE_5 STATE_6 STATE_7 STATE_8 STATE_9";
        fileManager.writeToFile(states + '\n', NUM_AUTOMAT_PATH);

        for (char c : NUMBERS.toCharArray()){
            fileManager.writeToFile(c + " ", NUM_AUTOMAT_PATH);
        }

        fileManager.writeToFile("+ - . e E\n", NUM_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_EMPTY\n" +
                "STATE_3 STATE_4 STATE_5 STATE_8\n", NUM_AUTOMAT_PATH);


        for (char c : NUMBERS.toCharArray()){
            fileManager.writeToFile("STATE_EMPTY " + c + " STATE_3\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_1 " + c + " STATE_4\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_2 " + c + " STATE_3\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_3 " + c + " STATE_3\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_4 " + c + " STATE_4\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_5 " + c + " STATE_4\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_6 " + c + " STATE_8\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_7 " + c + " STATE_8\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_8 " + c + " STATE_8\n", NUM_AUTOMAT_PATH);
        }

        for (char c : SIGNUM.toCharArray()){
            fileManager.writeToFile("STATE_EMPTY " + c + "STATE_2\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_6 " + c + " STATE_7\n", NUM_AUTOMAT_PATH);
        }

        for (char c : E.toCharArray()){
            fileManager.writeToFile("STATE_3 " + c + " STATE_6\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_4 " + c + " STATE_6\n", NUM_AUTOMAT_PATH);
            fileManager.writeToFile("STATE_5 " + c + " STATE_6\n", NUM_AUTOMAT_PATH);
        }

        fileManager.writeToFile("STATE_EMPTY " + '.' + " STATE_POINT\n", NUM_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_2 " + '.' + " STATE_1\n", NUM_AUTOMAT_PATH);
        fileManager.writeToFile("STATE_3 " + '.' + " STATE_5\n", NUM_AUTOMAT_PATH);
    }

}