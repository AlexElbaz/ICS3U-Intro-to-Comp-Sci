package ApFreeResponsePractice;

public class Driver {
    public static void main(String[] args) {
        double[] arr = {1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8};
        SkyView nightSky = new SkyView(3, 3, arr);

        nightSky.getAverage(0, 1, 1, 2);
    }
}
