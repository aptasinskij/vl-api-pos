package com.skysoft.vaultlogic.web.controller.local;

import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/cash-in/increase-balance")
public class CashInController {

    private final CashInRepository cashInRepository;

    @Autowired
    public CashInController(CashInRepository cashInRepository) {
        this.cashInRepository = cashInRepository;
    }

    @GetMapping
    public ResponseEntity<Void> increaseBalance(@RequestParam BigInteger channelId, @RequestParam BigInteger amount) {
        cashInRepository.findByChannelId(channelId).ifPresent(channel -> {
            channel.updateBalance(amount);
            cashInRepository.save(channel);
        });
        return ResponseEntity.ok().build();
    }

}
