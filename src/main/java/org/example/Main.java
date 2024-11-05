package org.example;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.example.dataManagement.api.MetricEnum;
import org.example.dataManagement.impl.ExtractData;
import org.example.logic.api.Metric;
import org.example.logic.impl.Browser;
import org.example.logic.impl.Country;
import org.example.logic.impl.Os;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args){

        Country country = new Country();
        Os os = new Os();
        Browser browser = new Browser();
        try {
            List<List<String>> allUserData = ExtractData.parseLogFile("all.log");

            for (List<String> userData : allUserData) {

                country.addValueToData(userData.get(MetricEnum.COUNTRY.getValue()));
                browser.addValueToData(userData.get(MetricEnum.BROWSER.getValue()));
                os.addValueToData(userData.get(MetricEnum.OS.getValue()));
            }

            country.printAnalyzeData("Countries");
            os.printAnalyzeData("Os");
            browser.printAnalyzeData("Browsers");
        }
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
