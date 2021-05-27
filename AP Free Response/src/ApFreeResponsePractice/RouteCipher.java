package ApFreeResponsePractice;

public class RouteCipher {
    private String[][] letterBlock;
    private int numRows;
    private int numCols;

    private void fillBlock(String str) {
        int counter = 0;
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (str.length() > counter) {   // make sure counter is a valid index in str.
                    letterBlock[r][c] = str.substring(counter, counter + 1);
                    counter++;
                } else {
                    letterBlock[r][c] = "A";
                }
            }
        }
    }

    private String encryptBlock() {
        // implementation not shown.
        return null;
    }

    public String encryptMessage(String message) {  // Might be wrong (ask Dlo).
        String result = "";
        int count = 0;
        int numEncrypts = message.length()/(numRows*numCols);
        if (message.length() % (numRows*numCols) != 0) {
            numEncrypts++;
        }

        for (int i = 0; i < numEncrypts; i++) {
            fillBlock(message.substring(count, count + (numRows*numCols) + 1));
            result += encryptBlock();
            count += numRows*numCols;
        }

        return result;
    }
}
