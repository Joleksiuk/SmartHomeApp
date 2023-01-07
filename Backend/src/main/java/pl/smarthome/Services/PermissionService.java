package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.RolePermission;
import pl.smarthome.Repositories.PermissionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public void updatePermission(RolePermission permission) {
        List<RolePermission> perms= getPermissionByDeviceId(permission.getDeviceId());
        if(perms.size()<2) {
            permissionRepository.save(permission);
            return;
        }
        for(RolePermission perm:perms){
            if(perm.getRole().equals(permission.getRole())){
                perm.setCanSee(permission.getCanSee());
                perm.setCanControl(permission.getCanControl());
                permissionRepository.save(perm);
            }

        }
    }
    public void createPermission(RolePermission permission){
        permissionRepository.save(permission);
    }

    public void deletePermissionById(Long id){
        permissionRepository.deleteById(id);
    }

    public List<RolePermission> getPermissionByDeviceId(Long deviceId) {
        return permissionRepository.getAllByDeviceId(deviceId);
    }

    public List<RolePermission> getPermissionByDeviceList(List<Device> deviceIds){
        List<RolePermission> result=new LinkedList<>();
        deviceIds.forEach(device -> result.addAll(getPermissionByDeviceId(device.getId())));
        return result;
    }
}
