package com.photoworld.images;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.mysql.jdbc.PreparedStatement;
import com.photoworld.main.Photo;
/**
 * Servlet implementation class ImagesServlet
 */
public class ImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// database connection settings
	private String dbURL = "jdbc:mysql://localhost/photoworld";
	private String dbUser = "root";
	private String dbPass = "roland";
	private static final String SQL_FIND = "SELECT photo, longitude, latitude FROM data WHERE username = ?";

	@Resource(name = "jdbc:mysql://localhost/photoworld") // For Tomcat, define
															// as <Resource> in
															// context.xml and
															// declare as
															// <resource-ref> in
															// web.xml.
	private DataSource dataSource;
    public java.util.List<Photo> photoArray;
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String username =  request.getParameter("name");
     
        
        photoArray=new ArrayList<Photo>();
        
        try {
            // connects to the database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
           java.sql.Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
 


PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SQL_FIND);
          statement.setString(1, username);
			statement.setString(1, "Psothny Soth");


            try (ResultSet resultSet = statement.executeQuery()) {
            	if(resultSet.wasNull()){                	
            		response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            	}
            	else{
            		while (resultSet.next()) {
            			Photo photo=new Photo();
            			//preparing path
            			String path = resultSet.getString("photo");
            			String preparedPath = path.substring(path.indexOf("PhotoWorld"));
            			photo.setPath(preparedPath.substring(preparedPath.indexOf("data")));
            			photo.setLongitude(resultSet.getFloat("longitude"));
	                     photo.setLatitude(resultSet.getFloat("latitude"));
	                     photoArray.add(photo);
	                }
            		
            		  String json = new Gson().toJson(photoArray);
            		    //System.out.println(json);
            		    
            		  response.setContentType("application/json");
            		   response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            		    response.getWriter().write(json); 
            	//	PrintWriter out = response.getWriter();
            		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            	//	out.print(json);
            	//	out.flush();
            		
            		
            		//response.setContentType(getServletContext().getMimeType(username));
            	//	response.setContentLength(photoArray.length);
            	   // response.getOutputStream().write(photoArray);
            	}
            }
        } 
        catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
    
        
//        try {
//            List<Photo> photos = getPhotos.list(); // Obtain all products.
//            request.setAttribute("photos", photos); // Store products in request scope.
//            request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
//        } catch (SQLException e) {
//            throw new ServletException("Retrieving products failed!", e);
//        }
    }
}