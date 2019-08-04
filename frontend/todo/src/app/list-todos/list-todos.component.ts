import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Router } from '@angular/router';

export class Todo {
  constructor(
    public id: number,
    public description: string,
    public completed: boolean,
    public targetDate: Date
  ) { }
}

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {

  todos: Todo[];
  message: String;
  //  = [
  //   new Todo(1, "Learn Java", true, new Date()),
  //   new Todo(2, "Become an Expert in spring", true, new Date()),
  //   new Todo(3, "Become an Expert in angular", true, new Date())
  // ]

  // todo = {
  //   id: 1,
  //   description: "Learn Java"
  // };

  constructor(private todoService: TodoDataService,
    private router: Router
  ) { }

  ngOnInit() {
    this.refreshTodos();
  }

  refreshTodos() {
    this.todoService.retrieveAllTodos('abhinav').subscribe(
      response => {
        this.todos = response;
      }, error => {
        alert('Error occured!! ');
        console.log(error);
      }
    )
  }

  deleteTodoByIdAndUsername(id, todoName) {
    this.todoService.deleteTodoByIdAndUsername('abhinav', id).subscribe(
      response => {
        this.message = `Deletion of Todo With desctiption "${todoName}" is successful.`;
        this.refreshTodos();
      },
      error => alert(`Error while deleting todo with id ${id}.`)
    );
  }

  updateTodoByIdAndUsername(id) {
    this.router.navigate(['todos', id]);
  }


  addTodo() {
    this.router.navigate(['todos', -1]);
  }
}
