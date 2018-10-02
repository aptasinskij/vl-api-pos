package com.skysoft.vaultlogic.common.domain.cashin;

import com.skysoft.vaultlogic.common.domain.TransactionLog;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;

@Entity
@Immutable
public class CashInChannelTxLog extends TransactionLog<CashInChannel, CashInChannel.Status> {
}
