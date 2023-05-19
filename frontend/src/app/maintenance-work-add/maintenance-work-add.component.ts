import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaintenanceAddResponse } from '../entity/MaintenanceAddResponse';
import { MaintenanceWorkDto } from '../entity/MaintenanceWorkDto';
import { AuthService } from '../service/auth.service';
import { MaintenanceWorkService } from '../service/maintenance-work.service';
import Swal from 'sweetalert2';

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
    private authService: AuthService,
    private router: Router,
    private activatedRt: ActivatedRoute) {
  }

  ngOnInit(): void {
    if (this.authService.username.getValue() == null) {
      Swal.fire("Login to see this page").then(() => this.router.navigate(['/login']));
      return;
    } else if (!this.authService.checkAuthoritiy('MAINT_WORK')) {
      Swal.fire("U can't access this page").then(() => this.router.navigate(['/main']))
      return;
    }
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
    this.router.navigate([`cars`])
  }
}
