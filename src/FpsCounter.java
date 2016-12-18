@SuppressWarnings("MagicNumber")
public class FpsCounter {
    private final long[] frameTimes = new long[10];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;
    private double frameRate;

    public void update(long now) {
        final long oldFrameTime = frameTimes[frameTimeIndex];
        frameTimes[frameTimeIndex] = now;
        frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
        if (frameTimeIndex == 0) {
            arrayFilled = true;
        }
        if (arrayFilled) {
            final long elapsedNanos = now - oldFrameTime;
            final long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
            frameRate = 1000000000. / elapsedNanosPerFrame;
        }
    }

    public double getFrameRate() {
        return ((int) (frameRate * 1000)) / 1000.;
    }
}
