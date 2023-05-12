import { Component, Input, OnInit } from '@angular/core';
import { DetailAddResponse } from '../entity/DetailAddResponse';
import { DetailService } from '../service/detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailDto } from '../entity/DetailDto';

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
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.detail = {
      id: null,
      name: "",
      description: "",
      price: 0.0,
      producer: "",
      timeToDelivery: "",
      dimension: "",
      detailType: "",
      detailResponses: []
    }
  }

  ngOnInit(): void {
    this.detailService.showSaveDetail().subscribe({
      next: value => {
        this.detailAddResponse = value;
      }
    })
  }

  saveDetail(): void {
    this.detailService.saveDetail(this.detail).subscribe({
      next: value => this.router.navigate(['/api/cars'])
    });

  }

  createDimension() {
    this.detailAddResponse.dimensions.push(this.dimens)
  }

  filterReplaces(event: any) {
    this.showedDetails = this.detailAddResponse.details
      .filter(det => det.detailType == this.detail.detailType)
  }

  checkSelected(event: any, id: number | null) {
    this.selected = (event.target as HTMLInputElement).checked;
    if (this.selected) {
      this.detail.detailResponses.push({id: id, name: ""});
    } else {
      this.detail.detailResponses.splice(this.detail.detailResponses.map(d => d.id).indexOf(id), 1);
    }
  }

  toDetail(id: number | null, event: MouseEvent) {
    event.preventDefault();
    window.open("http://localhost:4200/api/detail/" + id);
  }

  ifPresent(id: number | null) {
    return this.detail.detailResponses.map(d => d.id).indexOf(id) != -1
  }
}
