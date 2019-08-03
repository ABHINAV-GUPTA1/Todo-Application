import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export class HelloWorldBean {
  constructor(private message: string) { }
}

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  constructor(private http: HttpClient) { }

  executeHelloWorldBeanService() {
    // console.log("Hello World Bean Service!");
    return this.http.get<HelloWorldBean>("http://localhost:8090/helloworld");
  }

  executeHelloWorldBeanServiceWithPatVariable(name) {
    // console.log("Hello World Bean Service!");
    return this.http.get<HelloWorldBean>(`http://localhost:8090/helloworld/${name}`);
  }
}
