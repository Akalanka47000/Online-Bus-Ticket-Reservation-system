package breezingbolt.database.seeders;

import breezingbolt.entities.City;
import breezingbolt.http.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CitySeeder {

    @Autowired
    private CityRepository cityRepository;

    public void seed() {
        if(cityRepository.findAll().size()==0){
            List<City> cities = new ArrayList<>();
            cities.add(new City("Colombo"));
            cities.add(new City("Kandy"));
            cities.add(new City("Matara"));
            cities.add(new City("Kataragama"));
            cities.add(new City("Mannar"));
            cities.add(new City("Kurunegala"));
            cities.add(new City("Kalpitiya"));
            cities.add(new City("Matale"));
            cities.add(new City("Theldeniya"));
            cities.add(new City("Dayagama"));
            cities.add(new City("Medawachchiya"));
            cities.add(new City("Nawalapitiya"));
            cities.add(new City("Hatton"));
            cities.add(new City("Gampola"));
            cities.add(new City("Hakmana"));
            cities.add(new City("Mahiyanganaya"));
            cities.add(new City("Udugama"));
            cities.add(new City("Kalmunai"));
            cities.add(new City("Trincomalee"));
            cities.add(new City("Anuradhapura"));
            cities.add(new City("Deniyaya"));
            cities.add(new City("Rakwana"));
            cities.add(new City("Nuwaraeliya"));
            cities.add(new City("Kuliyapitiya"));
            cities.add(new City("Akkaraipattu"));
            cities.add(new City("Badulla"));
            cities.add(new City("Rathnapura"));
            cities.add(new City("Trincomalee"));
            cities.forEach((city)->{
                cityRepository.save(city);
            });
        }
    }
}
