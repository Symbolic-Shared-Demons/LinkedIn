import { Component } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { AddPostComponent } from './add-post/add-post.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'linkedin';
}
