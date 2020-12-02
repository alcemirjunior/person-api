package jr.alcemir.personAPI.service;

import jr.alcemir.personAPI.dto.MessageResponseDTO;
import jr.alcemir.personAPI.dto.request.PersonDTO;
import jr.alcemir.personAPI.entity.Person;
import jr.alcemir.personAPI.mapper.PersonMapper;
import jr.alcemir.personAPI.repository.PersonRepository;
import jr.alcemir.personAPI.service.exception.PersonNotFoungException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> ListAll() {
        List<Person> allPeople =  personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoungException {
        Person person = personRepository.findById(id)
                .orElseThrow(()->new PersonNotFoungException(id));

        return personMapper.toDTO(person);
    }
}
