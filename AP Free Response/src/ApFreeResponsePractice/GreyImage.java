package ApFreeResponsePractice;

public class GreyImage {
    public static final int BLACK = 0;
    public static final int WHITE = 255;

    private int[][] pixelValues;

    public int countWhitePixels() {
        int count = 0;
        for (int r = 0; r < pixelValues.length; r++) {
            for (int c = 0; c < pixelValues[r].length; c++) {
                if (pixelValues[r][c] == WHITE)
                    count++;
            }
        }

        return count;
    }

    public void processImage() {
        for (int r = 0; r < pixelValues.length - 2; r++) {
            for (int c = 0; c < pixelValues[r].length - 2; c++) {
                if (r+2 < pixelValues.length && c+2 < pixelValues[r].length) {
                    if (pixelValues[r][c] >= pixelValues[r+2][c+2])
                        pixelValues[r][c] -= pixelValues[r+2][c+2];
                    else
                        pixelValues[r][c] = BLACK;
                }
            }
        }
    }
}