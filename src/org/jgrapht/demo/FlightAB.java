package org.jgrapht.demo;
import org.jgrapht.*;
import org.jgrapht.alg.*;
import org.jgrapht.graph.*;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/***************************************************
 * Part A: Representing Direct Flights 
 * *************************************************/
    public class FlightAB {
    	
    	//Creates the flight graph
        public static SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>	createStringGraph(){ 
        	
        	SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> airport = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
        	
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

            DefaultWeightedEdge X01 = airport.addEdge("Edinburgh", "Heathrow");
            				airport.setEdgeWeight(X01, 110);
            DefaultWeightedEdge X11 = airport.addEdge("Heathrow", "Edinburgh");
            				airport.setEdgeWeight(X11, 110);

            DefaultWeightedEdge X02= airport.addEdge("Heathrow", "Amsterdam");
            				airport.setEdgeWeight(X02, 100);
            DefaultWeightedEdge X12 = airport.addEdge("Amsterdam", "Heathrow");
            				airport.setEdgeWeight(X12, 100);

            DefaultWeightedEdge X03 = airport.addEdge("Heathrow", "Boston");
            				airport.setEdgeWeight(X03, 230);
            DefaultWeightedEdge X13 = airport.addEdge("Boston", "Heathrow");
            				airport.setEdgeWeight(X13, 230);

            DefaultWeightedEdge X04 = airport.addEdge("Boston", "Chicago");
            				airport.setEdgeWeight(X04, 150);
            DefaultWeightedEdge X14 = airport.addEdge("Chicago", "Boston");
            				airport.setEdgeWeight(X14, 150);

            DefaultWeightedEdge X05 = airport.addEdge("Boston", "Montreal");
            				airport.setEdgeWeight(X05, 100);
            DefaultWeightedEdge X15 = airport.addEdge("Montreal", "Boston");
            				airport.setEdgeWeight(X15, 100);

            DefaultWeightedEdge X06 = airport.addEdge("Montreal", "Toronto");
            				airport.setEdgeWeight(X06, 90);
            DefaultWeightedEdge X16 = airport.addEdge("Toronto", "Montreal");
            				airport.setEdgeWeight(X16, 90);

            DefaultWeightedEdge X07 = airport.addEdge("Edinburgh", "Chicago");
            				airport.setEdgeWeight(X07, 560);
            DefaultWeightedEdge X17 = airport.addEdge("Chicago", "Edinburgh");
            				airport.setEdgeWeight(X17, 560);

            DefaultWeightedEdge X08 = airport.addEdge("New Delhi", "Shanghai");
            				airport.setEdgeWeight(X08, 430);
            DefaultWeightedEdge X18 = airport.addEdge("Shanghai", "New Delhi");
            				airport.setEdgeWeight(X18, 430);

            DefaultWeightedEdge X09 = airport.addEdge("Shanghai", "Hong Kong");
            				airport.setEdgeWeight(X09, 230);
            DefaultWeightedEdge X19 = airport.addEdge("Hong Kong", "Shanghai");
            				airport.setEdgeWeight(X19, 230); 
         //Commented out to run Part B 
        /** System.out.println("Available Flights");				
            Iterable<DefaultWeightedEdge> plan;
            plan = airport.edgeSet();
            Iterator<DefaultWeightedEdge> flightPlan = plan.iterator();
            	            					
            while (flightPlan.hasNext()){
            	System.out.println(flightPlan.next());
            }
            	**/         
            	return airport;
        }     

/***************************************************** 
 * Part B: Least Cost Connections 
 ****************************************************/        
     public static void main(String [] args) { 
    	   SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>stringGraph = createStringGraph(); 
    	   
           //Request and receive user input for airports
           Scanner S = new Scanner(System.in);
           System.out.println("Please enter the start airport");
           String departure = S.nextLine();
           System.out.println("Please enter the destination airport");
           String destination = S.nextLine();
           ShortestPath(departure, destination, stringGraph);
        }
     // Shortest path and least cost connections of flights
     private static void ShortestPath(String departure, String destination, SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> stringGraph) {
  	   List<DefaultWeightedEdge> cheapestPath = DijkstraShortestPath.findPathBetween(stringGraph, departure, destination);
  	   DijkstraShortestPath p = new DijkstraShortestPath(stringGraph, departure, destination);
         if (cheapestPath == null){
         	System.out.println("Sorry, Flight not available");
         	}
         else    
         	{
      	   System.out.println("Shortest (i.e. cheapest) path:" + cheapestPath);
             System.out.println("Cost of shortest (i.e. cheapest) path = £" + p.getPathLength()); 
        } 

     }     
   }