package fst.ecommerce.controller;

import fst.ecommerce.dto.coupon.CouponDto;
import fst.ecommerce.service.coupon.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    // 🔹 Créer un coupon
    @PostMapping
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponDto dto) {
        CouponDto savedCoupon = couponService.createCoupon(dto);
        return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
    }

    // 🔹 Récupérer un coupon par code
    @GetMapping("/{code}")
    public ResponseEntity<CouponDto> getCouponByCode(@PathVariable String code) {
        CouponDto coupon = couponService.getCouponByCode(code);
        return ResponseEntity.ok(coupon);
    }

    // 🔹 Lister tous les coupons
    @GetMapping
    public ResponseEntity<List<CouponDto>> getAllCoupons() {
        List<CouponDto> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    // 🔹 Supprimer un coupon
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}
