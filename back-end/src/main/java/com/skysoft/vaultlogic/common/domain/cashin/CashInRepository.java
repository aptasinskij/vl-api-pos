package com.skysoft.vaultlogic.common.domain.cashin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface CashInRepository extends JpaRepository<CashInChannel, BigInteger> {

    Optional<CashInChannel> findByChannelId(BigInteger channelId);

    Optional<CashInChannel> findBySession_xTokenAndStatus(String xToken, CashInChannel.Status status);

}
