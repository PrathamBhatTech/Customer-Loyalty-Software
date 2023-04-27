import java.io.*;
import java.net.*;
import java.util.*;

public class HttpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080); // Create a new server socket on port 8080

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Wait for a client to connect

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Read the client's request

                String requestLine = in.readLine(); // Extract the request line

                StringTokenizer tokens = new StringTokenizer(requestLine); // Tokenize the request line

                String httpMethod = tokens.nextToken(); // Extract the HTTP method (e.g. GET)
                String httpPath = tokens.nextToken(); // Extract the requested path

                if (httpMethod.equals("GET")) { // Only handle GET requests
                    String filePath;

                    switch (httpPath) {
                        case "/login" ->  filePath = "resources/login.html";
                        case "/transaction" ->  filePath = "resources/transaction.html";
                        case "/" -> filePath = "resources/index.html";
                        case "/signup" -> filePath = "resources/signup.html";
                        case "/logout" ->  filePath = "resources/logout.html";
                        case "/home" ->  filePath = "resources/home.html";
                        case "/admin" -> filePath = "resources/admin.html";
                        case "/membership" -> filePath = "resources/membership.html";
                        case "/edit-profile" -> filePath = "resources/edit-profile.html";
                        default -> {  // If the client requests a path that is not supported
                            // Write a 404 Not Found response
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            out.println("HTTP/1.1 404 Not Found");
                            out.println("Content-Type: text/plain");
                            out.println();
                            out.println("404 Not Found");
                            continue;
                        }
                    }

                    File file = new File(filePath); // Attempt to open the requested file

                    if (file.exists() && !file.isDirectory()) { // If the file exists and is not a directory
                        String contentType = "text/html"; // Set the content type to HTML

                        if (filePath.endsWith(".css")) { // If the requested file is a CSS file
                            contentType = "text/css"; // Set the content type to CSS
                        }

                        // Write the HTTP response
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        out.println("HTTP/1.1 200 OK");
                        out.println("Content-Type: " + contentType);
                        out.println();

                        // Read the requested file and write it to the client
                        BufferedReader fileReader = new BufferedReader(new FileReader(file));
                        String fileLine;
                        while ((fileLine = fileReader.readLine()) != null) {
                            out.println(fileLine);
                        }
                        fileReader.close();
                    } else { // If the requested file does not exist or is a directory
                        // Write a 404 Not Found response
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        out.println("HTTP/1.1 404 Not Found");
                        out.println("Content-Type: text/plain");
                        out.println();
                        out.println("404 Not Found");
                    }
                }

                clientSocket.close(); // Close the client socket
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
