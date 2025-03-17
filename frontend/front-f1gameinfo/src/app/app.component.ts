import { Component } from '@angular/core';
import { RouterOutlet, Router } from '@angular/router';
import { OnInit } from '@angular/core';

@Component({
    selector: 'app-root',
    imports: [RouterOutlet],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'F1GameInfo';

constructor(private router:Router){}

  ngOnInit(){
    this.router.navigate(["/login"]);
  }
}
