export interface Page<T> {
  contents: T[],
  pageSize: number,
  pageNumber: number,
  totalPages: number,
  totalElements: number,
}
