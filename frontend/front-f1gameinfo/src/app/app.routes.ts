import { Routes } from '@angular/router';
import { RegistrarComponent } from './registrar/registrar.component';
import { LoguearComponent } from './loguear/loguear.component';
import { DashboardComponent } from './dashboard/dashboard.component';

export const routes: Routes = [
    { path: 'register', component: RegistrarComponent},
    { path:"login",component:LoguearComponent},
    {path:"dashboard",component:DashboardComponent}

];
