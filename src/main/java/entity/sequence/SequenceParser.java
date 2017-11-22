package entity.sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class SequenceParser {

    public List<String> parseSequence(String sequence){
        List<String> resSeq = new ArrayList<String>();

        for (char c : sequence.toCharArray()){
            if(c == ' ')
                resSeq.add("SPACE");
            if(c == '\t')
                resSeq.add("\\t");
            if(c == '\r')
                resSeq.add("\\r");
            if(c == '\n')
                resSeq.add("\\n");
            else resSeq.add(Character.toString(c));
        }

        return resSeq;
    }
    public String parseSymbol(char symbol){
        if(symbol == ' ')
            return "SPACE";
        if(symbol == '\r')
            return "\\r";
        if(symbol == '\t')
            return "\\t";
        if(symbol == '\n')
            return "\\n";
        return Character.toString(symbol);
    }
}
