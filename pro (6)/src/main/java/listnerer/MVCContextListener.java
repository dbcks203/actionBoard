package listnerer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import mvc.util.WebUtil;

/**
 * Application Lifecycle Listener implementation class MVCContextListener
 *
 */
public class MVCContextListener implements ServletContextListener {
    /**
     * Default constructor. 
     */
    public MVCContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
		try {
			Context  ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			WebUtil.setDataSource((DataSource) envContext.lookup("jdbc/proDB"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
