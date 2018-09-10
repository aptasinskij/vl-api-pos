package com.skysoft.vaultlogic.blockchain.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.JsonRpc2_0Quorum;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
@Profile("cloud-quorum")
public class CloudQuorumConfiguration {

    private static final String CLIENT_HOST = "http://localhost:22000";
    private static final Long POLLING_INTERVAL = 100L;
    private static final Integer POOL_SIZE = 100;

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

}
