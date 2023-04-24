import { DetailResponse } from "./DetailResponse"

export interface DetailAddResponse {
    detailTypes: string[]
    dimensions: string[]
    details: DetailResponse[]
}