package org.example.dataManagement.impl;
import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExtractData {

    private static DatabaseReader initDatabaseReader() throws IOException {
        String databaseFilePath = "GeoLite2-Country.mmdb";
        File database = new File(databaseFilePath);
        return new DatabaseReader.Builder(database).withCache(new CHMCache()).build();
    }

    public static List<List<String>> parseLogFile(String logFilePath) throws IOException {

        List<String> logLines = Files.readAllLines(Paths.get(logFilePath));
        List<List<String>> allUsersData = new ArrayList<>();
        DatabaseReader dbReader = initDatabaseReader();

        for (String line : logLines) {
            String[] parts = line.split(" ");
            String ipAddress = parts[0];
            String userAgentString = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));

            // Parse User-Agent
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            Browser browser = userAgent.getBrowser();
            OperatingSystem os = userAgent.getOperatingSystem();

            allUsersData.add(storeUser(ipAddress,browser,os,dbReader));

        }
        return allUsersData;
    }
    private static List<String> storeUser(String ipAddress, Browser browser, OperatingSystem os,DatabaseReader dbReader){
        List<String> userData = new ArrayList<>();
        userData.add(getCountry(ipAddress, dbReader));
        userData.add(browser.getGroup().getName());
        userData.add(os.getGroup().getName());
        return userData;
    }

    private static String getCountry(String ipAddress, DatabaseReader dbReader){

        try {
            InetAddress ip = InetAddress.getByName(ipAddress);
            CountryResponse response = dbReader.country(ip);
            Country country = response.getCountry();
            return (country.getName() != null) ? country.getName() : "Unknown Country";
        } catch (AddressNotFoundException e) {
            return "Unknown Country";
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

}
