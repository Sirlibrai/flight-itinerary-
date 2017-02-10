package org.jgrapht.demo;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.jgrapht.*;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;



public class AdvancedFlights 
{

    private AdvancedFlights(){}
    
    public static void main(String [] args)
    {        
        // create a graph 
        SimpleDirectedWeightedGraph<String, Flight> stringGraph = createFlightGraph();
        

        // note directed edges are printed as: (<v1>,<v2>)
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the departure airport");
        String departure = s.nextLine();
        //prevents user input error
        while(!stringGraph.containsVertex(departure))
        {
            System.err.println("Please enter an existing airport.");
            departure = s.nextLine();
        }
        System.out.println("Please enter the destination airport");
        String destination = s.nextLine();
        while(!stringGraph.containsVertex(destination))
        {
            System.err.println("Please enter an existing airport.");
            destination = s.nextLine();
        }

        ShortestPath(departure, destination, stringGraph);
        
    }
    
    private static SimpleDirectedWeightedGraph<String, Flight> createFlightGraph()
    {
        SimpleDirectedWeightedGraph<String, Flight> airport =
                new SimpleDirectedWeightedGraph<String, Flight>(Flight.class);
        
        String [] destinations = new String []{
                   "Edinburgh","Heathrow", "Amsterdam", "Boston", "Chicago", "Montreal", "Toronto", "Shanghai", "Hong Kong"
                   ,"New Delhi"};
          //Prints out airports used
          System.out.println("The following airports are used.");
               for (String s : destinations)
               {
                   System.out.println(". " + s);
               }
            for (int i = 0; i<10 ;i ++){
            airport.addVertex(destinations[i]);
            }
        
        //add the edges to create linking structure
    /********************************************
     * Part C: Additional Flight Information    *
     ********************************************/
            
        Flight BA115 = new Flight("BA115", "13:30", "14:30", 1, 110);
        airport.addEdge("Edinburgh", "Heathrow", BA115);
        airport.setEdgeWeight(BA115, 110);
        Flight BA455 = new Flight("BA455", "17:45", "18:45", 1, 110);
        airport.addEdge("Heathrow", "Edinburgh", BA455);
        airport.setEdgeWeight(BA455, 110);
        
        Flight BA111 = new Flight("BA111", "08:00", "09:00", 1, 100);
        airport.addEdge("Heathrow", "Amsterdam", BA111);
        airport.setEdgeWeight(BA111, 100);
        Flight BA712 = new Flight("BA712", "20:00", "21:00", 1, 100);
        airport.addEdge("Amsterdam", "Heathrow", BA712);
        airport.setEdgeWeight(BA712, 100);
        
        Flight BA145 = new Flight("BA145", "15:00", "22:00", 7, 230);
        airport.addEdge("Heathrow", "Boston", BA145);
        airport.setEdgeWeight(BA145, 230);
        Flight BA541 = new Flight("BA541", "10:00", "17:00", 7, 230);
        airport.addEdge("Boston", "Heathrow",BA541);
        airport.setEdgeWeight(BA541, 230);
        
        Flight DA178 = new Flight("DA178", "08:00", "12:00", 4, 150);
        airport.addEdge("Boston", "Chicago", DA178);
        airport.setEdgeWeight(DA178, 150);
        Flight DA817 = new Flight("DA817", "11:15", "15:15", 4, 150);
        airport.addEdge("Chicago", "Boston", DA817);
        airport.setEdgeWeight(DA817, 150);
        
        Flight AA100 = new Flight("AA100", "16:00", "19:00", 3, 100);
        airport.addEdge("Boston", "Montreal", AA100);
        airport.setEdgeWeight(AA100, 100);
        Flight AA001 = new Flight("AA001", "06:00", "10:00", 3, 100);
        airport.addEdge("Montreal", "Boston", AA001);
        airport.setEdgeWeight(AA001, 100);
        
        Flight CA000 = new Flight("CA000", "11:00", "12:00", 1, 90);
        airport.addEdge("Montreal", "Toronto", CA000);
        airport.setEdgeWeight(CA000, 90);
        Flight CA360 = new Flight("CA360", "09:00", "10:00", 1, 90);
        airport.addEdge("Toronto", "Montreal", CA360);
        airport.setEdgeWeight(CA360, 90);
        
        Flight BA112 = new Flight("BA112", "07:45", "18:45", 11, 560);
        airport.addEdge("Edinburgh", "Chicago", BA112);
        airport.setEdgeWeight(BA112, 560);
        Flight BA217 = new Flight("BA217", "17:15", "06:15", 11, 560);
        airport.addEdge("Chicago", "Edinburgh", BA217);
        airport.setEdgeWeight(BA217, 560);
        
        Flight AI505 = new Flight("AI505", "02:25", "07:25", 5, 430);
        airport.addEdge("New Delhi", "Shanghai", AI505);
        airport.setEdgeWeight(AI505, 430);
        Flight AI506 = new Flight("AI506", "11:55", "16:55", 5, 430);
        airport.addEdge("Shanghai", "New Delhi", AI506);
        airport.setEdgeWeight(AI506, 430);
        
        Flight AC450 = new Flight("AC450", "21:00", "03:00", 6, 230);
        airport.addEdge("New Delhi", "Hong Kong", AC450);
        airport.setEdgeWeight(AC450, 230);
        Flight AC054 = new Flight("AC054", "18:00", "00:00", 6, 230);
        airport.addEdge("Hong Kong", "New Delhi", AC054);
        airport.setEdgeWeight(AC054, 230);
        
        return airport;
    }
/***********************************************
 *			Part D: Itinerary				   *
 *			Part E: Itinerary Duration		   *
 *			Part F: Alternative Extensions	   *
 ***********************************************/
    public static void ShortestPath(String departure, String destination, Graph<String, Flight> a)
    {
        DijkstraShortestPath<String, Flight> p = new DijkstraShortestPath<String, Flight>(a, departure, destination);
        
        List<Flight> h = new ArrayList<Flight>();
        h = p.getPathEdgeList();
        if( h == null){
        	System.out.println("Sorry, No flights to destination not available");
        }
        else{
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %n", "Leave", "FlightNumber", "Departure", "Arrival", "Destination");	
        
        int duration = 0;
        Iterator<Flight> it = h.listIterator();
        
        int i = 0;        
        
        for(i=0; i<h.size();i++)
        {
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %n", a.getEdgeSource(h.get(i)), h.get(i).getFlightNumber(), h.get(i).getDepartureTime(), h.get(i).getArrivalTime(), a.getEdgeTarget(h.get(i)));
            duration += h.get(i).getDuration();
        }
        
        System.out.println();
        System.out.println("The total cost of this journey is: £" + p.getPathLength());
        System.out.println("The total duration of your flights is: " + duration + " hours.");	
        }
        
        }
}   
     


















