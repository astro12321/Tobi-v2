public class Custom extends Application
{
    private final Hex hex;


    @Override
    public Hex getHex() { return this.hex; }


    public Custom(Hex hex) {
        this.hex = hex;
    }

}
