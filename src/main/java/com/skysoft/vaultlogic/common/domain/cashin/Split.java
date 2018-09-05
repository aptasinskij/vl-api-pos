package com.skysoft.vaultlogic.common.domain.cashin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigInteger;

@Data
@Embeddable
public class Split {

    @Column(nullable = false)
    private String party;

    @Column(nullable = false)
    private BigInteger fee;

}
