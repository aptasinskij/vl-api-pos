package com.skysoft.vaultlogic.blockchain.configuration;

import com.skysoft.vaultlogic.blockchain.contracts.*;
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

    private static final String APPLICATION_MANAGER = "0xe58dad58ccb22b9b1de9f2e3edea7681bdacd773";
    private static final String APPLICATION_STORAGE = "0xe5854bbbb4f1447c839f57420c44ca7d56eb53d4";

    private static final String SESSION_MANAGER = "0x2c4b289749361521c9f238e76993ea9024f23d23";
    private static final String SESSION_STORAGE = "0x9d40ad39b007bfc5fb739f0a5f1489b370ddef04";
    private static final String SESSION_ORACLE = "0x45fc7653901d481a75e9ecbb6fcd250dedc68dc9";

    private static final String CAPITAL_HERO_ADDRESS = "0x04d3d07229557903459acadd33f5e505f928bd3c";

    private static final String CASH_ACCEPTOR_ORACLE_ADDRESS = "0x71c539c871e81f6b5c034ecc159d11837f21d845";

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
