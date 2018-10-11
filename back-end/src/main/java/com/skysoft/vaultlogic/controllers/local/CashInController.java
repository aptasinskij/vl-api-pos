package com.skysoft.vaultlogic.controllers.local;

import com.skysoft.vaultlogic.postback.impl.protocol.data.CashInsert;
import com.skysoft.vaultlogic.services.CashInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Profile("local")
@RequestMapping("/cash-in/increase-balance")
public class CashInController {

    private final CashInService cashInService;

    @Autowired
    public CashInController(CashInService cashInService) {
        this.cashInService = cashInService;
    }

    @GetMapping
    public ResponseEntity<Void> increaseBalance(@RequestParam String xToken, @RequestParam BigDecimal amount) {
        CashInsert event = new CashInsert();
        event.setCurrentAmount(amount);
        cashInService.updateBalance(event, xToken);
        return ResponseEntity.ok().build();
    }

}
