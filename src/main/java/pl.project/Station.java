package pl.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

/**
 * {
 * Json Synop: https://danepubliczne.imgw.pl/api/data/synop
 * Json formatter: https://jsonformatter.curiousconcept.com/#
 *       "id_stacji":"12295",
 *       "stacja":"Bia\u0142ystok",
 *       "data_pomiaru":"2021-04-27",
 *       "godzina_pomiaru":"15",
 *       "temperatura":"7.3",
 *       "predkosc_wiatru":"3",
 *       "kierunek_wiatru":"300",
 *       "wilgotnosc_wzgledna":"53.7",
 *       "suma_opadu":"0.5",
 *       "cisnienie":"1016.1"
 *    }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {
    @JsonProperty("id_stacji")
    private Integer stationId;
    @JsonProperty("stacja")
    private String station;
    @JsonProperty("data_pomiaru")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date measurementDate;
    @JsonProperty("godzina_pomiary")
    @JsonFormat(pattern = "HH")
    private Time measurementTime;
    @JsonProperty("temperatura")
    private Double temperature;
    @JsonProperty("predkosc_wiatru")
    private Integer windSpeed;
    @JsonProperty("kierunek_wiatru")
    private Integer windDirection;
    @JsonProperty("wilgotnosc_wzgledna")
    private Double relativeHumidity;
    @JsonProperty("suma_opadu")
    private Double totalRainfall;
    @JsonProperty("cisnienie")
    private Double pressure;
}
