import { Component, OnInit, OnDestroy } from '@angular/core';
import { AppState } from '../../app.service';

import { isUndefined } from 'util'

import { ProductsService   } from '../../services/products.service';
import { CategoriesEventsService } from '../../services/CategoryEvents.service'
import { Product, Products } from '../../models/Products'
import { Subscription } from 'rxjs/Subscription';

@Component({
    selector: 'product-list',
    providers: [ ProductsService ],
    styleUrls: [ './ProductList.component.css'],
    templateUrl: './ProductList.component.html'
})
export class ProductListComponent implements OnInit, OnDestroy {
    public products: Product[];
    public categoryHeading: String;

    constructor( 
        public appState           : AppState,
        public productsService    : ProductsService,
        private categoryChangeEvent: CategoriesEventsService
    ){}

    private _subscription: Subscription;
    private _productSub: Subscription;

    ngOnInit( ) {
        console.log("Hello from the productlist component!")

        this._subscription = this.categoryChangeEvent.category$.subscribe( event => {
            this.categoryHeading = event;

            this.getSomeOrAllProducts( event );
        }, 
        err => {
            console.log( err );
        });
    }

    private sortPriceAsc( a: Product, b: Product ): number {
        if     ( a.price  <  b.price ) return 1 
        else if( a.price  >  b.price ) return -1;
        
        return 0;
    }

    private sortPriceDsc( a: Product, b: Product ): number {
        if     ( a.price  >  b.price ) return 1 
        else if( a.price  <  b.price ) return -1;
        
        return 0;
    }

    /**
     * Used when you click the label "Price" to sort the returned list.
     */
    clickSortPrice() {
        if( this.appState["sortPriceOrder"] === 0 ) this.appState["sortPriceOrder"] = 1
        else this.appState["sortPriceOrder"] = 0;

        this.sortPrice( this.appState["sortPriceOrder"] );
    }

    private sortPrice( sortOrder: number ) {
        switch ( sortOrder ) {
            case 0:  
                this.products.sort( this.sortPriceAsc );
                break;
            default:
                this.products.sort( this.sortPriceDsc );
                break;
        }
    }

    private getSomeOrAllProducts( category: String ) {
        if( !isUndefined( this._productSub ) ) {
            this._productSub.unsubscribe();
        }

        console.log( `Hello there! [${category}]` );

        this._productSub = this.productsService.getSomeOrAllProducts( category ).subscribe( products => {
            // console.log(products);
            this.products = products.products;

            this.sortPrice( this.appState["sortPriceOrder"] )
            // console.log( this.products )
        },
        err => {
            console.log(err);
        });            
    }

    ngOnDestroy() {
        console.log( "Hello from OnDestroy")
        this._subscription.unsubscribe();
        this._productSub.unsubscribe();
    }
}