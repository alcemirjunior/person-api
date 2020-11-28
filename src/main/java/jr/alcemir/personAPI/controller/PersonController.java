package jr.alcemir.personAPI.controller;

import jr.alcemir.personAPI.dto.MessageResponseDTO;
import jr.alcemir.personAPI.entity.Person;
import jr.alcemir.personAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")

public class PersonController {

    private PersonRepository personRepository;
    //aqui entra a tal da injeção de dependência!!!

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping //e não GetMapping pois agora queremos criar um livro
    public MessageResponseDTO createPerson(@RequestBody Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
