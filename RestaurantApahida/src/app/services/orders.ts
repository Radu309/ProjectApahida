import {Dish} from "./dish";

export interface Order{
  id: number;
  totalPrice: number;
  localDate: string;
  localTime: string;
  status: string;
  dishList: Dish[];
}
