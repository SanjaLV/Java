public class InputBuffer {
    private String value;
    public InputBuffer() {
        value = null;
    }
    synchronized public void set(String value) {
        this.value = value;
    }
    synchronized public String get() {
        return this.value;
    }
}
