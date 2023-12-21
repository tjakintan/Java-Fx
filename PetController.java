package edu.psu.pet.Controller;


import edu.psu.pet.model.Pet;
import edu.psu.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class PetController {

    @Autowired
    private PetService petService;


//    Home page
    @GetMapping("/")
    public String home(Model model){
        List<Pet> database = petService.getPets();
        model.addAttribute("pets", database);
        return "home";
    }


//    contact page
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }


//    about page
    @GetMapping("/about")
    public String about(){
        return "about";
    }


//    login page
    @GetMapping("/pets")
    public String pets(){
        return "pets";
    }


//    find pet
    @GetMapping("/search")
    public String Find(Model model, Model model1, @RequestParam String findPet){
        List<Pet> database = petService.findPet(findPet);
        model1.addAttribute("find", findPet + " Not found");
        model.addAttribute("pets", database);
        return "home";
    }


//    add pet
    @GetMapping("/add")
    public String addPet(Model model){
        return "add";
    }

    @PostMapping("/add")
    public String submitAddPet(
            @RequestParam String petName,
            @RequestParam String petType,
            @RequestParam String petWeight,
            @RequestParam String petAge,
            Model model
            ){
        petService.addPet(petName, petType, petWeight, petAge);
        List<Pet> database = petService.getPets();
        model.addAttribute("pets", database);
        return "home";
    }


//    remove pet
    @GetMapping("/remove/{petID}")
    public String removePet(Model model, @PathVariable long petID){
        petService.removeByID(petID);
        List<Pet> pet = petService.getPets();
        model.addAttribute("pets", pet);
        return "home";
    }


//    edit pet by its id number
    @GetMapping("/edit")
    public String editPetPage(Model model){
        return "edit";
    }
    @GetMapping("/edit/{petID}")
    public String editPet(Model model, @PathVariable long petID){
        Pet pet =  petService.getPetByID(petID);
        model.addAttribute("pet", pet);
        return "edit";
    }
    @PostMapping("/edit")
    public  String submitEditWatch(
            @RequestParam Long petID,
            @RequestParam String petName,
            @RequestParam String petType,
            @RequestParam String petWeight,
            @RequestParam String petAge,
            Model model
    ){
        petService.editPetByID(petID, petName, petType, petWeight, petAge);
        List<Pet> database = petService.getPets();
        model.addAttribute("pets", database);
        return "home";
    }


}
