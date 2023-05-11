import { AfterViewInit, Component, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MyDataService } from 'src/app/services/my-data.service';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

@Component({
  selector: 'app-mychart',
  templateUrl: './mychart.component.html',
  styleUrls: ['./mychart.component.css']
})
export class MychartComponent implements OnInit, AfterViewInit {
  dataSource: any;
  mydata: any;
  g2 = 0;
  g3 = 0;
  g4 = 0;
  g5 = 0;
  other = 0;


  // totalRecords: any;
  constructor(private myDataService: MyDataService) { }
  ngOnInit(): void {
    this.myDataService.getData().subscribe(x => {
      this.mydata = x;
      // this.totalRecords = this.mydata.length;
      this.mydata.forEach((row: any) => {
        if (row.technology === '2G') {
          this.g2++;
        } else if (row.technology === '3G') {
          this.g3++;
        } else if (row.technology === '4G') {
          this.g4++;
        } else if (row.technology === '5G') {
          this.g5++;
        } else {
          this.other++;
        }
      });
    });
  }

  async ngAfterViewInit(): Promise<void> {
    // wait for data to be processed
    await new Promise(f => setTimeout(f, 800));
    if(this.mydata!=null)
    {
    // Putting data in a chart
    const ops = new Chart("myChart", {
      type: 'bar',
      data: {
        labels: ['2G', '3G', '4G', '5G'],
        datasets: [{
          label: 'Count of Technology',
          data: [this.g2, this.g3, this.g4, this.g5],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
          ],
          borderWidth: 1
        }]
      },
      options: {
        plugins: {
          legend: {
              display: true,
              labels: {generateLabels: (chart:any) => {
                const datasets = chart.data.datasets;
                return datasets[0].data.map((data:any, i:any) => ({
                  text: `${chart.data.labels[i]} tech: ${data}      `,
                  fillStyle: datasets[0].backgroundColor[i],
                  index: i
                }))}}
            }
          },
        scales: {
          y: {
            beginAtZero: true,
          }
        }
      }
    });
    // const config = {
    //   type: 'bar',
    //   myChar,
    //   options: {
    //     plugins:{
    //       legend:{
    //         labels:{
    // generateLabels: (myChar: any) => {
    // return myChar.data.labels.map((this.label,this.index) => ({
    // text: this.label,
    // strokeStyle: myChar.data.datasets[0].borderColor[this.index],
    // fillStyle: myChar.data.datasets[0].backgroundColor[this.index]
    // })
    // );}
  // },}}
    //     scales: {
    //       y: {
    //         beginAtZero: true
    //       }
    //     }
    //   }
    // };


// })
// }
//         }
//       }
//     };
  } else {this.ngAfterViewInit();}
}
}
