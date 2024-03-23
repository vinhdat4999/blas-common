mvn clean verify sonar:sonar \
  -Dsonar.projectKey=blas-common \
  -Dsonar.projectName='blas-common' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_f128dcd273a57d30df9be45aaacc2f73621843a1
