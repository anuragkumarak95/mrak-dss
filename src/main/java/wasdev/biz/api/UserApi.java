package wasdev.biz.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;

import wasdev.biz.conn.*;
import wasdev.biz.model.*;

@ApplicationPath("api")
@Path("/user")
public class UserApi extends Application{
  MyCloudantClient<User> conn = EntityFactory.getUserInstance();

  @POST
  @Path("/")
  @Produces("application/json")
  @Consumes("application/json")
  public String addUsers(User u){
    if (conn == null) return "Connection not established.";

    return new Gson().toJson(conn.persist(u));

  }

  @GET
  @Path("/")
  @Produces("application/json")
  public Collection<User> getAllUsers(){
    if (conn == null) return null;

    return conn.getAll();
  }

  @GET
  @Path("/{_id}")
  @Produces("application/json")
  public String getUserby_id(@PathParam("_id") String _id){
    return new Gson().toJson(conn.get(_id));
  }

  @DELETE
  @Path("/{_id}")
  @Produces("application/text")
  public String deleteUserby_id(@PathParam("_id") String _id){

    conn.delete(_id);
    return "User Deleted.";
  }

  @PUT
  @Path("/{_id}")
  @Consumes("application/json")
  @Produces("application/json")
  public String updateUserbyId(@PathParam("_id") String _id,User user){
    conn.update(_id,user);
    return "{\"status\":\"OK\",\"task\":\"USER UPDATE\"}";
  }
  
  @POST
  @Path("/uLogin")
  @Produces("application/text")
  public String loginUser(@FormParam("userId")String userId,@FormParam("userPass") String userPass  ){
	  Collection<User> uList = getAllUsers();
	  if(uList==null) return "Connection not extablished";
	  for(User u : uList){
		  if(u.getUserId().equals(userId) && u.getUserPass().equals(userPass)) return u.get_id();
	  }
	  return "no such user found.";
  }
  
  
}
