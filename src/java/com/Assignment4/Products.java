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
import java.util.ArrayList;
import javax.json.JsonObject;
import javax.persistence.Id;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;
/**
 * REST Web Service
 *
 * @author HP
 */
@Path("/")
public class Products {
   Products items = new Products();
    ArrayList<Products> product = new ArrayList<>();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Products
     */
    public Products() {
        
    }

    private Products(int aInt, String string, String string0, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Retrieves representation of an instance of com.Assignment4.Products
     * @return an instance of java.lang.String
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Products> getXml() throws SQLException {
        //TODO return proper representation object
       // throw new UnsupportedOperationException();
       Connection conn = getConnection();
       String query = "select * from product";
       Statement s = conn.createStatement();
       ResultSet result = s.executeQuery(query);
       
       while(result.next()){
           Products items = new Products(result.getInt("ProductId"), result.getString("name"), result.getString("description"), result.getInt("quantity"));
           product.add(items);
       }
        
       return product;
       }
    
    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Products> oneProduct(@PathParam("id") int id) throws SQLException
    {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select * from product where ProductID="+id);

      
        while (rs.next()) {

            Products pro = new Products(rs.getInt("ProductID"), rs.getString("name"), rs.getString("description"), rs.getInt("quantity"));
            product.add(pro);
        }

        return product;
    }
   @POST
   @Path("/product")
   @Consumes(MediaType.APPLICATION_JSON)
   public void createProducts(String str) throws ParseException, SQLException{
     
       JSONParser jparser = new JSONParser();
       JsonObject jobject = (JsonObject) jparser.parse(str);

       Object ID = jobject.get("id");
       String ProductID = ID.toString();
       int iD = Integer.parseInt(ProductID);
       
       Object Name = jobject.get("name");
       String name = Name.toString();
       
       Object Description = jobject.get("description");
       String description = Description.toString();
       
       Object Qty = jobject.get("quantity");
       String quantity = Qty.toString();
       int Quantity = Integer.parseInt(quantity);
       
       Connection conn = getConnection();
        Statement s = conn.createStatement();
        s.executeUpdate("INSERT INTO product VALUES ('"+iD+"','"+name+"','"+description+"','"+quantity+"' )");
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
