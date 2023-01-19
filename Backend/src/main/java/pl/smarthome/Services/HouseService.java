package pl.smarthome.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.House;
import pl.smarthome.Models.RolePermission;
import pl.smarthome.Models.Scene;
import pl.smarthome.Models.dtos.DeviceDto;
import pl.smarthome.Models.dtos.HouseDto;
import pl.smarthome.Models.ids.HouseUserId;
import pl.smarthome.Repositories.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseService {

    private final HouseRepository houseRepository;
    private final HouseUserService houseUserService;
    private final HouseUserRepository houseUserRepository;
    private final DeviceRepository deviceRepository;
    private final SceneRepository sceneRepository;
    private final ComponentRepository componentRepository;
    private final SceneService sceneService;
    private final PermissionService permissionService;
    private final DeviceService deviceService;

    public void createHouse(House house, Long userId) {
        houseRepository.save( house);
        houseUserService.addHouseUser(house.getId(),userId,"Admin");
    }

    public void updateHouse(House house) {
        houseRepository.save( house);
    }

    public void deleteHouseById(Long id){

        houseUserRepository.getAllByHouseId(id)
                .forEach(houseUser -> houseUserService.deleteHouseUserById(new HouseUserId(houseUser.getUserId(),id)));
        sceneRepository.getAllByHouseId(id).forEach(scene -> sceneService.deleteScene(id));
        deviceService.deleteAllDevicesByHouseId(id);
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

    public String deleteDeviceFromHouse(Long deviceId, Long houseId){

        House house = houseRepository.findById(houseId).orElse(null);
        if(house==null){
            return "There is no house house with id " + houseId.toString();
        }
        sceneRepository.getAllByHouseId(houseId).forEach(
            scene -> {
                sceneService.deleteDeviceFromScene(scene.getId(),deviceId);
            }
        );
        permissionService.deletePermissionByDeviceId(deviceId);
        deviceRepository.deleteById(deviceId);
        return "Device has been deleted.";
    }

}
