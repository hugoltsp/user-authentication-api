package io.github.hugoltsp.user.presenter.domain;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Valid
    @NotEmpty
    private Set<UserPhoneRequest> phones = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserPhoneRequest> getPhones() {
        return phones;
    }

    public void setPhones(Set<UserPhoneRequest> phones) {
        this.phones = phones;
    }

    public static class UserPhoneRequest {

        @NotBlank
        private String ddd;

        @NotBlank
        private String number;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserPhoneRequest that = (UserPhoneRequest) o;
            return Objects.equals(getDdd(), that.getDdd()) &&
                    Objects.equals(getNumber(), that.getNumber());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getDdd(), getNumber());
        }
    }
}
