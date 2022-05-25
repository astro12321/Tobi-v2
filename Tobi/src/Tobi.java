//for debug: https://hpd.gasmi.net/
//45000054a63e400040017a590a0000020808080808004b8c6fa400027b938d620000000066040f0000000000101112131415161718191a1b1c1d1e1f202122232425262728292a2b2c2d2e2f3031323334353637


public class Tobi {

    static { System.loadLibrary("native"); }

    private native String getPkt();
    private native void sendPkt(String pkt);
    private native void kamui();

    public static void main(String[] args) throws WrongByteLength {
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

                System.out.println(pkt.getIndex() + ". " + pkt + "\n");

                self.sendPkt(pkt.toString());
            }
        }

    }

}