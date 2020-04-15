package hou.tidaa.news.adapter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    private final UserClient userClient;

    @Autowired
    public UserServiceImpl(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public String getUsername(String id) {
        return userClient.getUsername(id);
    }
}
