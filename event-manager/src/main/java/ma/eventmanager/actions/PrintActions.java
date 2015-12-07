package ma.eventmanager.actions;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import ma.eventmanager.entitys.Event;
import ma.eventmanager.model.TicketRepotBean;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

@Namespace("/print")
@ResultPath(value = "/")
public class PrintActions extends ActionSupport{

	
	@Action(value = "printTicket")
	public String printTicketTest() throws JRException, IOException{
		
	
		//InputStream input = servletContext.getResourceAsStream("file.txt");
		ServletContext context = ServletActionContext.getServletContext();	
		TicketRepotBean ticketBean = (TicketRepotBean)ServletActionContext.getRequest().getSession().getAttribute("ticketRepotBean");
		List<TicketRepotBean> list = new ArrayList<TicketRepotBean>();
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

//        PrinterJob printerJob = PrinterJob.getPrinterJob();
//        PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
//        printerJob.defaultPage(pageFormat);
//        int selectedService = 0;
//        AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printerNameShort, null));
//        PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
//        printerJob.setPrintService(printService[selectedService]);
//        JRPrintServiceExporter exporter;
//        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//        printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
//        printRequestAttributeSet.add(new Copies(1));
//        // these are deprecated
//        exporter = new JRPrintServiceExporter();
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
//        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
//        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
//        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
//        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
//        exporter.exportReport();
		return null;
	}
}
