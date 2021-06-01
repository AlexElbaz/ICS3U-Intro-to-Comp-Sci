package ApFreeResponsePractice;

public class SkyView {
    private double[][] view;

    public SkyView(int numRows, int numCols, double[] scanned) {
        view = new double[numRows][numCols];

        int i = 0;
        for (int r = 0; r < numRows; r++) {
            if (r % 2 == 0) {
                for (int c = 0; c < numCols; c++) {
                    view[r][c] = scanned[i];
                    i++;
                }
            } else {
                for (int c = numCols - 1; c >= 0; c--) {
                    view[r][c] = scanned[i];
                    i++;
                }
            }
        }
    }

    public double getAverage(int startRow, int endRow, int startCol, int endCol) {
        double total = 0;
        int count = 0;
        for (int r = 0; r < view.length; r++) {
            for (int c = 0; c < view[r].length;c++) {
                if (r >= startRow && r <= endRow && c >= startCol && c <= endCol) {
                    total += view[r][c];
                    count++;
                }
            }
        }

        return total/count;
    }
}
