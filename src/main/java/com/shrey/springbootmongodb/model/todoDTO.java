package com.shrey.springbootmongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")
public class todoDTO {
		
		@Id
		private String id;
		
		private String todo;
		private String description;
		private Boolean completed;
		
		private Date createdAt;
		private Date updatedAt;
		
		
		//setters
		public void setId(String id) {
			this.id = 	id;
		}
		public void setTodo(String todo) {
			this.todo = 	todo;
			}
		public void setDescription(String description) {
			this.description = 	description;
			}
		public void setCompleted(Boolean completed) {
			this.completed = 	completed;
			}
		
		public void setCreatedAt(Date date) {
			this.createdAt = date;
			}
		public void setUpdatedAt(Date date) {
			this.updatedAt = date;
			}

		//getters
		public String getId() {
			return this.id;
		}
		public String getTodo() {
			return this.todo;
			}
		public String getDescription( ) {
			return this.description;
			}
		public Boolean getCompleted() {
			return this.completed;
			}
		
		public Date getCreatedAt( ) {
			return this.createdAt;
			}
		public Date getUpdatedAt( ) {
			return this.updatedAt;
			}

		

		
		
		
	

}
