import { Routes } from '@angular/router';
import { RegistrarComponent } from './registrar/registrar.component';
import { LoguearComponent } from './loguear/loguear.component';
import { InfoComponent } from './info/info.component';
import { RankingComponent } from './ranking/ranking.component';
import { CartasusuariosComponent } from './cartasusuarios/cartasusuarios.component';
import { ComprarcartasComponent } from './comprarcartas/comprarcartas.component';
import { CrearcartaComponent } from './crearcarta/crearcarta.component';
import { AuthGuard } from './auth.guard';

export const routes: Routes = [
  {path: 'register', component: RegistrarComponent},
  {path: 'login', component: LoguearComponent},
  {path: 'info', component: InfoComponent, canActivate: [AuthGuard]},
  {path: 'ranking', component: RankingComponent, canActivate: [AuthGuard]},
  {path: 'comprarcartas', component: ComprarcartasComponent, canActivate: [AuthGuard]},
  {path: 'crearcarta', component: CrearcartaComponent, canActivate: [AuthGuard]},
  {path: 'cartasusuarios', component: CartasusuariosComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: 'login'}
];
