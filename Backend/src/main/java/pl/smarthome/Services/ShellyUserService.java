package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.AES.AES;
import pl.smarthome.Models.users.ShellyUser;
import pl.smarthome.Repositories.ShellyUserRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShellyUserService {

    private final ShellyUserRepository shellyUserRepository;

    public void createShellyUser(ShellyUser shellyUser) {
        shellyUserRepository.save(createEncryptedUser(shellyUser));
    }

    public void updateShellyUser(ShellyUser shellyUser) {
        shellyUserRepository.save(createEncryptedUser(shellyUser));
    }

    public Optional<ShellyUser> findShellyUserById(Integer id) {
        return shellyUserRepository.findById(id);
    }

    private ShellyUser createEncryptedUser(@NotNull ShellyUser shellyUser){
        shellyUser.setAuth_key(AES.encrypt(shellyUser.getAuth_key()));
        shellyUser.setServer(AES.encrypt(shellyUser.getServer()));
        return shellyUser;
    }


}