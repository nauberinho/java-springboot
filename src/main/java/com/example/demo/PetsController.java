package main.java.com.example.demo;
import main.java.com.example.demo.models.Pets;
import main.java.com.example.demo.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@RestController
@RequestMapping("/pets")
@ComponentScan({"main.java.com.example.demo.repositories.PetsRepository"})
public class PetsController {
  private PetsRepository repository;

  

  public static void main(String[] args) {
    SpringApplication.run(PetsController.class, args);
  }

  @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Pets> getAllPets() {
    return repository.findAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pets getPetById(@PathVariable("id") ObjectId id) {
    return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid 
    @RequestBody Pets pets) {
    pets.set_id(id);
    repository.save(pets);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Pets createPet(@Valid @RequestBody Pets pets) {
    pets.set_id(ObjectId.get());
    repository.save(pets);
    return pets;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable ObjectId id) {
    repository.delete(repository.findBy_id(id));
    }


}