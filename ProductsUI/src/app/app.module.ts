import { BrowserModule                   } from '@angular/platform-browser';
import { FormsModule                     } from '@angular/forms';
import { HttpModule                      } from '@angular/http';
import { RouterModule, PreloadAllModules } from '@angular/router';
import { NgModule, ApplicationRef        } from '@angular/core';

import { removeNgStyles, createNewHosts, createInputTransfer } from '@angularclass/hmr';

/*
 * Platform and Environment providers/directives/pipes
 */
import { ENV_PROVIDERS               } from './environment';
import { ROUTES                      } from './app.routes';
// App is our top level component
import { AppComponent                } from './app.component';
import { APP_RESOLVER_PROVIDERS      } from './app.resolver';
import { AppState, InternalStateType } from './app.service';

import { NoContentComponent } from './components/no-content';
import { TitleBarComponent  } from './components/titlebar/titlebar.component'; 
import { SideBarComponent   } from './components/sidebar/sidebar.component'; 

import {ProductListComponent} from './Components/ProductList/ProductList.component';
import {ProductItemComponent} from './Components/ProductList/ProductItem/ProductItem.component';

import {ProductDetailComponent} from './Components/ProductDetail/ProductDetail.component';
import { CategoriesEventsService } from './services/CategoryEvents.service'

import '../styles/styles.scss';
import '../styles/headings.css';

// Application wide providers
const APP_PROVIDERS = [
  ...APP_RESOLVER_PROVIDERS,
  AppState
];

type StoreType = {
  state: InternalStateType,
  restoreInputValues: () => void,
  disposeOldHosts: () => void
};

/**
 * `AppModule` is the main entry point into Angular2's bootstraping process
 */
@NgModule({
  bootstrap: [ AppComponent ],
  declarations: [
    AppComponent,
    TitleBarComponent,
    SideBarComponent,
    ProductListComponent,
    ProductItemComponent,
    ProductDetailComponent,
    NoContentComponent
  ],
  /**
   * Import Angular's modules.
   */
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(ROUTES, { useHash: true, preloadingStrategy: PreloadAllModules })
  ],
  /**
   * Expose our Services and Providers into Angular's dependency injection.
   */
  providers: [
    ENV_PROVIDERS,
    APP_PROVIDERS,
    CategoriesEventsService
  ]
})
export class AppModule {

  constructor(
    public appRef: ApplicationRef,
    public appState: AppState
  ) {}

  public hmrOnInit(store: StoreType) {
    if (!store || !store.state) {
      return;
    }
    console.log('HMR store', JSON.stringify(store, null, 2));
    /**
     * Set state
     */
    this.appState._state = store.state;
    /**
     * Set input values
     */
    if ('restoreInputValues' in store) {
      let restoreInputValues = store.restoreInputValues;
      setTimeout(restoreInputValues);
    }

    this.appRef.tick();
    delete store.state;
    delete store.restoreInputValues;
  }

  public hmrOnDestroy(store: StoreType) {
    const cmpLocation = this.appRef.components.map((cmp) => cmp.location.nativeElement);
    /**
     * Save state
     */
    const state = this.appState._state;
    store.state = state;
    /**
     * Recreate root elements
     */
    store.disposeOldHosts = createNewHosts(cmpLocation);
    /**
     * Save input values
     */
    store.restoreInputValues  = createInputTransfer();
    /**
     * Remove styles
     */
    removeNgStyles();
  }

  public hmrAfterDestroy(store: StoreType) {
    /**
     * Display new elements
     */
    store.disposeOldHosts();
    delete store.disposeOldHosts;
  }

}
