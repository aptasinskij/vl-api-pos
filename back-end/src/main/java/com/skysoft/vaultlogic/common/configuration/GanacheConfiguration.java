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

    private static final String APPLICATION_MANAGER = "0x1d09293ce251a746693828fb51a324502422b61e";
    private static final String APPLICATION_STORAGE = "0x096317c5181cc5892b079367cd7f1a3f2fecc36c";

    private static final String SESSION_MANAGER = "0x0ad328ffe4e36f8aa533898dbd93e9c07e5e5670";
    private static final String SESSION_STORAGE = "0xaefe33efa6248c1ee3494fefdd3a744ecaf1c59b";
    private static final String SESSION_ORACLE = "0xd6dc964864c7f01d2d3f45684942b530a612e713";

    private static final String CAPITAL_HERO_ADDRESS = "0x52071509e918601e7ef456252a1c94a63a8408b3";

    private static final String CASH_ACCEPTOR_ORACLE_ADDRESS = "0x34653005dc3f732e97f24101c842d634a1bb3c0c";

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
