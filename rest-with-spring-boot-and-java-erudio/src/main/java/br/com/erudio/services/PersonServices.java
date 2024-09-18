package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<PersonVO> findAll(){
		logger.info("Finding all the people!");
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		logger.info("Finding a person!");
		
		Person vo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerMapper.parseObject(vo, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating a person!");
		Person entity = DozerMapper.parseObject(person, Person.class);
		Person p = repository.save(entity);
		return DozerMapper.parseObject(p, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating a person!");
		
		Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public void delete(Long id) {
		logger.info("Deleting a person!");
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating a person!");
		Person entity = mapper.convertVoToEntity(person);
		PersonVOV2 p = mapper.convertEntityToVo(repository.save(entity));
		return p;
	}
	
}
