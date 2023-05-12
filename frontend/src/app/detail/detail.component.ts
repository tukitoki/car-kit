import { Component, Input } from '@angular/core';
import { DetailDto } from '../entity/DetailDto';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailService } from '../service/detail.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent {

  @Input() detail!: DetailDto

  constructor(
    private detailService: DetailService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getDetailFromServer(this.route.snapshot.params['id'])
  }

  private getDetailFromServer(id: number) {
    this.detailService.getById(id).subscribe({
      next: value => this.detail = value
    })
  }

  toDetail(id: number | null, event: MouseEvent) {
    event.preventDefault();
    window.open("http://localhost:4200/api/detail/" + id);
  }
}
