import { Component } from '@angular/core';
import { RequestDto } from '../entity/request/RequestDto';
import { TimetableService } from '../service/timetable.service';
import { AuthService } from '../service/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Page } from '../entity/Page';
import Swal from 'sweetalert2';
import { CarDto } from '../entity/car/CarDto';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent {

  url = 'cars'
  page!: Page<RequestDto>
  requests!: RequestDto[];
  currPage: number = 1;
  currPageSize: number = 10;
  order!: string;

  constructor(private requestService: TimetableService,
    private authService: AuthService,
    private router: Router,
    private activatedRt: ActivatedRoute) {
}

  ngOnInit(): void {
    if (this.authService.username.getValue() == null) {
      Swal.fire("Login to see this page").then(() => this.router.navigate(['/login']));
      return;
    } else if (!this.authService.checkAuthoritiy('CARS_EDIT')) {
      Swal.fire("U can't access this page").then(() => this.router.navigate(['/main']))
      return;
    }
    this.order = 'ASC';
    this.getAllFromServer(this.currPage, this.currPageSize, this.order);
  }

  private getAllFromServer(currPage: number, currPageSize: number, order: string) {
    this.activatedRt.queryParams.subscribe(params => {
      this.currPage = params['page'],
      this.currPageSize = params['size']
    })
    this.requestService.getAllRequests(this.currPage, this.currPageSize, this.order).subscribe({
      next: value => {
        this.page = value;
        this.requests = this.page.contents;
        setTimeout(() => this.getAllFromServer(currPage, currPageSize, order), 1000);
      }
    })
    console.log(this.page)
  }

  carToDto(carDto: CarDto) {
    return carDto.brand + " " + carDto.model + " " + carDto.modificationDto.name;
  }

  toDetail(id: number | null, event: MouseEvent) {
    event.preventDefault();
    window.open(`${window.location.origin}/detail/${id}`);
  }

  filter() {
    if (this.order == 'ASC') {
      this.order = 'DESC'
    } else {
      this.order = 'ASC'
    }
    this.getAllFromServer(this.currPage, this.currPageSize, this.order)
  }
}
