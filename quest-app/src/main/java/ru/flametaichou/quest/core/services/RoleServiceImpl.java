package ru.flametaichou.quest.core.services;

import java.util.ArrayList;
import java.util.List;

import ru.flametaichou.quest.core.domain.AccountRole;

public class RoleServiceImpl implements RoleService {

    @Override
    public List<String> listRoles() {
        List<String> roles = new ArrayList<String>();
        for (Enum value : AccountRole.Role.values()) {
            roles.add(value.name());
        }
        return roles;
    }
}
