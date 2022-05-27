enum Direction { SENT, RECV, UNDEF }

public class Packet {
    private final Hex hex;
    private final int index;
    private Direction direction = Direction.UNDEF;
    private Network network = null;
    private Transport transport = null;


    public int getIndex() { return this.index; }
    public Direction getDirection() { return this.direction; }

    public Network getNetwork() { return this.network; }
    public IP getSourceIP() { return this.getNetwork().getSource(); }
    public IP getDestIP() { return this.getNetwork().getDest(); }


    public Transport getTransport() { return this.transport; }
    public int getSourcePort() { return this.getTransport().getSource(); }
    public int getDestPort() { return this.getTransport().getDest(); }


    public boolean isNetworkLayerOK() {
        if (this.network == null)
            return false;
        return true;
    }
    public boolean isTransportLayerOK() {
        if (this.transport == null)
            return false;
        return true;
    }


    public Packet(int index, Hex hex) {
        this.index = index;
        this.hex = hex;

        Hex tempHex = new Hex(this.hex);

        this.network = Parser.FindNetworkProto(tempHex);

        if(this.isNetworkLayerOK()) {
            if (Tobi.HOSTIP.equals(this.getSourceIP().toString()))
                this.direction = Direction.SENT;
            else
                this.direction = Direction.RECV;

            //Remove the network part of the hex
            tempHex = tempHex.get(this.getNetwork().getHex().size(), -1);

            this.transport = Parser.FindTransportProto(tempHex, this.getNetwork().getTransportProto());
        }
    }


    public String toString() { return this.hex.toString(); }
}