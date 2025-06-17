package com.josef.Josef_app_restaurant.service;


import com.josef.Josef_app_restaurant.DTO.DrinksDTO;
import com.josef.Josef_app_restaurant.DTO.FoodDTO;
import com.josef.Josef_app_restaurant.entities.Food;
import com.josef.Josef_app_restaurant.mapper.ItemOrderMapper;
import com.josef.Josef_app_restaurant.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Float.NaN;

@Service
public class FoodService extends ItemOrdemService<Food,FoodDTO> {

    private final FoodRepository foodRepository;

    public FoodService(ItemOrderMapper itemOrderMapper, FoodRepository foodRepository, FoodRepository foodRepository1) {
        super(foodRepository, itemOrderMapper);
        this.foodRepository = foodRepository1;

    }

    @Override
    protected FoodDTO mapToDTO(Food entity) {
        return itemOrderMapper.toFoodDTO(entity);
    }

    @Override
    protected Food mapToEntity(FoodDTO dto) {
        return itemOrderMapper.toFoodEntity(dto);
    }


// ================= MÉTODOS CRUD =================

    /**
     * Busca todas as comidas
     *
     * @return Lista de FoodDTO
     */
    public List<FoodDTO> findAllFood() {
        return super.findAll();
    }

    /**
     * Busca comida por ID
     *
     * @param id ID da comida
     * @return Optional<FoodDTO>
     */
    public Optional<FoodDTO> findFoodById(Long id) {
        return super.findById(id);
    }

    /**
     * Cria uma nova comida
     *
     * @param foodDTO DTO da comida a ser criada
     * @return FoodDTO da comida criada
     */
    public FoodDTO createFood(FoodDTO foodDTO) {
        return super.save(foodDTO);
    }

    /**
     * Atualiza uma comida existente
     *
     * @param id      ID da comida a ser atualizada
     * @param foodDTO Dados atualizados da comida
     * @return FoodDTO da comida atualizada
     * @throws RuntimeException se a comida não for encontrada
     */
    public FoodDTO updateFood(Long id, FoodDTO foodDTO) {
        // Verifica se a comida existe
        if (!super.existsById(id)) {
            throw new RuntimeException("Comida com ID " + id + " não encontrada");
        }

        // Define o ID no DTO para garantir que é uma atualização
        foodDTO.setId(id);

        // Validações antes de atualizar (opcional)
        //validateFoodData(foodDTO);

        return super.save(foodDTO);
    }



    /**
     * Atualização parcial de uma comida
     *
     * @param id      ID da comida
     * @param foodDTO Dados parciais para atualização
     * @return FoodDTO da comida atualizada
     */
    public FoodDTO partialUpdateFood(Long id, FoodDTO foodDTO) {
        Optional<FoodDTO> existingFoodOpt = findFoodById(id);

        if (existingFoodOpt.isEmpty()) {
            throw new RuntimeException("Comida com ID " + id + " não encontrada");
        }

        FoodDTO existingFood = existingFoodOpt.get();

        // Atualiza apenas campos não nulos do DTO recebido
        if (foodDTO.getName() != null) {
            existingFood.setName(foodDTO.getName());
        }
        if (foodDTO.getPrice() == NaN || foodDTO.getPrice() < 0) {
            {
                existingFood.setPrice(foodDTO.getPrice());
            }
            if (foodDTO.getDescription() != null) {
                existingFood.setDescription(foodDTO.getDescription());
            }
//        if (foodDTO.getCategory() != null) {
//            existingFood.setCategory(foodDTO.getCategory());
//        }



        }
        return super.save(existingFood);
    }

        /**
         * Deleta uma comida por ID
         * @param id ID da comida a ser deletada
         * @throws RuntimeException se a comida não for encontrada
         */
        public void deleteFood(Long id){
            if (!super.existsById(id)) {
                throw new RuntimeException("Comida com ID " + id + " não encontrada");
            }
            super.deleteById(id);
        }

        /**
         * Verifica se uma comida existe
         * @param id ID da comida
         * @return true se existe, false caso contrário
         */
        public boolean foodExists (Long id){
            return super.existsById(id);
        }

        /**
         * Conta o total de comidas
         * @return Número total de comidas
         */
        public long countAllFood (Long id){
            return foodRepository.count();
        }

        /**
         * Deleta todas as comidas (cuidado!)
         */
        public void deleteAllFood () {
            foodRepository.deleteAll();
        }

        /**
         * Salva múltiplas comidas de uma vez
         * @param foodDTOList Lista de DTOs de comidas
         * @return Lista de DTOs das comidas salvas
         */
        public List<FoodDTO> saveAllFood(List < FoodDTO > foodDTOList) {
            // Valida cada comida
            foodDTOList.forEach(this::validateFoodData);

            // Converte DTOs para entidades
            List<Food> foodEntities = foodDTOList.stream()
                    .map(this::mapToEntity)
                    .collect(Collectors.toList());

            // Salva todas as entidades
            List<Food> savedEntities = foodRepository.saveAll(foodEntities);

            // Converte entidades salvas de volta para DTOs
            return savedEntities.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        // ================= MÉTODOS ESPECÍFICOS PARA FOOD =================

        /**
         * Busca comidas por categoria (ex: "APPETIZER", "MAIN_COURSE", "DESSERT")
         * @param category Categoria da comida
         * @return Lista de FoodDTO da categoria especificada
         */
        public List<FoodDTO> findFoodByCategory (String category){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findByCategory(category);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas vegetarianas ou não vegetarianas
         * @param vegetarian true para vegetarianas, false para não vegetarianas
         * @return Lista de FoodDTO
         */
        public List<FoodDTO> findFoodByVegetarian ( boolean vegetarian){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findByVegetarian(vegetarian);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas veganas
         * @param vegan true para veganas, false para não veganas
         * @return Lista de FoodDTO
         */
        public List<FoodDTO> findFoodByVegan ( boolean vegan){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findByVegan(vegan);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas por faixa de preço
         * @param minPrice Preço mínimo
         * @param maxPrice Preço máximo
         * @return Lista de FoodDTO na faixa de preço
         */
        public List<FoodDTO> findFoodByPriceRange (Double minPrice, Double maxPrice){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findByPriceBetween(minPrice, maxPrice);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas ativas/disponíveis
         * @return Lista de FoodDTO ativas
         */
        public List<FoodDTO> findActiveFood () {
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findByActiveTrue();
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas por nível de tempero/picância
         * @param spiceLevel Nível de tempero (ex: "MILD", "MEDIUM", "HOT", "EXTRA_HOT")
         * @return Lista de FoodDTO com o nível de tempero especificado
         */
        public List<FoodDTO> findFoodBySpiceLevel (String spiceLevel){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findBySpiceLevel(spiceLevel);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas por tempo de preparo
         * @param maxPreparationTime Tempo máximo de preparo em minutos
         * @return Lista de FoodDTO com tempo de preparo menor ou igual ao especificado
         */
        public List<FoodDTO> findFoodByPreparationTime (Integer maxPreparationTime){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findByPreparationTimeLessThanEqual(maxPreparationTime);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas que contêm determinados ingredientes
         * @param ingredient Ingrediente a ser buscado
         * @return Lista de FoodDTO que contém o ingrediente
         */
        public List<FoodDTO> findFoodByIngredient (String ingredient){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findByIngredientsContaining(ingredient);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas por alergênicos
         * @param allergen Alergênico a ser evitado
         * @return Lista de FoodDTO que NÃO contém o alergênico especificado
         */
        public List<FoodDTO> findFoodWithoutAllergen (String allergen){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findByAllergensNotContaining(allergen);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Busca comidas mais populares (com base em algum critério de ranking)
         * @param limit Número máximo de resultados
         * @return Lista de FoodDTO das comidas mais populares
         */
        public List<FoodDTO> findMostPopularFood ( int limit){
            // Assumindo que existe este método no FoodRepository
            List<Food> foods = foodRepository.findMostPopular(limit);
            return foods.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        // ================= MÉTODOS DE VALIDAÇÃO =================

        /**
         * Valida os dados da comida antes de salvar
         * @param foodDTO DTO a ser validado
         * @throws IllegalArgumentException se os dados são inválidos
         */
        private void validateFoodData (FoodDTO foodDTO){
            if (foodDTO == null) {
                throw new IllegalArgumentException("Dados da comida não podem ser nulos");
            }

            if (foodDTO.getName() == null || foodDTO.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome da comida é obrigatório");
            }

//            if (foodDTO.getPrice() == null || foodDTO.getPrice() < 0) {
//                throw new IllegalArgumentException("Preço da comida deve ser maior ou igual a zero");
//            }

//            if (foodDTO.getCategory() == null || foodDTO.getCategory().trim().isEmpty()) {
//                throw new IllegalArgumentException("Categoria da comida é obrigatória");
//            }
//
//            if (foodDTO.getPreparationTime() != null && foodDTO.getPreparationTime() < 0) {
//                throw new IllegalArgumentException("Tempo de preparo deve ser maior ou igual a zero");
//            }

           // Validações específicas para categorias conhecidas
//            List<String> validCategories = List.of("APPETIZER", "MAIN_COURSE", "DESSERT", "SALAD", "SOUP", "SIDE_DISH");
//            if (!validCategories.contains(foodDTO.getCategory().toUpperCase())) {
//                throw new IllegalArgumentException("Categoria inválida. Categorias válidas: " + String.join(", ", validCategories));
//            }


        }
    }


