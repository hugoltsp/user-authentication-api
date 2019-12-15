package io.github.hugoltsp.user.presenter.domain;

import java.time.LocalDateTime;

public class UserResponse {

    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDateTime lastLogin;
    private String token;
    private String href;

    public UserResponse(Long id, LocalDateTime creationDate, LocalDateTime modificationDate, LocalDateTime lastLogin, String token, String href) {
        this.id = id;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.lastLogin = lastLogin;
        this.token = token;
        this.href = href;
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

}
