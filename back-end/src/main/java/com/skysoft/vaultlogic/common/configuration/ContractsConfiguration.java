package com.skysoft.vaultlogic.common.configuration;

import com.skysoft.vaultlogic.common.configuration.properties.BlockchainNetwork;
import com.skysoft.vaultlogic.contracts.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.JsonRpc2_0Quorum;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.math.BigInteger.ZERO;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;

@Configuration
public class ContractsConfiguration {

    @Bean
    public Web3jService web3jService(BlockchainNetwork network) {
        return new HttpService(network.getUrl());
    }

    @Bean
    public Quorum quorum(Web3jService web3jService) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        return new JsonRpc2_0Quorum(web3jService, 100L, executorService);
    }

    @Bean
    public Admin admin(Web3jService web3jService) throws IOException {
        Admin build = Admin.build(web3jService);
        build.personalUnlockAccount(build.ethCoinbase().send().getAddress(), "", BigInteger.ZERO).send();
        return build;
    }

    @Bean
    public ClientTransactionManager clientTransactionManager(Quorum quorum, Admin admin) {
        return admin.ethCoinbase()
                .sendAsync()
                .thenApply(ethCoinbase -> new ClientTransactionManager(quorum, ethCoinbase.getAddress(), null))
                .join();
    }

    @Bean
    public ApplicationManager applicationManager(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return ApplicationManager.load(ApplicationManager.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public ApplicationStorage applicationStorage(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return ApplicationStorage.load(ApplicationStorage.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public SessionStorage sessionStorage(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return SessionStorage.load(SessionStorage.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public SessionManager sessionManager(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return SessionManager.load(SessionManager.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public CashInOracle cashInOracle(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return CashInOracle.load(CashInOracle.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public SessionOracle sessionOracle(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return SessionOracle.load(SessionOracle.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public CapitalHero capitalHero(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return CapitalHero.load(CapitalHero.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public KioskStorage kioskStorage(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return KioskStorage.load(KioskStorage.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public ParameterStorage parameterStorage(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return ParameterStorage.load(ParameterStorage.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public CameraOracle cameraOracle(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return CameraOracle.load(CameraOracle.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

    @Bean
    public PrinterOracle printerOracle(Quorum quorum, ClientTransactionManager transactionManager, BlockchainNetwork network) {
        return PrinterOracle.load(PrinterOracle.getPreviouslyDeployedAddress(network.getId()), quorum, transactionManager, ZERO, GAS_LIMIT);
    }

}
