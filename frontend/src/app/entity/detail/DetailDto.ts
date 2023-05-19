import { Time } from "@angular/common";
import { DetailResponse } from "./DetailResponse";

export interface DetailDto {
  id: number | null;
  name: string;
  description: string;
  price: number;
  producer: string;
  timeToDelivery: string;
  dimension: string;
  detailType: string;
  detailResponses: DetailResponse[];
}
