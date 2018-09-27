package com.skysoft.vaultlogic.common.configuration.properties;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@ConfigurationProperties("maya")
public class MayaProperties {

    private static final String URI_FORMAT = "%s%s";

    private String baseUrl;

    private String sso;

    private Access access;

    private Application application;

    private Device device;

    private CustomerUrl customer;

    private GeneralInfo generalInfo;

    @Data
    public static class Access {
        @NotBlank
        private String clientId;
        @NotBlank
        private String clientSecret;
    }

    @Data
    public static class Application {
        @NotBlank
        private String launchApplication;
        @NotBlank
        private String keepAlive;
        @NotBlank
        private String clientActivity;
        @NotBlank
        private String closeApplication;
    }

    @Data
    public static class Device {
        @NotBlank
        private String info;
        private CashDevice cash;
        private Scanner scanner;
        private ReceiptDevice receipt;
        private CameraDevice camera;
    }

    @Data
    public static class CashDevice {
        @NotBlank
        private String cashDeviceStatus;
        @NotBlank
        private String recyclerDeviceStatus;
        @NotBlank
        private String enableCashAcceptor;
        @NotBlank
        private String disableCashAcceptor;
        @NotBlank
        private String dispensableAmount;
        @NotBlank
        private String dispenseCash;
    }

    @Data
    public static class Scanner {
        @NotBlank
        private String status;
    }

    @Data
    public static class ReceiptDevice {
        @NotBlank
        private String createReceipt;
        @NotBlank
        private String printReceipt;
    }

    @Data
    public static class CameraDevice {
        @NotBlank
        private String takePhoto;
        @NotBlank
        private String takeScan;
        @NotBlank
        private String startPreview;
        @NotBlank
        private String stopPreview;
    }

    @Data
    public static class CustomerUrl {
        @NotBlank
        private String setCustomerInSession;
        @NotBlank
        private String getCustomerInformation;
    }

    @Data
    public static class GeneralInfo {
        @URL
        @NotBlank
        private String locationsAndDevices;
        @URL
        @NotBlank
        private String device;
        @URL
        @NotBlank
        private String devices;
    }

}