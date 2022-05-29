package Transport;

import Tobi.*;
import DataTypes.*;
import Network.*;
import Application.*;

public class UDP extends Transport {
    private final Hex hex;
    private final Frame frame;
    private final int source;
    private final int dest;
    private final String csum;


    @Override
    public Hex getHex() { return this.hex; }
    @Override
    public int getSource() { return source; }
    @Override
    public int getDest() { return dest; }
    @Override
    public String getCsum() { return csum; }


    public UDP(Hex hex)
    {
        this.hex = hex;
        this.frame = new Frame(hex);

        this.source = frame.source.toDec();
        this.dest = frame.dest.toDec();
        this.csum = frame.csum.toHexString();
    }


    private class Frame {
        private final Hex source;
        private final Hex dest;
        private final Hex csum;

        public Frame(Hex hex) {
            this.source = hex.get(0, 2);
            this.dest = hex.get(2, 4);
            this.csum = hex.get(6, 8);
        }

    }
}
