import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaintenanceWorkService } from '../service/maintenance-work.service';
import { MaintenanceWorkDto } from '../entity/MaintenanceWorkDto';
import { MaintenanceAddResponse } from '../entity/MaintenanceAddResponse';

@Component({
  selector: 'app-maintenance-work-add',
  templateUrl: './maintenance-work-add.component.html',
  styleUrls: ['./maintenance-work-add.component.css']
})
export class MaintenanceWorkAddComponent implements OnInit {

  carId!: number;
  dto!: MaintenanceWorkDto;
  addResponse!: MaintenanceAddResponse;

  constructor(private maintService: MaintenanceWorkService,
    private router: Router,
    private activatedRt: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRt.queryParams.subscribe(params => {
      this.carId = params['id']
    })
    this.dto = {
      detailType: '',
      dimension: '',
      timeToChange: ''
    }
    this.maintService.showAddCarWork().subscribe({
      next: value => {
        this.addResponse = value
      }
    })
  }

  sendWork() {
    this.maintService.addCarWork(this.carId, this.dto).subscribe();
    this.router.navigate([`api/cars`])
  }
}
