package General;

import java.io.InputStream;
import java.util.Properties;

public  class GetConfig {
	
public static String getConfigValue(String key) {
	String value=null;
	try {
		
		Properties prop= new Properties();
		InputStream in =GetConfig.class.getClassLoader().getResourceAsStream("Config.properties");
		prop.load(in);
		value=prop.getProperty(key);
		 	
	}catch(Exception e ){
		e.printStackTrace();
		
	}
	return value;
	
}
	

}
