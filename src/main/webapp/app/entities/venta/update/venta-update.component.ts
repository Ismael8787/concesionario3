import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IVenta, Venta } from '../venta.model';
import { VentaService } from '../service/venta.service';
import { IUser, User } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { ICoche } from 'app/entities/coche/coche.model';
import { CocheService } from 'app/entities/coche/service/coche.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

@Component({
  selector: 'jhi-venta-update',
  templateUrl: './venta-update.component.html',
})
export class VentaUpdateComponent implements OnInit {
  isSaving = false;

  usersSharedCollection: IUser[] = [];
  cochesSharedCollection: ICoche[] = [];
  userSharedCollection: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    fecha: [null, [Validators.required]],
    numFactura: [null, [Validators.required]],
    precioTotal: [null, [Validators.required]],
    compradorId: [],
    vendedorId: [],
    coche: [],
    comprador: [],
  });
  account: Account | null | undefined;

  constructor(
    protected ventaService: VentaService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    protected cocheService: CocheService,
    protected fb: FormBuilder,
    protected accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ venta }) => {
      this.updateForm(venta);

      this.loadRelationshipsOptions();

      this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const venta = this.createFromForm();
    if (venta.id !== undefined) {
      this.subscribeToSaveResponse(this.ventaService.update(venta));
    } else {
      this.subscribeToSaveResponse(this.ventaService.create(venta));
    }
  }

  trackUserById(index: number, item: IUser): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVenta>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(venta: IVenta): void {
    this.editForm.patchValue({
      id: venta.id,
      fecha: venta.fecha,
      numFactura: venta.numFactura,
      precioTotal: venta.precioTotal,
      // compradorId: venta.compradorId,
      vendedorId: venta.vendedorId,
      coche: venta.coche,
      comprador: venta.comprador,
    });

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing(
      this.usersSharedCollection,
      // venta.compradorId,
      venta.vendedorId
    );
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(
        map((users: IUser[]) =>
          this.userService.addUserToCollectionIfMissing(
            users,
            this.editForm.get('compradorId')!.value,
            this.editForm.get('vendedorId')!.value
          )
        )
      )
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.cocheService
      .queryD()
      .pipe(map((res: HttpResponse<ICoche[]>) => res.body ?? []))
      .pipe(map((coches: ICoche[]) => this.cocheService.addCocheToCollectionIfMissing(coches, this.editForm.get('coche')!.value)))
      .subscribe((coches: ICoche[]) => (this.cochesSharedCollection = coches));
  }

  protected createFromForm(): IVenta {
    return {
      ...new Venta(),
      id: this.editForm.get(['id'])!.value,
      fecha: this.editForm.get(['fecha'])!.value,
      numFactura: this.editForm.get(['numFactura'])!.value,
      precioTotal: this.editForm.get(['precioTotal'])!.value,
      comprador: this.editForm.get(['comprador'])!.value,
      vendedorId: this.account,
      coche: this.editForm.get(['coche'])!.value.id,
    };
  }
}
