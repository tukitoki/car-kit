import { Component, Input, OnInit } from '@angular/core';
import { DetailAddResponse } from '../entity/detail/DetailAddResponse';
import { DetailService } from '../service/detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailDto } from '../entity/detail/DetailDto';
import { AuthService } from '../service/auth.service';
import Swal from 'sweetalert2';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-add-detail',
  templateUrl: './detail-add.component.html',
  styleUrls: ['./detail-add.component.css']
})
export class DetailAddComponent implements OnInit {

  @Input() detailAddResponse!: DetailAddResponse;
  detail: DetailDto;
  dimens!: string;
  showedDetails!: DetailDto[];
  selected!: boolean;

  constructor(
    private detailService: DetailService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.detail = {
      id: -1,
      name: "",
      description: "",
      price: 0.0,
      producer: "",
      timeToDelivery: "",
      dimension: "",
      detailType: "",
      count: 0.0,
      detailResponses: []
    }
  }

  ngOnInit(): void {
    if (this.authService.username.getValue() == null) {
      Swal.fire("Login to see this page").then(() => this.router.navigate(['/login']));
      return;
    } else if (!this.authService.checkAuthoritiy('CARS_EDIT')) {
      Swal.fire("U can't access this page").then(() => this.router.navigate(['/main']))
      return;
    }
    this.detailService.showSaveDetail().subscribe({
      next: value => {
        this.detailAddResponse = value;
      }
    })
  }

  saveDetail(): void {
    this.detailService.saveDetail(this.detail).subscribe({
      next: value => this.router.navigate(['/cars'])
    });

  }

  createDimension() {
    this.detailAddResponse.dimensions.push(this.dimens)
  }

  filterReplaces(event: any) {
    this.showedDetails = this.detailAddResponse.details
      .filter(det => det.detailType == this.detail.detailType)
  }

  checkSelected(event: any, id: number) {
    this.selected = (event.target as HTMLInputElement).checked;
    if (this.selected) {
      this.detail.detailResponses.push({id: id, name: "", dimension: "", count: -1});
    } else {
      this.detail.detailResponses.splice(this.detail.detailResponses.map(d => d.id).indexOf(id), 1);
    }
  }

  toDetail(id: number, event: MouseEvent) {
    event.preventDefault();
    window.open(`${environment.baseUrl}/detail/${id}`);
  }

  ifPresent(id: number) {
    return this.detail.detailResponses.map(d => d.id).indexOf(id) != -1
  }
}
