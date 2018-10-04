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

    //get access token
    public String getAccessTokenUrl(){
        return sso;
    }

    //access
    public String getClientIdUrl(){
        return String.format("%s%s", getBaseUrl(), access.getClientId());
    }

    public String getClientSecretUrl(){
        return String.format("%s%s", getBaseUrl(), access.getClientSecret());
    }

    //application
    public String getLaunchApplicationUrl(){
        return String.format("%s%s", getBaseUrl(), application.getLaunchApplication());
    }

    public String getKeepAliveUrl(){
        return String.format("%s%s", getBaseUrl(), application.getKeepAlive());
    }

    public String getClientActivityUrl(){
        return String.format("%s%s", getBaseUrl(), application.getClientActivity());
    }

    public String getCloseApplicationUrl(){
        return String.format("%s%s", getBaseUrl(), application.getCloseApplication());
    }

    //device info
    public String getDeviceInfoUrl(){
        return String.format("%s%s", getBaseUrl(), device.getInfo());
    }

    //cash
    public String getCashDeviceStatusUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCash().getCashDeviceStatus());
    }

    public String getRecyclerDeviceStatusUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCash().getRecyclerDeviceStatus());
    }

    public String getEnableCashAcceptorUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCash().getEnableCashAcceptor());
    }

    public String getDisableCashAcceptorUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCash().getDisableCashAcceptor());
    }

    public String getDispensableAmountUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCash().getDispensableAmount());
    }

    public String getDispenseCashUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCash().getDispenseCash());
    }

    //scanner
    public String getScannerStatusUrl(){
        return String.format("%s%s", getBaseUrl(), device.getScanner().getStatus());
    }

    //printer
    public String getCreateReceiptUrl(){
        return String.format("%s%s", getBaseUrl(), device.getPrinter().getCreateReceipt());
    }

    public String getPrintReceiptUrl(){
        return String.format("%s%s", getBaseUrl(), device.getPrinter().getPrintReceipt());
    }

    //camera
    public String getTakePhotoUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCamera().getTakePhoto());
    }

    public String getTakeScanUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCamera().getTakeScan());
    }

    public String getStartPreviewUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCamera().getStartPreview());
    }

    public String getStopPreviewUrl(){
        return String.format("%s%s", getBaseUrl(), device.getCamera().getStopPreview());
    }

    //customer
    public String getSetCustomerInSessionUrl(){
        return String.format("%s%s", getBaseUrl(), customer.getSetCustomerInSession());
    }

    public String getCustomerInformationUrl(){
        return String.format("%s%s", getBaseUrl(), customer.getCustomerInformation());
    }

    //general info
    public String getLocationAndDevicesUrl(){
        return String.format("%s%s", getBaseUrl(), generalInfo.getLocationsAndDevices());
    }

    public String getDeviceUrl(){
        return String.format("%s%s", getBaseUrl(), generalInfo.getDevice());
    }

    public String getDevicesUrl(){
        return String.format("%s%s", getBaseUrl(), generalInfo.getDevices());
    }

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

        public String getCashDeviceStatus(){
            return cash.getCashDeviceStatus();
        }

        public String getRecyclerDeviceStatus(){
            return cash.getRecyclerDeviceStatus();
        }

        public String getEnableCashAcceptor(){
            return cash.getEnableCashAcceptor();
        }

        public String getDisableCashAcceptor(){
            return cash.getDisableCashAcceptor();
        }

        public String getDispensableAmount(){
            return cash.getDispensableAmount();
        }

        public String getDispenseCash(){
            return cash.getDispenseCash();
        }

        public String getScannerStatus(){
            return scanner.getStatus();
        }

        public String getCreateReceipt(){
            return printer.getCreateReceipt();
        }

        public String getPrintReceipt(){
            return printer.getPrintReceipt();
        }

        public String getTakePhoto(){
            return camera.getTakePhoto();
        }

        public String getTakeScan(){
            return camera.getTakeScan();
        }

        public String getStartPreview(){
            return camera.getStartPreview();
        }

        public String getStopPreview(){
            return camera.getStopPreview();
        }

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
    public static class PrinterDevice {
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