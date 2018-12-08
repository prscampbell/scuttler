package eu.scuttlercup;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Scuttler 
{

	private static final OkHttpClient client = new OkHttpClient();
	private static final Gson gson = new Gson();
	
	public static Gson gson() {
		return gson;
	}
	
	public static Optional<Response> execute(String endpoint) 
	{
		try
		{
			String apiKey = System.getenv("RIOT_API_KEY");
			String url = System.getenv("RIOT_REGION_BASE_URL");
			
			if(apiKey == null) {
				System.out.println("No RIOT_API_KEY environment variable set. Not executing command");
				return Optional.empty();
			}
			
			if(url == null) {
				System.out.println("No RIOT_REGION_BASE_URL environment variable set. Not executing command");
				return Optional.empty();
			}
			
			url += endpoint;
			
			Request request = new Request.Builder()
									.get()
									.header("X-Riot-Token", apiKey)
									.header("Content-Type", "application/json")
									.url(url)
									.build(); 
			
			return Optional.of(client.newCall(request).execute());
		}
		catch (IOException ex)
		{
			System.out.println("Error making request to: " + endpoint);
			ex.printStackTrace();
			return Optional.empty();
		}
	}
	
}
