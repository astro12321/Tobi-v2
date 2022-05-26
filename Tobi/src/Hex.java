import java.util.ArrayList;

public class Hex extends ArrayList<Byte>
{

    public Hex() { } //Empty Byte

    public Hex(String h)
    {
        for (int i = 0; i < h.length(); i +=2)
        {
            String b = "";

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


    public Hex get(int beginning, int end) {
        Hex t = new Hex();

        for (int i = beginning; i < end; i++)
            t.add(this.get(i));

        return t;
    }

}


class WrongHexLength extends Error {
    public WrongHexLength() {
        super("This hex is either too long, or too short");
    }
}