package raffleapp;

import java.io.PrintStream;

/**
 *
 * @author Tony
 */
public interface TicketManager
{
    /**
     * @return The number of tickets eligible to win
     */
    int numberOfEntries();
    
    /**
     * Add a ticket to the raffle
     * @param ticket 
     */
    void add(Ticket ticket);
    
    /**
     * Randomly select one of the the entries to win
     * (The winner is removed from future picks. 
     *  You must add the ticket a second time to be eligible
     *  for additional picks.)
     * @return the winning ticket
     */
    Ticket pick();
    
    /**
     * Print out the full details of every ticket 
     * submitted to and remaining in the raffle.
     * @param out the destination for logging
     */
    void logAllUnpickedEntries(PrintStream out);
}