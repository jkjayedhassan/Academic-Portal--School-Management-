
export interface Sort {
    empty: boolean;
    unsorted: boolean;
    sorted: boolean;
    sortColumn?: string;
}

export interface Page<T> {
    content: T[];
    pageable: Pageable;
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    sort: Sort;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}

export interface Pageable {
    sort: Sort;
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
}

export const defaultPage: Page<any> = {
    content: [],
    pageable: {
        pageNumber: 0,
        pageSize: 10,
        sort: {
            empty: false,
            unsorted: false,
            sorted: true
        },
        offset: 0,
        unpaged: false,
        paged: true
    },
    last: false,
    totalPages: 0,
    totalElements: 0,
    size: 10,
    number: 0,
    sort: {
        empty: false,
        unsorted: false,
        sorted: true
    },
    first: true,
    numberOfElements: 10,
    empty: false
}