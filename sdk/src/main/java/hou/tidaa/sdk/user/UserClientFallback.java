package hou.tidaa.sdk.user;

public class UserClientFallback implements UserClient{
    @Override
    public String getUsername(String id) {
        return "anonymity";
    }
}
