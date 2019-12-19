package web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class FirstCardInfo {
        private String firstCardNumber;
        private String firstCardSecretNumber;

    }

    public static FirstCardInfo getFirstCardInfo() {
        return new FirstCardInfo("5559000000000001", "**** **** **** 0001");
    }

    @Value
    public static class SecondCardInfo {
        private String secondCardNumber;
        private String secondCardSecretNumber;
    }

    public static SecondCardInfo getSecondCardInfo() {
        return new SecondCardInfo("5559000000000002", "**** **** **** 0002");
    }
}
