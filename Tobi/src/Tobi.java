public class Tobi {

    static { System.loadLibrary("native"); }

    private native String getPkt();
    private native void sendPkt(String pkt);
    private native void kamui();

    public static void main(String[] args) {
        int ind = 0;

        Tobi self = new Tobi();

        Runnable runnable = () -> { self.kamui(); };
        Thread thread = new Thread(runnable);
        thread.start();

        while (true) {
            String pkt = self.getPkt();

            if(!pkt.equals(".")) {
                ind++;

                System.out.println(ind + ". " + pkt);

                self.sendPkt(pkt);
            }
        }

    }

}