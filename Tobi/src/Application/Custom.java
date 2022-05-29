package Application;

import Tobi.*;
import DataTypes.*;
import Network.*;
import Transport.*;

public class Custom extends Application
{
    private final Hex hex;


    @Override
    public Hex getHex() { return this.hex; }


    public Custom(Hex hex) {
        this.hex = hex;
    }

}
