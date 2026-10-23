/**
 * An image that may need to be rescaled and/or resized. An image can need one
 * of the two processes without needing the other.
 */
public class Image {

    private final boolean scaled;
    private final boolean resized;

    /**
     * @param scaled  {@code true} if the image must be rescaled
     * @param resized {@code true} if the image must be resized
     */
    public Image(boolean scaled, boolean resized) {
        this.scaled = scaled;
        this.resized = resized;
    }

    /** @return {@code true} if the image needs to be rescaled */
    public boolean scale() {
        return scaled;
    }

    /** @return {@code true} if the image needs to be resized */
    public boolean resize() {
        return resized;
    }
}
