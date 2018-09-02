public class WindowDimension {
    private int windowHeight;
    private int windowWidth;

    public WindowDimension(int windowWidth, int windowHeight) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
    }

    public WindowDimension() {
    }

    public int getWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
        return windowHeight - 96;
    }
    public int getWindowHeight() {
        return windowHeight - 96;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public int getWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
        return windowWidth -10;
    }
    public int getWindowWidth() {
        return windowWidth - 10;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }
}
