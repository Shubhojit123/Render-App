package com.shubho.cupon.repo;

import com.shubho.cupon.entity.CuponClaim;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuponClaimRepo extends JpaRepository<CuponClaim,Integer> {
    Optional<CuponClaim> findByIpAddress(String ipAddress);
    Optional<CuponClaim> findBySessionId(String sessionId);
}
