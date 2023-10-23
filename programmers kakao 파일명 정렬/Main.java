import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public String[] solution(String[] inputs) {
        List<File> files = Arrays.stream(inputs)
            .map(File::new)
            .sorted()
            .collect(Collectors.toList());

        return files.stream().map(file -> file.ordinary)
            .toArray(String[]::new);
    }


    private static final class File implements Comparable<File> {

        private final String ordinary;
        private String head;
        private int number;

        private File(String input) {
            ordinary = input;
            input = input.toLowerCase();
            setHead(input);
            setNumber(input);
        }

        private void setHead(String input) {
            for (int i = 0; i < input.length(); i++) {
                if (isNumber(input.charAt(i))) {
                    this.head = input.substring(0, i);
                    return;
                }
            }
        }

        private void setNumber(String input) {
            int numberStart = 0;
            int numberEnd = input.length();
            for (int i = 0; i < input.length(); i++) {
                if (isNumber(input.charAt(i)) && numberStart == 0) {
                    numberStart = i;
                    continue;
                }
                if (!isNumber(input.charAt(i)) && numberStart != 0) {
                    numberEnd = i;
                    break;
                }
            }

            number = Integer.parseInt(input.substring(numberStart, numberEnd));
        }

        private boolean isNumber(char c) {
            return c >= '0' && c <= '9';
        }

        @Override
        public int compareTo(File file) {
            int headCompare = head.compareTo(file.head);
            if (headCompare != 0) {
                return headCompare;
            }
            return Integer.compare(number, file.number);
        }
    }
}
