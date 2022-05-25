public class Byte
{
    private final String b;


    public Byte(String b) throws WrongByteLength
    {
        if (b.length() > 2 || b.isEmpty())
            throw new WrongByteLength();

        //Always give bytes that are 2 characters long
        if (b.length() == 1)
            this.b = "0" + b;
        else
            this.b = b;
    }

    public Byte first() throws WrongByteLength {
        return new Byte(this.b.substring(0, 1));
    }

    public Byte last() throws WrongByteLength {
        return new Byte(this.b.substring(1, 2));
    }

    public String toString() { return this.b; }
}


class WrongByteLength extends Exception {
    public WrongByteLength() {
        super("This byte is either too long, or too short");
    }
}