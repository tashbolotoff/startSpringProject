package kg.brigada.startspringproject.services.impl;

import kg.brigada.startspringproject.entities.Permission;
import kg.brigada.startspringproject.entities.UserRole;
import kg.brigada.startspringproject.models.PermissionBoolModel;
import kg.brigada.startspringproject.models.PermissionModel;
import kg.brigada.startspringproject.repos.PermissionRepo;
import kg.brigada.startspringproject.repos.UserRoleRepo;
import kg.brigada.startspringproject.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PermissionRepo privilegeRepo;

    @Override
    public void grantSelectedPrivileges(PermissionModel permissionModel) {
        UserRole userRole = userRoleRepo.getOne(permissionModel.getRoleId());
        ArrayList<Permission> permissionArrayList = new ArrayList<>();
        for(PermissionBoolModel permissionBoolModel : permissionModel.getPermissionBools()){
            if(permissionBoolModel.getPermissionBool()){
                Permission permission = privilegeRepo.getOne(permissionBoolModel.getPermId());
                permissionArrayList.add(permission);
            }
        }
        userRole.setPermissions(permissionArrayList);
        userRoleRepo.save(userRole);
    }
}
