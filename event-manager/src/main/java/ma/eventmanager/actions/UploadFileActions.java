package ma.eventmanager.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.model.ClientVo;
import ma.eventmanager.model.OperationResponse;
import ma.eventmanager.service.ReferenceService;
import ma.eventmanager.util.ProjectHelper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


@Namespace("/upload")
@ResultPath(value = "/")
public class UploadFileActions extends ActionSupport{

	
	@Autowired private EventManagerDao eventManagerDao;
	private OperationResponse operationResponse = new OperationResponse();

	private File upload;
	private String uploadsFileName;
	private String uploadsContentType; 
	
	@Action( value = "/uploadClients",
			results={@Result(name="success",location="/admin/manageClient.jsp"),
			         @Result(name="error",location="/admin/error.jsp"),
			         @Result(name="input",location="/admin/error.jsp")
			},
            interceptorRefs={
	        @InterceptorRef(
	            params={
//	            	"allowedTypes","image/jpeg,application/zip",
	            	"maximumSize","1000000"
			    }, 
	            value="fileUpload"
	        ),
	        @InterceptorRef("defaultStack"),
	        @InterceptorRef("validation")
		    }
	)
	public String uploadClients() throws IOException{
		List<Client> clientsList = new ArrayList<Client>();
		FileInputStream fis = new FileInputStream(upload.getAbsoluteFile());
		Workbook workbook = null;
		try{
			workbook = new XSSFWorkbook(fis);
		}catch(Exception e){
			buildMessage("MSG000010","0");
			//ProjectHelper.sendObjectAsJsonResponse(operationResponse,ServletActionContext.getResponse());
			//return null;
			return "error";
		}
		//int numberOfSheets = workbook.getNumberOfSheets();
		Sheet sheet = workbook.getSheetAt(0);
		Iterator rowIterator = sheet.iterator();
		
		//iterating over each row
		while (rowIterator.hasNext()) {
			ClientVo client = new ClientVo();
			Row row = (Row) rowIterator.next();
			
			client.setFirstName(getCellValue(row.getCell(1)));
			client.setLastName(getCellValue(row.getCell(2)));
			client.setMail(getCellValue(row.getCell(6)));
			client.setPassword(getCellValue(row.getCell(7)));
			client.setCne(getCellValue(row.getCell(10)));
			client.setOrderNumber(getCellValue(row.getCell(11)));
			client.setGender(getCellValue(row.getCell(12)));
			
			clientsList.add(new Client(client));
		}
		eventManagerDao.addClients(clientsList);
		//buildMessage("MSG000011","1");
		//ProjectHelper.sendObjectAsJsonResponse(operationResponse,ServletActionContext.getResponse());
		//return null;
		return "success";
	}
	private String getCellValue(Cell cell)
	{
		String value = null;
		if(cell != null){
			if (cell.CELL_TYPE_STRING == cell.getCellType()) {
				value = cell.getStringCellValue();
			}else if (cell.CELL_TYPE_NUMERIC == cell.getCellType()){
				value = String.valueOf(cell.getNumericCellValue());
			}	
		}

		return value;
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
	public File getUpload()
	{
		return upload;
	}

	public void setUpload(File upload)
	{
		this.upload = upload;
	}
	

}
