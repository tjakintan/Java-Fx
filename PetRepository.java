package edu.psu.pet.repository;

import edu.psu.pet.model.Pet;

import java.util.List;

public interface PetRepository {

    List<Pet> getPets();

    //    add Pet
    void addPet(Pet pet);


//    search
    List<Pet> findPet(String find);

    //    get pet id
    Pet getPetByID(Long PetID);

    //    edit pet by id
    void editPetByID(Long petId, String petName, String petType, String petWeight, String petAge);

    //    remove pet by id
    void removeByID(Long PetID);
}
