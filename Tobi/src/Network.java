public interface Network {
    public Hex getHex();
    public IP getSource();
    public IP getDest();
    public int getTtl();
}
