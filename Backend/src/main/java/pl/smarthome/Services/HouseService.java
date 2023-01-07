package pl.smarthome.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.House;
import pl.smarthome.Repositories.HouseRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseService {

    private final HouseRepository houseRepository;
    private final HouseUserService houseUserService;

    public void createHouse(House house, Long userId) {
        houseRepository.save( house);
        houseUserService.addHouseUser(house.getId(),userId,"Admin");
    }

    public void updateHouse(House house) {
        houseRepository.save( house);
    }

    public void deleteHouseById(Long id){
        houseRepository.deleteById(id);
    }

    public Optional<House> findHouseByHouseId(Long houseId) {
        return houseRepository.findById(houseId);
    }

    public List<House> findHousesByOwnerId(Long ownerId) {
        return houseRepository.getAllByOwnerId(ownerId);
    }
}
