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
    // content=blob, name=varchar(255) UNIQUE.
    private static final String SQL_FIND = "SELECT photo FROM data WHERE username = ?";

    @Resource(name="jdbc:mysql://localhost/photoworld") // For Tomcat, define as <Resource> in context.xml and declare as <resource-ref> in web.xml.
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = "helen";//test //request.getPathInfo().substring(1); // Returns "foo.png".
        
        try {
            // connects to the database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
           java.sql.Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
 

PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SQL_FIND);
            statement.setString(1, imageName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    byte[] content = resultSet.getBytes("photo");
                    response.setContentType(getServletContext().getMimeType(imageName));
                    response.setContentLength(content.length);
                    response.getOutputStream().write(content);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
    }
}