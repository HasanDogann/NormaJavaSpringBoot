import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;
import java.util.Scanner;


@Mojo(name="counter-dependency",defaultPhase= LifecyclePhase.INITIALIZE)
public class CounterDependency extends AbstractMojo {
    @Parameter(defaultValue = "${project}",required = true,readonly = true)
    MavenProject project;

    public static List depList;
    String dependency,version;
    Scanner scn = new Scanner(System.in);
    String projectName;
    String packingType ;
    String category ;
    List<Dependency> dependencies;
    long numDependencies ;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        getLog().info("********************** WELCOME *****************************");
        getLog().info("---------->> PROJECT RELEASE 1.0 <<-----------------");
        getProjectName();
        getVersionName();
        getDepCount();
        myDeps();
        getPackagingType();


        getLog().info("Which dependency do you want to check the details?");
        dependency=scn.nextLine();
        specifyDependency(dependency);

        getLog().info("******************* HAVE A GOOD DAY! ************************");

    }
    //Bütün dep. leri liste halinde ekrana yazdıran method
    public void myDeps(){
        for (int i = 0; i <dependencies.size(); i++) {
            getLog().info("Project "+(i+1)+". dependecy is: "+dependencies.get(i).getArtifactId());
        }
    }

    //Verdiğimiz dep.nin detaylarını ekrana yazdıran method
    public void specifyDependency(String dep){
        depList=project.getDependencyArtifacts().stream().filter(d->d.toString().contains(dep)).toList();
        getLog().info("your dependency details are : "+depList.toString());
    }
    //Project adını ekrana yazdıran method
    public void getProjectName(){
        this.projectName = project.getName();
        getLog().info("My Project Name is : "+projectName);
    }

    //Projenin packaging türünü ekrana yazdıran method
    public void getPackagingType(){
        packingType = project.getPackaging();
        getLog().info("My Project Packing Mode is : "+packingType);
    }
    //Projenin version ismini ekrana yazdıran method
    public void getVersionName(){
        category = project.getArtifactId();
        getLog().info("My Project Version Name is : "+category);    }

    //Projenin dep. sayısını ekrana yazdıran method
    public void getDepCount(){
        dependencies=project.getDependencies();
        numDependencies = dependencies.stream().count();
        getLog().info("My Project Dependencies Count: "+numDependencies);
    }

}
