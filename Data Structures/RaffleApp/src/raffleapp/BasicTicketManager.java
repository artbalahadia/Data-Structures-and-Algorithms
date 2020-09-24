package raffleapp;

import java.io.PrintStream;
import java.util.*;


/**
 * @author Tony
 */
public class BasicTicketManager implements TicketManager
{
	/*
	 * tickets (TreeSet) - contains all IDs with their unique entries
	 * duplicates (ArrayList) - contains duplicate entries for each ID
	 * ticketMap (HashMap) - contains ID and all its entries (from "tickets" set and "duplicates" arraylist)
	 */
	private Set<Ticket> tickets = new TreeSet<Ticket>();
	private List<Ticket> duplicates = new ArrayList<Ticket>();
	private Map<Byte, List<Ticket>> ticketMap = new HashMap<Byte, List<Ticket>>();

    @Override
    public int numberOfEntries()
    {
        // return size of List
    	return tickets.size();
    	
    }

    @Override
    public void add(Ticket ticket)
    {
    	// adds a list for a ticket if it does not exist yet in our map
    	List<Ticket> ticketsForThisId = ticketMap.get(ticket.getNumber());
    	if(ticketsForThisId == null) {
    		ticketsForThisId = new ArrayList<Ticket>();
    		ticketMap.put(ticket.getNumber(), ticketsForThisId);
    	}
    	ticketsForThisId.add(ticket);
    	
    	
    	// if a ticket is already in the set "tickets", it will be sent to a duplicate list "duplicates"
    	if(!tickets.add(ticket)) {
    		duplicates.add(ticket);
    	}
    }

    @Override
    public Ticket pick()
    {
        
         /*
          *  returns a random ticket from the list (bounds are the number of entries).
          *  selected ticket will be removed from list 
          */
    	Random random = new Random();
    	int numberSelected = random.nextInt(numberOfEntries());
    	int count = 0;
    	Ticket selected = null;
    	for(Ticket t : tickets) {
    		if(count >= numberSelected) {
    			selected = t;
    			break;
    		}
    		count++;
    	}
    	tickets.remove(selected);
    	// prints the ID's entry as well as duplicate entries
    	System.out.print(getIdList(selected.getNumber()));
    	return selected;
    }

    @Override
    public void logAllUnpickedEntries(PrintStream out)
    {
        // prints "tickets" and "duplicates" list
    	out.println("ENTRIES:");
    	tickets.forEach(ticket -> out.println(ticket));
    	
    	out.println("DUPLICATES:");
    	duplicates.forEach(ticket -> out.println(ticket));
    }
    
    
    // method to return a list of picks from a specific ID
    public List<Ticket> getIdList(byte ticketID)
    {
    	return ticketMap.get(ticketID);
    	
//    	// algorithmic method to search entries for an ID
//    	List<Ticket> idEntryList = new ArrayList();
//    	// looping through duplicate list
//    	for(Ticket id : duplicates) {
//    		if(id.getNumber() == ticketID) {
//    			idEntryList.add(id);
//    		}
//    	}
//    	// looping through main list
//    	for(Ticket id : tickets) {
//    		if(id.getNumber() == ticketID) {
//    			idEntryList.add(id);
//    		}
//    	}
//    	return idEntryList;
    	
    }

}
