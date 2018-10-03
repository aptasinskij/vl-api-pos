package com.skysoft.vaultlogic.common.configuration;

import com.skysoft.vaultlogic.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

@Configuration
@Profile("ganache")
public class GanacheConfiguration {

    private static final String CLIENT_HOST = "http://localhost:7545";
    private static final Long POLLING_INTERVAL = 100L;
    private static final Integer POOL_SIZE = 100;

    private static final String ACCOUNT_PRIVATE_KEY = "c8ccf0a4cdf6db90aafa63c7be4ddd003efcf66155b33eb3d9b0214aa2b5eb09";

    private static final String NETWORK_ID  = "5777";

    private static final String APPLICATION_MANAGER = "0x32ca3b3e56c53104cc19930a5bc736fd5e7ccc21";
    private static final String APPLICATION_STORAGE = "0x953922b019922da51ead8a6731508b4202c71f35";

    private static final String SESSION_MANAGER = "0x37b6b88fd2bdffc6250b80daa4a039e2ea182b3c";
    private static final String SESSION_STORAGE = "0x156001f80a5ea6b235a8353ec01079cddc51e1a3";
    private static final String SESSION_ORACLE = "0x156001f80a5ea6b235a8353ec01079cddc51e1a3";

    private static final String CAPITAL_HERO_ADDRESS = "0x4132daa580ff0ab0f2f7b79de6e6a7b10a1226ba";

    private static final String CASH_ACCEPTOR_ORACLE_ADDRESS = "0x27b5a83b18445b7dfdb7ab91fc2adedbf6f372e2";

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(POOL_SIZE);
    }

    @Bean
    public Web3j web3j(ScheduledExecutorService scheduledExecutorService) {
        return Web3j.build(new HttpService(CLIENT_HOST), POLLING_INTERVAL, scheduledExecutorService);
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create(ACCOUNT_PRIVATE_KEY);
    }

    @Bean
    @Autowired
    public ApplicationManager applicationServiceApi(Web3j web3j, Credentials credentials) {
        return ApplicationManager.load(ApplicationManager.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public ApplicationStorage applicationStorage(Web3j web3j, Credentials credentials) {
        return ApplicationStorage.load(ApplicationStorage.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public SessionStorage sessionStorage(Web3j web3j, Credentials credentials) {
        return SessionStorage.load(SessionStorage.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public SessionManager sessionServiceApi(Web3j web3j, Credentials credentials) {
        return SessionManager.load(SessionManager.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public CashInOracle cashAcceptorOracle(Web3j web3j, Credentials credentials) {
        return CashInOracle.load(CashInOracle.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public SessionOracle sessionOracle(Web3j web3j, Credentials credentials) {
        return SessionOracle.load(SessionOracle.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public CapitalHero capitalHero(Web3j web3j, Credentials credentials) {
        return CapitalHero.load(CapitalHero.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public KioskStorage kioskStorage(Web3j web3j, Credentials credentials) {
        return KioskStorage.load(KioskStorage.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public ParameterStorage parameterStorage(Web3j web3j, Credentials credentials) {
        return ParameterStorage.load(ParameterStorage.getPreviouslyDeployedAddress(NETWORK_ID), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

}
