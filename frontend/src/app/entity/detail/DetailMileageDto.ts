import { DetailDto } from "./DetailDto";

export interface DetailMileageDto {
  countToChange: number;
  detailType: string;
  detailsToChange: DetailDto[];
  otherDetails: DetailDto[];
}
