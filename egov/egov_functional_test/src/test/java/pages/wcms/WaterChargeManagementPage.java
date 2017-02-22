package pages.wcms;

import com.gargoylesoftware.htmlunit.javascript.host.canvas.ext.WEBGL_compressed_texture_s3tc;
import entities.ptis.*;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WaterChargeManagementPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "propertyIdentifier")
    private WebElement waterConnectionAssesmentNumberTextBox;

    @FindBy(id = "approvalDepartment")
    private WebElement approvalWaterDepartment;

    @FindBy(id = "approvalDesignation")
    private WebElement approvalWaterDesignation;

    @FindBy(id = "approvalPosition")
    private WebElement approvalWaterPosition;

    @FindBy(id = "approvalComent")
    private WebElement approvalWaterComment;

    @FindBy(id = "Forward")
    private WebElement additionalForwardButton;

    @FindBy(id = "Generate Estimation Notice")
    private WebElement generateEstimationNoticeButton;

    @FindBy(id = "moduleName")
    private WebElement searchApplicationService;

    @FindBy(id = "applicationType")
    private WebElement searchApplicationType;

    @FindBy(id = "searchapplication")
    private WebElement searchApplicationButton;

    @FindBy(id = "payBtn")
    private WebElement collectFeesButton;

    @FindBy(id = "totalamounttobepaid")
    private WebElement totalAmount;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
    private WebElement amountToBePaidTextBox;

    @FindBy(css = "input[type='submit'][id='button2']")
    private WebElement button2;

    @FindBy(css = "input[type='button'][id='button2']")
    private WebElement editDCBCloseButton;

    @FindBy(id = "buttonClose")
    private WebElement closeReceiptButton;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(linkText = "Close")
    private WebElement closeSearchApplication;

    @FindBy(id = "approvalNumber")
    private WebElement sanctionNumber;

    @FindBy(id = "Approve")
    private WebElement commissionerApprove;

    @FindBy(id = "Sign")
    private WebElement digitalSignature;

    @FindBy(id = "Generate WorkOrder")
    private WebElement generateWorkOrder;

    @FindBy(id = "Execute Tap")
    private WebElement executeTap;

    @FindBy(id = "Generate Acknowledgement")
    private WebElement generateAcknowledgement;

    @FindBy(id = "Generate Reconnection Ack")
    private WebElement generateReConnectionAcknowledgement;

    @FindBy(id = "monthlyFee")
    private WebElement monthlyFees;

    @FindBy(id = "existingConnection.donationCharges")
    private WebElement donationCharges;

    @FindBy(id = "Create")
    private WebElement createDataEntryScreen;

    @FindBy(id = "consumerCodeData")
    private WebElement hscNumber;

    @FindBy(id = "executionDate")
    private WebElement dataEntryExecutionDate;

    @FindBy(name = "fromDate")
    private WebElement searchApplicationDate;

    @FindBy(id = "applicationNumber")
    private WebElement additionalApplicationNumber;

    @FindBy(name = "applicationNumber")
    private WebElement applicationSearchBox;

    @FindBy(linkText = "Close")
    private WebElement additionalCloseButton;

    @FindBy(id = "app-appcodo")
    private WebElement consumerNumberTextBox;

    @FindBy(id = "submitButtonId")
    private WebElement consumerSearchButton;

    @FindBy(id = "reConnectionReason")
    private WebElement reConnectionReason;

    @FindBy(id = "editDCB")
    private WebElement addEditDCB;

    @FindBy(id = "actualAmount")
    private WebElement dcbActualAmount;

    @FindBy(id = "actualCollection")
    private WebElement dcbActualCollection;

    @FindBy(id = "submitButtonId")
    private WebElement dcbSubmit;

    @FindBy(id = "cashradiobutton")
    private WebElement cashRadio;

    @FindBy(id = "consumerCode")
    private WebElement reConnectionConsumerCode;

    @FindBy(id = "aplicationSearchResults")
    private WebElement applicationSearchTable;

    @FindBy(css = ".panel-title.text-center")
    private WebElement forwardMessage;

    private WebElement appRow1;

    private String message = null;

    public WaterChargeManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterWaterConnectionAssessmentNumber(String number){
        enterText(waterConnectionAssesmentNumberTextBox, number , webDriver);
    }

    public void enterWaterApprovalDetails(ApprovalDetails approvalDetails){

        selectFromDropDown(approvalWaterDepartment , approvalDetails.getApproverDepartment() ,webDriver);
        selectFromDropDown(approvalWaterDesignation , approvalDetails.getApproverDesignation() ,webDriver);
        selectFromDropDown(approvalWaterPosition , approvalDetails.getApprover() ,webDriver);

        clickOnButton(additionalForwardButton , webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void clickOnGenerateNotice(){
        jsClick(generateEstimationNoticeButton , webDriver);
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void searchWaterConnectionApplications(String connectionType , String applicationNumber){

        selectFromDropDown(searchApplicationService , "Water Tax" ,webDriver);
        selectFromDropDown(searchApplicationType , connectionType.replaceAll("_"," ") ,webDriver);

        enterText(applicationSearchBox , applicationNumber , webDriver);
        enterDate(searchApplicationDate , getCurrentDate() , webDriver);
        clickOnButton(searchApplicationButton , webDriver);
    }

    public void clickOnCollectCharges(){
        jsClick(collectFeesButton,webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void toReceiveAmount(){
        jsClick(cashRadio ,webDriver);

        waitForElementToBeVisible(totalAmount , webDriver);
        String amount = totalAmount.getAttribute("value");

        waitForElementToBeClickable(amountToBePaidTextBox ,webDriver);
        amountToBePaidTextBox.sendKeys(amount.split("\\.")[0]);

        jsClick(button2, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void closeSuccessfulPaymentReceiptPage(){

        clickOnButton(closeReceiptButton ,webDriver);
    }
    public void closeSearchApplicationPage(){

        for (String winHandle : webDriver.getWindowHandles()) {
            String title = webDriver.switchTo().window(winHandle).getCurrentUrl();
            if(title.equals("http://kurnool-qa.egovernments.org/wtms/elastic/appSearch/")){
                break;
            }
        }

        clickOnButton(closeSearchApplication , webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void forward() {
        clickOnButton(forwardButton , webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public String getAcknowledgementMessage(){
        message = getTextFromWeb(forwardMessage , webDriver);
        return message;
    }

    public void closeAcknowledgementPage(){
        jsClick(closeSearchApplication , webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void commissionerApprove(){

        enterText(sanctionNumber , "12345" , webDriver);
        jsClick(commissionerApprove ,webDriver);

        switchToNewlyOpenedWindow(webDriver);
        closeAcknowledgementPage();
    }

    public void commissionerDigitalSignature(){
        jsClick(digitalSignature , webDriver);

        switchToNewlyOpenedWindow(webDriver);
        closeAcknowledgementPage();
    }

    public void generateWorkOrder(){

        jsClick(generateWorkOrder , webDriver);

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public String executeTap(){

        WebElement applicationNumber = webDriver.findElement(By.xpath(".//*[@id='page-container']/div[1]/div[2]/div[2]/div[4]"));
        waitForElementToBeVisible(applicationNumber , webDriver);
        String number = applicationNumber.getText();

        jsClick(executeTap , webDriver);

        switchToNewlyOpenedWindow(webDriver);
        closeAcknowledgementPage();
        return number;
    }

    public void commissionerClosureApprove(){

        clickOnButton(commissionerApprove , webDriver);

        switchToNewlyOpenedWindow(webDriver);
        closeAcknowledgementPage();
    }

    public void toGenerateAcknowledgement(){

        clickOnButton(generateAcknowledgement ,webDriver);

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();

        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void enterWaterDataEntryDetails(ApplicantInfo applicantInfo , String assessmentNumber){
        enterText(waterConnectionAssesmentNumberTextBox, assessmentNumber ,webDriver);
        enterText(hscNumber , applicantInfo.getHscNumber() , webDriver);
        enterText(dataEntryExecutionDate , applicantInfo.getConnectionDate() ,webDriver);
    }

    public void estimationFeeDetails(){

        enterText(monthlyFees ,"1000" , webDriver);
        enterText(donationCharges , "100" , webDriver);

        clickOnButton(createDataEntryScreen ,webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public String closesTheDataEntryPage(){

        WebElement successMessage = webDriver.findElement(By.cssSelector(".main-content>table>tbody>tr>td>strong"));
        String message = getTextFromWeb(successMessage , webDriver);

        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);

        return message;
    }

    public String findAdditionalApplicationNumber(){
        String number = webDriver.getCurrentUrl().split("\\=")[1];

        webDriver.close();

        switchToPreviouslyOpenedWindow(webDriver);

        return number;
    }

    public void enterConsumerNumber(String consumerNumber){

        enterText(consumerNumberTextBox, consumerNumber , webDriver);

        clickOnButton(consumerSearchButton ,webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public String enterReConnectionDetails(){
        WebElement acknowledgementNumber = webDriver.findElement(By.id("applicationNumber"));
        String number = getTextFromWeb(acknowledgementNumber , webDriver);

        enterText(reConnectionReason , "Required Again"  ,webDriver );
        return number;
    }

    public void toGenerateReConnectionAcknowledgement(){

        clickOnButton(generateReConnectionAcknowledgement ,webDriver);

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();

        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void clickOnAddEditDCB(){

        clickOnButton(addEditDCB ,webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void enterDetailsOfDCB(){

        enterText(dcbActualAmount , "100" , webDriver);
        enterText(dcbActualCollection , "100" , webDriver);

        clickOnButton(dcbSubmit ,webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public String closesDCBPage(){
        WebElement element = webDriver.findElement(By.xpath("html/body/div[1]/div/table/tbody/tr[1]/td/strong"));
        message = getTextFromWeb(element , webDriver);

        clickOnButton(editDCBCloseButton , webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    public void collectWaterCharges(){

        clickOnButton(collectFeesButton , webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void closeCollectChargesReceipt(){

        clickOnButton(closeReceiptButton , webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void openSearchApplication(String applicationNumber) {
        appRow1 = getSearchApplicationRowFor(applicationNumber);
        jsClick(appRow1 , webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getSearchApplicationRowFor(String applicationNumber) {

        List<WebElement> applicationRows = applicationSearchTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        for (WebElement applicationRow1 : applicationRows) {
            if (applicationRow1.findElements(By.tagName("td")).get(1).getText().contains(applicationNumber))
                return applicationRow1;
        }

        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }
}