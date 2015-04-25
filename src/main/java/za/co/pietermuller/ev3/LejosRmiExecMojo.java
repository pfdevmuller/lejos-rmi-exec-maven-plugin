package za.co.pietermuller.ev3;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;

/**
 * Goal which uploads and executes a Lejos application on a Mindstorms Brick.
 */
public class LejosRmiExecMojo
    extends AbstractMojo
{
    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private File outputDirectory;

    public void execute()
        throws MojoExecutionException
    {
        File f = outputDirectory;

        System.out.println("PRINT: OutputDirectory is " + outputDirectory);
        getLog().info("LOG: OutputDirectory is " + outputDirectory);

            //throw new MojoExecutionException( "Error creating file " + touch, e );
    }
}
