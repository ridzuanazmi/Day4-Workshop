package darryl.sdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost" , 7000);

        try (OutputStream os = socket.getOutputStream()){
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            Console console = System.console();
            String readInput = "";

            try (InputStream is = socket.getInputStream()) {
                BufferedInputStream bis = new BufferedInputStream(is);
                DataInputStream dis = new DataInputStream(bis);
    
                while (!readInput.equalsIgnoreCase("close")) {
                    readInput = console.readLine("Enter a command: ");
                    dos.writeUTF(readInput);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            while (!readInput.equalsIgnoreCase("close")) {
                readInput = console.readLine("Enter a command: ");
                dos.writeUTF(readInput);
                dos.flush(); // must flush to send over
            }

        } catch (EOFException e) {
            // TODO: handle exception
            socket.close();
        }

    }
}
