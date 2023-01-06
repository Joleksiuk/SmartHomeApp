package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.House;
import pl.smarthome.Models.HouseUser;
import pl.smarthome.Models.HouseUserDto;
import pl.smarthome.Models.ids.HouseUserId;
import pl.smarthome.Models.users.User;
import pl.smarthome.Repositories.HouseRepository;
import pl.smarthome.Repositories.HouseUserRepository;
import pl.smarthome.Repositories.UserRepository;

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
}
