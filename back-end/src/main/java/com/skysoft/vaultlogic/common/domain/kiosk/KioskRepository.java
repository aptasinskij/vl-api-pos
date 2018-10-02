package com.skysoft.vaultlogic.common.domain.kiosk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KioskRepository extends JpaRepository<Kiosk, String> {

    Optional<Kiosk> findByShortId(String shortId);

}
