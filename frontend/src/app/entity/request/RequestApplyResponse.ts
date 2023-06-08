import { RequestTime } from "./RequestTime";

export interface RequestApplyResponse {
  requestTime: RequestTime;
  date: string | null;
  startTime: string | null;
  phoneNumber: string;
  map: Object
}
