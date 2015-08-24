package ma.eventmanager.actions;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import ma.eventmanager.model.Ticket;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Namespace("/print")
@ResultPath(value = "/")
public class PrintActions extends ActionSupport{

	
	@Action(value = "printTicket")
	public String printTicketTest() throws JRException, IOException{
		
	
		//InputStream input = servletContext.getResourceAsStream("file.txt");
		ServletContext context = ServletActionContext.getServletContext();	

		
		Ticket ticketBean = new Ticket();
		ticketBean.setId("id001");
		ticketBean.setCreationDate("AAAA");
		List<Ticket> list = new ArrayList<Ticket>();
		list.add(ticketBean);
		
		JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(list);
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		final Map<String, Object> map = new HashMap<String, Object>();
		
		String reportpath = context.getRealPath(".")+File.separator+"reports"+File.separator+"printTicket.jrxml";
		JasperReport jr= JasperCompileManager.compileReport(reportpath);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jr, map, jrBean);
		jasperPrintList.add(jasperPrint);
		
		OutputStream outStream = ServletActionContext.getResponse().getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

		return null;
	}
}
