package www.service.hrservice.service;

import www.service.hrservice.interceptor.exception.ForbiddenException;

public interface AuthService {

    default boolean isAdmin(String key) {
        return true;
    }

    default boolean isClient(String login, String password) {
        return true;
    }

}
