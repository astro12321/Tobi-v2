import java.util.ArrayList;

public class Hex extends ArrayList<Byte>
{


    public Hex(String h) throws WrongByteLength
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

}