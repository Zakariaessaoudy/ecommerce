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

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    public CouponServiceImpl(CouponRepository couponRepository , CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper ;
    }

    @Override
    public CouponDto createCoupon(CouponDto dto) {
        Coupon coupon = new Coupon();
        coupon.setCode(dto.getCode());
        coupon.setReduction(dto.getReduction());
        coupon.setEndDate(dto.getEndDate());

        Coupon saved = couponRepository.save(coupon);
        return couponMapper.toDTO(saved);
    }

    @Override
    public CouponDto getCouponByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new RessourceNotFound("Coupon non trouvé avec le code : " + code));

        if (!CouponIsValid.isValid(coupon)) {
            throw new RessourceNotFound("Coupon expiré ou invalide !");
        }

        return couponMapper.toDTO(coupon);
    }

    @Override
    public List<CouponDto> getAllCoupons() {
        return couponRepository.findAll()
                .stream()
                .map(couponMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCoupon(Long id) {
        if (!couponRepository.existsById(id)) {
            throw new RessourceNotFound("Coupon non trouvé avec id : " + id);
        }
        couponRepository.deleteById(id);
    }
}
