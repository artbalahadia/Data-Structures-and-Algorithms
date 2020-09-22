package raffleapp;

/**
 * @author Tony
 */
public class Ticket implements Comparable<Ticket>
{
    private byte number;
    private String name;

    @Override
    public int compareTo(Ticket o)
    {
        return number - o.number;
    }

    
    public Ticket(byte number, String name)
    {
        this.number = number;
        this.name = name;
    }

    public byte getNumber()
    {
        return number;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return number + ": " + name;
    }

}
