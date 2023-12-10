# Metric Boet API Database Project

## Introduction
Database project uses MySql database to store the information long term. Database is planned to run on AWS cloud and facility all best practises when running the database on the cloud. Most of the objects we store within our system for long term use follow the same architecture. This being a single entity table with a relationship to a manay to many parameter value table  which correlates to a parameter type table. This approach normalises the database structure while still being flexible enough to add new parameters in the future. 

## Schema Diagram
<img src="documentation/metric-boet-database-schema-v1.0.0 .png" alt="Metric Boet Databse Schema Diagram v1.0.0" width="100">
