# Taboola Test: Apache Log Analytics

## Overview
The goal of this project is to process an Apache log file and output various analytics about the users of the web server. The program analyzes the log data to provide insights into user demographics and behaviors.

## Metrics Analyzed
The following metrics are extracted and analyzed from the Apache log file:

- **Operating System**
- **Country**
- **Browser**

The program calculates the percentage of each metric in the log file and outputs the results.

## Features
- Parses Apache log files to extract relevant data.
- Calculates percentages for each metric category.
- Outputs analytics for:
  - **Operating Systems** used by visitors.
  - **Countries** from which the requests originate.
  - **Browsers** used by the visitors.

## Usage
1. Ensure you have a valid Apache log file in the specified format.
2. Run the program to generate analytics on operating systems, countries, and browsers.
