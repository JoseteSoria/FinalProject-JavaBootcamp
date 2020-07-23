import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

import { User } from 'src/app/models/user/user.model';
import { Observable } from 'rxjs';
import { EChartOption } from 'echarts';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

  selectedValue: string;
  months = [
    {value: '1', viewValue: 'Last Month'},
    {value: '3', viewValue: 'Last 3 Months'},
    {value: '6', viewValue: 'Last 6 Months'},
    {value: '12', viewValue: 'Last Year'},
    {value: '1200', viewValue: 'Always'},
  ];

  transcations = [
    {value: 'sales-users', viewValue: 'Sales By User'},
    {value: 'purchases-patients', viewValue: 'Purchases By Patient'}
  ];
  routeTransaction: string;
  period: number;
  chartOption: EChartOption;

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (this.user == null){
      this.router.navigate(['/login']);
    }
    else{
      this.httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          Authorization: 'Basic ' + btoa( this.user.username + ':' + this.user.password)
        })
      };

      this.period = 1;
    }
  }

  drawChart(graphRoute: string, graphName: string): void {
    this.routeTransaction = graphRoute;
    let xdata: string[] = [];
    let ydata: number[] = [];
    let ydata2: number[] = [];
    console.log('route ' + this.routeTransaction);
    this.retrieveDataInPeriod(this.routeTransaction, this.period)
    .subscribe((res) => {
      console.log(res);
      res.forEach((result) => {
        xdata.push(result[0]);
        ydata.push(result[1]);
        ydata2.push(result[2]);
      });
      // const xdata: string[] = res.map((item) => item.name);
      // const ydata: number[] = res.map((item) => item.count);
      console.log('xdata ' + xdata);
      console.log('ydata ' + ydata);
      console.log('ydata2 ' + ydata2);
      this.chartOption = this.populateChart(graphName, xdata, ydata, ydata2);
    });
  }

  // retrieveDataInPeriod(route: string, time: number){
  //   console.log('http://localhost:8080/reports/' + route + '/' + time, this.httpOptions);
  // }

  retrieveDataInPeriod(route: string, time: number): Observable<any[]> {
    console.log('http://localhost:8080/reports/' + route + '/' + time, this.httpOptions);
    return this.http.get<any[]>('http://localhost:8080/reports/' + route + '/' + time, this.httpOptions);
  }


  onChange(): void{
    console.log(this.months);
    console.log(this.period);
    console.log(this.routeTransaction);
    this.drawChart(this.routeTransaction);
  }

  populateChart(titletxt: string, xdata: any, seriesdata: any, seriesdata2: any): EChartOption {
    console.log('populate data xdata: ' + xdata);
    console.log('populate data seriesdata: ' + seriesdata);
    return {
      backgroundColor: '#fff',
      title: {
        text: titletxt,
        left: 'center',
      },
      color: ['#772304'],
      tooltip: {},
      xAxis: {
        type: 'category',
        data: xdata,
        axisTick: {
          alignWithLabel: true,
        },
      },
      yAxis: [{
        type: 'value',
      }, {
        type: 'value',
      }],
      series: [
        {
          name: 'Nº Times',
          data: seriesdata,
          type: 'bar',
          color: '#ffda00',
          barWidth: 60,
          yAxis: 0
        },
        {
          name: 'Total Price (EUR)',
          data: seriesdata2,
          type: 'bar',
          color: 'rgb(165, 31, 165)',
          barWidth: 60,
          yAxis: 1
        },
      ],
    };
  }

}
