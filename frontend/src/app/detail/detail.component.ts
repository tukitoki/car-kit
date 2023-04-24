import { Component, OnInit } from '@angular/core';
import { DetailAddResponse } from '../entity/DetailAddResponse';
import { DetailService } from '../service/detail.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  detailAddResponse!: DetailAddResponse;
  dimensionName:string = "";

  constructor(
    private detailService: DetailService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    
  }

  ngOnInit(): void {
    this.detailService.showSaveDetail().subscribe({
      next: value => this.detailAddResponse = value
    })
  }

  createDimension() {
    this.detailAddResponse.dimensions.push(this.dimensionName)
  }
}
