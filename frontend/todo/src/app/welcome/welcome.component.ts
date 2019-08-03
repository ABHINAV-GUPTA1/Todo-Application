import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WelcomeDataService } from '../service/data/welcome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})

export class WelcomeComponent implements OnInit {

  name: string;
  welcomeMessageFromResponse: string

  constructor(private service: WelcomeDataService, 
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.name = this.route.snapshot.params['name'];
  }

  getWelcomeMessage() {
    this.service.executeHelloWorldBeanService().subscribe(
      // response=>console.log(response)
      response=>this.handleSuccessfullResponse(response),
      error=>this.handleErrorResponse(error)
    );
    console.log("Last line of getWelcome message!");
  }

  getWelcomeMessageWithParameter() {
    this.service.executeHelloWorldBeanServiceWithPatVariable(this.name).subscribe(
      response=>this.handleSuccessfullResponse(response),
      error=>this.handleErrorResponse(error)
    );
  }

  handleSuccessfullResponse(response) {
    // console.log(response);
    // console.log(response.message);
    this.welcomeMessageFromResponse = response.message;
  }

  handleErrorResponse(error) {
    alert((error ? (error.error ? (error.error.message ? error.error.message : "some error") : "some error") :"some error"));
  }
}
