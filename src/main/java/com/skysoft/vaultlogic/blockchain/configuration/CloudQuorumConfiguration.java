package com.skysoft.vaultlogic.blockchain.configuration;

import com.skysoft.vaultlogic.blockchain.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.JsonRpc2_0Quorum;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

@Configuration
@Profile("cloud-quorum")
public class CloudQuorumConfiguration {

    private static final String CLIENT_HOST = "http://localhost:22000";
    private static final Long POLLING_INTERVAL = 100L;
    private static final Integer POOL_SIZE = 100;

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
    public Admin admin() {
        return Admin.build(new HttpService(CLIENT_HOST));
    }

    @Bean
    public Quorum web3j(ScheduledExecutorService scheduledExecutorService) {
        return new JsonRpc2_0Quorum(new HttpService(CLIENT_HOST), POLLING_INTERVAL, scheduledExecutorService);
    }

    @Bean
    public ClientTransactionManager clientTransactionManager(Quorum quorum, Admin admin) throws IOException {
        String account = admin.ethCoinbase().send().getAddress();
        return new ClientTransactionManager(quorum, account, null);
    }

    @Bean
    public IApplicationManager applicationServiceApi(Quorum quorum, ClientTransactionManager clientTransactionManager) {
        return IApplicationManager.load(APPLICATION_MANAGER, quorum, clientTransactionManager, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    public ApplicationStorage applicationStorage(Quorum quorum, ClientTransactionManager clientTransactionManager) {
        return ApplicationStorage.load(APPLICATION_STORAGE, quorum, clientTransactionManager, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    public SessionStorage sessionStorage(Quorum quorum, ClientTransactionManager clientTransactionManager) {
        return SessionStorage.load(SESSION_STORAGE, quorum, clientTransactionManager, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    public ISessionManager iSessionManager(Quorum quorum, ClientTransactionManager clientTransactionManager) {
        return ISessionManager.load(SESSION_MANAGER, quorum, clientTransactionManager, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    public CashInOracle cashAcceptorOracle(Quorum quorum, ClientTransactionManager clientTransactionManager) {
        return CashInOracle.load(CASH_ACCEPTOR_ORACLE_ADDRESS, quorum, clientTransactionManager, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    public SessionOracle sessionOracle(Quorum quorum, ClientTransactionManager clientTransactionManager) {
        return SessionOracle.load(SESSION_ORACLE, quorum, clientTransactionManager, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    public CapitalHero capitalHero(Quorum quorum, ClientTransactionManager clientTransactionManager) {
        return CapitalHero.load(CAPITAL_HERO_ADDRESS, quorum, clientTransactionManager, GAS_PRICE, GAS_LIMIT);
    }

}
