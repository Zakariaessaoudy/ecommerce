package fst.ecommerce.dto.coupon;

import fst.ecommerce.entity.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponDto toDTO(Coupon coupon);
    Coupon toEntity(CouponDto dto);
}
