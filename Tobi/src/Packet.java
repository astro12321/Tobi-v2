public class Packet {
    private final Hex hex;
    private final int index;


    public Hex getHex() { return this.hex; }
    public int getIndex() { return this.index; }


    public Packet(int index, Hex hex) {
        this.index = index;
        this.hex = hex;
    }


    public String toString() { return this.hex.toString(); }
}