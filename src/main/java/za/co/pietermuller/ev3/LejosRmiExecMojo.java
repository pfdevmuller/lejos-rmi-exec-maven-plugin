package za.co.pietermuller.ev3;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal which uploads and executes a Lejos application on a Mindstorms Brick.
 */
@Mojo(name = "lejos-rmi-exec")
public class LejosRmiExecMojo
    extends AbstractMojo
{
    @Parameter( property = "lejos-rmi-exec.target")
    private String target;

    @Parameter( property = "lejos-rmi-exec.message", defaultValue = "Default Message!" )
    private String message;

    public void execute()
        throws MojoExecutionException
    {
        //File f = outputDirectory;

        getLog().info("OutputDirectory is " + target);
        getLog().info("Message is " + message);


            //throw new MojoExecutionException( "Error creating file " + touch, e );
    }
}
