public abstract class Network {
    public Hex getHex() { return new Hex("00"); }
    public IP getSource() { return new IP("UNDEF"); }
    public IP getDest() { return new IP("UNDEF"); }
    public int getTtl() { return -1; }
    public int getTransportProto() { return -1; }
}
