package edu.psu.pet.service;

import edu.psu.pet.model.Pet;

import java.util.List;

public interface PetService {

    public List<Pet> getPets();

//    add Pet
    void addPet(String petName, String petType, String petWeight, String petAge);

//    find pet
    List<Pet> findPet(String find);

//    get pet id
    Pet getPetByID(Long PetID);

//    edit pet by id
    void editPetByID(Long petID, String petName, String petType, String petWeight, String petAge);

//    remove pet by id
    void removeByID(Long petID);

}
