import { Injectable } from '@angular/core';
import { Todo } from 'src/app/list-todos/list-todos.component';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(private http: HttpClient) { }

  retrieveAllTodos(username) {
    return this.http.get<Todo[]>(`http://localhost:8090/users/${username}/todos`);
  }

  deleteTodoByIdAndUsername(username, id) {
    return this.http.delete(`http://localhost:8090/users/${username}/todos/${id}`);
  }

  retrieveTodoByIdAndUsername(username, id) {
    return this.http.get<Todo>(`http://localhost:8090/users/${username}/todos/${id}`);
  }

  updateTodo(username, id, todo) {
    return this.http.put(`http://localhost:8090/users/${username}/todos/${id}`,
      todo);
  }

  createTodo(username, todo) {
    return this.http.post(`http://localhost:8090/users/${username}/todos`,
      todo);
  }
}
