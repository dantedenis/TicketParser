package ticketParse;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main
{

	public static void main(String[] args)
	{
		try
		{
			ArrayList<Long> timesArray = new ArrayList<>();
			
			Gson gson = new Gson();
			String json = FileUtils.readFileToString(new File(args[0]), StandardCharsets.UTF_8);
			TicketList tickets = gson.fromJson(json, TicketList.class);
			
			tickets.getTickets().forEach(element -> timesArray.add(element.getMinArrival()));
			
			printResult(timesArray, 90);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void printResult(ArrayList<Long> array, int percentile)
	{
		int indexProc = (int) Math.ceil(percentile / 100.0 * array.size());
			
		System.out.println("Average flight time: " +
							array.stream().collect(Collectors.summarizingLong(Long::longValue)).getAverage()
							+ " (min)\n" +
							"90th percentile of time: " +
							array.stream().sorted().skip(indexProc - 1).findFirst().get()
							+ " (min)");
	}
}

