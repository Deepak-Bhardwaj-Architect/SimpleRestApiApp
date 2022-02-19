package org.dpq.webservices.resources;

import java.util.HashMap;
import java.util.Map;

import jakarta.inject.Singleton;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/msgs")
//@Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.TEXT_PLAIN)
    public String [] getAllMessages() {
    	return messages.values().stream().toArray(String [] :: new);
    }
    
    @GET
    @Path("/dummy")
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        return "Dummy message";
    }
}
