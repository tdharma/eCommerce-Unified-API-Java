package Canada;

import JavaAPI.*;

public class TestCanadaCavvPurchase
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String cust_id = "CUS887H67";
		String amount = "10.42";
		String pan = "4242424242424242";
		String expdate = "1901"; //YYMM
		String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA=";
		String dynamic_descriptor = "123456";
		String processing_country_code = "CA";
		String crypt_type = "5";
		boolean status_check = false;

		CavvPurchase cavvPurchase = new CavvPurchase();
		cavvPurchase.setOrderId(order_id);
		cavvPurchase.setCustId(cust_id);
		cavvPurchase.setAmount(amount);
		cavvPurchase.setPan(pan);
		cavvPurchase.setExpdate(expdate);
		cavvPurchase.setCavv(cavv);
		cavvPurchase.setCryptType(crypt_type); //Mandatory for AMEX only
		cavvPurchase.setDynamicDescriptor(dynamic_descriptor);
		//cavvPurchase.setWalletIndicator("APP"); //set only for wallet transactions. e.g APPLE PAY
		//cavvPurchase.setNetwork("Interac"); //set only for Interac e-commerce
		//cavvPurchase.setDataType("3DSecure"); //set only for Interac e-commerce
		
		//optional - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("U");
		cof.setPaymentInformation("2");
		cof.setIssuerId("139X3130ASCXAS9"); 
		
		cavvPurchase.setCofInfo(cof);

		HttpsPostRequest mpgReq = new HttpsPostRequest(); 
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(cavvPurchase);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ISO = " + receipt.getISO());
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("CavvResultCode = " + receipt.getCavvResultCode());
			System.out.println("IssuerId = " + receipt.getIssuerId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
