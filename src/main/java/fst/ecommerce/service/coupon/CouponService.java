package fst.ecommerce.service.coupon;

import fst.ecommerce.dto.coupon.CouponDto;
import java.util.List;

public interface CouponService {
    CouponDto createCoupon(CouponDto couponDTO);
    CouponDto getCouponByCode(String code);
    List<CouponDto> getAllCoupons();
    void deleteCoupon(String id);
    CouponDto applyCoupon(String code);
    CouponDto deactivateCoupon(String id);
}
