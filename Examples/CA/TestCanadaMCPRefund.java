package Canada;

import JavaAPI.*;

public class TestCanadaMCPRefund
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String amount = "2.00";
		String crypt = "7";
		String dynamic_descriptor = "123456";
		String custid = "mycust9";
		String order_id = "Test1534871380572";
		String txn_number = "332654-0_11";
		String processing_country_code = "CA";
		boolean status_check = false;

		MCPRefund mcpRefund = new MCPRefund();
		mcpRefund.setTxnNumber(txn_number);
		mcpRefund.setOrderId(order_id);
		mcpRefund.setCryptType(crypt);
		mcpRefund.setCustId(custid);
		mcpRefund.setDynamicDescriptor(dynamic_descriptor);
		
		//MCP Fields
		mcpRefund.setMCPVersion("1.0");
		mcpRefund.setCardholderAmount("200");
		mcpRefund.setCardholderCurrencyCode("840");
		mcpRefund.setMCPRateToken("P1534873994652426");

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mcpRefund);
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
			
			System.out.println("MerchantSettlementAmount = " + receipt.getMerchantSettlementAmount());
			System.out.println("CardholderAmount = " + receipt.getCardholderAmount());
			System.out.println("CardholderCurrencyCode = " + receipt.getCardholderCurrencyCode());
			System.out.println("MCPRate = " + receipt.getMCPRate());
			System.out.println("MCPErrorStatusCode = " + receipt.getMCPErrorStatusCode());
			System.out.println("MCPErrorMessage = " + receipt.getMCPErrorMessage());
			System.out.println("HostId = " + receipt.getHostId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
