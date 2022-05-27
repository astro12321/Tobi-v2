public class IPV4 extends Network
{
    private final Hex hex;
    private final Frame frame;
    private final IP source;
    private final IP dest;
    private final int ttl;
    private final int transportProto;

    @Override
    public Hex getHex() { return this.hex; }
    @Override
    public IP getSource() { return this.source; }
    @Override
    public IP getDest() { return this.dest; }
    @Override
    public int getTtl() { return this.ttl; }
    @Override
    public int getTransportProto() { return this.transportProto; }


    public IPV4(Hex hex) {
        this.hex = hex;
        this.frame = new Frame(hex);

        this.source = new IP(frame.source);
        this.dest = new IP(frame.dest);
        this.ttl = frame.ttl.toDec();
        this.transportProto = frame.transportProto.toDec();
    }


    private class Frame {
        private final Hex source;
        private final Hex dest;
        private final Byte ttl;
        private final Byte transportProto;
        
        public Frame(Hex hex) {
            this.source = hex.get(12, 16);
            this.dest = hex.get(16, 20);
            this.ttl = hex.get(8);
            this.transportProto = hex.get(9);
        }

    }

}
