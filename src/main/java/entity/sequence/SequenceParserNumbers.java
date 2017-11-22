package entity.sequence;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class SequenceParserNumbers extends SequenceParser {

    @Override
    public List<String> parseSequence(String sequence){
        List<String> resSeq = new ArrayList<String>();
        for (int i = 0; i < sequence.length(); i++){

            if(Character.isDigit(sequence.charAt(i)))
                resSeq.add("NUMBER");

            else if (sequence.charAt(i) == '.')
                resSeq.add("POINT");

            else if (sequence.charAt(i) == '+' || sequence.charAt(i) == '-')
                resSeq.add("SIGNUM");

            else if (sequence.charAt(i) == 'e' || sequence.charAt(i) == 'E')
                resSeq.add("E");

            else
                resSeq.add(Character.toString(sequence.charAt(i)));
        }
        return resSeq;
    }

    @Override
    public String parseSymbol(char symbol){
        if(Character.isDigit(symbol))
            return "NUMBER";

        else if (symbol == '.')
            return "POINT";

        else if (symbol == '+' || symbol == '-')
            return "SIGNUM";

        else if (symbol == 'e' || symbol == 'E')
            return "E";

        return Character.toString(symbol);
    }
}
