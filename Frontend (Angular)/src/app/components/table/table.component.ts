import { AfterViewInit, Component, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MyDataService } from 'src/app/services/my-data.service';
import {MatPaginator} from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';



@Component({
  selector: 'app-home',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

export class HomeComponent implements AfterViewInit{
  title='data-table';
  dataSource!: MatTableDataSource<any>;
  data: any;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;
  
  columnsToDisplay= ['tower_id','operator','address','height','tower_type','latitude','longitude','technology'];
  
  constructor(private myDataService: MyDataService){
    this.dataSource = new MatTableDataSource();
    this.myDataService.getData().subscribe(x=> {
      // this.data=x;
      this.dataSource = new MatTableDataSource(x);
      this.dataSource.paginator = this.paginator;

      this.dataSource.sort = this.sort;
  });
}

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource();
    this.dataSource.sort = this.sort;
    console.log(this.dataSource);
  }
}
