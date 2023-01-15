package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.apache.xpath.operations.Bool;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.House;
import pl.smarthome.Models.HouseUser;
import pl.smarthome.Models.RolePermission;
import pl.smarthome.Models.dtos.HouseUserDto;
import pl.smarthome.Models.dtos.UserPermisson;
import pl.smarthome.Models.ids.HouseUserId;
import pl.smarthome.Models.users.User;
import pl.smarthome.Repositories.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseUserService {

    private final HouseUserRepository houseUserRepository;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final DeviceRepository deviceRepository;
    private final PermissionRepository permissionRepository;

    public void createHouseUser(HouseUser house) {
        houseUserRepository.save( house);
    }

    public void updateHouseUser(HouseUser house) {
        houseUserRepository.save( house);
    }

    public void deleteHouseUserById(HouseUserId id){
        houseUserRepository.deleteById(id);
    }

    public Optional<HouseUser> findHouseUserById(HouseUserId id) {
        return houseUserRepository.findById(id);
    }

    public List<HouseUser> getAllHouseUsersByHouseId(Long houseId){
        List<HouseUser> users = houseUserRepository.findAll();
        return users.stream().filter(user->(user.getHouseId()==houseId)).collect(Collectors.toList());
    }

    public List<HouseUserDto> convertHouseUserToDto(  List<HouseUser> houseUsers){
        List<HouseUserDto> users=new LinkedList<>();
        houseUsers.forEach(
                user -> {
                    User temp = userRepository.findById(user.getUserId()).orElse(null);
                    HouseUserDto dtoModel =new HouseUserDto(
                            temp.getId(),
                            user.getHouseId(),
                            temp.getUsername(),
                            temp.getEmail(),
                            user.getRole()
                             );
                    users.add(dtoModel);
                }
        );

        return users;
    }

    public String addHouseUser(Long houseId, String userName){
        //check if user exists
        User user = userRepository.findByUsername(userName).orElse(null);

        if(user!=null){
            //check if user is already a member of house
            HouseUserId id = new HouseUserId(user.getId(),houseId);
            HouseUser hu = houseUserRepository.findById(id).orElse(null);
            if(hu==null){
                //create user
                createHouseUser(new HouseUser( id.getUserId(),id.getHouseId(), "Guest"));
                return "User created!";
            }
            return "User already is a member of house!";
        }
        return "User does not exist!";
    }

    public void addHouseUser(Long houseId, Long userId, String role){
       createHouseUser( new HouseUser(userId,houseId,role));
    }

    public List<House> getHousesByUserId(Long userId){
        List<HouseUser> houseUsers =  houseUserRepository.
                findAll().stream().
                filter(houseUser -> (userId==houseUser.getUserId())).
                collect(Collectors.toList());
        List<House> houses = houseUsers.stream()
                .map(houseUser -> houseRepository.findById(houseUser.getHouseId()).orElse(null))
                .collect(Collectors.toList());
        return houses;
    }

    public UserPermisson getUserPermisson(Long userId, Long deviceId){
        UserPermisson userPermisson=new UserPermisson();
        Device device=  deviceRepository.findById(deviceId).orElse(null);
        HouseUserId houseUserId=new HouseUserId(userId,device.getHouseId());

        HouseUser houseUser = houseUserRepository.findById(houseUserId).orElse(null);
        String role="";
        if(houseUser!=null){
            role=houseUser.getRole();
        }
        List<RolePermission> permissions =  permissionRepository.getAllByDeviceId(deviceId);
        if(role.equals("Admin")){
            return new UserPermisson(userId,deviceId,true,true);
        }
        for(RolePermission perm:permissions){
            if(perm.getRole().equals(role)){
                Boolean canSee =false;
                if(perm.getCanSee().equals("true")){
                    canSee=true;
                }

                Boolean canControl =false;
                if(perm.getCanControl().equals("true")){
                    canControl=true;
                }
                userPermisson.setCanSee(canSee);
                userPermisson.setCanControl(canControl);
                userPermisson.setUserId(userId);
                userPermisson.setDeviceId(deviceId);
            }
        }
        return userPermisson;
    }



}
