package pl.project;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
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

        // average pressure for all stations
        double sumPressure = Arrays.stream(stations)
                .map(Station::getPressure)
                .filter(Objects::nonNull)
                .reduce(Double::sum)
                .get();
        System.out.printf("\nAverage pressure: %.2f", (sumPressure / stations.length));

        // average amount of rainfall for all stations
        double sumRainfall = Arrays.stream(stations)
                .map(Station::getTotalRainfall)
                .filter(Objects::nonNull)
                .reduce(Double::sum)
                .get();
        System.out.printf("\nAverage rainfall amount: %.2f", (sumRainfall / stations.length));

        // city with min temperature
        Station minTemp = Arrays.stream(stations)
                .min(Comparator.comparing(Station::getTemperature))
                .get();
        System.out.printf("\nMin temp in: %s, temp: %.2f", minTemp.getStation(), minTemp.getTemperature());

        // city with max temperature
        Station maxTemp = Arrays.stream(stations)
                .max(Comparator.comparing(Station::getTemperature))
                .get();
        System.out.printf("\nMax temp in: %s, temp: %.2f", maxTemp.getStation(), maxTemp.getTemperature());

        // city with min pressure
        Station minPressure = Arrays.stream(stations)
                .filter(station -> station.getPressure() != null)
                .min(Comparator.comparing(Station::getPressure))
                .get();
        System.out.printf("\nMin pressure in: %s, pressure: %.2f", minPressure.getStation()
                , minPressure.getPressure());

        // city with max pressure
        Station maxPressure = Arrays.stream(stations)
                .filter(station -> station.getPressure() != null)
                .max(Comparator.comparing(Station::getPressure))
                .get();
        System.out.printf("\nMax pressure in: %s, pressure: %.2f", maxPressure.getStation()
                , maxPressure.getPressure());

        // city with min rainfall
        Station minRainfall = Arrays.stream(stations)
                .min(Comparator.comparing(Station::getTotalRainfall))
                .get();
        System.out.println("\nMin rainfall in: " + minRainfall.getStation() + " total rainfall: "
                + minRainfall.getTotalRainfall());

        // city with max rainfall
        Station maxRainfall = Arrays.stream(stations)
                .filter(station -> station.getTotalRainfall() != null)
                .max(Comparator.comparing(Station::getTotalRainfall))
                .get();
        System.out.println("Max rainfall in: " + maxRainfall.getStation() + " total rainfall: "
                + maxRainfall.getTotalRainfall());

        System.out.println("\nEach station - total rainfall ");
        for (Station station : stations) {
            System.out.printf("\nStation: " + station.getStation()
                    + ", total rainfall: " + station.getTotalRainfall()
                    + ", date: " + station.getMeasurementDate());
        }

        System.out.println("\nEach station - temperature ");
        for (Station station : stations) {
            System.out.printf("\nStation: " + station.getStation()
                    + ", temperature: " + station.getTemperature()
                    + ", date: " + station.getMeasurementDate());
        }
    }
}
