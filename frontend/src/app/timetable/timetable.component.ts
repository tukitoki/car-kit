import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RequestTimeResponse } from '../entity/request/RequestTimeResponse';
import { RequestTime } from '../entity/request/RequestTime';
import { TimetableService } from '../service/timetable.service';
import { RequestApplyResponse } from '../entity/request/RequestApplyResponse';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent {

  @Input() response!: RequestTimeResponse;
  selectedDate!: string | null;
  selectedStartTime!: string | null;
  selected!: boolean;
  @Input() requestTime!: RequestTime;
  requestApply!: RequestApplyResponse;
  telephone!: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private timetableService: TimetableService
  ) {

  }

  ngOnInit(): void {
    this.selectedDate = null;
    this.selectedStartTime = null;
  }

  selectDate(date: string) {
    if (this.selectedDate == date) {
      this.selectedDate = null;
      this.selectedStartTime = null;
    } else {
      this.selectedDate = date;
    }
  }

  checkSelected(time: string) {
    if (this.selectedStartTime == time) {
      this.selectedStartTime = null;
    } else {
      this.selectedStartTime = time;
    }
    console.log(this.selectedStartTime)
  }

  sendRequest() {
    const phoneRegex = /^\+[1-9]\d{10}$/;
    if (!phoneRegex.test(this.telephone)) {
      Swal.fire("Неправильный формат телефона")
      return
    }
    this.requestApply = {
      requestTime: this.requestTime,
      date: this.selectedDate,
      startTime: this.selectedStartTime,
      phoneNumber: this.telephone,
      map: Object.fromEntries(this.requestTime.details)
    }

    console.log(this.requestApply)
    this.timetableService.sendRequest(this.requestApply).subscribe({
      next: value => this.router.navigate([''])
    });
  }
}
