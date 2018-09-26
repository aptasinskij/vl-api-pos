package com.skysoft.vaultlogic.common.configuration.properties;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Validated
@Configuration
@ConfigurationProperties("maya")
public class MayaProperties {

    @URL
    @NotBlank
    private String baseUrl;
    @NotBlank
    private String sso;

    @Valid
    private Application application;
    @Valid
    private Device device;
    @Valid
    private CustomerUrl customer;
    @Valid
    private GeneralInfo generalInfo;

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
        @Valid
        private CashDevice cash;
        @Valid
        private Scanner scanner;
        @Valid
        private ReceiptDevice receipt;
        @Valid
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
        String takePhoto;
        @NotBlank
        String takeScan;
        @NotBlank
        String startPreview;
        @NotBlank
        String stopPreview;
    }

    @Data
    public static class CustomerUrl {
        @NotBlank
        String setCustomerInSession;
        @NotBlank
        String getCustomerInformation;
    }

    @Data
    public static class GeneralInfo {
        @URL
        @NotBlank
        String locationsAndDevices;
        @URL
        @NotBlank
        String device;
        @URL
        @NotBlank
        String devices;
    }

}