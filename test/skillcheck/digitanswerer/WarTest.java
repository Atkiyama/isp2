package skillcheck.digitanswerer;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class WarTest extends AppTest {

	/* Server setup */
	@Override
	protected String getBaseUrl() {
		return "http://localhost:" + portNumber;
	}
	
	private static Server server;
	
	private static int portNumber;

	@BeforeClass
	public static void setup() throws Exception {
		String warFileName = System.getenv("WAFILE");
		if (warFileName == null || warFileName.length() == 0) {
			warFileName="webapp/digitanswerer.war"; // war filename
		}
		String portString = System.getenv("WAPORT");
		if (portString == null || portString.length() == 0) {
			portNumber=8081;
		}
		else {
			portNumber = Integer.parseInt(portString);
		}
		if (portNumber <= 1024) {
			portNumber=8081;
		}
		Configuration[] configurations = {
			new AnnotationConfiguration(),
			new WebInfConfiguration(),
			new WebXmlConfiguration(),
			new MetaInfConfiguration(),
			new FragmentConfiguration(),
			new EnvConfiguration(),
			new PlusConfiguration(),
			new JettyWebXmlConfiguration()
		};
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setWar(warFileName);
		context.setConfigurations(configurations);
		server = new Server(portNumber);	
		server.setHandler(context);
		try {
			new Thread() {
				@Override public void run(){
					try {
						server.start();
						server.join();
					}
					catch(Exception e) {
						System.out.println("Failed to start Jetty");
					}
				}
			}.start();
		}
		catch(Exception e) {
			System.out.println("Failed to start Thread");
		}
	}
		
	@AfterClass
	public static void tearDown() throws Exception {
		server.setStopTimeout(10000L);
		try {
			server.stop();
		}
		catch (Exception e) {
			System.out.println("Failed to stop Jetty");
		}
	}

}
