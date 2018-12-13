package exercise.mainlogic;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {


        int ret = new Solution().romanToInt("VII");

        String out = String.valueOf(ret);

        System.out.print(out);
    }
}


class Solution {

    private static Map<Character, Integer> romanIntMap = null;

    public Solution() {
        if (romanIntMap == null) {
            romanIntMap = Stream.of(new Object[][]{
                    {'I', 1},
                    {'V', 5},
                    {'X', 10},
                    {'L', 50},
                    {'C', 100},
                    {'D', 500},
                    {'M', 1000}
            }).collect(Collectors.toMap(data -> (Character) data[0], data -> (Integer) data[1]));
        }
    }

    public static int romanToInt(String s) {
        int index = 0;
        int total = 0;
        int validCnt = 0;


        try {
            char[] charArr = s.toCharArray();
            while (index < charArr.length) {
                int current = romanIntMap.get(charArr[index]);
                if (index + 1 < charArr.length && romanIntMap.get(charArr[index]) < romanIntMap.get(charArr[index + 1])) {
                    int next = romanIntMap.get(charArr[index + 1]);
                    if (validCnt > 1) {
                        throw new IOException("Wrong roman format");
                    } else {
                        if (charArr[index] == 'I' || charArr[index] == 'X' || charArr[index] == 'C') {
                            total += (romanIntMap.get(charArr[index + 1]) - romanIntMap.get(charArr[index]));
                            index += 2;
                            validCnt = 0;

                        } else {
                            throw new IOException("Wrong roman format");
                        }
                    }
                } else {
                    total += current;
                    index++;
                    validCnt++;

                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            total = 0;
        }
        return total;
    }
}
