import { Component, OnInit } from '@angular/core';

import { AppState } from '../../app.service';

import { CategoriesService } from '../../services/categories.service'
import { CategoriesEventsService } from '../../services/CategoryEvents.service'
import { Categories }        from '../../models/Categories'

@Component({
  /**
   * The selector is what angular internally uses
   * for `document.querySelectorAll(selector)` in our index.html
   * where, in this case, selector is the string 'home'.
   */
  selector: 'sidebar',  // <sidebar></sidebar>
  /**
   * We need to tell Angular's Dependency Injection which providers are in our app.
   */
  providers: [ CategoriesService ],
  /**
   * Our list of styles in our component. We may add more to compose many styles together.
   */
  styleUrls: [ './sidebar.component.css' ],
  /**
   * Every Angular template is first compiled by the browser before Angular runs it's compiler.
   */
  templateUrl: './sidebar.component.html'
})
export class SideBarComponent implements OnInit {
  /**
   * Set our default values
   */
  public localState = { value: '' };

  private categories: String[];
  /**
   * TypeScript public modifiers
   */
  constructor(
    public appState: AppState,
    public categoriesService: CategoriesService,
    private categoryChangeEvent: CategoriesEventsService
  ) {}

  public ngOnInit() {
    console.log('Hello from the `sideBar` component');
    this.categoriesService.getAllCategories().subscribe( ( categoriesObject: Categories ) => {
       this.categories = categoriesObject.categories; 
    },
    err => {
      console.log( err );
    });
  }

  public categoryClick( category: String ) {
    console.log(`Changing category to ${category}`);
    this.categoryChangeEvent.notifyCategoryChange( category );
  }
}
