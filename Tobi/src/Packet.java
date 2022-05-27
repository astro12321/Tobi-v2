enum Direction { SENT, RECV, UNDEF }

public class Packet {
    private final Hex hex;
    private final int index;
    private final Direction direction;
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
        return this.network != null;
    }
    public boolean isTransportLayerOK() {
        return this.transport != null;
    }


    public Packet(int index, Hex hex) {
        this.index = index;
        this.hex = hex;

        Hex tempHex = new Hex(this.hex);

        this.network = Parser.FindNetworkProto(tempHex);

        if(this.isNetworkLayerOK()) {
            this.direction = findPacketDirection(this.getSourceIP());

            //Remove the network part of the hex
            tempHex = tempHex.get(this.getNetwork().getHex().size(), -1);

            this.transport = Parser.FindTransportProto(tempHex, this.getNetwork().getTransportProto());

            if(this.isTransportLayerOK()) {
                //Remove the transport part of the hex
                tempHex = tempHex.get(this.getTransport().getHex().size(), -1);

                //Application Layer
            }
        }
        else
            this.direction = Direction.UNDEF;
    }


    private Direction findPacketDirection(IP sourceIP) {
        if (Tobi.HOSTIP.equals(sourceIP.toString()))
            return Direction.SENT;
        return Direction.RECV;
    }


    public String toString() { return this.hex.toString(); }
}