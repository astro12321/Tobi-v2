package Transport;

import Tobi.*;
import DataTypes.*;
import Network.*;
import Application.*;

public abstract class Transport {
    public Hex getHex() { return new Hex("00"); }
    public int getType() { return -1; }
    public int getCode() {  return -1; }
    public String getCsum() { return "UNDEF"; }
    public String getData() { return "UNDEF"; }
    public int getSource() { return -1; }
    public int getDest() { return -1; }
    public String getSeqNumber() { return "UNDEF"; }
    public String getAckNumber() { return "UNDEF"; }
    public int getHdrLen() { return -1; }
    public int getWindow() { return -1; }
}
