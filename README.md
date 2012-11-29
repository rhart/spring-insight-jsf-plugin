<h1>JSF plugin for Spring Insight</h1>

Spring Insight runs in the VMware vFabric tc Server.  See http://www.springsource.org/insight for details.  This plugin adds instrumentation for web applications that use Java Server Faces (JSF) for their web framework.  See (http://java.sun.com/javaee/javaserverfaces/overview.html) for details. 

<h2>What does it do?</h2>

It provides instrumentation for the following aspects of the JSF web framework:
<ul>
<li>All lifecyle phase executions</li>
<li>Actions</li>
<li>Validator operations</li>
<li>Action Listeners</li>
<li>Phase Listeners</li>
</ul>

<h2>Compatability</h2>

This plugin is compatible with:
<ul>
<li>
JSF 2.x Mojarra and Apache MyFaces implementations
</li>
<li>
Spring Insight 1.5+
</li>
</ul>

<h2>Installing the plugin</h2>

Download the latest release from https://github.com/rhart/spring-insight-jsf-plugin/downloads

or 

get and build the code from: <code>git clone https://github.com/rhart/spring-insight-jsf-plugin.git</code>

<pre>
<code>
cd spring-insight-jsf-plugin/collection-plugin
mvn clean package
</code>
</pre>

You'll need to have vFabric tc Server Developer Edition installed. Let's call the location of that <code>tcServer</code>.

Now copy the plugin jar to the right place under <code>tcServer</code>.

<pre>
<code>
cp insight-plugin-jsf-x.x.x.RELEASE.jar tcServer/spring-insight-instance/insight/collection-plugins
</code>
</pre>

If your version of vFabric tc server uses version 1.8.3 or lower of the insight-collection jar then there are additional install steps. vFabric tc server 2.7.1 and lower will have this version of the jar.
See here for more details https://github.com/rhart/spring-insight-jsf-plugin/wiki/Additional-Install-Details

<h2>Running the plugin</h2>

<ul>
<li>Start up vFabric tc Server as usual (see the vFabric tc Server Developer Edition documentation) and make sure your WAR file is deployed. Start your application and make sure it's running.</li>
<li>Browse to http://localhost:8080/insight to see the Spring Insight dashboard. Click on the 'Administration' button and then 'Collection Plug-ins' under 'Reports and Statistics'.</li>
<li>If you can see the JSF plugin it's installed and running.</li>
<li>Run through your application in a way you're sure exercises JSF. Then go back to the Spring Insight dashboard to see the traces.</li>
</ul>

<h2>Testing the plugin</h2>
There is a sample web app that can be used to test the plugin.  See here for details https://github.com/rhart/spring-insight-jsf-plugin/tree/master/samples/salary-calculator-webapp