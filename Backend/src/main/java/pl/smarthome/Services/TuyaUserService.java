package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.AES.AES;
import pl.smarthome.Models.users.TuyaUser;
import pl.smarthome.Repositories.TuyaUserRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TuyaUserService {

    private final TuyaUserRepository tuyaUserRepository;

    public void createTuyaUser(TuyaUser tuyaUser) {
        tuyaUserRepository.save( createEncryptedUser(tuyaUser));
    }

    public void updateTuyaUser(TuyaUser tuyaUser) {
        tuyaUserRepository.save( createEncryptedUser(tuyaUser));
    }

    public Optional<TuyaUser> findTuyaUserById(Long id) {
        return tuyaUserRepository.findById(id);
    }

    private TuyaUser createEncryptedUser(@NotNull TuyaUser tuyaUser){
        tuyaUser.setAccessId(AES.encrypt(tuyaUser.getAccessId()));
        tuyaUser.setSecretKey(AES.encrypt(tuyaUser.getSecretKey()));
        tuyaUser.setServer(AES.encrypt(tuyaUser.getServer()));
        return tuyaUser;
    }

}