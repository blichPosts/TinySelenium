package actions;
import java.util.List;

import actions.ExcelSheetActions;


// ////////////////////////////////////////////////////////////////////////////////////
// 								This is test class
//////////////////////////////////////////////////////////////////////////////////////
public class DataBaseTestClass {

	String returnData = "[SQLColumn [name=ID, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=CREATED_BY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=EX_1509169491, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILLED_CUSTOMER_NAME, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILLING_NUMBER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=EX_820883049, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_ADDRESS1, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_ADDRESS2, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_ADDRESS3, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_ADDRESS4, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_CITY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_COUNTRY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_EMAIL, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_NAME, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_PHONE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_STATE_PROVINCE_REGION, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BILL_TO_ZIP_POSTAL_CODE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=BULK_LOAD_IDENTIFIER, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=CARRIER_CONTACT_EMAIL, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=CARRIER_CONTACT_PHONE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=CONTRACT_NUMBER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=CREATED_ON, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=CURRENCY_CONVERSION_RATE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=CURRENT_CHARGES, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=CURRENT_CHARGES_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=CURRENT_CHARGES_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=CURRENT_CHARGES_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=CUSTOMER_NAME, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=DESCRIPTION, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=DIVISION, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=DUE_DATE, isAutoIncrement=false, typeName=DATE, typeCode=93], SQLColumn [name=EXCLUSIONS_APPLIED, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=FROM_CURRENCY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=INVOICE_CURRENCY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=INVOICE_DATE, isAutoIncrement=false, typeName=DATE, typeCode=93], SQLColumn [name=INVOICE_NUMBER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=IS_DEBIT_CREDIT, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=IS_FINAL, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=IS_MANUFACTURED_ACCOUNT_NUMBER, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=IS_MANUFACTURED_INVOICE_DATE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=IS_MANUFACTURED_INVOICE_NUMBER, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=IS_MEMO, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=IS_PARTIAL, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=JOB_IDENTIFIER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=LEGAL_ENTITY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=LOAD_DATE, isAutoIncrement=false, typeName=DATE, typeCode=93], SQLColumn [name=LPCS, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=LPCS_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=LPCS_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=LPCS_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=MODIFIED_BY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=MODIFIED_ON, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=NOTICES, isAutoIncrement=false, typeName=CLOB, typeCode=2005], SQLColumn [name=PAYMENTS, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PAYMENTS_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PAYMENTS_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=PAYMENTS_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=PAYMENT_METHOD, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=PAYMENT_TERMS, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=PO_NUMBER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=PREVIOUS_BALANCE_ADJ, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PREVIOUS_BALANCE_ADJ_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PREVIOUS_BALANCE_ADJ_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=PREVIOUS_BALANCE_ADJ_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=PREV_BALANCE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PREV_BALANCE_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PREV_BALANCE_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=PREV_BALANCE_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=QUOTE_NUMBER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=EX_1503595454, isAutoIncrement=false, typeName=DATE, typeCode=93], SQLColumn [name=BULK_LOAD_COMPLETE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=REMIT_TO_ADDRESS1, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_ADDRESS2, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_ADDRESS3, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_ADDRESS4, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_CITY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_COUNTRY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_EMAIL, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_NAME, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_PHONE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_STATE_PROVINCE_REGION, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=REMIT_TO_ZIP_POSTAL_CODE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SERVICE_FROM, isAutoIncrement=false, typeName=DATE, typeCode=93], SQLColumn [name=SERVICE_TO, isAutoIncrement=false, typeName=DATE, typeCode=93], SQLColumn [name=SHIP_TO_ADDRESS1, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_ADDRESS2, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_ADDRESS3, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_ADDRESS4, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_CITY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_COUNTRY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_EMAIL, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_NAME, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_PHONE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_POSTAL_CODE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=SHIP_TO_STATE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=TOTAL_AMOUNT_DUE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=TOTAL_AMOUNT_DUE_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=TOTAL_AMOUNT_DUE_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=TOTAL_AMOUNT_DUE_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=TO_CURRENCY, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=VENDOR_CONTACT_EMAIL, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=VENDOR_CONTACT_PHONE, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=VENDOR_INVOICE_NUM, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=VERSION, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=WIRE_TRANSFER_ACCOUNT_NUMBER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=WIRE_TRANSFER_BANK, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=WIRE_TRANSFER_ROUTING_NUMBER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=ALLOCATION_INFO_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=BILL_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=INVOICE_ACCOUNT_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=INVOICE_COUNTRY_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=JOB_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=MASTER_BILLING_SYSTEM_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=RECURRING_INVOICE_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=TENANT_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=TENANT_VENDOR_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=VENDOR_REMITTANCE_CODE_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=LEGACY_ID, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=REMITTANCE_ADDRESS_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=TENANT_VENDOR_CODE_FK, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=AMOUNT_TO_BE_PAID, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=AMOUNT_TO_BE_PAID_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=AMOUNT_TO_BE_PAID_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=AMOUNT_TO_BE_PAID_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=MANUAL_ADJUSTMENTS, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=MANUAL_ADJUSTMENTS_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=MANUAL_ADJUSTMENTS_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=MANUAL_ADJUSTMENTS_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=PAST_DUE_BALANCE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PAST_DUE_BALANCE_BASE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PAST_DUE_BALANCE_DATE, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=PAST_DUE_BALANCE_IND, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=EX_1070497531, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=EX_868201903, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=TOTAL_AMOUNT_DUE_IS_MFD, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=IS_ACTIVE, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=DATA_CONTAINER, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=INVOICE_HASH, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=NEW_CURRENT_CHRGS_IS_MFD, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=PAST_DUE_BALANCE_IS_MFD, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=LOCK_TIME, isAutoIncrement=false, typeName=TIMESTAMP, typeCode=93], SQLColumn [name=LOCK_OPERATION, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12], SQLColumn [name=IS_DELETED, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=MET_SLA, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=MET_SLO, isAutoIncrement=false, typeName=NUMBER, typeCode=2], SQLColumn [name=TOTAL_SLA_SLO_TIME_TAKEN, isAutoIncrement=false, typeName=VARCHAR2, typeCode=12]]";
	

	
	public static void runTest1() throws Exception {
		
		// make database call and get 
		
		ExcelSheetActions.readExcelSheet();

	}
	
	
	
}