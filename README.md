# ATGAutoTest
This is the Automation Technical Test Repository for ATG

1. Clone project: 
- You can clone this project with this link: https://github.com/linhpham2412/ATGAutoTest.git

2. Open Project: 
- You can open with any IDE you are familiar with but in this instruction, I will follow with IntelliJ

3. Set up Project Structure: 
- Go to File > Project Structure
- Set up the following image below
![image](https://github.com/linhpham2412/ATGAutoTest/assets/72133175/3870a167-abe2-464e-be4f-6d1022f3f1a9)

4. Set up Project configuration:
- Open edit configuration > click + to add a configuration > select TestNG configuration > follow the image below for details in TestNG configuration
![image](https://github.com/linhpham2412/ATGAutoTest/assets/72133175/29810bf0-12e0-4318-9a42-b0f79b1b3cbf)

5. Set up Maven dependencies:
- Open the pom.xml file then click on the Maven tab after that click these 3 buttons sequentially.
![image](https://github.com/linhpham2412/ATGAutoTest/assets/72133175/8634a26e-a0ca-4a45-aeb7-2f26190ab3f7)

6. Web Driver update:
- a.Based on your browser type and version please download the suitable webdriver to your PC.
- - Chrome: https://chromedriver.chromium.org/downloads
- - Firefox: https://github.com/mozilla/geckodriver/releases
- b.Save these 2 drivers to the local location and copy the URL of the folder
- c.Open class src/main/java/atg/automation/selenium/WebDriverCreator.java
- d.Update the URL to this parameter webDriverLocation same format as image below
![image](https://github.com/linhpham2412/ATGAutoTest/assets/72133175/a48fb323-f7fa-4255-a342-6976b3630a42)
