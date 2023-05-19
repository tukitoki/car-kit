import { ModificationDto } from "./ModificationDto";

export interface CarDto {
  id: number | null;
  brand: string;
  model: string | null;
  modificationDto: ModificationDto;
}
