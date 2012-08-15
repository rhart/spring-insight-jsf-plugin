<h1>Sample JSF Web App for Spring Insight JSF Plugin</h1>

This sample web app exercies all the aspects of the JSF web framework instrumented by the JSF Plugin for Spring Insight.

<h2>Running the web app</h2>

Download the latest WAR from https://github.com/rhart/spring-insight-jsf-plugin/downloads

or 

get and build the code from: <code>git clone https://github.com/rhart/spring-insight-jsf-plugin.git</code>

<pre>
<code>
cd spring-insight-jsf-plugin/samples/salary-calculator-webapp
mvn clean package
</code>
</pre>

You'll need to have tc Server Developer Edition installed.  Deploy the WAR to the server.

<h2>Testing the plugin</h2>

<ul>
<li>Browse to http://localhost:8080/salary-calculator-webapp to see the home page of the sample web app.</li>
<li>Click 'Calculate' without entering anything in the fields to exercie the JSF validators.</li>
<li>Enter correct values in the fields and click 'Calculate' again.</li>
<li>Browse to http://localhost:8080/insight to see the Spring Insight dashboard.</li>
<li>Select the application called 'salary-calculator-webapp (JSF Test Application)' and browse the 'JSF Endpoint' endpoint.</li>
<li>Or visit 'Recent Activity' and brose through the trace history.</li>
</ul>