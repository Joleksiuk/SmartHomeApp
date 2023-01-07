package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.RolePermission;

import java.util.List;

public interface PermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> getAllByDeviceId(Long deviceId);
}
