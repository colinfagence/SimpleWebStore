import { Injectable, EventEmitter } from '@angular/core';
import { Observable }               from 'rxjs/Rx';
import { BehaviorSubject }          from 'rxjs/BehaviorSubject';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CategoriesEventsService {
    private _category = new BehaviorSubject<String>('');

    category$ = this._category.asObservable();

    notifyCategoryChange( newCategory: String ) {
        this._category.next( newCategory );
    }
};