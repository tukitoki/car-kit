import { DetailDto } from "./DetailDto"

export interface DetailAddResponse {
  detailTypes: string[]
  dimensions: string[]
  details: DetailDto[]
}
