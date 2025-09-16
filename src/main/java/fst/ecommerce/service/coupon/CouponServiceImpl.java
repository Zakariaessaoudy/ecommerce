package fst.ecommerce.service.coupon;

import fst.ecommerce.dto.coupon.CouponDto;
import fst.ecommerce.dto.coupon.CouponMapper;
import fst.ecommerce.entity.Coupon;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.CouponRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponServiceImpl(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    @Override
    public CouponDto createCoupon(CouponDto dto) {
        Coupon coupon = couponMapper.toEntity(dto);
        Coupon saved = couponRepository.save(coupon);
        return couponMapper.toDTO(saved);
    }

    @Override
    public CouponDto getCouponByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new RessourceNotFound("Coupon non trouvé avec le code : " + code));

        if (!coupon.isActive() || coupon.getEndDate().before(new Date())) {
            throw new RessourceNotFound("Coupon expiré ou inactif");
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
    public void deleteCoupon(String id) {
        if (!couponRepository.existsById(id)) {
            throw new RessourceNotFound("Coupon non trouvé avec id : " + id);
        }
        couponRepository.deleteById(id);
    }

    @Override
    public CouponDto applyCoupon(String code) {
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new RessourceNotFound("Coupon non trouvé avec le code : " + code));

        if (!coupon.isActive() || coupon.getEndDate().before(new Date()) || coupon.getUsedCount() >= coupon.getUsageLimit()) {
            throw new RessourceNotFound("Coupon expiré, inactif ou limite atteinte");
        }

        // incrémenter utilisation
        coupon.setUsedCount(coupon.getUsedCount() + 1);
        Coupon updated = couponRepository.save(coupon);

        return couponMapper.toDTO(updated);
    }

    @Override
    public CouponDto deactivateCoupon(String id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Coupon non trouvé avec id : " + id));

        coupon.setActive(false);
        Coupon updated = couponRepository.save(coupon);

        return couponMapper.toDTO(updated);
    }
}
