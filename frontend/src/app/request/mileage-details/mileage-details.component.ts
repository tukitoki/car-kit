import { Component, Input } from '@angular/core';
import { DetailMileageDto } from '../../entity/detail/DetailMileageDto';
import { DetailService } from '../../service/detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailDto } from 'src/app/entity/detail/DetailDto';
import { TimetableService } from 'src/app/service/timetable.service';
import { RequestTimeResponse } from 'src/app/entity/request/RequestTimeResponse';
import { RequestTime } from 'src/app/entity/request/RequestTime';
import { environment } from 'src/environments/environment';
import { DetailTypeNeedableCount } from 'src/app/entity/DetailTypeNeedableCount';

@Component({
  selector: 'app-mileage-details',
  templateUrl: './mileage-details.component.html',
  styleUrls: ['./mileage-details.component.css']
})
export class MileageDetailsComponent {

  @Input() dto!: DetailMileageDto[];
  @Input() carId!: number | undefined;

  showChangeList: boolean = false;
  showOtherList: boolean = false;
  showDetailList: boolean = false;
  showReplacements: boolean = false;
  selectedType: string;

  detailTypeCount: DetailTypeNeedableCount[];

  selectedReplacemntId!: number | null;
  selectedDetail?: DetailDto;
  detailIds!: (number | null)[];
  detailIdss!: Map<number, number>
  selected!: boolean;
  response!: RequestTimeResponse;
  requestTime!: RequestTime;

  constructor(
    private detailService: DetailService,
    private router: Router,
    private route: ActivatedRoute,
    private timetableService: TimetableService
  ) {
    this.detailTypeCount = []
    this.selectedType = ""
  }

  ngOnInit(): void {
    this.detailIds = []
    this.detailIdss = new Map<number, number>;
    this.dto.forEach(element => {
      this.detailTypeCount.push({ detailType: element.detailType, currentCount: 0, isVisible: false })
    });
  }

  toDetail(id: number | null, event: MouseEvent) {
    event.preventDefault();
    window.open(`${window.location.origin}/detail/${id}`);
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

  checkSelected(event: any, id: number, type: string, counter: number) {
    this.selected = (event.target as HTMLInputElement).checked;
    this.detailTypeCount.forEach(element => {
      if (element.detailType == type) {
        if (event.target.value == 0) {
          this.detailIdss.delete(id)
          element.currentCount = 0
        } else {
          if (this.detailIdss.has(id)) {
            var currCount = this.detailIdss.get(id)
            if (currCount != undefined) {
              if (currCount > event.target.value) {
                element.currentCount -= counter
              } else {
                element.currentCount += counter
              }
            }
          } else {
            element.currentCount += counter
          }
          this.detailIdss.set(id, event.target.value)
        }
        console.log("count after plus " + element.currentCount)
      }
    });
  }

  ifPresent(id: number) {
    return this.detailIdss.hasOwnProperty(id)
    // return this.detailIds.indexOf(id) != -1
  }

  getTimetable() {
    this.timetableService.infoRequest(this.carId, [...this.detailIdss.keys()]).subscribe({
      next: value => this.response = value
    })
    if (this.carId !== undefined) {
      this.requestTime = {
        carId: this.carId,
        details: this.detailIdss
      }
    }
  }

  calcMaximumInput(type: string, id: number) {
    for (let i = 0; i < this.detailTypeCount.length; i++) {
      if (this.detailTypeCount[i].detailType == type) {
        for (let j = 0; j < this.dto.length; j++) {
          if (this.dto[j].detailType == type) {
            if (this.detailTypeCount[i].currentCount >= this.dto[j].countToChange) {
              if (this.detailIdss.has(id)) {
                console.log("maximum for type:" + type, "value:" + this.detailIdss.get(id))
                return this.detailIdss.get(id)
              }
            }
          }
        }
      }
    }
    return 1000000000000;
  }

  isShouldBeDisables(id: number, type: string) {
    for (let i = 0; i < this.detailTypeCount.length; i++) {
      if (this.detailTypeCount[i].detailType == type) {
        for (let j = 0; j < this.dto.length; j++) {
          if (this.dto[j].detailType == type) {
            if (!this.detailIdss.has(id) && this.detailTypeCount[i].currentCount >= this.dto[j].countToChange) {
              return true;
            }
          }
        }
        return false;
      }
    }
    return false;
  }

  getValueInInput(id: number) {
    if (this.detailIdss.has(id)) {
      return this.detailIdss.get(id)
    } else {
      return 0
    }
  }

  setVisibleType(type: string) {
    if (this.selectedType == type) {
      this.selectedType = ""
    } else {
      this.selectedType = type;
    }
  }

  buttonSendVisible() {
    var flag = true
    for (let k = 0; k < this.dto.length; k++) {
      for (let i = 0; i < this.detailTypeCount.length; i++) {
        if (this.detailTypeCount[i].detailType != this.dto[k].detailType) {
          continue;
        }
        if (!this.isNeedToChange(this.dto[k].detailType)) {
          continue;
        }
        if (this.detailTypeCount[i].detailType == this.dto[k].detailType
          && this.dto[k].countToChange <= this.detailTypeCount[i].currentCount) {
          flag = true
        } else {
          return false
        }
      }

    }
    console.log(this.detailIdss.size)
    return flag && this.detailIdss.size > 0;
  }

  private isNeedToChange(type: string) {
    for (let k = 0; k < this.dto.length; k++) {
      for (let i = 0; i < this.dto[k].detailsToChange.length; i++) {
        if (type == this.dto[k].detailsToChange[i].detailType) {
          return true;
        }
      }
    }
    return false;
  }
}
