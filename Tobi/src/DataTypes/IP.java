package DataTypes;

import Tobi.*;
import Network.*;
import Transport.*;
import Application.*;

public class IP {
    private final String ip;


    public String toString() { return this.ip; }


    public IP(String ip) {
        this.ip = ip;
    }


    public IP(Hex hex) {
        if (hex.size() != 4)
            throw new WrongHexLength();
        this.ip = hex.get(0).toDec() + "." + hex.get(1).toDec() + "." + hex.get(2).toDec() + "." + hex.get(3).toDec();
    }

}