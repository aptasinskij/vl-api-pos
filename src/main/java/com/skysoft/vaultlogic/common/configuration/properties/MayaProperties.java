package com.skysoft.vaultlogic.common.configuration.properties;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PUBLIC;

@Data
@Component
public class MayaProperties {

    private final BaseUrl baseUrl;
    private final ApplicationUrl applicationUrl;
    private final DeviceInfo deviceInfo;
    private final CashDevice cashDevice;
    private final ScannerDevice scannerDevice;
    private final ReceiptDevice receiptDevice;
    private final CameraDevice cameraDevice;
    private final CustomerUrl customerUrl;
    private final GeneralInfo generalInfo;

    @Data
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya")
    public static class BaseUrl {
        @NotBlank
        String baseUrl;
        @NotBlank
        String sso;
    }

    @Data
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya.application")
    public static class ApplicationUrl {
        @NotBlank
        String launchApplication;
        @NotBlank
        String keepAlive;
        @NotBlank
        String clientActivity;
        @NotBlank
        String closeApplication;
    }

    @Data
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya.device")
    public static class DeviceInfo {
        @NotBlank
        String info;
    }

    @Data
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya.device.cash")
    public class CashDevice {
        @NotBlank
        String cashDeviceStatus;
        @NotBlank
        String recyclerDeviceStatus;
        @NotBlank
        String enableCashAcceptor;
        @NotBlank
        String disableCashAcceptor;
        @NotBlank
        String dispensableAmount;
        @NotBlank
        String dispenseCash;
    }

    @Data
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya.device")
    public class ScannerDevice {
        @NotBlank
        String scannerStatus;
    }

    @Data
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya.device.receipt")
    public class ReceiptDevice {
        @NotBlank
        String createReceipt;
        @NotBlank
        String printReceipt;
    }

    @Data
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya.device.camera")
    public class CameraDevice {
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
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya.customer")
    public class CustomerUrl {
        @NotBlank
        String setCustomerInSession;
        @NotBlank
        String getCustomerInformation;
    }

    @Data
    @Validated
    @FieldDefaults(level = PUBLIC)
    @ConfigurationProperties(prefix = "maya.general-info")
    public class GeneralInfo {
        @NotBlank
        String locationsAndDevices;
        @NotBlank
        String device;
        @NotBlank
        String devices;
    }

}