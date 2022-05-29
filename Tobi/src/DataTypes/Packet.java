package DataTypes;

import Tobi.*;
import Network.*;
import Transport.*;
import Application.*;

enum Direction { SENT, RECV, UNDEF }

public class Packet {
    private final Hex hex;
    private final int index;
    private final Direction direction;
    private final Network network;
    private final Transport transport;
    private final Application application;


    public int getIndex() { return this.index; }
    public Direction getDirection() { return this.direction; }

    public Network getNetwork() { return this.network; }
    public IP getSourceIP() { return this.getNetwork().getSource(); }
    public IP getDestIP() { return this.getNetwork().getDest(); }

    public Transport getTransport() { return this.transport; }
    public int getSourcePort() { return this.getTransport().getSource(); }
    public int getDestPort() { return this.getTransport().getDest(); }

    public Application getApplication() { return this.application; }


    public boolean isNetworkLayerOK() {
        return this.network != null;
    }
    public boolean isTransportLayerOK() {
        return this.transport != null;
    }
    public boolean isApplicationLayerOK() {
        return this.application != null;
    }


    public Packet(int index, Hex hex) {
        Hex tempHex;

        this.index = index;
        this.hex = hex;

        this.network = Parser.FindNetworkProto(hex);

        if(this.isNetworkLayerOK()) {
            this.direction = findPacketDirection(this.getSourceIP());

            //Remove the network part of the hex
            tempHex = hex.get(this.getNetwork().getHex().size(), -1);

            this.transport = Parser.FindTransportProto(tempHex, this.getNetwork().getTransportProto());

            //Only keep the application part of the packet hex (if there's any)
            tempHex = tempHex.get(this.getTransport().getHex().size(), -1);

            if(this.isTransportLayerOK() && tempHex != null)
                this.application = Parser.FindApplicationProto(tempHex);
            else
                this.application = null;
        }
        else {
            this.direction = Direction.UNDEF;
            this.transport = null;
            this.application = null;
        }
    }


    private Direction findPacketDirection(IP sourceIP) {
        if (Tobi.HOSTIP.equals(sourceIP.toString()))
            return Direction.SENT;
        return Direction.RECV;
    }


    public Hex getHex() { return this.hex; }
}