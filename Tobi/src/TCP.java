public class TCP extends Transport {
    private final Hex hex;
    private final Frame frame;
    private final int source;
    private final int dest;
    private final String seqNumber;
    private final String ackNumber;
    private final int hdrLen;
    private final int window;
    private final String csum;


    @Override
    public Hex getHex() { return this.hex; }
    @Override
    public int getSource() { return source; }
    @Override
    public int getDest() { return dest; }
    @Override
    public String getSeqNumber() { return seqNumber; }
    @Override
    public String getAckNumber() { return ackNumber; }
    @Override
    public int getHdrLen() { return hdrLen; }
    @Override
    public int getWindow() { return window; }
    @Override
    public String getCsum() { return csum; }


    public TCP(Hex hex)
    {
        this.frame = new Frame(hex);

        this.source = frame.source.toDec();
        this.dest = frame.dest.toDec();
        this.seqNumber = frame.seqNumber.toHexString();
        this.ackNumber = frame.ackNumber.toHexString();
        this.hdrLen = frame.hdrLen.toDec();
        this.window = frame.window.toDec();
        this.csum = frame.csum.toHexString();

        this.hex = hex.get(0, this.hdrLen * 4); //Check the header length to only take the TCP part of the packet hex
    }


    private class Frame {
        private final Hex source;
        private final Hex dest;
        private final Hex seqNumber;
        private final Hex ackNumber;
        private final Byte hdrLen;
        private final Hex window;
        private final Hex csum;

        public Frame(Hex hex) {
            this.source = hex.get(0, 2);
            this.dest = hex.get(2, 4);
            this.seqNumber = hex.get(4, 8);
            this.ackNumber = hex.get(8, 12);
            this.hdrLen = hex.get(12).first();
            this.window = hex.get(14, 16);
            this.csum = hex.get(16, 18);
        }

    }

}
