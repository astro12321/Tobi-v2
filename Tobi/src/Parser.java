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
        switch (transportProto)
        {
            case 1: //ICMP
                return new ICMP(hex);

            case 6: //TCP
                return new TCP(hex);

            /*case 17: //UDP
            {
                Hex transportHex = pktHex.get(networkProtoSize, 8);

                return std::make_unique<UDP>(transportHex);
            }*/

            default: //Other
                return null;
        }
    }

}
