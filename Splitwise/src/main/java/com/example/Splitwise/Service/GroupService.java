package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.Group;
import com.example.Splitwise.Entity.User;
import com.example.Splitwise.Enum.CurrencyType;

import java.util.*;

public class GroupService {
    Map<String, Group> groups;
    UserService userService;

    public GroupService(UserService userService) {
        this.groups = new HashMap<>();
        this.userService = userService;
    }

    public Group createGroup(String name, CurrencyType currencyType, String userId)
    {
        UUID uuid =UUID.randomUUID();

        List<User> users = new ArrayList<>();
        User user = userService.getUserById(userId);
        if(user!=null)
            users.add(user);
        Group group = new Group(uuid.toString(), name, users, currencyType);
        groups.put(group.getId(), group);
        return group;
    }

    public Group getGroupById(String groupId)
    {
        if(groups.containsKey(groupId))
            return groups.get(groupId);
        return null;
    }

    public void addUserToGroup(String groupId, String userId)
    {
        if(!groups.containsKey(groupId))
        {
            System.out.println("Group doesnt Exist");
            return;
        }
        User user = userService.getUserById(userId);
        if(user==null)
        {
            System.out.println("User doesnt exist");
            return;
        }
        groups.get(groupId).getUsers().add(user);
    }

}
