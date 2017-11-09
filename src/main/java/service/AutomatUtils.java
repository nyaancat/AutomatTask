package service;

import java.util.ArrayList;
import java.util.List;

public class AutomatUtils {
    public static int getMaxNumberInArray(int[] counts){
        int max = -1;
        int maxNumber = -1;
        for (int i = 0; i < counts.length; i++){
            if (counts[i] > max){
                max = counts[i];
                maxNumber = i;
            }
        }
        return maxNumber;
    }
}
