package es.uma.rysd.app;

import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.Gson;

import es.uma.rysd.entities.*;

public class SWClient {
	// TODO: Fill in the name of the application
    private final String app_name = "RodrigoWars";
    private final int year = 2022;
    
    private final String url_api = "https://swapi.dev/api/";

    // Auxiliary methods provided
    
    // Gets the URL of the resource id of type resource
	public String generateEndpoint(String resource, Integer id){
		return url_api + resource + "/" + id + "/";
	}
	
	// Given a URL of a resource get its ID
	public Integer getIDFromURL(String url){
		String[] parts = url.split("/");

		return Integer.parseInt(parts[parts.length-1]);
	}
	
	// Queries a resource and returns how many elements it has
	public int getNumberOfResources(String resource) {
		// TODO: Deal appropriately with possible exceptions that may occur.

		int res = 0;

		try {

			// TODO: Create the corresponding URL: https://swapi.dev/api/{resource}/ by replacing the resource by the parameter

			StringBuilder url = new StringBuilder(url_api + resource);

			// TODO: Create the connection from the URL

			URL service = new URL(url.toString());

			// TODO: Add the User-Agent and Accept headers (see the statement)

			HttpsURLConnection connection = (HttpsURLConnection) service.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name);

			// TODO: Indicate that it is a GET request

			connection.setRequestMethod("GET");

			// TODO: Check that the code received in the reply is correct.

			int check = connection.getResponseCode();

			if(check / 100 != 2) {
				System.err.println("Reply is not correct");
				return -1;
			}

			// TODO: Deserialise the response to ResourceCountResponse

			Gson parser = new Gson();
			InputStream in = connection.getInputStream(); // TODO: Get the InputStream of the connection
			ResourceCountResponse c = parser.fromJson(new InputStreamReader(in), ResourceCountResponse.class);
			res = c.count;
			connection.disconnect();
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return res;
	}
	
	public Person getPerson(String urlname) {
    	Person p = null;
    	// In case it comes as http we switch it to https.
    	urlname = urlname.replaceAll("http:", "https:");

    	// TODO: Deal appropriately with possible exceptions that may occur.

		try {

			// TODO: Create the connection from the received URL

			URL service = new URL(urlname.toString());

			// TODO: Add User-Agent and Accept headers (see statement)

			HttpsURLConnection connection = (HttpsURLConnection) service.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name);

			// TODO: Indicate that it is a GET request

			connection.setRequestMethod("GET");

			// TODO: Check that the code received in the reply is correct.

			int check = connection.getResponseCode();

			if(check / 100 != 2) {
				System.err.println("Reply is not correct");
				return p;
			}

			// TODO: Deserialise the response to Person

			Gson parser = new Gson();
			InputStream in = connection.getInputStream(); // TODO: Get the InputStream of the connection
			p = parser.fromJson(new InputStreamReader(in), Person.class);

			// TODO: For exercises 2 and 3 (you don't need to complete this for exercise 1)

			p.homeplanet = getPlanet(p.homeworld);
			p.homeworld = p.homeplanet.name;

			// TODO: From the URL in the homreworld field get the planet data and store it in the homeplanet attribute.
			connection.disconnect();
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
    	return p;
	}

	public Planet getPlanet(String urlname) {
    	Planet p = null;
    	// In case it comes as http we switch it to https.
    	urlname = urlname.replaceAll("http:", "https:");

    	// TODO: Deal appropriately with possible exceptions that may occur.

		try {

			// TODO: Create the connection from the received URL

			URL service = new URL(urlname.toString());

			// TODO: Add User-Agent and Accept headers (see statement)

			HttpsURLConnection connection = (HttpsURLConnection) service.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name);

			// TODO: Indicate that it is a GET request

			connection.setRequestMethod("GET");

			// TODO: Check that the code received in the reply is correct.

			int check = connection.getResponseCode();

			if(check / 100 != 2) {
				System.err.println("Reply is not correct");
				return p;
			}

			// TODO: Deserialise the response to Planet

			Gson parser = new Gson();
			InputStream in = connection.getInputStream(); // TODO: Get the InputStream of the connection
			p = parser.fromJson(new InputStreamReader(in), Planet.class);
			connection.disconnect();
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
        return p;
	}

	public Person search(String name) {
    	Person p = null;

    	// TODO: Deal appropriately with possible exceptions that may occur.

		try {

			// TODO: Create the connection from the URL (url_api + treated name - see statement)

			String name2 = url_api + "people/?search=" + URLEncoder.encode(name, "utf-8");
			URL service = new URL(name2.toString());

			// TODO: Add User-Agent and Accept headers (see statement)

			HttpsURLConnection connection = (HttpsURLConnection) service.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name);

			// TODO: Indicate that it's a GET request

			connection.setRequestMethod("GET");

			// TODO: Check that the code received in the reply is correct

			int check = connection.getResponseCode();

			if(check / 100 != 2) {
				System.err.println("Reply is not correct");
				return p;
			}

			// TODO: Deserialise the response to SearchResponse -> Use the first position of the array as the result

			Gson parser = new Gson();
			InputStream in = connection.getInputStream(); // TODO: Get the InputStream of the connection
			SearchResponse aux = parser.fromJson(new InputStreamReader(in), SearchResponse.class);

			// TODO: For exercises 2 and 3 (you don't need to complete this for exercise 1)

			p = aux.results[0];

			// TODO: From the URL in the homreworld field get the planet data and store it in homeplanet attribute

			p.homeplanet = getPlanet(p.homeworld);
			connection.disconnect();
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
        return p;
    }

	public Film getFilm(String urlname) {
		Film p = null;
		// In case it comes as http we switch it to https.
		urlname = urlname.replaceAll("http:", "https:");

		// TODO: Deal appropriately with possible exceptions that may occur.

		try {

			// TODO: Create the connection from the received URL

			URL service = new URL(urlname.toString());

			// TODO: Add User-Agent and Accept headers (see statement)

			HttpsURLConnection connection = (HttpsURLConnection) service.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name);

			// TODO: Indicate that it is a GET request

			connection.setRequestMethod("GET");

			// TODO: Check that the code received in the reply is correct.

			int check = connection.getResponseCode();

			if(check / 100 != 2) {
				System.err.println("Reply is not correct");
				return p;
			}

			// TODO: Deserialise the response to Planet

			Gson parser = new Gson();
			InputStream in = connection.getInputStream(); // TODO: Get the InputStream of the connection
			p = parser.fromJson(new InputStreamReader(in), Film.class);
			connection.disconnect();
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return p;
	}

	public Film searchFilm(String name) {
		Film p = null;

		// TODO: Deal appropriately with possible exceptions that may occur.

		try {

			// TODO: Create the connection from the URL (url_api + treated name - see statement)

			URL service = new URL(name.toString());

			// TODO: Add User-Agent and Accept headers (see statement)

			HttpsURLConnection connection = (HttpsURLConnection) service.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name);

			// TODO: Indicate that it's a GET request

			connection.setRequestMethod("GET");

			// TODO: Check that the code received in the reply is correct

			int check = connection.getResponseCode();

			if(check / 100 != 2) {
				System.err.println("Reply is not correct");
				return p;
			}

			// TODO: Deserialise the response to SearchResponse -> Use the first position of the array as the result

			Gson parser = new Gson();
			InputStream in = connection.getInputStream(); // TODO: Get the InputStream of the connection
			p = parser.fromJson(new InputStreamReader(in), Film.class);
			connection.disconnect();
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return p;
	}
}
