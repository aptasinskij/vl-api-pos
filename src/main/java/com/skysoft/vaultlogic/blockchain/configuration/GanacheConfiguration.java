package com.skysoft.vaultlogic.blockchain.configuration;

import com.skysoft.vaultlogic.blockchain.contracts.wrappers.ApplicationRepository;
import com.skysoft.vaultlogic.blockchain.contracts.wrappers.ApplicationServiceApi;
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
    private static final String APPLICATION_SERVICE_ADDRESS = "0x1b57bdacba1101f936dd52826f0a3550b15aad2b";
    private static final String APPLICATION_REPOSITORY_ADDRESS = "0x78cb4288be8e069e8acdb6697d68fba06229ac57";

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
    public ApplicationServiceApi applicationServiceApi(Web3j web3j, Credentials credentials) {
        return ApplicationServiceApi.load(APPLICATION_SERVICE_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Bean
    @Autowired
    public ApplicationRepository scApplicationRepository(Web3j web3j, Credentials credentials) {
        return ApplicationRepository.load(APPLICATION_REPOSITORY_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

}
