import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../../models/patient/patient.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients: Patient[];
  displayedColumns: string[] = [
    'patientId',
    'name',
    'birthday',
    'phoneNumber'
  ];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Patient[]>('http://localhost:8083/patients')
    .subscribe(patients => this.patients = patients);
  }

}
