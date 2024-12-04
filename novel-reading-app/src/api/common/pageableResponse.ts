export interface PageableResponse<T> {
    pageNumber: number;
    pageSize: number;
    totalPages: number;
    totalElements: number;
    data: Array<T>;
}
