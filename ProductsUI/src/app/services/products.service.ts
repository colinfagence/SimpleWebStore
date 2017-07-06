// Imports
import { Injectable }                              from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Product, Products }                       from '../models/Products';
import { Observable }                              from 'rxjs/Rx';
import { isNullOrUndefined }                       from 'util'

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class ProductsService {
     // Resolve HTTP using the constructor
     constructor (private http: Http) {}

     // private instance variable to hold base url
     private commentsUrl = 'http://localhost:8080/products';
     private categoryUrl = ( category: String ) => `http://localhost:8080/products/category/${category}`;

     // Fetch all products
     getAllProducts() : Observable<Products> {
         // ...using get request
         return this.http.get(this.commentsUrl)
                        // ...and calling .json() on the response to return data
                         .map((res:Response) => res.json())
                         //...errors if any
                         .catch((error:any) => Observable.throw(error.json().error || error));
     }

     getSingleProduct( id: number ) : Observable<Product> {
         // ...using get request
         return this.http.get( `${this.commentsUrl}/${id}` )
                        // ...and calling .json() on the response to return data
                         .map((res:Response) => res.json())
                         //...errors if any
                         .catch((error:any) => Observable.throw(error.json().error || error));
     }

     getCategoryProducts( category: String ) : Observable<Products> {
         // ...using get request
         return this.http.get( this.categoryUrl( category ) )
                        // ...and calling .json() on the response to return data
                         .map((res:Response) => res.json())
                         //...errors if any
                         .catch((error:any) => Observable.throw(error.json().error || error));         
     }

     getSomeOrAllProducts( category?: String ) : Observable<Products> {
         if( isNullOrUndefined( category ) || category === '' ) {
            return this.getAllProducts( );
         }
         else {
            return this.getCategoryProducts( category );
         }
     }
}