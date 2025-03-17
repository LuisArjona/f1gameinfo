import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ParkService } from '../park.service';

@Component({
    selector: 'app-dashboard',
    imports: [FormsModule],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  dinosaurios:any[]=[];
  recintos:any[]=[];
  dinosauriosIds:any[]=[];
  recintosIds:any[]=[];
  parque:any=null;
  monedas:number=0;
  userId:String="";



  constructor(private http: HttpClient, private parkService:ParkService) { }

  ngOnInit(){
    this.parkService.getPilotos().subscribe((data)=>{this.dinosaurios=data});
    this.parkService.getCircuitos().subscribe((data)=>{this.recintos=data});
    this.parkService.getParkStatus().subscribe((data) => {
      this.parque = data;
      this.userId=this.parque.userId;
      this.monedas = this.parque.coins;
      this.dinosauriosIds = this.parque.dinosaurIds;
      this.recintosIds = this.parque.recintosIds;
    });
  }

  sumarMonedas():void{
    this.monedas+=100;
    this.parkService.updatePark(this.userId,this.dinosauriosIds,this.recintosIds,this.monedas);
  }

  comprarDino(id:any, monedas:any):void{
    if(! this.dinosauriosIds.includes(id)){
      this.dinosauriosIds.push(id);
      this.monedas-=monedas;
      this.parkService.updatePark(this.userId,this.dinosauriosIds,this.recintosIds,this.monedas);
    }

  }

  comprarReci(id:any, monedas:any):void{
    if(! this.recintosIds.includes(id)){
      this.recintosIds.push(id);
      this.monedas-=monedas;
      this.parkService.updatePark(this.userId,this.dinosauriosIds,this.recintosIds,this.monedas);
    }

  }









}
