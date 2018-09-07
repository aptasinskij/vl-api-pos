package com.skysoft.vaultlogic.blockchain.configuration;

import com.skysoft.vaultlogic.blockchain.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

@Configuration
public class GanacheConfiguration {

    private static final String CLIENT_HOST = "http://localhost:7545";
    private static final Long POLLING_INTERVAL = 100L;
    private static final Integer POOL_SIZE = 100;

    private static final String ACCOUNT_PRIVATE_KEY = "fd6e21e4d9329f8499dc2a8446527fa8f63cd7b5a7ae2f6c7b01b5d203cf9c88";

    private static final String NETWORK_ID  = "5777";

    private static final String APPLICATION_MANAGER = "0x8d87a816eaffcc65f672f7336d7efd1f8237d94c";
    private static final String APPLICATION_STORAGE = "0x489f07014c8d98ebf6932273728ebb92c79abd61";

    private static final String SESSION_MANAGER = "0x365e4d1ee07377b14550b775aeb6eb452bac323e";
    private static final String SESSION_STORAGE = "0x1cb53752950d0354f82460918b029f61ac10e5c6";
    private static final String SESSION_ORACLE = "0x4e253145ad7c34b351becbec5ef69d901d2abaed";

    private static final String CAPITAL_HERO_ADDRESS = "0x6c5f8e1ac9af21e200850e1dcb9f2b19aeb7c4f4";

    private static final String CASH_ACCEPTOR_ORACLE_ADDRESS = "0x6afad92a7f3ba6b0e88241f11b83ddfa8dfa6e0f";

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
    public IApplicationManager applicationServiceApi(Web3j web3j, Credentials credentials) {
        return IApplicationManager.load(APPLICATION_MANAGER, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public ApplicationStorage applicationStorage(Web3j web3j, Credentials credentials) {
        return ApplicationStorage.load(APPLICATION_STORAGE, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public SessionStorage sessionStorage(Web3j web3j, Credentials credentials) {
        return SessionStorage.load(SESSION_STORAGE, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public ISessionManager sessionServiceApi(Web3j web3j, Credentials credentials) {
        return ISessionManager.load(SESSION_MANAGER, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public CashInOracle cashAcceptorOracle(Web3j web3j, Credentials credentials) {
        return CashInOracle.load(CASH_ACCEPTOR_ORACLE_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public SessionOracle sessionOracle(Web3j web3j, Credentials credentials) {
        return SessionOracle.load(SESSION_ORACLE, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public CapitalHero capitalHero(Web3j web3j, Credentials credentials) {
        return CapitalHero.load(CAPITAL_HERO_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

}
