package fst.ecommerce.service.panier;

import fst.ecommerce.dto.panier.PanierDto;
import fst.ecommerce.entity.Panier;
import fst.ecommerce.repository.PanierRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PanierServiceImpl implements PanierService {

    @Override
    public PanierDto create(PanierDto panierDto) {
        return null;
    }

    @Override
    public PanierDto update(PanierDto panierDto) {
        return null;
    }

    @Override
    public void delete(Panier panier) {

    }

    @Override
    public PanierDto getById(Panier panier) {
        return null;
    }

    @Override
    public List<PanierDto> getAll() {
        return List.of();
    }
}
