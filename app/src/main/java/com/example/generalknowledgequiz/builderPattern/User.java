package com.example.generalknowledgequiz.builderPattern;

import android.hardware.usb.UsbRequest;
import android.widget.Toast;

import com.example.generalknowledgequiz.MainActivity;
import com.example.generalknowledgequiz.RegisterFragment;

public class User {
    private String login;
    private String nickname;
    private String password;
    private String email;

    public static final class Builder {
        private String login;
        private String nickname;
        private String password;
        private String email;


        public Builder setLogin(final String login) {
            this.login = login;
            return this;
        }
        public Builder setNickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public User create() {
            User user = new User();
            user.login = this.login;
            user.nickname = this.nickname;
            user.password = this.password;
            user.email = this.email;
            return user;
        }
    }
    @Override
    public String toString() { return nickname; }
}
