package com.shubho.cupon.services;

import com.shubho.cupon.entity.Cupon;
import com.shubho.cupon.entity.CuponClaim;
import com.shubho.cupon.repo.CuponClaimRepo;
import com.shubho.cupon.repo.CuponRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CouponService {

    @Autowired
    private CuponRepo couponRepository;

    @Autowired
    private CuponClaimRepo couponClaimRepository;

    private final AtomicInteger lastAssignedIndex = new AtomicInteger(0);

    public String claimCoupon(HttpServletRequest request, HttpServletResponse response) {
        String userIp = request.getRemoteAddr();
        String sessionId = request.getSession().getId();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("couponClaimed") && cookie.getValue().equals("true")) {
                    return "⏳ You have already claimed a coupon. Try again later!";
                }
            }
        }

        Optional<CuponClaim> existingIpClaim = couponClaimRepository.findByIpAddress(userIp);
        Optional<CuponClaim> existingSessionClaim = couponClaimRepository.findBySessionId(sessionId);

        if (existingIpClaim.isPresent()) {
            LocalDateTime lastClaimTime = existingIpClaim.get().getDateTime();
            if (Duration.between(lastClaimTime, LocalDateTime.now()).toHours() < 1) {
                return "⏳ Please wait before claiming another coupon.";
            }
        }

        if (existingSessionClaim.isPresent()) {
            LocalDateTime lastClaimTime = existingSessionClaim.get().getDateTime();
            if (Duration.between(lastClaimTime, LocalDateTime.now()).toHours() < 1) {
                return "⏳ Please wait before claiming another coupon.";
            }
        }

        List<Cupon> coupons = couponRepository.findAll();
        if (coupons.isEmpty()) {
            return "❌ No coupons available.";
        }

        int currentIndex = lastAssignedIndex.getAndIncrement() % coupons.size();
        Cupon coupon = coupons.get(currentIndex);

        CuponClaim claim = new CuponClaim();
        claim.setIpAddress(userIp);
        claim.setSessionId(sessionId);
        claim.setCoupon(coupon);
        claim.setClaimedAt(LocalDateTime.now());
        couponClaimRepository.save(claim);

        Cookie cookie = new Cookie("couponClaimed", "true");
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "🎉 Coupon Claimed: " + coupon.getCupons();
    }
}
