package edu.psu.pet.service.imp;

import edu.psu.pet.model.Pet;
import edu.psu.pet.repository.PetRepository;
import edu.psu.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> getPets(){return petRepository.getPets();}

//    find pet
    public List<Pet> findPet(String find){
        if(!(StringUtils.hasText(find))){
            throw new IllegalArgumentException("search is required");
        }
        return petRepository.findPet(find);
    }

//    add pet
    @Override
    public void addPet(String petName, String petType, String petWeight, String petAge){
        var pet = new Pet(null, petName, petType, Double.parseDouble(petWeight), Integer.parseInt(petAge));
        petRepository.addPet(pet);
    }

//    get ped id
     @Override
     public  Pet getPetByID(Long petID){
        return petRepository.getPetByID(petID);
    }

//    edit pet by id
    @Override
    public void editPetByID(Long petID, String petName, String petType, String petWeight, String petAge) {
        petRepository.editPetByID(petID, petName, petType, petWeight, petAge);
    }

//    remove by petID
    @Override
    public void removeByID(Long petID){
        if (petID == null || petID <= 0) {
            throw new IllegalArgumentException("Pet ID is required");
        }
        petRepository.removeByID(petID);
    }






}
