package atg.automation.reportmanager;

import atg.automation.selenium.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HTMLReporter {
    private static HTMLReporter htmlReporter;
    private static ExtentReports extentReports;
    private static ExtentHtmlReporter extentHTMLReporter;
    private static ExtentTest currentReportClass;
    private static ExtentTest currentReportNode;
    private static String reportOutPutFolderName;

    public HTMLReporter(String reportName, String reportOutPutFolderPrefixName) {
        reportOutPutFolderName = reportOutPutFolderPrefixName + getReportCurrentDateTime();
        extentReports = new ExtentReports();
        extentHTMLReporter = new ExtentHtmlReporter(getCurrentRunningReportLocation() + reportOutPutFolderName + "\\" + reportName);
        extentHTMLReporter.loadXMLConfig("src/main/java/atg/automation/reportmanager/ReportXMLConfig.xml");
        extentReports.attachReporter(extentHTMLReporter);
    }

    public static void initHTMLReporter(String reporterName, String reportOutPutFolderPrefixName) {
        htmlReporter = new HTMLReporter(reporterName, reportOutPutFolderPrefixName);
        htmlReporter.createReportCaptureScreenShotFolder();
    }

    public static HTMLReporter getHtmlReporter() {
        return htmlReporter;
    }

    public static ExtentTest getCurrentReportNode() {
        return currentReportNode;
    }

    private static String getCurrentRunningReportLocation() {
        Path currentWorkingDir = Paths.get("").toAbsolutePath();
        return currentWorkingDir.normalize() + "\\src\\test\\java\\ReportsOutput\\";
    }

    private static String getCaptureImageLocation() {
        return getCurrentRunningReportLocation() + reportOutPutFolderName + "\\CapturedImage\\";
    }

    public String takesScreenshot(WebDriver driver, String fileName) throws IOException {
        String imageName = fileName.replaceAll("[^a-zA-Z0-9]+", "");
        imageName = imageName + "_" + getReportCurrentDateTime();

        File captureLocation = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File saveLocation = new File(getCaptureImageLocation() + imageName + ".png");
        Files.copy(captureLocation.toPath(), saveLocation.toPath());

        return saveLocation.getAbsolutePath();
    }

    public ExtentReports getExtentReports() {
        return extentReports;
    }

    public void createReportClass(String className, String classDescription) {
        currentReportClass = extentReports.createTest(cleanClassName(className), classDescription);
    }

    public void createReportNode(String className, String classDescription) {
        currentReportNode = currentReportClass.createNode(className, classDescription);
    }

    private String cleanClassName(String fullClassName) {
        int lastIndexOfDot = fullClassName.lastIndexOf(".");
        return fullClassName.substring(lastIndexOfDot + 1, fullClassName.length() - 1);
    }

    private String getReportCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timeAndDate = dtf.format(now);
        timeAndDate = timeAndDate.replace("/", "_");
        timeAndDate = timeAndDate.replace(":", "_");
        timeAndDate = timeAndDate.replace(" ", "_");
        return timeAndDate;
    }

    private void createReportCaptureScreenShotFolder() {
        File directory = new File(getCaptureImageLocation());
        boolean isCreated = directory.mkdir();
        if (isCreated) {
            System.out.println("Report capture image output folder created successfully!");
        } else {
            createReportCaptureScreenShotFolder();
        }
    }

    public Markup markupTextWithColor(String text, ExtentColor color) {
        return MarkupHelper.createLabel(text, color);
    }

    public Markup markupJSONCodeBlock(String jsonObject) {
        return MarkupHelper.createCodeBlock(jsonObject, CodeLanguage.JSON);
    }

    public String markupFailedText(String failedString){
        return "<span class='badge white-text red'>"+failedString+"</span>";
    }

    @SneakyThrows
    public String markupCreateAPIInfoBlock(String requestMessage, String requestType, String requestURL, String requestBody, String responseTime, String responseBody, String responseCode) {
        String tableHTMLFrame = Files.readString(Paths.get("src/main/java/atg/automation/reportmanager/tableHTMLFrame.html"));
        String randomID = Utils.generateRandomNumberInRange(100000, 999999);
        String reqRuntimeHTMLReportDivID = "runtimeHTMLReport_div_"+randomID;
        String reqBodyElementID = "req_json_body_" + randomID;
        String reqBodyScriptFunctionName = "req_function_" + randomID;
        String resBodyElementID = "res_json_body_" + randomID;
        String resBodyScriptFunctionName = "res_function_" + randomID;
        responseTime += "ms";
        switch (requestType) {
            case "GET":
                requestType = "<span class='badge white-text green'>GET</span>";
                requestMessage = "<div class=\"collapsible-header\" style=\"color:green;\"><b>"+requestMessage+"</b></div>";
                break;
            case "POST" :
                requestType = "<span class='badge white-text orange'>POST</span>";
                requestMessage = "<div class=\"collapsible-header\" style=\"color:orange;\"><b>"+requestMessage+"</b></div>";
                break;
            case "PUT" :
                requestType = "<span class='badge white-text blue'>PUT</span>";
                requestMessage = "<div class=\"collapsible-header\" style=\"color:blue;\"><b>"+requestMessage+"</b></div>";
                break;
            case "DELETE" :
                requestType = "<span class='badge white-text red'>DELETE</span>";
                requestMessage = "<div class=\"collapsible-header\" style=\"color:red;\"><b>"+requestMessage+"</b></div>";
                break;
        }
        switch (responseCode.charAt(0)){
            case '1', '3' -> responseCode ="<span class='badge white-text orange'>"+responseCode+"</span>";
            case '2' -> responseCode ="<span class='badge white-text green'>"+responseCode+"</span>";
            case '4', '5' -> responseCode ="<span class='badge white-text red'>"+responseCode+"</span>";
        }
        tableHTMLFrame = tableHTMLFrame.replaceAll("%RequestMessage%", requestMessage);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%runtimeHTMLReport-div%", reqRuntimeHTMLReportDivID);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%RequestType%", requestType);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%RequestURL%", requestURL);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%RequestBodyID%", reqBodyElementID);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%RequestBodyFunctionName%", reqBodyScriptFunctionName);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%RequestBodyContent%", requestBody);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%ResponseTime%", responseTime);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%ResponseBodyID%", resBodyElementID);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%ResponseBodyFunctionName%", resBodyScriptFunctionName);
        tableHTMLFrame = tableHTMLFrame.replaceAll("%ResponseBodyContent%", java.util.regex.Matcher.quoteReplacement(responseBody));
        tableHTMLFrame = tableHTMLFrame.replaceAll("%ResponseCode%", responseCode);
        return tableHTMLFrame;
    }

    public Markup markupRequestInfoTable(String requestType, String requestURL, String requestBody, String responseTimeInMiliSecond, String responseBody, String responseCode) {
        String[][] tableData = new String[4][3];
        tableData[0] = new String[]{"Request Type", "Request URL", "Request Body"};
        tableData[1] = new String[]{requestType, requestURL, requestBody};
        tableData[2] = new String[]{"Response Time", "Response Body", "Response Code"};
        tableData[3] = new String[]{responseTimeInMiliSecond + "ms", responseBody, responseCode};
        return MarkupHelper.createTable(tableData);
    }
}
