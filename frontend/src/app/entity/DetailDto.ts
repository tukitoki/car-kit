import { Time } from "@angular/common";

export interface DetailDto {
  id: number | null;
  name: string;
  description: string;
  price: number;
  producer: string;
  timeToDelivery: string;
  dimension: string;
  detailType: string;
  replacementIds: number[];
}
