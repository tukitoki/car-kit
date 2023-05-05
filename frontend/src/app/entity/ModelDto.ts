import { ModificationDto } from "./ModificationDto";

export interface ModelDto {
  model: string;
  modifications: ModificationDto[];
}
