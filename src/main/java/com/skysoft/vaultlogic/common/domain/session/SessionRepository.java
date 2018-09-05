package com.skysoft.vaultlogic.common.domain.session;

import com.skysoft.vaultlogic.common.domain.session.projections.SmartContractSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByXToken(String xToken);

    SmartContractSession findSmartContractSessionByXToken(String xToken);

}
