package pl.project;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class StationApp {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://danepubliczne.imgw.pl/api/data/synop");
        ObjectMapper objectMapper = new ObjectMapper();

        Station[] stations = objectMapper.readValue(url, Station[].class);

        //average temp for all stations
        double sumTemp = Arrays.stream(stations)
                .map(Station::getTemperature)
                .filter(Objects::nonNull)
                .reduce(0.0, Double::sum);
        System.out.printf("\nAverage temperature: %.2f", (sumTemp / stations.length));
        
    }
}
