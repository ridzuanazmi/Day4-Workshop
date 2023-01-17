package darryl.sdf;

import java.io.File;

public class App {
    public static void main(String[] args) {
        String dirPath = "\\data2";
        // instantiate a file/directory object
        File newDirectory = new File(dirPath);
    
        // if directory exist, print message if not 
        // create directory
        if (newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        } 
    }
}

