public class Parser
{
    public static Network FindNetworkProto(Hex pktHex) {
        int ipVersion = pktHex.get(0).first().toDec();

        //Ipv4
        if (ipVersion == 4) {
            Hex networkHex = pktHex.get(0, 20);
            return new Ipv4(networkHex);
        }

        return null;
    }

}
