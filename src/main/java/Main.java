import lejos.remote.ev3.RMIMenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class Main {

    public static void main(String[] args) throws IOException, NotBoundException {
        String brickName = "192.168.1.102";
        String filePath = "/Users/mullerp/dev/Mindstorms/LejosTest/EV3HelloWorld/target";

        String fileName = "ev3-hello-world-1.0-SNAPSHOT-jar-with-dependencies.jar";

        RMIMenu rmiMenu = (RMIMenu) Naming.lookup("//" + brickName + "/RemoteMenu");
        File f = new File(filePath + "/" + fileName);
        FileInputStream in = new FileInputStream(f);
        byte[] data = new byte[(int) f.length()];
        in.read(data);
        in.close();

        System.out.println("Uploading to " + brickName + " ...");

        rmiMenu.uploadFile("/home/lejos/programs/" + fileName, data);

        System.out.println("Program has been uploaded");

        System.out.println("Running program ...");
        rmiMenu.runProgram(fileName.replace(".jar", ""));
    }
}
