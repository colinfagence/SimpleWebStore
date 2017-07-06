export interface Product {
    id         : number;
    name       : String;
    description: String;
    price      : number;
}

export interface Products {
    products: Product[];
}

