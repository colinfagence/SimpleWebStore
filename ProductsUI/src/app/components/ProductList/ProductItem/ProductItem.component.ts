import { Component, Input, OnInit } from '@angular/core';
import { AppState } from '../../../app.service';

import { ProductsService   } from '../../../services/products.service';
import { Product }           from '../../../models/Products'

@Component({
    selector: 'product-item',
    providers: [ ],
    styleUrls: [ './ProductItem.component.css'],
    templateUrl: './ProductItem.component.html'
})
export class ProductItemComponent implements OnInit {
    @Input() public singleProduct: Product;

    constructor( 
        public appState: AppState
    ){}

    ngOnInit( ) {
        console.log("Hello from the productItem component!")
    }

    clicked() {
        console.log( `Product ${this.singleProduct.name} has been clicked` )
    }
}