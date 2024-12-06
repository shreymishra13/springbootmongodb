package com.shrey.springbootmongodb.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shrey.springbootmongodb.model.todoDTO;
import com.shrey.springbootmongodb.repository.ToDoRepository;

@RestController
public class todoController {

	@Autowired
	private ToDoRepository todoRepo;

	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos(){
		
		List<todoDTO> todos = todoRepo.findAll();
		
		if(todos.size()>0) {
			return new ResponseEntity <List<todoDTO>>(todos, HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>("No Todos available here",HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody todoDTO todo){
		try {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todo.setUpdatedAt(new Date(System.currentTimeMillis()));
			todoDTO toBeSavedTodo = todoRepo.save(todo);
			

			
			return new ResponseEntity<todoDTO>(toBeSavedTodo, HttpStatus.OK);
			
			
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
				
		}
	}
	
	

	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
		
		Optional <todoDTO> todoOptional = todoRepo.findById(id);
		if (todoOptional.isPresent()) {
			return new ResponseEntity<>(todoOptional.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Todo not found",HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
	//updating using put mapping

	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable("id") String id, @RequestBody todoDTO tempTodo){
		
		Optional <todoDTO> todoOptional = todoRepo.findById(id);
		
		
		if (todoOptional.isPresent()) {
			
			todoDTO todo = todoOptional.get();			
			
			todo.setCompleted(tempTodo.getCompleted()!=null ? tempTodo.getCompleted() : todo.getCompleted());
			
			todo.setDescription(tempTodo.getDescription()!=null ? tempTodo.getDescription() : todo.getDescription());
			
			todo.setTodo(tempTodo.getTodo()!=null ? tempTodo.getTodo() : todo.getTodo());
			
			todo.setUpdatedAt(new Date(System.currentTimeMillis()));
			
			todoRepo.save(todo);
			
			return new ResponseEntity<>(todo,HttpStatus.OK);
	
		}
		else {
			return new ResponseEntity<>("Todo the id mentioned not found"+id ,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		
		if(!todoRepo.existsById(id)){
			return new ResponseEntity<>("Data with the id: "+id+" doesn't exist",HttpStatus.NOT_FOUND);
		}
		
		
		try {
			todoRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Successfully with id"+id , HttpStatus.OK);
			
		}catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		
		
	}
	
	

}
