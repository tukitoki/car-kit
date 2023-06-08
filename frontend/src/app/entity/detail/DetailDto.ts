import { Time } from "@angular/common";
import { DetailResponse } from "./DetailResponse";

export interface DetailDto {
  id: number;
  name: string;
  description: string;
  price: number;
  producer: string;
  timeToDelivery: string;
  dimension: string;
  count: number;
  detailType: string;
  detailResponses: DetailResponse[];
}
