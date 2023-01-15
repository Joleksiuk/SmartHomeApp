package pl.smarthome.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.smarthome.Models.House;
import pl.smarthome.Models.RolePermission;
import pl.smarthome.Models.dtos.DeviceDto;
import pl.smarthome.Models.dtos.HouseDto;
import pl.smarthome.Models.ids.HouseUserId;
import pl.smarthome.Repositories.ComponentRepository;
import pl.smarthome.Repositories.DeviceRepository;
import pl.smarthome.Repositories.HouseRepository;
import pl.smarthome.Repositories.SceneRepository;

import java.util.LinkedList;
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
    public HouseDto getHouseDataOfUser( Long houseId, Long userId){
        HouseDto houseDto=new HouseDto();
        houseDto.setHouse(houseRepository.findById(houseId).orElse(null));


        List<DeviceDto> devices= deviceRepository.getAllByHouseId(houseId).stream().map(sceneService::devicetoDto).toList();
        String role = houseUserService.findHouseUserById(new HouseUserId(userId,houseId)).orElse(null).getRole();

        houseDto.setComponents(componentRepository.findAll());
        houseDto.setScenes(sceneRepository.getAllByHouseId(houseId));

        if(role.equals("Admin")){
            houseDto.setDevices(devices);
        }
        else{
            List<DeviceDto> result=new LinkedList<>();
            for(DeviceDto device: devices){
                for(RolePermission rp: device.getPermissions()){
                    if(rp.getRole().equals(role) && rp.getCanSee().equals("true")){
                        result.add(device);
                        break;
                    }
                }
            }
            houseDto.setDevices(result);
        }
        return houseDto;
    }

}
