package service;


import service.Tasks.Task3;

public class Main {
    private static final String PATH = "C:\\Projects\\AutomatTask\\AutomatTask\\Task3";
    private static final String SEQ_PATH = "C:\\Projects\\AutomatTask\\AutomatTask\\Task3\\sequence.txt";
    private static final String SEQ_AUTOMAT_PATH = "C:\\Temp\\automat.txt";
    private static final String SQUARE_AUTOMAT_PATH = "C:\\Temp\\square2.txt";
    private final static String OUTPUT_FILE_PATH = "C:\\Projects\\AutomatTask\\AutomatTask\\Task3\\output.txt";


    public static void main(String[] args)
    {
        FileManager fileManager = new FileManager();
        SequenceChecker sequenceChecker = new SequenceChecker();
        //taskSquare(fileManager, automatService);
        //taskSequence(fileManager, automatService);
        Task3.lexicalAnalysis(fileManager, sequenceChecker, PATH, OUTPUT_FILE_PATH, SEQ_PATH);
        //generateAutomats();
    }




}
