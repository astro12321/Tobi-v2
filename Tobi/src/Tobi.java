//for debug: https://hpd.gasmi.net/
//45000054a63e400040017a590a0000020808080808004b8c6fa400027b938d620000000066040f0000000000101112131415161718191a1b1c1d1e1f202122232425262728292a2b2c2d2e2f3031323334353637
public class Tobi {
    static { System.loadLibrary("native"); }

    private native String getPkt();
    private native void sendPkt(String pkt);
    private native void kamui();

    public static String HOSTIP = "10.0.0.2"; //Host machine IP set in the createInterface script, static value


    public static void main(String[] args) {
        int ind = 0;

        Tobi self = new Tobi();

        Runnable runnable = self::kamui;
        Thread thread = new Thread(runnable);
        thread.start();

        while (true) {
            String rawPkt = self.getPkt();

            if(!rawPkt.equals(".")) {
                ind++;

                Hex pktHex = new Hex(rawPkt);
                Packet pkt = new Packet(ind, pktHex);

                System.out.println("----------------------------------------------------------------");
                System.out.println(pkt.getIndex() + ". Packet: " + pkt.getDirection());
                System.out.println();

                System.out.println("Packet hex: ");
                System.out.println(pkt);
                System.out.println();

                if(pkt.isNetworkLayerOK()) {
                    System.out.println("Network:");
                    System.out.println(pkt.getNetwork().getHex());
                    System.out.println();
                    System.out.println("SourceIP: " + pkt.getSourceIP());
                    System.out.println("DestIP: " + pkt.getDestIP());
                    System.out.println("\n");

                    if(pkt.isTransportLayerOK()) {
                        System.out.println("Transport:");
                        System.out.println(pkt.getTransport().getHex());
                        System.out.println();

                        if(pkt.getTransport() instanceof ICMP)
                        {
                            System.out.println("Protocol: " + pkt.getTransport().getClass());
                            System.out.println("Type: " + pkt.getTransport().getType());
                            System.out.println("Code: " + pkt.getTransport().getCode());
                            System.out.println("Csum: " + pkt.getTransport().getCsum());
                            System.out.println("Data: " + pkt.getTransport().getData());
                        }
                        else if(pkt.getTransport() instanceof TCP)
                        {
                            System.out.println("Protocol: " + pkt.getTransport().getClass());
                            System.out.println("Source Port: " + pkt.getSourcePort());
                            System.out.println("Dest Port: " + pkt.getDestPort());
                            System.out.println("Seq Number: " + pkt.getTransport().getSeqNumber());
                            System.out.println("Ack Number: " + pkt.getTransport().getAckNumber());
                            System.out.println("Header Length: " + pkt.getTransport().getHdrLen());
                            System.out.println("Window: " + pkt.getTransport().getWindow());
                            System.out.println("Checksum: " + pkt.getTransport().getCsum());
                        }

                        System.out.println("\n");
                    }
                    
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("\n");
                }

                self.sendPkt(pkt.toString());
            }
        }

    }

}