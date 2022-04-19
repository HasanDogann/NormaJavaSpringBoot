import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name="Hello")
public class DependencyVersion2 extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("Hello Programmer.");
    }
}
