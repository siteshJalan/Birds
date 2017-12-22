package com.sample.test.rest;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sample.test.common.Bird;
import com.sample.test.common.BirdService;
import com.sample.test.impl.mongo.BirdServiceMongoImpl;

@Path("/")
public class BirdsRestService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewBird(Bird b){
		
		if(b.getAdded()==null || b.getAdded().isEmpty()){
			b.setAdded(getCurrentDate());
		}
		if(b.getVisible()==null){
			b.setVisible(false);
		}
		//String postString="post Called";
		BirdService bs= BirdServiceMongoImpl.getInstance();
		String postString=bs.addBird(b);
		return Response.status(200).entity(postString).build();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBirdDetails(@PathParam("id") String id){
		String birdID=id;
		BirdService bs= BirdServiceMongoImpl.getInstance();
		String b= bs.getBirdDetails(birdID);
		
		if(b==null){
			Response.status(404).entity(b).build();
		}
		return Response.status(200).entity(b).build();
		
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBirds(@PathParam("id") String id){
		String birdID=id;
		BirdService bs= BirdServiceMongoImpl.getInstance();
		boolean removed= bs.removeBird(birdID);
		
		if(!removed){
			return Response.status(404).build();
		}
		return Response.status(200).build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBirdList(){
		System.out.println("GET Birds called");
		BirdService bs= BirdServiceMongoImpl.getInstance();
		return Response.status(200).entity(bs.getBirdList()).build();
	}
	
	private String getCurrentDate(){
		final Date date = new Date();
		final String ISO_FORMAT = "yyyy-MM-dd";
		final SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT);
		final TimeZone utc = TimeZone.getTimeZone("UTC");
		sdf.setTimeZone(utc);
		System.out.println(sdf.format(date));
		return sdf.format(date);
	}
	
}
