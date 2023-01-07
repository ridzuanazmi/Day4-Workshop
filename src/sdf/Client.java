package sdf;

import java.io.Console;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException{        
        // Connect to the server listening on port 3000
        // 127.0.0.1 or localhost means my local computer 
        // otherwise have to find out what is the IP address of the server
        Socket socket = new Socket("127.0.0.1" , 3000);
        System.out.println("Connected to server on localhost:3000");
        boolean stop = false;

        // Console to read user's input
        Console cons = System.console();

        // Output and Input stream
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os); 
        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);

        // Do something
        while (!stop) {
            // Asks the user to input something  
            System.out.println("Enter calculation or type exit");
            String input = cons.readLine("> ");
            String[] terms = input.trim().split(" ");

            // Compares the string array length, if exit is entered, length is 1 
            // otherwise it is length 3
            switch (terms.length) {
                case 1:
                    oos.writeUTF("exit");
                    oos.flush();
                    stop = true;
                    break;

                    case 3:
                    // This part transfers the number and operator the client enters into the server
                    // In order of the operand in [1] followed by [0] and [1]
                    oos.writeUTF(terms[1].trim());
                    oos.writeFloat(Float.parseFloat(terms[0]));
                    oos.writeFloat(Float.parseFloat(terms[2]));
                    oos.flush(); 
            
                    // This part receives the result from the server side and displays
                    System.out.println("----------- From server side -----------");
                    Float line = ois.readFloat();
                    System.out.printf("The result is: %s\n", line);
                
                    default: // ignore everything
            }

        }

        System.out.println("Terminating client connection");
            try {
                socket.close();
            } catch (IOException ex) { }
            
    }
}
