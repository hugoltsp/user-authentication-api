package io.github.hugoltsp.user.usecase.domain;

import io.github.hugoltsp.user.data.orm.PhoneNumber;
import io.github.hugoltsp.user.data.orm.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserCreationPort {

    private final String name;
    private final String email;
    private final String encodedPassword;
    private final List<PhoneNumberPort> phones;

    public UserCreationPort(String name, String email, String encodedPassword, List<PhoneNumberPort> phones) {
        this.name = name;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public List<PhoneNumberPort> getPhones() {
        return phones;
    }

    public User asEntity() {
        var user = new User();
        user.setCreationDate(LocalDateTime.now());
        user.setEmail(email);
        user.setName(name);
        user.setPassword(encodedPassword);
        phones.stream().map(PhoneNumberPort::asEntity).forEach(user::addPhoneNumber);
        return user;
    }

    public static class PhoneNumberPort {

        private final String ddd;
        private final String number;

        public PhoneNumberPort(String ddd, String number) {
            this.ddd = ddd;
            this.number = number;
        }

        public String getDdd() {
            return ddd;
        }

        public String getNumber() {
            return number;
        }

        PhoneNumber asEntity() {
            var phoneNumber = new PhoneNumber();
            phoneNumber.setDdd(getDdd());
            phoneNumber.setNumber(getNumber());
            return phoneNumber;
        }
    }

}
