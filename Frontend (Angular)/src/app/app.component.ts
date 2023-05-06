import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'byanat';
  public show:boolean = true;
  public show1:boolean = false;
  public buttonName:any = 'Show Towers Tech Chart';

  ngOnInit () {  }

  toggle() {
    this.show = !this.show;
    this.show1 = !this.show1;

    // Change the name of the button.
    if(!this.show && this.show1)  
      this.buttonName = "Show Towers Data Table";
    else
      this.buttonName = "Show Towers Tech Chart";
  }
}
