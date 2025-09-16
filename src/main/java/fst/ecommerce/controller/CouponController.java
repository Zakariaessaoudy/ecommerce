package fst.ecommerce.controller;

import fst.ecommerce.dto.coupon.CouponDto;
import fst.ecommerce.service.coupon.CouponService;
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

    @PostMapping
    public ResponseEntity<CouponDto> create(@RequestBody CouponDto dto) {
        return ResponseEntity.ok(couponService.createCoupon(dto));
    }

    @GetMapping("/{code}")
    public ResponseEntity<CouponDto> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(couponService.getCouponByCode(code));
    }

    @GetMapping
    public ResponseEntity<List<CouponDto>> getAll() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/apply/{code}")
    public ResponseEntity<CouponDto> apply(@PathVariable String code) {
        return ResponseEntity.ok(couponService.applyCoupon(code));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<CouponDto> deactivate(@PathVariable String id) {
        return ResponseEntity.ok(couponService.deactivateCoupon(id));
    }
}
