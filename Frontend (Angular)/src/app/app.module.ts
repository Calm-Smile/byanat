import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatTableModule } from '@angular/material/table' 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/table/table.component';
import {HttpClientModule} from '@angular/common/http';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { NgApexchartsModule } from 'ng-apexcharts';
import { MychartComponent } from './components/mychart/mychart.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MychartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, HttpClientModule, MatSortModule,NgApexchartsModule,
    MatTableModule,MatPaginatorModule,BrowserAnimationsModule, FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
