import { Component, Input, OnInit } from '@angular/core';
import { DetailAddResponse } from '../entity/DetailAddResponse';
import { DetailService } from '../service/detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailDto } from '../entity/DetailDto';

@Component({
  selector: 'app-detail',
  templateUrl: './detail-add.component.html',
  styleUrls: ['./detail-add.component.css']
})
export class DetailComponent implements OnInit {

  @Input() detailAddResponse!: DetailAddResponse;
  detail: DetailDto;
  dimens!: string;

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
      timeToDelivery: "2:20",
      dimension: "",
      detailType: "",
      replacementIds: []
    }
  }

  ngOnInit(): void {
    this.detailService.showSaveDetail().subscribe({
      next: value => this.detailAddResponse = value
    })
  }

  saveDetail(): void {
    this.detailService.saveDetail(this.detail).subscribe({
      next: value => this.router.navigate(['/detail'])
    });
  }

  createDimension() {
    this.detailAddResponse.dimensions.push(this.dimens)
  }
}
