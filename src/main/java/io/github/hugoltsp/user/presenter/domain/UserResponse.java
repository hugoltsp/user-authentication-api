package io.github.hugoltsp.user.presenter.domain;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponse {

    private final Long id;
    private final LocalDateTime creationDate;
    private final LocalDateTime modificationDate;
    private final LocalDateTime lastLogin;
    private final String token;
    private final String href;
    private final List<PhoneNumberResponse> phones;

    public UserResponse(Long id, LocalDateTime creationDate, LocalDateTime modificationDate,
                        LocalDateTime lastLogin, String token, String href,
                        List<PhoneNumberResponse> phones) {
        this.id = id;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.lastLogin = lastLogin;
        this.token = token;
        this.href = href;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public String getToken() {
        return token;
    }

    public String getHref() {
        return href;
    }

    public List<PhoneNumberResponse> getPhones() {
        return phones;
    }

    public static class PhoneNumberResponse {

        private final String ddd;
        private final String number;

        public PhoneNumberResponse(String ddd, String number) {
            this.ddd = ddd;
            this.number = number;
        }

        public String getDdd() {
            return ddd;
        }

        public String getNumber() {
            return number;
        }
    }

}
