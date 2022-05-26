public class Ipv4 implements Network
{
    private final Hex hex;
    private final Frame frame;
    private final IP source;
    private final IP dest;
    private final int ttl;


    @Override
    public Hex getHex() { return this.hex; }

    public IP getSource() { return source; }
    public IP getDest() { return dest; }
    public int getTtl() { return ttl; }


    public Ipv4(Hex hex) {
        this.hex = hex;
        this.frame = new Frame(hex);

        this.source = new IP(frame.source);
        this.dest = new IP(frame.dest);
        this.ttl = frame.ttl.toDec();
    }


    private class Frame {
        private final Hex source;
        private final Hex dest;
        private final Byte ttl;


        public Hex getSource() { return source;  }
        public Hex getDest() { return dest; }
        public Byte getTtl() { return ttl; }


        public Frame(Hex hex) {
            this.source = hex.get(12, 16);
            this.dest = hex.get(16, 20);
            this.ttl = hex.get(8);
        }

    }

}
