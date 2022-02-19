package org.dpq.webservices.messenger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/msgs")
@Produces({MediaType.APPLICATION_JSON , MediaType.TEXT_PLAIN})
@Consumes(MediaType.TEXT_PLAIN)
@Singleton
public class MyResource {
   
   private Map<Long,String> messages = new HashMap<>();

   public MyResource() {
	   System.out.println("Instance created...");
	   messages.put(1L,"I am fine");
	   messages.put(2L,"How are you");
	   messages.put(3L,"Hope you are doing well");
   }
	
	
    @GET
    @Path("/{messageId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(@PathParam("messageId") long id) {
    	
        return messages.get(id);
    }
    
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long id) {
    	messages.remove(id);
    	System.out.println("messages:: "+messages);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllMessages() {
    	return Arrays.toString(messages.values().stream().toArray(String [] :: new));
    }
    
    @GET
    @Path("/dummy")
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        return "Dummy message";
    }
    
    @PUT
    @Path("/{messageId}")
    public void updateMessage(@PathParam("messageId") long id, String message) {
    	if(messages.containsKey(id)) {
    		messages.put(id, message);
    		System.out.println("updated message and message was "+message);
    	}else {
    		System.out.println("id not found to update message");
    	}
    }
    
    @POST
    public void addMessage(String message) {
    	messages.put(Long.valueOf(messages.size())+1, message);
    }
    
    
}
