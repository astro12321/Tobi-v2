enum Direction { SENT, RECV, UNDEF }

public class Packet {
    private final Hex hex;
    private final int index;
    private final Network network;
    private Direction direction = Direction.UNDEF;


    public int getIndex() { return this.index; }
    public Network getNetwork() { return this.network; }
    public IP getSourceIP() { return this.getNetwork().getSource(); }
    public IP getDestIP() { return this.getNetwork().getDest(); }
    public Direction getDirection() { return this.direction; }

    public boolean isNetworkLayerOK(){
        if (this.network == null)
            return false;
        return true;
    }


    public Packet(int index, Hex hex) {
        this.index = index;
        this.hex = hex;
        this.network = Parser.FindNetworkProto(hex);

        if(this.isNetworkLayerOK()) {
            if (Tobi.HOSTIP.equals(this.getSourceIP().toString()))
                this.direction = Direction.SENT;
            else
                this.direction = Direction.RECV;
        }
    }


    public String toString() { return this.hex.toString(); }
}