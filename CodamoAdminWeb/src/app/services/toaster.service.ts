import {Injectable} from '@angular/core';
import {IndividualConfig, ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  constructor(private toastrService: ToastrService) {
  }

  showError(message: string): void {
    this.toastrService.error(undefined, message, this.getPartialConfig());
  }

  private getPartialConfig(): Partial<IndividualConfig> {
    return {
      closeButton: false,
      timeOut: 3000,
      progressAnimation: 'increasing',
      positionClass: 'toast-top-right'
    } as Partial<IndividualConfig>;
  }
}
