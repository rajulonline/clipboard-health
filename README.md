# Clipboard Health
### Assignment submission
## Steps to execute the tests

## Pre-Req
## For this project to run, you would need to install below 3 dependencies on your machine:
- Java 11 (as the core programming language)
- Maven 3.8.5 (for dependency management)
- Google Chrome latest version (browser to run your tests)

## Steps to execute on local
- Clone the repo SSH - git@github.com:rajulonline/clipboard-health.git
- Run mvn install to install all dependencies and packages.
- Open the choices.conf file and point the HOST to localhost => HOST = "host.localhost"
# Heads up - FYI - I have added the @Test tag to only of the tests - ValidateProductAboutSectionTest
- After a successful install of mvn, run the following command
- mvn test
- This should run the ValidateProductAboutSectionTest.

## Steps to execute on selenium grid
- Open the choices.conf file and point the HOST to localhost => HOST = "host.docker.selenium.grid"
- My personal laptop runs on mac M1, so i had to use Experimental Mult-Arch aarch64/armhf/amd64 Images (https://github.com/seleniumhq-community/docker-seleniarm)
- Ran the following command on my terminal docker run --rm -it -p 4444:4444 -p 5900:5900 -p 7900:7900 --shm-size 16g seleniarm/standalone-chromium:latest to spin up the standalone container images.
- Connect to VNC on http://localhost:7900 with password "secret"
- You can connect to the grid on http://localhost:4444/ui
- Run the following command "mvn test" to kick off the test
- Click on the "Sessions" link on the Selenium Grid page to view the current session in play
- Click on the video icon for the current session to be navigated to VNC to watch the test execution live.
