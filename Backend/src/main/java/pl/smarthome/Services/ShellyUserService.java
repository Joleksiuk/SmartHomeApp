package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.AES.AES;
import pl.smarthome.Controllers.shelly.ShellyService;
import pl.smarthome.Models.users.ShellyUser;
import pl.smarthome.Repositories.ShellyUserRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShellyUserService {

    private final ShellyUserRepository shellyUserRepository;
    private final ShellyService shellyService;

    public ShellyUser createShellyUser(ShellyUser shellyUser) {
        if(shellyService.areShellyCredentialsValid(shellyUser.getServer(),shellyUser.getAuth_key())){
            shellyUserRepository.save(createEncryptedUser(shellyUser));
            return shellyUser;
        }
       return null;
    }

    public void updateShellyUser(ShellyUser shellyUser) {
        shellyUserRepository.save(createEncryptedUser(shellyUser));
    }

    public Optional<ShellyUser> findShellyUserById(Long id) {
        return shellyUserRepository.findById(id);
    }

    private ShellyUser createEncryptedUser(@NotNull ShellyUser shellyUser){
        shellyUser.setAuth_key(AES.encrypt(shellyUser.getAuth_key()));
        shellyUser.setServer(AES.encrypt(shellyUser.getServer()));
        return shellyUser;
    }


}