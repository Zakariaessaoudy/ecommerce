package fst.ecommerce.service.commande;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.commande.CommandeMapper;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;

    @Override
    public CommandeDto create(CommandeDto commandeDto) {
        Commande commande = commandeMapper.toEntity(commandeDto);
        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDTO(saved);
    }

    @Override
    public CommandeDto getById(String id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Commande non trouvée avec id : " + id));
        return commandeMapper.toDTO(commande);
    }

    @Override
    public List<CommandeDto> getAll() {
        return commandeRepository.findAll()
                .stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if (!commandeRepository.existsById(id)) {
            throw new RessourceNotFound("Commande non trouvée avec id : " + id);
        }
        commandeRepository.deleteById(id);
    }
}
