package com.villagerhunt.handlers;

import static com.villagerhunt.handlers.Role.hasRole;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;

public class Role {

    private static List<Role> allRoles = new ArrayList<Role>();
    private static HashMap<String, Role> playerRoles = new HashMap<String, Role>();
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName.trim();
        allRoles.add(this);
    }

    public String getName() {
        return roleName;
    }

    public void add(Player p) {
        playerRoles.put(p.getName(), this);
    }

    public boolean remove(Player p) {
        if (hasRole(p)) {
            playerRoles.remove(p.getName());
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasRole(Player p) {
        return playerRoles.containsKey(p.getName());
    }

    public static Role getRole(Player p) {
        if (!hasRole(p)) {
            return null;
        } else {
            return playerRoles.get(p.getName());
        }
    }
    
    public static Role getRole(String name){
        for(Role r : allRoles)
            if(r.roleName.equalsIgnoreCase(name))
                return r;
        return null;
    }
    
    public static List<Role> getAllRoles(){
        return allRoles;
    }
}
