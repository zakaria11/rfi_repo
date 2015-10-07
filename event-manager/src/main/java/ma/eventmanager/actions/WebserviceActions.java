package ma.eventmanager.actions;

import java.io.IOException;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.model.OperationResponse;
import ma.eventmanager.service.ReferenceService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;



@Namespace("/webservice")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class WebserviceActions extends AbstractAction{
	
	
	@Autowired private EventManagerDao eventmanagerdao;
	
	OperationResponse operationResponse = new OperationResponse();
	private String subscriptionId; 
	private String DeviceID; 
	private String UserID;

	@Action(value = "subscribeClient", results = { @Result(name = "success", type = "json", params = {"root", "operationResponse" }) })
	
	public String subscribeClient(){
		Integer intId = null;
		try{
			intId = Integer.parseInt(subscriptionId);	
		}catch (Exception e){
			buildMessage("MSG000006","0");
			return SUCCESS;
		}
		
		
		Subscription subscription = eventmanagerdao.getSubscriptionById(intId);
		if(subscription == null){
			buildMessage("MSG000007","0");
			return SUCCESS;
		}else{
			subscription.setAssisted("1");
			eventmanagerdao.updateSubscription(subscription);
			buildMessage("MSG000008","1");
		}
		
		return SUCCESS;
	}

	private void buildMessage(String msgCode,String isOK){
		this.operationResponse.setIsOK(isOK);
		this.operationResponse.setCode(msgCode);
		this.operationResponse.setMessage(ReferenceService.getMessages().get(msgCode));
		
	}

	public OperationResponse getOperationResponse()
	{
		return operationResponse;
	}

	public void setOperationResponse(OperationResponse operationResponse)
	{
		this.operationResponse = operationResponse;
	}

	public String getSubscriptionId()
	{
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId)
	{
		this.subscriptionId = subscriptionId;
	}

	public String getDeviceID()
	{
		return DeviceID;
	}

	public void setDeviceID(String deviceID)
	{
		DeviceID = deviceID;
	}

	public String getUserID()
	{
		return UserID;
	}

	public void setUserID(String userID)
	{
		UserID = userID;
	}
	
	
}
