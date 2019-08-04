import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../list-todos/list-todos.component';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {


  id: number;
  todo: Todo;

  constructor(private todoService: TodoDataService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.todo = new Todo(this.id, '', false, new Date());
    if (this.id != -1) {
      this.todoService.retrieveTodoByIdAndUsername('abhinav', this.id).subscribe(
        response => this.todo = response,
        error => alert("error occured! while retrieving information about todo")
      );
    }
  }

  saveTodo() {

    if (this.id == -1) {
      this.todoService.createTodo('abhinav', this.todo).subscribe(
        response => this.router.navigate(['todos']),
        error => alert("Error while creating!!")
      );
    } else {
      this.todoService.updateTodo('abhinav', this.id, this.todo).subscribe(
        response => this.router.navigate(['todos']),
        error => alert("Error while updating!!")
      );
    }
  }



}
