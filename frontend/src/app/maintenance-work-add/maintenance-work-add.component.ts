import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaintenanceWorkService } from '../service/maintenance-work.service';

@Component({
  selector: 'app-maintenance-work-add',
  templateUrl: './maintenance-work-add.component.html',
  styleUrls: ['./maintenance-work-add.component.css']
})
export class MaintenanceWorkAddComponent implements OnInit {

  carId!: number;

  constructor(private maintService: MaintenanceWorkService,
              private router: Router,
              private activatedRt: ActivatedRoute) {
}

  ngOnInit(): void {
    this.activatedRt.queryParams.subscribe(params => {
      this.carId = params['id']
    })
  }
}
