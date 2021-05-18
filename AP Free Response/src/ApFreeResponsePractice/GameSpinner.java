package ApFreeResponsePractice;

public class GameSpinner {
    
    private int sectors;
    private int currentRun;
    private int lastSpin;

    public GameSpinner(int sectors) {
        this.sectors = sectors;
        this.currentRun = 0;
        this.lastSpin = -1;
    }

    public int spin() {
        int currentSpin = (int) ((Math.random() * sectors) + 1);
        if (currentSpin == lastSpin)
            currentRun++;
        else
            currentRun = 1;

        return currentSpin;
    }

    public int currentRun() {
        return currentRun;
    }
}
