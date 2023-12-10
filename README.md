# Metric Boet API

Metric Boet API is a comprehensive full-stack application designed to empower farmers in effectively managing users, basic static web pages, and field devices within their agricultural operations. Field devices serve the crucial function of monitoring specific areas of the farm, enabling farmers to stay informed and take timely actions as needed. Leveraging AWS as its cloud provider, the application utilizes an Application Load Balancer (ALB) with GoLang Lambda serving as the backend, while the frontend is developed using the Amplify Vue application. Additionally, the database is hosted on AWS.

## Structure

Metric Boet API is organized with a simple and intuitive file structure. For more in-depth information about each subfolder, please refer to the respective README.md files. This approach is designed to provide developers with a clear understanding of the project without overwhelming them. It is highly recommended to read all README.md files before running the application locally.

bash
````
|+- Metric Boet API Application ROOT
|--- .env
|--- .gitignore
|--- backend/
|--- database/
|--- frontend/
|--- tester/
|--- README.md
````
### Subfolder Details

* **backend/** Contains the backend logic and functionality. Refer to the backend/README.md for specific details.

* **database/** Hosts information related to the database. Explore the database/README.md for configuration and usage instructions.

* **frontend/:** Encompasses the frontend codebase. Check the frontend/README.md for guidance on setup and usage.

* **tester/:** Involves testing-related components. Find more information in the tester/README.md for testing instructions.

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)