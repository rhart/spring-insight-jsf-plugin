h1. JSF plugin for Spring Insight

Spring Insight runs in the SpringSource tc Server.  See http://www.springsource.org/insight for details.  This plugin adds instrumentation for web applications that use Java Server Faces (JSF) for the web framework.  See (http://java.sun.com/javaee/javaserverfaces/overview.html) for details. 

h2. What does it do?

It provides instrumentation for the following aspects of the JSF web framework:
# All lifecyle phase executions
# Managed bean operations
# Validator operations

h2. Compatability

This plugin is compatible with:
# JSF 2.x Mojarra and Apache MyFaces implementations
# Spring Insight 1.5+

h2. Installing the plugin

Download the latest release from https://github.com/rhart/spring-insight-jsf-plugin/downloads

or 

get and build the code from: <code>git clone https://github.com/rhart/spring-insight-jsf-plugin.git</code>

<pre>
<code>
cd spring-insight-jsf-plugin/collection-plugin
mvn clean package
</code>
</pre>

You'll need to have tc Server Developer Edition installed. Let's call the location of that <code>tcServer</code>.

Now copy the plugin jar to the right place under <code>tcServer</code>.

<pre>
<code>
cp target/insight-plugin-jsf-x.x.jar tcServer/spring-insight-instance/insight/collection-plugins
</code>
</pre>

h2. Running the plugin

# Start up Spring Insight Server as usual (see the Spring tc Server Developer Edition documentation) and make sure your WAR file is loaded. Start your application and make sure it's running.
# Browse to http://localhost:8080/insight to see the Spring Insight dashboard. Click on the Administration button and then _Collection Plug-ins_ under _Reports and Statistics_.
# If you can see the JSF plugin it's installed and running.
# Run through your application in a way you're sure exercises JSF. Then go back to the Spring Insight dashboard to see the traces.