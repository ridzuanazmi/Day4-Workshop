package darryl.sdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String dirPath = "./data2";
        // instantiate a file/directory object
        File newDirectory = new File(dirPath);
    
        // if directory exist, print message if not 
        // create directory
        if (newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        } 

        Cookie cookie = new Cookie();
        cookie.readCookieFile();
        cookie.showCookies();

        // start of server side program
        ServerSocket serverSocket = new ServerSocket(7000);
        Socket socket = serverSocket.accept(); // establish the connection with the client at port 7000

        try (InputStream is = socket.getInputStream()){
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            String messageReceived = "";

            try (OutputStream os = socket.getOutputStream()){
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                while (!messageReceived.equals("close")) {
                    messageReceived = dis.readUTF();

                    if (messageReceived.equalsIgnoreCase("get-cookie")) {
                        String cookieValue = cookie.returnCookie();
                        System.out.println(cookieValue);

                        dos.writeUTF(cookieValue);
                        dos.flush();
                    }
                }
                dos.close();
                bos.close();
                os.close();

            } catch (EOFException e) {
                // TODO: handle exception
            }

            while (messageReceived.equals("close")) {
                messageReceived = dis.readUTF();

                if (messageReceived.equalsIgnoreCase("get-cookie")) {
                    String cookieValue = cookie.returnCookie();
                    System.out.println(cookieValue);
                }
            }
        } catch (EOFException eofException) {
            // TODO: handle exception
            socket.close();
            serverSocket.close();
        }

    } // end of main
}

