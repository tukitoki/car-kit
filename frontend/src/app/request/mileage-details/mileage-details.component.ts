import { Component, Input } from '@angular/core';
import { DetailMileageDto } from '../../entity/DetailMileageDto';
import { DetailService } from '../../service/detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailDto } from 'src/app/entity/DetailDto';
import { TimetableService } from 'src/app/service/timetable.service';
import { RequestTimeResponse } from 'src/app/entity/RequestTimeResponse';
import { RequestTime } from 'src/app/entity/RequestTime';

@Component({
  selector: 'app-mileage-details',
  templateUrl: './mileage-details.component.html',
  styleUrls: ['./mileage-details.component.css']
})
export class MileageDetailsComponent {

  @Input() dto!: DetailMileageDto;
  @Input() carId!: number | undefined;
  showChangeList: boolean = false;
  showOtherList: boolean = false;
  showReplacements: boolean = false;
  selectedReplacemntId!: number | null;
  selectedDetail?: DetailDto;
  detailIds!: (number | null)[];
  selected!: boolean;
  response!: RequestTimeResponse;
  requestTime!: RequestTime;

  constructor(
    private detailService: DetailService,
    private router: Router,
    private route: ActivatedRoute,
    private timetableService: TimetableService
  ) {
  }

  ngOnInit(): void {
    this.detailIds = []
  }

  toDetail(id: number | null, event: MouseEvent) {
    event.preventDefault();
    window.open("http://localhost:4200/api/detail/" + id);
  }

  showReplacementsButton(id: number | null) {
    if (this.selectedReplacemntId != id && this.showReplacements) {
      this.selectedReplacemntId = id;
    } else if (this.showReplacements == false) {
      this.showReplacements = true;
      this.selectedReplacemntId = id;
    } else {
      this.showReplacements = false;
      this.selectedReplacemntId = -1;
    }
  }

  checkSelected(event: any, id: number | null) {
    this.selected = (event.target as HTMLInputElement).checked;
    if (this.selected) {
      this.detailIds.push(id);
    } else {
      this.detailIds.splice(this.detailIds.indexOf(id), 1);
    }
  }

  ifPresent(id: number | null) {
    return this.detailIds.indexOf(id) != -1
  }

  getTimetable() {
    this.timetableService.infoRequest(this.carId, this.detailIds).subscribe({
      next: value => this.response = value
    })
    if (this.carId !== undefined) {
      this.requestTime = {
        carId: this.carId,
        details: this.detailIds
      }
    }
  }
}
