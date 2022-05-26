public class Byte
{
    private final String b;


    public Byte(String b)
    {
        if (b.length() > 2 || b.isEmpty())
            throw new WrongByteLength();

        //Always give bytes that are 2 characters long
        if (b.length() == 1)
            this.b = "0" + b;
        else
            this.b = b;
    }

    public Byte first() {
        return new Byte(this.b.substring(0, 1));
    }

    public Byte last() {
        return new Byte(this.b.substring(1, 2));
    }

    public int toDec() { return Integer.parseInt(b,16); }

    public String toString() { return this.b; }
}


class WrongByteLength extends Error {
    public WrongByteLength() {
        super("This byte is either too long, or too short");
    }
}