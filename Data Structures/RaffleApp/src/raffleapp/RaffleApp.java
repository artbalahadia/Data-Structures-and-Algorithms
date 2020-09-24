package raffleapp;

import java.util.Random;

/**
 *
 * @author Tony
 */
public class RaffleApp
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        final int NUMBER_OF_ENTRIES = 20;
        final int MAX_ID            = 10;//Byte.MAX_VALUE;
        Random idGenerator = new Random();

        TicketManager manager = new BasicTicketManager();

        // Add randomly generated entries to the TicketManager
        for (short i = 0; i < NUMBER_OF_ENTRIES; i++)
        { 
        	manager.add(new Ticket((byte) idGenerator.nextInt(MAX_ID), "Entry " + i));
        }

        // Show all of the entries
        System.out.println("Number of Entries " + manager.numberOfEntries());
        manager.logAllUnpickedEntries(System.out);
        System.out.println("..........\nPick tickets so long as there are entries");
        while (manager.numberOfEntries() > 0)
        {
            System.out.println("Picked: " + manager.pick());
        }
        System.out.println("..........\nAll entries picked? "+ manager.numberOfEntries() + "\n.......");
        manager.logAllUnpickedEntries(System.out);
    }
}