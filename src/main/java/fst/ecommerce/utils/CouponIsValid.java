package fst.ecommerce.utils;

import fst.ecommerce.entity.Coupon;

import java.util.Date;

public class CouponIsValid {
    public static boolean isValid(Coupon coupon) {
        return coupon != null && coupon.getEndDate().after(new Date());
    }
}
