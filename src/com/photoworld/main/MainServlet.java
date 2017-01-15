package com.photoworld.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;



/**
 * Servlet implementation class MainServlet
 */
<<<<<<< HEAD

@MultipartConfig(maxFileSize = 16177215)
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DATA_DIRECTORY = "data";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// database connection settings
	private String dbURL = "jdbc:mysql://localhost/photoworld";
	private String dbUser = "root";
	private String dbPass = "roland";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// gets values of text fields

		// Check that we have a file upload request
		boolean isMultipart = org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			return;
		}

		// zapis pliku na dysk

		// Create a factory for disk-based file items
		org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory factory = new DiskFileItemFactory();

		// Sets the size threshold beyond which files are written directly to
		// disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// constructs the folder where uploaded file will be stored
		String uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);
		String filePath = "";
		try {
			// Parse the request
			List items = upload.parseRequest(new ServletRequestContext(request));
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					String fileName = new File(item.getName()).getName();
					filePath = uploadFolder + File.separator + fileName;
					File uploadedFile = new File(filePath);
					System.out.println(filePath);
					// saves the file to upload directory
					item.write(uploadedFile);
				}
			}

			// naprawiæ
			// String username =
			// request.getParameter("google-signin-client_id");
			String username = "helen";

			InputStream inputStream = null; // input stream of the upload file

			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("photo");
			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			}

			Connection conn = null; // connection to the database
			String message = null; // message will be sent back to client

			try {
				// connects to the database
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

				// constructs SQL statement
				String sql = "INSERT INTO data (username, photo) values (?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				// dodanie nazwy uzytkownika
				statement.setString(1, username);

				// dodanie pliku do bazy
				if (filePath.length()>0 ) {
					// fetches input stream of the upload file for the blob
					// column
					statement.setString(2, filePath);
				}

				// sends the statement to the database server
				int row = statement.executeUpdate();
				if (row > 0) {
					message = "File uploaded and saved into database";
				}
			} catch (SQLException ex) {
				message = "ERROR: " + ex.getMessage();
				ex.printStackTrace();
			} finally {
				if (conn != null) {
					// closes the database connection
					try {
						conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				// sets the message in request scope
				request.setAttribute("Message", message);

				// forwards to the message page
				getServletContext().getRequestDispatcher("/").forward(request, response);
			}
		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}
=======
@WebServlet("/servlet")
@MultipartConfig(maxFileSize = 16177215) 
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 // database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/photoworld";
    private String dbUser = "root";
    private String dbPass = "admin";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
    	
    	//naprawiæ
        //String username = request.getParameter("google-signin-client_id");
    	String username = "helen";
    	
       
         
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            String sql = "INSERT INTO data (username, photo) values (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(2, inputStream);
            }
 
            // sends the statement to the database server
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        }
    }
>>>>>>> parent of 9e2293d... mapowanie rsc

}
