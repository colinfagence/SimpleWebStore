import {
  Component,
  OnInit
} from '@angular/core';

import { AppState } from '../../app.service';

@Component({
  /**
   * The selector is what angular internally uses
   * for `document.querySelectorAll(selector)` in our index.html
   * where, in this case, selector is the string 'home'.
   */
  selector: 'titlebar',  // <titlebar></titlebar>
  /**
   * We need to tell Angular's Dependency Injection which providers are in our app.
   */
  providers: [ ],
  /**
   * Our list of styles in our component. We may add more to compose many styles together.
   */
  styleUrls: [ './titlebar.component.css' ],
  /**
   * Every Angular template is first compiled by the browser before Angular runs it's compiler.
   */
  templateUrl: './titlebar.component.html'
})
export class TitleBarComponent implements OnInit {
  /**
   * Set our default values
   */
  public localState = { value: '' };
  /**
   * TypeScript public modifiers
   */
  constructor(
    public appState: AppState
  ) {}

  public ngOnInit() {
    console.log('Hello from the `TitleBar` component');
    /**
     * this.title.getData().subscribe(data => this.data = data);
     */
  }
}
