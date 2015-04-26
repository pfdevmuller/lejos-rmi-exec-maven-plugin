package za.co.pietermuller.ev3;

import lejos.remote.ev3.RMIMenu;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Goal which uploads and executes a Lejos application on a Mindstorms Brick.
 */
@Mojo(name = "lejos-rmi-exec")
public class LejosRmiExecMojo extends AbstractMojo {

    @Parameter(property = "lejos-rmi-exec.target")
    private String target;

    @Parameter(property = "lejos-rmi-exec.brickIP")
    private String brickIP;

    public void execute()
            throws MojoExecutionException {
        try {
            getLog().debug("Target file is " + target);

            File targetFile = new File(target);

            RMIMenu rmiMenu = (RMIMenu) Naming.lookup("//" + brickIP + "/RemoteMenu");
            FileInputStream in = new FileInputStream(targetFile);
            byte[] data = new byte[(int) targetFile.length()];
            in.read(data);
            in.close();

            getLog().info(String.format("Uploading %s to brick at %s...", targetFile.getName(), brickIP));

            rmiMenu.uploadFile("/home/lejos/programs/" + targetFile.getName(), data);

            getLog().info("Program has been uploaded");

            getLog().info("Running program ...");

            rmiMenu.runProgram(targetFile.getName().replace(".jar", ""));
        } catch (RemoteException e) {
            throw new MojoExecutionException("Mojo failed with: " + e.getMessage(), e);
        } catch (MalformedURLException e) {
            throw new MojoExecutionException("Mojo failed with: " + e.getMessage(), e);
        } catch (FileNotFoundException e) {
            throw new MojoExecutionException("Mojo failed with: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new MojoExecutionException("Mojo failed with: " + e.getMessage(), e);
        } catch (NotBoundException e) {
            throw new MojoExecutionException("Mojo failed with: " + e.getMessage(), e);
        }
    }
}
