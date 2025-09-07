package fst.ecommerce.service.commande;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.commande.CommandeMapper;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.CommandeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    private   final CommandeRepository commandeRepository ;
    private final CommandeMapper commandeMapper ;

    public CommandeServiceImpl( CommandeRepository commandeRepository ,
                                CommandeMapper commandeMapper){
        this.commandeMapper = commandeMapper ;
        this.commandeRepository = commandeRepository ;

    }

    @Override
    public CommandeDto createCommande (CommandeDto commandeDto){
        Commande commande = commandeMapper.toEntity(commandeDto);
        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDTO(saved);
    }

    @Override
    public  CommandeDto getCommandeById (Long id){
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Commande non trouvée"));
        return commandeMapper.toDTO(commande);

    }

    @Override

    public void deleteCommande (Long id){
        if(!commandeRepository.existsById(id)){
            throw new RessourceNotFound("commande d'id "+id +"non trouvée");
        }

        commandeRepository.deleteById(id);
    }

    @Override
    public List<CommandeDto> getAllCommande(){
        return commandeRepository.findAll()
                .stream()
                .map(commandeMapper ::toDTO)
                .collect(Collectors.toList());
    }

}
