public class Parser
{
    public static Network FindNetworkProto(Hex pktHex) {
        int ipVersion = pktHex.get(0).first().toDec();

        //Ipv4
        if (ipVersion == 4) {
            Hex networkHex = pktHex.get(0, 20);
            return new IPV4(networkHex);
        }

        return null;
    }


    public static Transport FindTransportProto(Hex hex, int transportProto)
    {
        switch (transportProto) {
            case 1: //ICMP
                return new ICMP(hex);
            case 6: //TCP
                return new TCP(hex); //The constructor will only take the TCP part of the packet hex
            case 17: //UDP
                return new UDP(hex.get(0, 8)); //Only give the 8 first bytes, because this length will never change in an UDP packet
            default: //Other
                return null;
        }
    }

}
