package com.learn.todo.application.todo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

	private long id;
	private String userName;
	private String description;
	private Date targetDate;
	private boolean isCompleted;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		
		return (id != other.id) ? Boolean.FALSE : Boolean.TRUE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

}
