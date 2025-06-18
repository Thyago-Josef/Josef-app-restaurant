package com.josef.Josef_app_restaurant.service;

import org.springframework.stereotype.Service;

import com.josef.Josef_app_restaurant.DTO.DrinksDTO;
import com.josef.Josef_app_restaurant.entities.Drinks;
import com.josef.Josef_app_restaurant.mapper.ItemOrderMapper;
import com.josef.Josef_app_restaurant.repository.DrinksRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Float.NaN;

@Service
public class DrinksService extends ItemOrdemService<Drinks,DrinksDTO> {

    private final DrinksRepository drinksRepository;

    public DrinksService(DrinksRepository drinksRepository, ItemOrderMapper itemOrderMapper) {
        super(drinksRepository, itemOrderMapper);
        this.drinksRepository = drinksRepository;

    }

    @Override
    protected DrinksDTO mapToDTO(Drinks entity) {
        return itemOrderMapper.toDrinksDTO(entity); // Método específico do mapper
    }

    @Override
    protected Drinks mapToEntity(DrinksDTO dto) {
        return itemOrderMapper.toDrinksEntity(dto); // Método específico do mapper
    }

//--------MEtodo CRUD -----------------------
    /**
     * Busca todas as bebidas
     * @return Lista de DrinksDTO
     */
    public List<DrinksDTO> findAllDrinks() {
        return super.findAll();
    }
    /**
     * Busca bebida por ID
     * @param id ID da bebida
     * @return Optional<DrinksDTO>
     */
    public Optional<DrinksDTO> findDrinkById(Long id) {
        return super.findById(id);
    }


    /**
     * Cria uma nova bebida
     * @param drinksDTO DTO da bebida a ser criada
     * @return DrinksDTO da bebida criada
     */
    public DrinksDTO createDrink(DrinksDTO drinksDTO) {
        return super.save(drinksDTO);
    }

    /**
     * Atualiza uma bebida existente
     * @param id ID da bebida a ser atualizada
     * @param drinksDTO Dados atualizados da bebida
     * @return DrinksDTO da bebida atualizada
     * @throws RuntimeException se a bebida não for encontrada
     */
    public DrinksDTO updateDrink(Long id, DrinksDTO drinksDTO) {
        // Verifica se a bebida existe
        if (!super.existsById(id)) {
            throw new RuntimeException("Bebida com ID " + id + " não encontrada");
        }
        // Define o ID no DTO para garantir que é uma atualização
        drinksDTO.setId(id);

        // Validações antes de atualizar (opcional)
        validateDrinkData(drinksDTO);

        return super.save(drinksDTO);
    }

    /**
     * Atualização parcial de uma bebida
     * @param id ID da bebida
     * @param drinksDTO Dados parciais para atualização
     * @return DrinksDTO da bebida atualizada
     */
    public DrinksDTO partialUpdateDrink(Long id, DrinksDTO drinksDTO) {
        Optional<DrinksDTO> existingDrinkOpt = findDrinkById(id);

        if (existingDrinkOpt.isEmpty()) {
            throw new RuntimeException("Bebida com ID " + id + " não encontrada");
        }

        DrinksDTO existingDrink = existingDrinkOpt.get();

        // Atualiza apenas campos não nulos do DTO recebido
        if (drinksDTO.getName() != null) {
            existingDrink.setName(drinksDTO.getName());
        }
        if (Double.isNaN(drinksDTO.getPrice()) || drinksDTO.getPrice() < 0) {
            throw new IllegalArgumentException("Preço da bebida deve ser maior ou igual a zero");
        }
        if (drinksDTO.getDescription() != null) {
            existingDrink.setDescription(drinksDTO.getDescription());
        }
        // Adicione outros campos conforme necessário

        return super.save(existingDrink);
    }

    /**
     * Deleta uma bebida por ID
     * @param id ID da bebida a ser deletada
     * @throws RuntimeException se a bebida não for encontrada
     */
    public void deleteDrink(Long id) {
        if (!super.existsById(id)) {
            throw new RuntimeException("Bebida com ID " + id + " não encontrada");
        }
        super.deleteById(id);
    }


    /**
     * Conta o total de bebidas
     * @return Número total de bebidas
     */
    public long countAllDrinks() {
        return drinksRepository.count();
    }


    /** * Deleta todas as bebidas (cuidado!)
     */
    public void deleteAllDrinks() {
        drinksRepository.deleteAll();
    }

    /**
     * Salva múltiplas bebidas de uma vez
     * @param drinksDTOList Lista de DTOs de bebidas
     * @return Lista de DTOs das bebidas salvas
     */
    public List<DrinksDTO> saveAllDrinks(List<DrinksDTO> drinksDTOList) {
        // Valida cada bebida
        drinksDTOList.forEach(this::validateDrinkData);

        // Converte DTOs para entidades
        List<Drinks> drinksEntities = drinksDTOList.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        // Salva todas as entidades
        List<Drinks> savedEntities = drinksRepository.saveAll(drinksEntities);

        // Converte entidades salvas de volta para DTOs
        return savedEntities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
// ================= MÉTODOS ESPECÍFICOS PARA DRINKS =================

    /**
     * Busca bebidas por tipo (ex: "SODA", "JUICE", "ALCOHOLIC")
     * @param type Tipo da bebida
     * @return Lista de DrinksDTO do tipo especificado
     */
//    public List<DrinksDTO> findDrinksByType(String type) {
//        // Assumindo que existe este método no DrinksRepository
//        List<Drinks> drinks = drinksRepository.findByType(type);
//        return drinks.stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }

    /**
     * Busca bebidas alcoólicas ou não alcoólicas
     * @param alcoholic true para alcoólicas, false para não alcoólicas
     * @return Lista de DrinksDTO
     */
    public List<DrinksDTO> findDrinksByAlcoholic(boolean alcoholic) {
        // Assumindo que existe este método no DrinksRepository
        List<Drinks> drinks = drinksRepository.findByAlcoholic(alcoholic);
        return drinks.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca bebidas por faixa de preço
     * @param minPrice Preço mínimo
     * @param maxPrice Preço máximo
     * @return Lista de DrinksDTO na faixa de preço
     */
    public List<DrinksDTO> findDrinksByPriceRange(Double minPrice, Double maxPrice) {
        // Assumindo que existe este método no DrinksRepository
        List<Drinks> drinks = drinksRepository.findByPriceBetween(minPrice, maxPrice);
        return drinks.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca bebidas ativas/disponíveis
     * @return Lista de DrinksDTO ativas
     */
//    public List<DrinksDTO> findActiveDrinks() {
//        // Assumindo que existe este método no DrinksRepository
//        List<Drinks> drinks = drinksRepository.findByActiveTrue();
//        return drinks.stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }

    // ================= MÉTODOS DE CONSULTA =================

        public boolean drinkExists(Long id) {
            return super.existsById(id);
        }

    // ================= MÉTODOS DE VALIDAÇÃO =================

    /**
     * Valida os dados da bebida antes de salvar
     * @param drinksDTO DTO a ser validado
     * @throws IllegalArgumentException se os dados são inválidos
     */
    private void validateDrinkData(DrinksDTO drinksDTO) {
        if (drinksDTO == null) {
            throw new IllegalArgumentException("Dados da bebida não podem ser nulos");
        }

        if (drinksDTO.getName() == null || drinksDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da bebida é obrigatório");
        }

        if (Double.isNaN(drinksDTO.getPrice()) || drinksDTO.getPrice() < 0) {
            throw new IllegalArgumentException("Preço da bebida deve ser maior ou igual a zero");
        }
        // Adicione outras validações conforme necessário
        // Exemplo: tamanho máximo do nome, formato de dados, etc.
    }
}



