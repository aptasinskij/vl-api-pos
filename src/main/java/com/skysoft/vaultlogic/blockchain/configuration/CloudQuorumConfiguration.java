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

    private static final String APPLICATION_MANAGER = "0xc15a329d04d06a7dc6f1d4b7d5799ed662e3d123";
    private static final String APPLICATION_STORAGE = "0xb7812457238a8304ae0b55c9fcef072476a86a85";

    private static final String SESSION_MANAGER = "0xebd7470a50719a983541ba3985f88fc8a589b117";
    private static final String SESSION_STORAGE = "0xf72542c0450f03d4e7707f126a2c7e68f5a38185";
    private static final String SESSION_ORACLE = "0xcff2f2094f5e81ed56558adebbbf2e997559c5de";

    private static final String CAPITAL_HERO_ADDRESS = "0xe2233d836993ebe096d5d57003f66786141a04c1";

    private static final String CASH_ACCEPTOR_ORACLE_ADDRESS = "0xc6d951a64ef1b5152e38117f13d02beb889124b0";

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
