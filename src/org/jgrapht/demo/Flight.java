package org.jgrapht.demo;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Flight extends DefaultWeightedEdge 
{
  private String departure, destination, flightNumber;
  private String departureTime, arrivalTime;
  private int duration;
  
  public Flight()
  {
	  
  }
  public Flight (String flightNumber, String departureTime, String arrivalTime, int duration, int ticketPrice)
  {
    super();
    this.flightNumber = flightNumber;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.duration = duration;

  }

  public String getDeparture()
  {
    return departure;
  }
  
  public String getDestination()
  {
    return destination;
  }

  public String getFlightNumber()
  {
    return flightNumber;
  }

  public String getDepartureTime()
  {
    return departureTime;
  }

  public String getArrivalTime()
  {
    return arrivalTime;
  }

  public int getDuration()
  {
    return duration;
  }

}

