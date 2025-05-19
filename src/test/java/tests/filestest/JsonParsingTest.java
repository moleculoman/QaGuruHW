package tests.filestest;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.CityModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonParsingTest {
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    @DisplayName("Проверка содержимого json-файла")
    public void checkCityFromJsonTest() throws IOException {
        CityModel city = mapper.readValue(
                JsonParsingTest.class.getResourceAsStream("/city.json"),
                CityModel.class
        );
        assertNotNull(city, "Город не должен быть null");
        assertEquals("Красноярск", city.getCity());
        assertEquals(1187771, city.getPopulation());
        assertEquals(1628, city.getFounded());
        assertEquals(379, city.getArea_km2());
        assertEquals("Енисей", city.getRiver());
        assertEquals(
                List.of("Часовня Параскевы Пятницы", "Красноярские Столбы", "Коммунальный мост", "Театр оперы и балета"),
                city.getLandmarks()
        );
    }
}
