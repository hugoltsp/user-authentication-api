package io.github.hugoltsp.user.data.orm;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_TOKEN")
public class UserToken {

    @Id
    @Column(name = "JWT", nullable = false)
    private String jwt;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "USER_ID", insertable = false, updatable = false)
    private Long userId;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }

}
