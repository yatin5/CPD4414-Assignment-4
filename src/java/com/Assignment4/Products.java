/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Assignment4;
import static com.Assignment4.database.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author HP
 */
@Path("product")
public class Products {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Products
     */
    public Products() {
    }

    /**
     * Retrieves representation of an instance of com.Assignment4.Products
     * @return an instance of java.lang.String
     * @throws java.sql.SQLException
     */
    @GET
    @Produces("application/json")
    public String getJson() throws SQLException {
        //TODO return proper representation object
       // throw new UnsupportedOperationException();
       Connection conn = getConnection();
       String query = "select * from product";
       Statement s = conn.createStatement();
       ResultSet result = s.executeQuery(query);
       
       if(conn != null){
          return "connection etablished";
       }
       return "NO connection etablished";
       
        
    }

    /**
     * PUT method for updating or creating an instance of Products
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
