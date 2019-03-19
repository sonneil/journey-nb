package rs.services;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class CandidateServices {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
        @Path("candidate")
	public String candidate() {
		return "Nelson Benitez";
	}
        
        @Path("compress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public Response compress(JsonObject msg) {
		String strVal = msg.getString("value");
		String compressedStr = compressString(strVal);
		 
           JsonObject responseBody = Json.createObjectBuilder()
                                    .add("compressed", compressedStr).build();    
                
	    return Response
	      .status(Response.Status.OK)
	      .entity(responseBody)
	      .build();
	}
	
	private String compressString(String strVal) {
		String res = "";
		if (strVal==null || strVal.isEmpty()) return res;
		strVal = strVal.toUpperCase();
		char firstChar = strVal.charAt(0);
		int counter = 0;
		for (int i=0;i<strVal.length();i++) {
                    if (strVal.charAt(i) == firstChar) {
                        counter++;
                    } else {
                        res += counter + "" + firstChar;
                        firstChar = strVal.charAt(i);
                        counter = 1;
                    }
		}
		res += counter + "" + firstChar;
		return res;
	}
}