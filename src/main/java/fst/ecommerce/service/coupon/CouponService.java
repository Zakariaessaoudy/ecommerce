package fst.ecommerce.service.coupon;

import fst.ecommerce.dto.categorie.CategorieDto;
import fst.ecommerce.dto.coupon.CouponDto;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.entity.Coupon;
import fst.ecommerce.service.CrudDtoService;

import java.util.List;

public interface CouponService  {
    CouponDto createCoupon(CouponDto couponDTO);
    CouponDto getCouponByCode(String code);
    List<CouponDto> getAllCoupons();
    void deleteCoupon(Long id);

}
