# blas-common

## Changes

| Version | Date release | Tickets/Notes                                                                                                    |
|---------|--------------|------------------------------------------------------------------------------------------------------------------|
| 5.7.0   | 24/05/2024   | Support Global ID to trace the request                                                                           |
| 5.6.0   | 22/04/2024   | Support blas-jet                                                                                                 |
| 5.5.0   | 18/04/2024   | Create ObjectMapper bean                                                                                         |
| 5.4.0   | 17/04/2024   | Support new MarketInfo endpoint                                                                                  |
| 5.3.0   | 04/04/2024   | Support Hazelcast on Kubernetes                                                                                  |
| 5.2.0   | 23/03/2024   | Upgrade Java 21                                                                                                  |
| 5.1.0   | 23/03/2024   | Extract configuration                                                                                            |
| 5.0.0   | 18/03/2024   | Support new HttpRequest to send HTTP Request and new Centralized Log                                             |
| 4.12.0  | 02/02/2024   | Add new method AES encrypt, decrypt and support check report                                                     |
| 4.11.0  | 29/01/2024   | Update Blas Error Code list                                                                                      |
| 4.10.0  | 14/01/2024   | Add Blas report generator                                                                                        |
| 4.9.0   | 01/01/2024   | Add time zone configuration and PDF password                                                                     |
| 4.8.0   | 29/12/2023   | Support VNPay payment                                                                                            |
| 4.7.0   | 19/12/2023   | Remove and revoke plain text email password                                                                      |
| 4.6.0   | 12/12/2023   | Change BlasGateInfo and CentralizedLog to use MongoDB                                                            |
| 4.5.0   | 05/12/2023   | Support Github Packages Maven Repository                                                                         |
| 4.4.0   | 01/12/2023   | Upgrade spring boot 3.2.0                                                                                        |
| 4.3.0   | 24/10/2023   | Add new File utilities and Hazelcast Message Topic                                                               |
| 4.2.0   | 23/09/2023   | Support OAuth2                                                                                                   |
| 4.1.0   | 22/09/2023   | Support Hazelcast caching and MongoDB                                                                            |
| 4.0.0   | 21/08/2023   | Support Blas Error Code and change to user username to verify account                                            |
| 3.5.0   | 19/08/2023   | Support GoogleSheet storage                                                                                      |
| 3.4.0   | 09/08/2023   | Add Github Action and support guest card payment                                                                 |
| 3.3.0   | 31/07/2023   | Add receipt URL attribute for charge response blas-payment-gateway and support send email using Excel email data |
| 3.2.0   | 30/06/2023   | Support send Telegram message                                                                                    |
| 3.1.0   | 11/06/2023   | Support check maintenance time                                                                                   |
| 3.0.0   | 02/06/2023   | Add status code to blas http response                                                                            |
| 2.11.0  | 02/06/2023   | Add spending report email template, set automatic redirect following for POST and PUT request                    |
| 2.10.0  | 25/05/2023   | Upgrade spring boot 3.1.0                                                                                        |
| 2.9.0   | 01/05/2023   | Add email template notify when add new card successfully                                                         |
| 2.8.0   | 30/04/2023   | Support more log, Blas Payment gateway entity and payment receipt email template                                 |
| 2.7.0   | 29/04/2023   | Add Blas Payment Gateway                                                                                         |
| 2.6.0   | 25/04/2023   | Support AES encryption                                                                                           |
| 2.5.0   | 20/04/2023   | Remove backup security check                                                                                     |
| 2.4.0   | 04/04/2023   | Add backup validate step for BlasGateInterceptor                                                                 |
| 2.3.0   | 03/04/2023   | Add BlasGateInterceptor and improve features                                                                     |
| 2.2.0   | 02/04/2023   | Support PDF utilities and add email template                                                                     |
| 2.1.0   | 30/03/2023   | Upgrade Spring boot 3.0.5                                                                                        |
| 2.0.0   | 23/03/2023   | Support JsonUtils and refactor with new syntax                                                                   |
| 1.31.0  | 20/03/2023   | Support handlers exception blas-email                                                                            |
| 1.30.0  | 19/03/2023   | Support monitoring blas-email                                                                                    |
| 1.29.0  | 17/03/2023   | Support email template                                                                                           |
| 1.28.0  | 16/03/2023   | Fix Sonarqube issues                                                                                             |
| 1.27.0  | 13/03/2023   | Update config JWT                                                                                                |
| 1.26.0  | 06/03/2023   | Support custom secret JWT                                                                                        |
| 1.25.0  | 16/11/2022   | Add Blas Configuration system                                                                                    |
| 1.24.0  | 13/11/2022   | Add send email for internal application                                                                          |
| 1.23.0  | 10/11/2022   | Add Centralized Logger                                                                                           |
| 1.22.0  | 10/11/2022   | Update Statistic Log entity                                                                                      |
| 1.21.0  | 23/10/2022   | Update AuthenKey entity and HTTP services                                                                        |
| 1.20.0  | 22/10/2022   | Add email payload                                                                                                |
| 1.19.0  | 22/10/2022   | Support valid utilities                                                                                          |
| 1.18.0  | 21/10/2022   | Support convert calendar to lunar calendar                                                                       |
| 1.17.0  | 14/10/2022   | Add Statistic Log entity and Role enum                                                                           |
| 1.16.0  | 08/10/2022   | Fix Sonarqube issues                                                                                             |
| 1.15.0  | 06/10/2022   | Support more FileUtils, ImageUtils, ResponseUtils methods                                                        |
| 1.14.1  | 01/09/2022   | Refactor code                                                                                                    |
| 1.13.1  | 05/07/2022   | Add block user and reformat code                                                                                 |
| 1.12.2  | 27/06/2022   | Add new service: Create help                                                                                     |
| 1.11.0  | 17/06/2022   | Export, import CSV and Excel (xlsx, xls)                                                                         |
| 1.10.0  | 14/06/2022   | Add QR code and bar code generator                                                                               |
| 1.9.1   | 13/06/2022   | Add common services                                                                                              |
| 1.8.0   | 12/06/2022   | Add common business logic                                                                                        |
| 1.7.2   | 11/06/2022   | New utilities: Time, update utilities of service: File                                                           |
| 1.6.0   | 04/06/2022   | New utilities of service: Image resize                                                                           |
| 1.5.2   | 02/06/2022   | New utilities of service: Send HTTP request (JSON body, form-urlencoded)                                         |
| 1.4.0   | 30/05/2022   | Utilities of service: JWT, Send email, Exception, File, ID, List, Security, URL                                  |
