package pl.smarthome.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.smarthome.Models.House;
import pl.smarthome.Models.dtos.HouseDto;
import pl.smarthome.Repositories.ComponentRepository;
import pl.smarthome.Repositories.DeviceRepository;
import pl.smarthome.Repositories.HouseRepository;
import pl.smarthome.Repositories.SceneRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseService {

    private final HouseRepository houseRepository;
    private final HouseUserService houseUserService;
    private final DeviceRepository deviceRepository;
    private final SceneRepository sceneRepository;
    private final ComponentRepository componentRepository;
    private final SceneService sceneService;

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
    public HouseDto getHouseData(@PathVariable Long houseId){
        HouseDto houseDto=new HouseDto();
        houseDto.setHouse(houseRepository.findById(houseId).orElse(null));
        houseDto.setDevices(deviceRepository.getAllByHouseId(houseId).stream().map(sceneService::devicetoDto).toList());
        houseDto.setComponents(componentRepository.findAll());
        houseDto.setScenes(sceneRepository.getAllByHouseId(houseId));
        return houseDto;
    }
}
