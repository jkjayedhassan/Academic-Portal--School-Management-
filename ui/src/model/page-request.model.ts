export class PageRequest {
    pageNumber: number;
    pageSize: number;
    search?: string;
    sortColumn?: string;
    order?: string;

    constructor(pageNumber: number, pageSize: number, sortColumn?: string, order?: string, search?: string) {
        this.pageNumber = pageNumber || 0;
        this.pageSize = pageSize || 10;
        this.search = search || '*';
        this.sortColumn = sortColumn || 'id';
        this.order = order || 'asc';
    }
    getUrlPathString(): string {
        return `${this.pageNumber}/${this.pageSize}/${this.sortColumn || 'id'}/${this.order || 'asc'}/${this.search || '*'}`;
    }
}
