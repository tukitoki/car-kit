import { CarDto } from "../car/CarDto";
import { DetailResponse } from "../detail/DetailResponse";

export interface RequestDto {
  id: number;
  startTime: string;
  endTime: string;
  phoneNumber: string;
  carDto: CarDto;
  details: DetailResponse[];
}
