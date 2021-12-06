package ticketParse;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketList {
	private List<Ticket> tickets;

	public TicketList(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	class Ticket {
		private String origin;
		private String origin_name;
		private String destination;
		private String destination_name;
		private String departure_date;
		private String departure_time;
		private String arrival_date;
		private String arrival_time;
		private String carrier;
		private int stops;
		private int price;

		public Ticket(String origin, String origin_name, String destination, String destination_name, String departure_date, String departure_time, String arrival_date, String arrival_time, String carrier, int stops, int price) {
			this.origin = origin;
			this.origin_name = origin_name;
			this.destination = destination;
			this.destination_name = destination_name;
			this.departure_date = departure_date;
			this.departure_time = departure_time;
			this.arrival_date = arrival_date;
			this.arrival_time = arrival_time;
			this.carrier = carrier;
			this.stops = stops;
			this.price = price;
		}

		public String getOrigin() {
			return origin;
		}

		public String getOrigin_name() {
			return origin_name;
		}

		public String getDestination() {
			return destination;
		}

		public String getDestination_name() {
			return destination_name;
		}

		public String getDeparture_date() {
			return departure_date;
		}

		public String getDeparture_time() {
			return departure_time;
		}

		public String getArrival_date() {
			return arrival_date;
		}

		public String getArrival_time() {
			return arrival_time;
		}

		public String getCarrier() {
			return carrier;
		}

		public int getStops() {
			return stops;
		}

		public int getPrice() {
			return price;
		}
		public Long getMinArrival(){
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
			
			LocalDateTime timeArrival = LocalDateTime.parse(getArrival_date() + " " + getArrival_time(), dtf);
			ZonedDateTime timeZoneArriv = timeArrival.atZone(ZoneId.of("GMT+2"));
			
			LocalDateTime timeDeparture = LocalDateTime.parse(getDeparture_date() + " " + getDeparture_time(), dtf);
			ZonedDateTime timeZoneDeaprt = timeDeparture.atZone(ZoneId.of("GMT+10"));

			return Duration.between(timeZoneDeaprt, timeZoneArriv).toMinutes();
		}
	}
}
