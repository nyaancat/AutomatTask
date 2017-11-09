package entity.sequence;


import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.List;

public class SequenceParserChars extends SequenceParser {

    @Override
    public List<String> parseSequence(String sequence){
        List<String> resSeq = new ArrayList<String>();
        for (int i = 0; i < sequence.length(); i++){

            if(Character.isDigit(sequence.charAt(i)))
                resSeq.add("NUMBER");

            else if (Character.isAlphabetic(sequence.charAt(i)))
                resSeq.add("LETTER");

            else if(sequence.charAt(i) == ' ')
                resSeq.add("SPACE");

            else if(sequence.charAt(i) == '\\')
                resSeq.add("\\");

            else
                resSeq.add(Character.toString(sequence.charAt(i)));
        }
        return resSeq;
    }

    @Override
    public String parseSymbol(char symbol){
        if(Character.isDigit(symbol))
            return "NUMBER";

        if (Character.isAlphabetic(symbol))
            return "LETTER";

        if(symbol == ' ')
            return "SPACE";

        return Character.toString(symbol);
    }


}
