package com.photoworld.main;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import com.photoworld.main.Photo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Servlet implementation class ImagesServlet
 */
@WebServlet("/images/*")
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

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username =request.getPathInfo().substring(1); 
        
        try {
            // connects to the database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
           java.sql.Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
 

PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SQL_FIND);
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
            	if(resultSet.wasNull()){                	
            		response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            	}
            	else{
            		int count=0;
            		while (resultSet.next()) {
	                    byte[] content = resultSet.getBytes("photo");
	                    response.setContentType(getServletContext().getMimeType(username));
	                    response.setContentLength(content.length);
	                    response.getOutputStream().write(content);
	                    count++;
	                }
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