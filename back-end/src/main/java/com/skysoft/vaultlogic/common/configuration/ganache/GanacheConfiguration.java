package com.skysoft.vaultlogic.common.configuration.ganache;

import com.skysoft.vaultlogic.common.configuration.BlockchainNetwork;
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

    private static final String ACCOUNT_PRIVATE_KEY = "c8ccf0a4cdf6db90aafa63c7be4ddd003efcf66155b33eb3d9b0214aa2b5eb09";

    @Bean
    public Web3j web3j(BlockchainNetwork network) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        return Web3j.build(new HttpService(network.getUrl()), 100L, executorService);
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create(ACCOUNT_PRIVATE_KEY);
    }

    @Bean
    @Autowired
    public ApplicationManager applicationServiceApi(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return ApplicationManager.load(ApplicationManager.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public ApplicationStorage applicationStorage(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return ApplicationStorage.load(ApplicationStorage.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public SessionStorage sessionStorage(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return SessionStorage.load(SessionStorage.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public SessionManager sessionServiceApi(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return SessionManager.load(SessionManager.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public CashInOracle cashAcceptorOracle(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return CashInOracle.load(CashInOracle.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public SessionOracle sessionOracle(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return SessionOracle.load(SessionOracle.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public CapitalHero capitalHero(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return CapitalHero.load(CapitalHero.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public KioskStorage kioskStorage(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return KioskStorage.load(KioskStorage.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public ParameterStorage parameterStorage(Web3j web3j, Credentials credentials, BlockchainNetwork network) {
        return ParameterStorage.load(ParameterStorage.getPreviouslyDeployedAddress(network.getId()), web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

}
