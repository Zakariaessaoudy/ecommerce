package fst.ecommerce.service.produit;

import fst.ecommerce.dto.produit.*;
import fst.ecommerce.entity.Produit;
import fst.ecommerce.exception.ProduitNotFoundException;
import fst.ecommerce.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;

    @Override
    public ProduitAdminDto create(ProduitAdminDto produitAdminDto) {
        log.info("Creating new product: {}", produitAdminDto);
        Produit produit = produitMapper.toEntity(produitAdminDto);
        Produit saved = produitRepository.save(produit);
        return produitMapper.toDto(saved);
    }

    @Override
    public ProduitAdminDto update(ProduitAdminDto produitAdminDto) {
        produitRepository.findById(produitAdminDto.getId())
                .orElseThrow(() -> new ProduitNotFoundException("Produit with id " + produitAdminDto.getId() + " not found"));
        Produit updated = produitRepository.save(produitMapper.toEntity(produitAdminDto));
        return produitMapper.toDto(updated);
    }

    @Override
    public void delete(String id) {
        Produit produit1 = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit with id " + id + " not found"));
        produitRepository.delete(produit1);
    }

    @Override
    public ProduitAdminDto getById(String id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit with id " + id + " not found"));
        return produitMapper.toDto(produit);
    }

    @Override
    public List<ProduitAdminDto> getAll() {
        return produitRepository.findAll()
                .stream()
                .map(produitMapper::toDto)
                .toList();
    }

    @Override
    public List<ProduitSimpleDto> LandingPageProduits() {
        return produitRepository.findAll()
                .stream()
                .map(produitMapper::toSimpleDto)
                .toList();
    }

    @Override
    public List<ProduitListDto> ListRechercheProduits(String keyword) {
        return produitRepository.searchProduits(keyword)
                .stream()
                .map(produitMapper::toListDto)
                .toList();
    }

    @Override
    public ProduitDetailsDto findById(String id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit with id " + id + " not found"));
        return produitMapper.toDetailsDto(produit);
    }
}
