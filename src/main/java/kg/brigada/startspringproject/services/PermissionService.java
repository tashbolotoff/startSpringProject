package kg.brigada.startspringproject.services;


import kg.brigada.startspringproject.models.PermissionModel;

public interface PermissionService {
    void grantSelectedPrivileges(PermissionModel permissionModel);
}
