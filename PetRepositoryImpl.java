package edu.psu.pet.repository.impl;

import edu.psu.pet.model.Pet;
import edu.psu.pet.repository.PetRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Repository
public class PetRepositoryImpl implements PetRepository {

    private List<Pet> petList = new ArrayList<>();  // "database"

    @Override
    public List<Pet> getPets(){return petList;}


//    find pet
    public List<Pet> findPet(String find){
        return petList.stream()
                .filter(w -> w.petName().toLowerCase().contains(find.toLowerCase()))
                .collect(Collectors.toList());
    }


    //    add pet
    @Override
    public void addPet(Pet pet){
        if (pet.petID() == null){
            var lastId = petList.stream().map(Pet::petID).mapToInt(Long::intValue).max();
            if (lastId.isEmpty()){
                pet = new Pet((long)1, pet.petName(), pet.petType(), pet.petWeight(), pet.petAge());
            }
            else{
                pet = new Pet((long) (lastId.getAsInt() + 1), pet.petName(), pet.petType(), pet.petWeight(), pet.petAge());
            }
        }
        petList.add(pet);
    }

//    get pet by id
    @Override
    public Pet getPetByID(Long petID){
        return petList.stream().filter(a -> Objects.equals(a.petID(), petID)).findFirst().orElse(null);
    }

//    edit pet by id
     @Override
     public void editPetByID(Long petId, String petName, String petType, String petWeight, String petAge){
        var newPet = new Pet(petId, petName, petType,Double.parseDouble(petWeight), Integer.parseInt(petAge));
        for (var i = 0; i < petList.size(); i++) {
            if (Objects.equals(petList.get(i).petID(), petId)) {
                petList.set(i, newPet);
            }
        }
    }

//    delete pet by id
    @Override
    public void removeByID(Long petID){
        petList = petList.stream().filter(a -> !Objects.equals(a.petID(), petID)).collect(Collectors.toList());
    }


}
