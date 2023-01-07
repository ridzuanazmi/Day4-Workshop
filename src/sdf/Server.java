package sdf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException{

        // Create a server socket and listen to a specific port (3000)
        System.out.println("Starting server on port 3000");
        ServerSocket server =  new ServerSocket(3000);
        
        // Wait for connection
        while (true) {
            System.out.println("Waiting for incoming connection");
            Socket conn = server.accept();
            System.out.println("Got a connection");

            Handler handler = new Handler(conn);

            try {
                // Calls the method run() in Handler class
                // Contains the calculator method
                handler.run();
            } catch (IOException ex) {
                
            }

        }
        /* 
        // Output and Input stream
        InputStream is = conn.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        OutputStream os = conn.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        
        boolean stop = false;
        // Do something
        while (!stop) {
            // Receives the data from the client side
            String oper = ois.readUTF();
            // Calculation or exit depending on the first readUTF from client side
            switch (oper) {  
                // Exits the while loop as client enters 'exit' 
                case "exit":
                    stop = true;
                    break;
                
                // Calculates addition as operand read is "+"
                case "+":
                    float num1 = ois.readFloat();
                    float num2 = ois.readFloat();
                    float result1 = num1 + num2;
                    System.out.printf("The result is: %s\n", result1);
                    oos.writeFloat(result1); // This part transfers the result to the client side
                    oos.flush();
                    break;

                // Calculates subtraction as operand read is "-"
                case "-":
                    float num3 = ois.readFloat();
                    float num4 = ois.readFloat();
                    float result2 = num3 - num4;
                    System.out.printf("The result is: %s\n", result2);
                    oos.writeFloat(result2); // This part transfers the result to the client side
                    oos.flush();
                    break;

                // Calculates mulitiplication as operand read is "*"
                case "*":
                    float num5 = ois.readFloat();
                    float num6 = ois.readFloat();
                    float result3 = num5 * num6;
                    System.out.printf("The result is: %s\n", result3);
                    oos.writeFloat(result3); // This part transfers the result to the client side
                    oos.flush();
                    break;

                // Calculates division as operand read is "/""
                case "/":
                    float num7 = ois.readFloat();
                    float num8 = ois.readFloat();
                    float result4 = num7 / num8;
                    System.out.printf("The result is: %s\n", result4);
                    oos.writeFloat(result4); // This part transfers the result to the client side
                    oos.flush();
                    break;

                default: 
            }
        }

        System.out.println("Terminating client connection");
        // Close connection
        try {
            conn.close();
        } catch (IOException ex) { }*/
            
    }
    
}
