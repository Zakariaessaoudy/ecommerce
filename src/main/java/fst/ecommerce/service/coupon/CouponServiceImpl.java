package fst.ecommerce.service.coupon;

import fst.ecommerce.entity.Coupon;
import fst.ecommerce.repository.CouponRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;

    public CouponServiceImpl(CouponRepository repository) {
        this.repository = repository;
    }

    @Override
    public Coupon create(Coupon coupon) {
        return repository.save(coupon);
    }

    @Override
    public Coupon update(Long id, Coupon coupon) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Coupon getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Coupon> getAll() {
        return repository.findAll();
    }
}
