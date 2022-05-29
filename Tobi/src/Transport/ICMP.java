package Transport;

import Tobi.*;
import DataTypes.*;
import DataTypes.Byte;
import Network.*;
import Application.*;

public class ICMP extends Transport {
    private final Hex hex;
    private final Frame frame;
    private final int type;
    private final int code;
    private final String csum;
    private final String data;


    @Override
    public Hex getHex() { return this.hex; }
    @Override
    public int getType() { return this.type; }
    @Override
    public int getCode() {  return this.code; }
    @Override
    public String getCsum() { return this.csum; }
    @Override
    public String getData() { return this.data; }


    public ICMP(Hex hex)
    {
        this.hex = hex;
        this.frame = new Frame(hex);

        this.type = frame.type.toDec();
        this.code = frame.code.toDec();
        this.csum = frame.csum.toHexString();
        this.data = frame.data.toHexString();
    }


    private class Frame {
        private final Byte type;
        private final Byte code;
        private final Hex csum;
        private final Hex data;

        public Frame(Hex hex) {
            this.type = hex.get(0);
            this.code = hex.get(1);
            this.csum = hex.get(2, 4);
            this.data = hex.get(16, -1);
        }

    }

}
