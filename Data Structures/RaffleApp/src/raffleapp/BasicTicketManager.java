package raffleapp;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Tony
 */
public class BasicTicketManager implements TicketManager
{
	
	List<Ticket> tickets = new ArrayList<Ticket>();

    @Override
    public int numberOfEntries()
    {
        // return size of List
    	return tickets.size();
    	
    }

    @Override
    public void add(Ticket ticket)
    {
        // method to add ticket to list
    	tickets.add(ticket);
    }

    @Override
    public Ticket pick()
    {
        /*
         * uses remove to return a random ticket from the list
         */
    	Random random = new Random();
    	return tickets.remove(random.nextInt(numberOfEntries()));
    }

    @Override
    public void logAllUnpickedEntries(PrintStream out)
    {
        // print for each ticket
    	tickets.forEach(ticket -> out.println(ticket));
    }

}
