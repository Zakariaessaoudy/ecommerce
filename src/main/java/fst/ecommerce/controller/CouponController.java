package fst.ecommerce.controller;

import fst.ecommerce.entity.Coupon;
import fst.ecommerce.service.coupon.CouponService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService service;

    public CouponController(CouponService service) {
        this.service = service;
    }

    @GetMapping
    public List<Coupon> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Coupon getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Coupon create(@RequestBody Coupon coupon) {
        return service.create(coupon);
    }

    @PutMapping("/{id}")
    public Coupon update(@PathVariable Long id, @RequestBody Coupon coupon) {
        return service.update(id, coupon);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
