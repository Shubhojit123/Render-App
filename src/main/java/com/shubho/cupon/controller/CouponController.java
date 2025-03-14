package com.shubho.cupon.controller;

import com.shubho.cupon.services.CouponService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupons")
@CrossOrigin("http://localhost:5174/")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/claim")
    public String claimCoupon(HttpServletRequest request, HttpServletResponse response) {
        return couponService.claimCoupon(request, response);
    }
}
