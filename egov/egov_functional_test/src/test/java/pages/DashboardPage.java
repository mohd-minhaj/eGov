package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DashboardPage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "searchtree")
    private WebElement searchTreeTextBox;

    @FindBy(linkText = "Create New Property")
    private WebElement createNewPropertyLink;

    @FindBy(linkText = "Data Entry Screen")
    private WebElement dataEntryScreenLink;

    @FindBy(linkText = "Search Property")
    private WebElement searchPropertyLink;

    @FindBy(linkText = "Property Tax")
    private WebElement propertyTaxLink;

    @FindBy(linkText = "Collect Charges")
    private WebElement collectChargesLink;

    @FindBy(className = "profile-name")
    private WebElement profileNameLink;

    @FindBy(linkText = "Create Spillover Estimate")
    private WebElement createSpilloverEstimate;

    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

    @FindBy(id = "official_inbox")
    private WebElement officialInboxTable;

    @FindBy(id = "official_inbox_wrapper")
    private WebElement inboxTable;

    @FindBy(id = "official_drafts")
    private WebElement officialDraftsTable;

    @FindBy(xpath = "//html/body/div[3]/div[2]/div/ul/li[2]/a")
    private WebElement dataEntryScreenLinkText;

    @FindBy(linkText = "Miscellaneous Receipt")
    private WebElement miscellaneousReceipt;

    @FindBy(linkText = "Apply for New Connection")
    private WebElement applyForNewWaterConnection;

    @FindBy(linkText = "Create Challan")
    private WebElement createChallanLink;

    @FindBy(linkText = "Daily collection report(VLT)")
    private WebElement vltReport;

    @FindBy(linkText = "Data Entry Screen")
    private WebElement dataEntryScreen;

    @FindBy(linkText = "Addition/Alteration of Assessment")
    private WebElement additionAlterationLink;

    @FindBy(linkText = "Apply for Additional Connection")
    private WebElement additionalNewWaterConnection;

    @FindBy(linkText = "Challan Receipt")
    private WebElement challanReceiptLink;

    @FindBy(linkText = "Daily collection report(PT)")
    private WebElement ptReport;

    @FindBy(linkText = "Search Application")
    private WebElement searchApplication;

    private WebElement appRow1;

    @FindBy(css = "li[class='dropdown'] a[data-original-title ='Drafts']")
    private WebElement draftsLink;

    @FindBy(linkText = "Collect Tax")
    private WebElement collectPropertyTax;

    @FindBy(linkText = "Apply for Closure of Connection")
    private WebElement closureConnection;

    @FindBy(linkText = "Create Journal Voucher")
    private WebElement createJournalVoucher;

    @FindBy(linkText = "Create Milestone Template")
    private WebElement createMilestoneTemplateLink;

    @FindBy(linkText = "View Milestone Template")
    private WebElement viewMilestoneTemplateLink;

    @FindBy(linkText = "Modify Milestone Template")
    private WebElement modifyMilestoneTemplateLink;

    @FindBy(linkText = "Create Letter of Acceptance")
    private WebElement createLOALink;

    @FindBy(linkText = "Create New License")
    private WebElement createTradeLicense;

    @FindBy(id = "btnsearch")
    private WebElement searchEstimateButton;

    @FindBy(xpath = "(//*[@id='resultTable']/tbody/tr/td/input)[1]")
    private WebElement estimateRadioButton;

    @FindBy(id = "btncreateloa")
    private WebElement createLOAButton;

    @FindBy(linkText = "Bill Payment")
    private WebElement voucherBillPayment;

    @FindBy(linkText = "Search/View LOA")
    private WebElement viewLOA;

    @FindBy(linkText = "Modify LOA")
    private WebElement modifyLOALink;

    @FindBy(linkText = "Create Milestone")
    private WebElement createMilestoneLink;

    @FindBy(linkText = "New Create Expense Bill")
    private WebElement createExpenseBill;

    @FindBy(linkText = "Create Estimate")
    private WebElement createEstimateLink;

    @FindBy(linkText = "Search Trade License")
    private WebElement searchTrade;

    @FindBy(linkText = "Create Legacy License")
    private WebElement searchLegencyTrade;

    @FindBy(linkText = "Modify Detailed Code")
    private WebElement modifyDetailedCode;

    @FindBy(linkText = "Create Remittance Recovery")
    private WebElement createRemittanceRecovery;

    @FindBy(linkText = "Track Milestone")
    private WebElement trackMilestoneLink;

    @FindBy(linkText = "Apply for Change of Use")
    private WebElement changeOfUse;

    @FindBy (linkText = "Create Preamble")
    private WebElement createPreamble;

    @FindBy(linkText = "Create Contractor Bill")
    private WebElement createContractorBillLink;

    @FindBy(linkText = "Create Advertisement")
    private WebElement createAdvertisementLink;

    @FindBy(linkText = "Search Advertisement")
    private WebElement searchAdvertisementLink;

    @FindBy(linkText = "Create Legacy Advertisement")
    private WebElement createLegacyAdvertisementLink;

    @FindBy(linkText = "Update Legacy Advertisements")
    private WebElement updateLegacyAdvertisementLink;

    @FindBy(linkText = "Create Voucher")
    private WebElement createExpenseBillVoucher;

    @FindBy(linkText = "Collect Advertisement Tax")
    private WebElement collectAdvertisementTaxLink;

    @FindBy(linkText = "Create Agency")
    private WebElement createAgencyLink;

    @FindBy(linkText = "Upload Estimate Photographs")
    private WebElement uploadEstimatePhotographslink;

    @FindBy(linkText = "Create Agenda")
    private WebElement createAgenda;

    @FindBy(linkText = "Create Meeting invitation")
    private WebElement createMeetingLink;

    @FindBy(linkText = "Search Agency")
    private WebElement searchAgencyLink;

    @FindBy(linkText = "Enter Attendance")
    private WebElement enterAttendanceLink;
    @FindBy(linkText = "Transfer Ownership")
    private WebElement transferOwnershipLink;

    @FindBy(linkText = "Create Council MOM")
    private WebElement createCouncilMOMlink;

    @FindBy(linkText = "Apply for Re-Connection")
    private WebElement applyForReConnection;

    @FindBy(linkText = "Deactivate Advertisement")
    private WebElement deactivateAdvertisementLink;

    @FindBy(linkText = "Property Mutation Fee")
    private WebElement propertyMutationFeeLink;

    @FindBy(linkText = "Collect Charges")
    private WebElement collectWaterCharges;

    @FindBy(linkText = "City Setup")
    private WebElement citySetup;

    @FindBy(xpath = ".//*[@id='settingstab']/li[2]/a")
    private WebElement municipalityInfo;

    @FindBy(id = "logo")
    private WebElement logoBrowse;

    @FindBy(id = "submitform")
    private WebElement logoSubmitForm;

    @FindBy(xpath = ".//*[@id='cityForm']/div[2]/div/button[3]")
    private WebElement closeButton;

    @FindBy(linkText = "Bank Remittance")
    private WebElement bankRemittanceLink;

    @FindBy(linkText = "Advertisement Renewal")
    private WebElement renewalAdvertisementLink;

    @FindBy(xpath = "html/body/div[1]/header/nav/div/div[3]/ul/li[2]/a")
    private WebElement profileLink;
    @FindBy(linkText = "Create Revision Petition")
    private WebElement createRevisionPetitionLink;

    @FindBy(linkText = "Create Marriage Registration")
    private WebElement createMarriageRegistrationLink;

    @FindBy(linkText = "Cheque Assignment")
    private List<WebElement> chequeAssignment;

    @FindBy(linkText = "RTGS Assignment")
    private WebElement rtgsAssignment;

    @FindBy(linkText = "Officials Register Grievance")
    private WebElement officialRegisterGrievanceLink;

    @FindBy(linkText = "Direct Bank Payments")
    private WebElement directBankPayments;

    @FindBy(css = "a[href^='/stms']")
    private WebElement newSewerageConnectionLink;

    @FindBy(linkText = "Collect Sewerage Charges")
    private WebElement collectSewerageTaxesLink;

    @FindBy(linkText = "Bank to Bank Transfer")
    private WebElement bankToBankTransfer;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseToSearchProperty(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("search property");
        waitForElementToBeVisible(searchPropertyLink, driver);

        searchPropertyLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateNewProperty() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create New Property");
        waitForElementToBeVisible(createNewPropertyLink, driver);

        createNewPropertyLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToDataEntryScreen(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Data Entry Screen");
        waitForElementToBeVisible(dataEntryScreenLink, driver);

        dataEntryScreenLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    private void searchFor(String value) {
        enterText(searchTreeTextBox, value);
    }

    public void logOut() {
        waitForElementToBeVisible(profileNameLink, driver);
        profileNameLink.click();
        waitForElementToBeVisible(signOutLink , driver);
        signOutLink.click();
    }

    public void openApplication(String applicationNumber) {
        getApplicationRowFor(applicationNumber).click();
        switchToNewlyOpenedWindow(driver);
    }

    private WebElement getApplicationRowFor(String applicationNumber) {
        waitForElementToBeVisible(driver.findElement(By.id("worklist")), driver);
        waitForElementToBeVisible(officialInboxTable, driver);

        await().atMost(20, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(applicationNumber))
                return applicationRow;
        }
        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }

    public void chooseToCollectTaxes() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Property Tax");
        waitForElementToBeVisible(propertyTaxLink, driver);
        propertyTaxLink.click();
        switchToNewlyOpenedWindow(driver);
    }


    //It choose the data entry screen from dashboard
    public void chooseToCreateNewDataEntryScreen() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Data Entry Screen");
        waitForElementToBeVisible(dataEntryScreenLinkText, driver);

        dataEntryScreenLinkText.click();

        switchToNewlyOpenedWindow(driver);}

    public void createMiscellenous() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("miscellaneous receipt");
        waitForElementToBeVisible(miscellaneousReceipt,driver);
        miscellaneousReceipt.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateDataEntry() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Data Entry Screen");
        waitForElementToBeVisible(dataEntryScreen, driver);
        dataEntryScreen.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToAdditionAlteration() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Addition/Alteration of Assessment");
        waitForElementToBeVisible(additionAlterationLink, driver);
        additionAlterationLink.click();
        switchToNewlyOpenedWindow(driver);
    }

        public void createSpilloverEstimate()
    {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Spillover Estimate");
        waitForElementToBeVisible(createSpilloverEstimate, driver);
        createSpilloverEstimate.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateNewWaterConnection(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for New Connection");
        waitForElementToBeVisible(applyForNewWaterConnection, driver);

        applyForNewWaterConnection.click();
        switchToNewlyOpenedWindow(driver);

    }

    public void chooseToFindDailyVLTReports(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Daily Collection Report(VLT)");
        waitForElementToBeVisible(vltReport, driver);

        vltReport.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void createChallan() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Challan");

        waitForElementToBeVisible(createChallanLink, driver);
        createChallanLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToAdditionalWaterConnection(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for Additional Connection");
        waitForElementToBeVisible(additionalNewWaterConnection, driver);

        additionalNewWaterConnection.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToSearchForChallanReceipt() {

        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("challan receipt");

        waitForElementToBeVisible(challanReceiptLink,driver);
        challanReceiptLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForDailyCollectionPTReports(){

        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Daily collection report(PT)");
        waitForElementToBeVisible(ptReport, driver);
        ptReport.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForSearchApplication(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Search Application");
        waitForElementToBeVisible(searchApplication, driver);
        searchApplication.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void openDrafts(){
        waitForElementToBeVisible(draftsLink, driver);
        draftsLink.click();
    }

    public void openCollection() {
        getCollectionRow().click();
        switchToNewlyOpenedWindow(driver);
    }

    private WebElement getCollectionRow() {
//        waitForElementToBeVisible(driver.findElement(By.id("drafts")), driver);
        waitForElementToBeVisible(officialDraftsTable, driver);

        await().atMost(10, SECONDS).until(() -> officialDraftsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialDraftsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        List<WebElement> propertyTaxList = new ArrayList<>();

       for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains("Property Tax-944177-Zone-1"))
                propertyTaxList.add(applicationRow);
       }
       return propertyTaxList.get(0);

//       throw new RuntimeException("No application row found for -- ");

    }

    public WebElement getReceiptRow() {
        waitForElementToBeVisible(driver.findElement(By.id("worklist")), driver);
        waitForElementToBeVisible(officialInboxTable, driver);
        await().atMost(10, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        List<WebElement> propertyTaxList = new ArrayList<>();

        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains("Property Tax-944177-Zone-1"))
                propertyTaxList.add(applicationRow);
        }
        return propertyTaxList.get(0);
    }

    public void openReceipt(){
        appRow1 = getReceiptRow();
        waitForElementToBeClickable(appRow1 , driver);
        appRow1.click();
        switchToNewlyOpenedWindow(driver);
    }
    public void choosePropertyTaxCollection() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Collect Tax");
        waitForElementToBeVisible(collectPropertyTax, driver);
        collectPropertyTax.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToApplyForClosureConnection(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for Closure of Connection");
        waitForElementToBeVisible(closureConnection , driver);
        closureConnection.click();
        switchToNewlyOpenedWindow(driver);
    }


    public void chooseToApplyForJournalVoucher(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Journal Voucher");
        waitForElementToBeClickable(createJournalVoucher ,driver);
        createJournalVoucher.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateMilestoneTemplate() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create milestone template");
        waitForElementToBeClickable(createMilestoneTemplateLink,driver);
        createMilestoneTemplateLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToViewMilestoneTemplate() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("View milestone template");
        waitForElementToBeClickable(viewMilestoneTemplateLink,driver);
        viewMilestoneTemplateLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToModifyMilestoneTemplate() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Modify milestone template");
        waitForElementToBeClickable(modifyMilestoneTemplateLink, driver);
        modifyMilestoneTemplateLink.click();
        switchToNewlyOpenedWindow(driver);
    }
    public void chooseToCreateLOA() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Letter of Acceptance");
        waitForElementToBeClickable(createLOALink, driver);
        createLOALink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToModifyLOA() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Modify LOA");
        waitForElementToBeVisible(modifyLOALink, driver);
        modifyLOALink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToViewLOA()
    {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Search/View LOA");
        waitForElementToBeClickable(viewLOA, driver);
        viewLOA.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void selectForVoucherBill() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Bill Payment");
        waitForElementToBeClickable(voucherBillPayment, driver);
        voucherBillPayment.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseTopayWaterCharge() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Collect Charges");
        waitForElementToBeVisible(collectChargesLink, driver);
        collectChargesLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateNewLicense() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create New License");
        waitForElementToBeVisible(createTradeLicense, driver);
        createTradeLicense.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForCreateMilestone() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("create milestone");
        waitForElementToBeVisible(createMilestoneLink, driver);
        createMilestoneLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void toCreateNewExpenseBill(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("New Create Expense Bill");
        waitForElementToBeVisible(createExpenseBill , driver);
        createExpenseBill.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForCreateEstimate() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("create estimate");
        waitForElementToBeVisible(createEstimateLink, driver);
        createEstimateLink.click();
        switchToNewlyOpenedWindow(driver);
    }
    public void searchTradeLicense() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Search Trade License");
        waitForElementToBeVisible(searchTrade , driver);
        searchTrade.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void searchLegencyTradeLicense() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Legacy License");
        waitForElementToBeVisible(searchLegencyTrade , driver);
        searchLegencyTrade.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToModifyDetailedCode(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Modify detailed Code");
        waitForElementToBeVisible(modifyDetailedCode , driver);
        modifyDetailedCode.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void createRemittanceRecovery(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Remittance Recovery");
        waitForElementToBeVisible(createRemittanceRecovery , driver);
        createRemittanceRecovery.click();
        switchToNewlyOpenedWindow(driver);
    }


    public void chooseToTrackMileStone() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Track Milestone");
        waitForElementToBeVisible(trackMilestoneLink, driver);
        trackMilestoneLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToChangeOfUse(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for Change of Use");
        waitForElementToBeVisible(changeOfUse , driver);
        changeOfUse.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void choosesToCreatePreamble() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Preamble");
        waitForElementToBeClickable(createPreamble, driver);
        createPreamble.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void createDataEntryScreenForWater() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Data Entry Screen");
        List<WebElement> dataEntryList = driver.findElements(By.linkText("Data Entry Screen"));
        waitForElementToBeVisible(dataEntryList.get(1), driver);
        dataEntryList.get(1).click();
        switchToNewlyOpenedWindow(driver);

    }

    public void createContractorBill() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("create contractor bill");
        waitForElementToBeVisible(createContractorBillLink,driver);
        createContractorBillLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateAdvertisement() {
        waitForElementToBeClickable(searchTreeTextBox,driver);
        searchFor("create advertisement");
        waitForElementToBeVisible(createAdvertisementLink,driver);
        createAdvertisementLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToSearchAdvertisement() {
        waitForElementToBeClickable(searchTreeTextBox,driver);
        searchFor("search advertisement");
        waitForElementToBeVisible(searchAdvertisementLink,driver);
        searchAdvertisementLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForCreateLegacyAdvertisements() {
        waitForElementToBeClickable(searchTreeTextBox,driver);
        searchFor("create legacy advertisement");
        waitForElementToBeVisible(createLegacyAdvertisementLink,driver);
        createLegacyAdvertisementLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToUpdateLegacyAdvertisements() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("update legacy advertisement");
        waitForElementToBeVisible(updateLegacyAdvertisementLink, driver);
        updateLegacyAdvertisementLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void createExpenseBillVoucher() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Create Voucher");
        waitForElementToBeVisible(createExpenseBillVoucher, driver);
        createExpenseBillVoucher.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCollectAdvTax() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Collect Advertisement Tax");
        waitForElementToBeVisible(collectAdvertisementTaxLink, driver);
        collectAdvertisementTaxLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateAdvertisementAgency() {
        waitForElementToBeClickable(searchTreeTextBox,driver);
        searchFor("create agency");
        waitForElementToBeVisible(createAgencyLink,driver);
        createAgencyLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToUploadPhoto() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Upload Estimate Photographs");
        waitForElementToBeClickable(uploadEstimatePhotographslink, driver);
        uploadEstimatePhotographslink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void createAgenda() {
        waitForElementToBeClickable(searchTreeTextBox,driver);
        searchFor("Create Agenda");
        waitForElementToBeClickable(createAgenda,driver);
        createAgenda.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void createMeeting() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Create Meeting invitation");
        waitForElementToBeClickable(createMeetingLink, driver);
        createMeetingLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToSearchAdvertisementAgency() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("search agency");
        waitForElementToBeClickable(searchAgencyLink, driver);
        searchAgencyLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void enterAttendance() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Enter Attendance");
        waitForElementToBeClickable(enterAttendanceLink, driver);
        enterAttendanceLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void createCouncilMOM() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Create Council MOM");
        waitForElementToBeClickable(createCouncilMOMlink, driver);
        createCouncilMOMlink.click();
        switchToNewlyOpenedWindow(driver);
    }
    public void createTransferOwnership() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Transfer Ownership");
        waitForElementToBeClickable(transferOwnershipLink, driver);
        transferOwnershipLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseReConnection() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for Re-Connection");
        waitForElementToBeClickable(applyForReConnection, driver);
        applyForReConnection.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToDeactivateAdv() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Deactivate Advertisement");
        waitForElementToBeClickable(deactivateAdvertisementLink, driver);
        deactivateAdvertisementLink.click();
        switchToNewlyOpenedWindow(driver);
    }
    public void chooseToPayMutationFee() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Property Mutation Fee");
        waitForElementToBeClickable(propertyMutationFeeLink, driver);
        propertyMutationFeeLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void choosesToSearchReceipt() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("search receipt");
        WebElement searchReceiptLink = driver.findElement(By.linkText("Search Receipts"));
        waitForElementToBeClickable(searchReceiptLink, driver);
        searchReceiptLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCollectWaterCharges(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Collect Charges");
        waitForElementToBeClickable(collectWaterCharges, driver);
        collectWaterCharges.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCitySetup(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("City Setup");
        waitForElementToBeClickable(citySetup, driver);
        citySetup.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void selectMunicipalityInfo(){

        waitForElementToBeClickable(municipalityInfo , driver);
        municipalityInfo.click();

        waitForElementToBeVisible(logoBrowse , driver);
        logoBrowse.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");

        waitForElementToBeClickable(logoSubmitForm , driver);
        logoSubmitForm.click();

        driver.switchTo().activeElement();
        waitForElementToBeClickable(closeButton , driver);
        closeButton.click();
        switchToPreviouslyOpenedWindow(driver);
    }


    public String getProfileName() {
        waitForElementToBeVisible(profileNameLink, driver);
        return profileNameLink.getText();
    }


    public void chooseToBankRemittance() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("bank remittance");
        waitForElementToBeClickable(bankRemittanceLink,driver);
        bankRemittanceLink.click();
        switchToNewlyOpenedWindow(driver);

    }

    public void choosesToRenewalAdvertisement() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("renewal");
        waitForElementToBeClickable(renewalAdvertisementLink,driver);
        renewalAdvertisementLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseRevisionPetition() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Revision Petition");
        waitForElementToBeClickable(createRevisionPetitionLink, driver);
        createRevisionPetitionLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateMarriageRegistration() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Marriage Registration");
        waitForElementToBeClickable(createMarriageRegistrationLink, driver);
        createMarriageRegistrationLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForModeOFAssignment(String mode){
        waitForElementToBeClickable(searchTreeTextBox, driver);

        if(mode.equalsIgnoreCase("cheque")){
            searchFor("Cheque Assignment");
            waitForElementToBeClickable(chequeAssignment.get(0), driver);
            chequeAssignment.get(0).click();
            switchToNewlyOpenedWindow(driver);
        }
        else {
            searchFor("RTGS Assignment");
            waitForElementToBeClickable(rtgsAssignment, driver);
            rtgsAssignment.click();
            switchToNewlyOpenedWindow(driver);
        }
    }

    public void chooseToRegisterComplaint() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.click();
        enterText(searchTreeTextBox, "Officials Register Grievance");
        officialRegisterGrievanceLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void openApplicationInDrafts(String crn) {
        getApplicationRowInDratf(crn).click();
        switchToNewlyOpenedWindow(driver);
    }

    private WebElement getApplicationRowInDratf(String crn){
        waitForElementToBeVisible(driver.findElement(By.id("drafts")), driver);
        waitForElementToBeVisible(officialDraftsTable, driver);

        await().atMost(20, SECONDS).until(() -> officialDraftsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialDraftsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(crn))
                return applicationRow;
        }
        throw new RuntimeException("No application row found for -- " + crn);
    }
    public void chooseForRemittanceAssignment(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Cheque Assignment");
        waitForElementToBeClickable(chequeAssignment.get(1), driver);
        chequeAssignment.get(1).click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForDirectBankPayment() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Direct Bank Payments");
        waitForElementToBeClickable(directBankPayments, driver);
        directBankPayments.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForNewSewerageConnection() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for new connection");
        waitForElementToBeClickable(newSewerageConnectionLink,driver);
        newSewerageConnectionLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCollectSewerageTax() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("collect sewerage charges");
        waitForElementToBeClickable(collectSewerageTaxesLink,driver);
        collectSewerageTaxesLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void choosesBankToBankTransfers(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("Bank to Bank Transfer");
        waitForElementToBeClickable(bankToBankTransfer,driver);
        bankToBankTransfer.click();
        switchToNewlyOpenedWindow(driver);
    }
}

