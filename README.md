Drools Demo
===

Course planner application for demonstrating Drools use-cases and configuration. Current configuration assumes a central
rules repository (Drools Workbench) what will contain the rules and can be configured at runtime.

## `drools-demo-facts`
Contains classes that are needed in authoring rules. This artifact is imported into the workbench to be able to
reference fact classes.

## `drools-demo-app`
The actual application for the course planner.

### Setup Drools workbench
Download this [repacked Tomcat 8](https://www.dropbox.com/s/9makqnl9hd5dmp5/apache-tomcat-8.tar.gz?dl=0) container pre-configured for Drools Workbench.

1.  Extract to desired location
2.  Set `CATALINA_HOME` environment variable (`.profile`) to the location of the container
3.  Set `JAVA_JOME` environment variable (`.profile`) to your JDK location
4.  Go to `$CATALINA_HOME/bin`
5.  Run `startup.sh` to run
6.  Run `shutdown-drools-wb.sh` to shutdown

Next, download the [Drools Workbench 6.3.0.Final](http://download.jboss.org/drools/release/6.3.0.Final/kie-drools-wb-6.3.0.Final-tomcat7.war).
This version is chosen to match the version of the project dependency.

### The following steps assumes some familiarity with Drools Workbench
1.  Go to [Tomcat Manager](http://localhost:9090/manager/html/).
2.  Scroll to `WAR file to deploy` section and choose the downloaded Workbench WAR file.
3.  Click `deploy`.
4.  Sign-in to Drools Workbench with `admin/admin`
5.  Create a project and add a drool file with the contents of `course_suggestion.drl`
6.  Deploy to rules and get the download link.
7.  Find `RulesConfig.java` in `drools-demo-app` and change the URL to the download URL from step 6
8.  Update the release ID in `RulesConfig.java` to the correct information of your Drools Project

## How to run
In the `drools-demo-facts` folder:
    
```
$ mvn clean install
```

    
In the `drools-demo-app` folder:
    
```
$ mvn spring-boot:run
```

    
## Testing
Use [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) to interact with
the API.

Sample GET request:
    http://localhost:8081/course/suggest?math=7&software=10&electronics=5&arts=10&social_studies=7