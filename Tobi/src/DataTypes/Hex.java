package DataTypes;

import Tobi.*;
import Network.*;
import Transport.*;
import Application.*;

import java.util.ArrayList;

public class Hex extends ArrayList<Byte>
{
    public Hex(Hex h) {
        this.addAll(h);
    }


    public Hex(Byte b) {
        this.add(b);
    }


    public Hex(String h)
    {
        for (int i = 0; i < h.length(); i +=2)
        {
            String b;

            if (i + 2 > h.length())
                b = h.substring(i, i + 1);
            else
                b = h.substring(i, i + 2);

            this.add(new Byte(b));
        }
    }


    public String toString() {
        StringBuilder t = new StringBuilder();

        for (Byte b: this)
            t.append(b.toString());

        return t.toString();
    }


    public String toHexString(){
        return "0x" + this;
    }


    public Hex get(int beginning, int end) {
        if (end == -1)
            end = this.size();
        if(end < beginning)
            throw new WrongHexLength();
        if(end - beginning == this.size())
            return this;
        if(beginning == end)
            return null;

        Hex t = new Hex(this.get(beginning));

        for (int i = beginning + 1; i < end; i++)
            t.add(this.get(i));

        return t;
    }


    public int toDec() { return Integer.parseInt(this.toString(),16); }
}


class WrongHexLength extends Error {
    public WrongHexLength() {
        super("This hex is either too long, or too short");
    }
}