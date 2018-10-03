package com.skysoft.vaultlogic.common.domain.session;

import com.skysoft.vaultlogic.common.domain.TransactionLog;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;

@Entity
@Immutable
public class SessionTxLog extends TransactionLog<Session, Session.Status> {

    public SessionTxLog() {

    }

    public SessionTxLog(String transactionHash, Session entity, Session.Status action) {
        super(transactionHash, entity, action);
    }

}
