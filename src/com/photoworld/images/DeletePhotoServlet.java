package com.photoworld.images;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class DeletePhotoServlet
 */
public class DeletePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DATA_DIRECTORY = "data";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	// database connection settings
	private String dbURL = "jdbc:mysql://localhost/photoworld";
	private String dbUser = "root";
	private String dbPass = "roland";
	private static final String SQL_DELETE= "DELETE  FROM data WHERE id = ?";
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       String photo =  request.getParameter("id");  
		
		try {
	            // connects to the database
	            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	           java.sql.Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
	 


	PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SQL_DELETE);
	          statement.setString(1, photo);
	         
	           	statement.executeUpdate();
	    }
	        catch (SQLException e) {
	            throw new ServletException("Something failed at SQL/DB level.", e);
	        }
	    
	        

	}
}