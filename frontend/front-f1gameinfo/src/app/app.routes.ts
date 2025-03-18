import { Routes } from '@angular/router';
import { RegistrarComponent } from './registrar/registrar.component';
import { LoguearComponent } from './loguear/loguear.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { InfoComponent } from './info/info.component';
import { RankingComponent } from './ranking/ranking.component';
import { CartasusuariosComponent } from './cartasusuarios/cartasusuarios.component';
import { ComprarcartasComponent } from './comprarcartas/comprarcartas.component';
import { CrearcartaComponent } from './crearcarta/crearcarta.component';

export const routes: Routes = [
    {path: 'register',component:RegistrarComponent},
    {path:"login",component:LoguearComponent},
    {path:"dashboard",component:DashboardComponent},
    {path:"info",component:InfoComponent},
    {path:"ranking",component:RankingComponent},
    {path:"comprarcartas",component:ComprarcartasComponent},
    {path:"crearcarta",component:CrearcartaComponent},
    {path:"cartasusuarios",component:CartasusuariosComponent}
];
