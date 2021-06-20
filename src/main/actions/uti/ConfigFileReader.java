package uti;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigFileReader {
    private final String projectLocation = System.getProperty("user.dir");
    public void setReportPath(){
        try (OutputStream output = new FileOutputStream(projectLocation+"\\Configuration.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("reportConfigPath", projectLocation+ "\\extent-config.xml");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
    public String getReportConfigPath(){
        Properties prop = new Properties();
        setReportPath();
        String reportConfigPath = prop.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
}
