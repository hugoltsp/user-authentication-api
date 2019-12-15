package io.github.hugoltsp.user.data.orm;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "PHONE_NUMBER")
@Entity
public class PhoneNumber {

    @EmbeddedId
    private PhoneNumberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public PhoneNumberId getId() {
        return id;
    }

    public void setId(PhoneNumberId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Embeddable
    public static class PhoneNumberId implements Serializable {

        @Column(name = "DDD", nullable = false)
        private String ddd;

        @Column(name = "NUMBER", nullable = false)
        private String number;

        public PhoneNumberId(String ddd, String number) {
            this.ddd = ddd;
            this.number = number;
        }

        public PhoneNumberId() {
        }

        public String getDdd() {
            return ddd;
        }

        public void setDdd(String ddd) {
            this.ddd = ddd;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

}
