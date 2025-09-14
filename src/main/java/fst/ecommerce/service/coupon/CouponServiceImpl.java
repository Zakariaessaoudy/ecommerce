package fst.ecommerce.service.coupon;

import fst.ecommerce.dto.coupon.CouponDto;
import fst.ecommerce.entity.Coupon;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.CouponRepository;
import fst.ecommerce.utils.CouponIsValid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import fst.ecommerce.dto.coupon.CouponMapper;

@Service
public class CouponServiceImpl implements CouponService {


    @Override
    public CouponDto createCoupon(CouponDto couponDTO) {
        return null;
    }

    @Override
    public CouponDto getCouponByCode(String code) {
        return null;
    }

    @Override
    public List<CouponDto> getAllCoupons() {
        return List.of();
    }

    @Override
    public void deleteCoupon(Long id) {

    }
}
