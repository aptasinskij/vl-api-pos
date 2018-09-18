package com.skysoft.vaultlogic.common.domain.session;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SessionTxLogRepository extends JpaRepository<SessionTxLog, BigInteger> {
}
