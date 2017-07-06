import { Routes } from '@angular/router';
import { NoContentComponent } from './components/no-content';
import { ProductDetailComponent } from './Components/ProductDetail/ProductDetail.component';
import { ProductListComponent } from './Components/ProductList/ProductList.component';

import { DataResolver } from './app.resolver';

export const ROUTES: Routes = [
  { path: '',      component: ProductListComponent },
  { path: 'product/:id', component: ProductDetailComponent },
  { path: '**',    component: NoContentComponent },
];
