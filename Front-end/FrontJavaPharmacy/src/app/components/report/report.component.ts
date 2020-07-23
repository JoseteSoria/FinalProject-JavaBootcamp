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
  nameTransaction: string;
  periodTransaction: number;
  chartOption1: EChartOption;
  chartOption2: EChartOption;

  tops = [
    {value: '1', viewValue: 'Winner'},
    {value: '3', viewValue: 'Top 3'},
    {value: '5', viewValue: 'Top 5'}
  ];
  bestMedicines = [
    {value: 'medicines-sold', viewValue: 'Best Sold Medicines'},
    {value: 'medicines-revenue', viewValue: 'Medicines With More Income'}
  ];
  routeBestie: string;
  nameBestie: string;
  ranking: number;
  periodBesties: number;

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (this.user == null){
      this.router.navigate(['/login']);
    } else if (this.user.role !== 'ROLE_OWNER') {
      this.router.navigate(['/']);
    }
    else{
      this.httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          Authorization: 'Basic ' + btoa( this.user.username + ':' + this.user.password)
        })
      };

      this.periodTransaction = 1;
      this.periodBesties = 1;
      this.ranking = 2;
    }
  }

  drawChart1(graphRoute: string, graphName: string): void {
    this.routeTransaction = graphRoute;
    this.nameTransaction = graphName;
    let xdata: string[] = [];
    let ydata: number[] = [];
    let ydata2: number[] = [];
    console.log('route ' + this.routeTransaction);
    this.retrieveDataInperiodTransaction1(this.routeTransaction, this.periodTransaction)
    .subscribe((res) => {
      console.log(res);
      res.forEach((result) => {
        xdata.push(result[0]);
        ydata.push(result[1]);
        ydata2.push(result[2]);
      });
      console.log('xdata ' + xdata);
      console.log('ydata ' + ydata);
      console.log('ydata2 ' + ydata2);
      this.chartOption1 = this.populateChart1(this.nameTransaction, xdata, ydata, ydata2);
    });
  }

  retrieveDataInperiodTransaction1(route: string, time: number): Observable<any[]> {
    console.log('http://localhost:8080/reports/' + route + '/' + time, this.httpOptions);
    return this.http.get<any[]>('http://localhost:8080/reports/' + route + '/' + time, this.httpOptions);
  }


  onChange1(): void{
    console.log(this.months);
    console.log(this.periodTransaction);
    console.log(this.routeTransaction);
    this.drawChart1(this.routeTransaction, this.nameTransaction);
  }

  populateChart1(titletxt: string, xdata: any, seriesdata: any, seriesdata2: any): EChartOption {
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

  drawChart2(graphRoute: string, graphName: string): void {
    this.routeBestie = graphRoute;
    this.nameBestie = graphName;
    let xdata: string[] = [];
    let ydata: number[] = [];
    console.log('route ' + this.routeBestie);
    this.retrieveDataInperiodTransaction2(this.routeBestie, this.periodBesties, this.ranking)
    .subscribe((res) => {
      console.log(res);
      res.forEach((result) => {
        xdata.push(result[0]);
        ydata.push(result[1]);
      });
      console.log('xdata ' + xdata);
      console.log('ydata ' + ydata);
      this.chartOption2 = this.populateChart2(this.nameBestie, xdata, ydata);
    });
  }

  retrieveDataInperiodTransaction2(route: string, time: number, ranking: number): Observable<any[]> {
    console.log('http://localhost:8080/reports/' + route + '/' + time + '/' + ranking, this.httpOptions);
    return this.http.get<any[]>('http://localhost:8080/reports/' + route + '/' + time + '/' + ranking, this.httpOptions);
  }

  onChange2(): void{
    console.log(this.periodBesties);
    console.log(this.routeBestie);
    this.drawChart2(this.routeBestie, this.nameBestie);
  }

  populateChart2(titletxt: string, xdata: any, seriesdata: any): EChartOption {
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
      yAxis: { type: 'value'},
      series: [
        {
          name: 'Amount',
          data: seriesdata,
          type: 'bar',
          color: '#ffda00',
          barWidth: 60,
        }
      ],
    };
  }
}
