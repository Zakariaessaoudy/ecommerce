package fst.ecommerce.dto.coupon;

import fst.ecommerce.entity.Coupon;

public class CouponMapperImpl implements CouponMapper{

    @Override
    public CouponDto toDTO(Coupon coupon) {
        CouponDto dto = new CouponDto();
        dto.setId(coupon.getId());
        dto.setCode(coupon.getCode());
        dto.setReduction(coupon.getReduction());
        dto.setEndDate(coupon.getEndDate());
        return dto;
    }

    @Override
    public Coupon toEntity(CouponDto couponDto){
        Coupon coupon = new Coupon();
        coupon.setId(couponDto.getId());
        coupon.setCode(couponDto.getCode());
        coupon.setReduction(couponDto.getReduction());
        coupon.setEndDate(couponDto.getEndDate());
        return coupon;
    }
}
