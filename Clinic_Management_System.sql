create database clinic_management_system;
use  clinic_management_system;
create table patients1(
name varchar(100),
age int,
doctorid int,
gender varchar(100));

create table doctor2(
doctorid int,
name varchar(100),
speclalization varchar(100));

create table patient_appointments(
clinic_name varchar(100),
patient_id int,
doctor_id int,
doa date,
patient_status varchar(100),
appointment_status varchar(100),
appointment_fees varchar(100));

create table clinic (

hospital_name varchar(100) not null,
address varchar(200),
city varchar(100),
state varchar(100),
phone varchar(20),
email varchar(100),
established_date date
);
INSERT INTO clinic (
    hospital_name, address, city, state, phone, email, established_date
) VALUES (
    'siddhivinayak hospital', ' 123 Road', 'Pune', 'Maharashtra', '9864736456', 'info@siddhivinayak.com', '1998-07-01'
);

select*from patients1;
select *from doctor2;
select*from patient_appointments;
select*from clinic;
