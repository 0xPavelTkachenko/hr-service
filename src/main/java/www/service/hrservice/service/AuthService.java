package www.service.hrservice.service;

public interface AuthService {

    default boolean isAdmin(String key) {
        return true;
    }

    default boolean isClient(String login, String password) {
        return true;
    }

    default Integer getClientId(String login, String password) {
        return null;
    }

}
