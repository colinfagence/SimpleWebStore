import { Component, Input, OnInit       } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { AppState                       } from '../../app.service';

import { ProductsService   } from '../../services/products.service';
import { Product }           from '../../models/Products'

@Component({
    selector: 'product-detail',
    providers: [ ProductsService ],
    styleUrls: [ './ProductDetail.component.css'],
    templateUrl: './ProductDetail.component.html'
})
export class ProductDetailComponent implements OnInit {
    public singleProduct: Product;

    constructor( 
        public  appState: AppState,
        private route: ActivatedRoute,
        private router: Router,        
        public  productsService: ProductsService
    ){}

    ngOnInit( ) {
        console.log("Hello from the productDetails component!")

         this.route.params.switchMap((params: Params) => this.productsService.getSingleProduct(+params['id']) )
            .subscribe( ( product: Product ) => {
                console.log( product )
                this.singleProduct = product;
            },
            err => {
                console.log(err);
            })
    }
}