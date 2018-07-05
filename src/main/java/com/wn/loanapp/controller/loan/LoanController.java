package com.wn.loanapp.controller.loan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.dto.BankStatementDTO;
import com.wn.loanapp.dto.DatatableJsonResponse;
import com.wn.loanapp.dto.DistributerDTO;
import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.dto.LoanDispersedDTO;
import com.wn.loanapp.form.ApiResponceForm;
import com.wn.loanapp.form.CSVForm;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.form.LoanDispersedForm;
import com.wn.loanapp.form.UserDetailsSessionForm;
import com.wn.loanapp.service.CommonService;
import com.wn.loanapp.util.Format;

@Controller
public class LoanController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/loan" , method = RequestMethod.GET)
	public ModelAndView getActors(HttpServletRequest request, UserDetailsSessionForm userDetailsSessionForm) {
		ModelAndView modelAndView = null;
		if(Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			modelAndView = new ModelAndView("loanDashboard");
			userDetailsSessionForm.setPageHeaderTitle(Constants.ADMIN_VIEW_LOAN_HEADER_TITLE);
			userDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_LOAN);
			userDetailsSessionForm.setSelectedSubLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_LOAN);
			
			List<DistributerDTO> distributers = new ArrayList<>();
			List<Object> distributerDTOs = commonService.getDistributers();
			if(Format.isCollectionNotEmtyAndNotNull(distributerDTOs)) {
				Iterator<Object> itr = distributerDTOs.iterator();
		    	while(itr.hasNext()){
		    	   Object[] obj = (Object[]) itr.next();
		    	   String distributerId = String.valueOf(obj[0]).trim();
		    	   String distributerName = String.valueOf(obj[1]);
		    	   DistributerDTO distributerDTO = new DistributerDTO();
		    	   distributerDTO.setDistId(distributerId);
		    	   distributerDTO.setDistName(distributerName);
		    	   distributers.add(distributerDTO);
		    	}
			}
			modelAndView.addObject("distributers", distributers);
		}else {
			modelAndView = redirectToLoginPage("/");
		}
		log.debug("End of method addRole");
		return modelAndView;
	}
	
	@RequestMapping(value="loanList", method=RequestMethod.POST)
	public @ResponseBody DatatableJsonResponse getActorList(LoanDetailsForm loanDetailsForm) {
		List<LoanDetailsDTO> detailsDTOs = null;
		Integer count = 0;
		DatatableJsonResponse datatableJsonResponse = new DatatableJsonResponse();
		try {
			detailsDTOs = commonService.getAppliedLoanDetails(loanDetailsForm);
			if(Format.isCollectionEmtyOrNull(detailsDTOs)){
				detailsDTOs = new ArrayList<>();
			}else{
				count = commonService.getAppliedLoanDetailsCount(loanDetailsForm).intValue();
			}
		}catch (ParseException e) {
			detailsDTOs = new ArrayList<>();
		}
		datatableJsonResponse.setData(detailsDTOs);
		datatableJsonResponse.setRecordsTotal(count);
		datatableJsonResponse.setRecordsFiltered(count);
		return datatableJsonResponse;
	}
	
	@RequestMapping(value = "/downloadloancsv", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public void downloadFile(HttpServletResponse response, 
		@RequestParam(required=false) String startDate,
		@RequestParam(required=false) String endDate,
		@RequestParam(required=false) String distributers) {
		
		LoanDetailsForm loanDetailsForm = new LoanDetailsForm();
		if(Format.isStringNotEmptyAndNotNull(startDate)) {
			loanDetailsForm.setTnDateStart(startDate);
		}
		if(Format.isStringNotEmptyAndNotNull(endDate)) {
			loanDetailsForm.setTnDateEnd(endDate);
		}
		if(Format.isStringNotEmptyAndNotNull(distributers)) {
			loanDetailsForm.setDistributer(distributers);
		}
		/*SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy_MM_dd,HH_mm_ss");
	    Date now = new Date();
	    String strTime = sdfTime.format(now);*/
	    
		response.addHeader("Content-Type", "application/csv");
		response.addHeader("Content-Disposition", "attachment; filename=loan_details.csv");
		response.setCharacterEncoding("UTF-8");
		
		List<LoanDetailsDTO> detailsDTOs = null;
        try {
        	PrintWriter out = response.getWriter();
        	
        	StringBuilder sb = new StringBuilder();
        	sb.append("Order No");
            sb.append(',');
            sb.append("Distributer Name");
            sb.append(',');
            sb.append("First Name");
            sb.append(',');
            sb.append("Date");
            sb.append(',');
            sb.append("Amount in Rs.");
            sb.append('\n');
            out.write(sb.toString());
            
        	detailsDTOs = commonService.getAppliedLoanDetails(loanDetailsForm);
        	if(Format.isCollectionNotEmtyAndNotNull(detailsDTOs)) {
        		detailsDTOs.forEach(detailsDTO -> {
        			StringBuilder sb1 = new StringBuilder();
                	sb1.append(detailsDTO.getOrderNo());
                    sb1.append(',');
                    sb1.append(detailsDTO.getDistributorName());
                    sb1.append(',');
                    sb1.append(detailsDTO.getFirstName());
                    sb1.append(',');
                    sb1.append(detailsDTO.getTnDate());
                    sb1.append(',');
                    sb1.append(detailsDTO.getAmount());
                    sb1.append('\n');
                    out.write(sb1.toString());
        		});
        	}
            out.close();
        }catch (IOException e) {
        	throw new RuntimeException("There is an error while downloading csv", e);
        } catch (ParseException e) {
        	throw new RuntimeException("Parse exception while creating csv", e);
		}
	}
	@RequestMapping(value="dispersedList", method=RequestMethod.POST)
	public @ResponseBody DatatableJsonResponse getDispersedList(LoanDispersedForm loanDispersedForm) {
		List<LoanDispersedDTO> loanDispersedDTOs = null;
		Integer count = 0;
		DatatableJsonResponse datatableJsonResponse = new DatatableJsonResponse();
		try {
			loanDispersedDTOs = commonService.getDispersedLoanDetails(loanDispersedForm);
			if(Format.isCollectionEmtyOrNull(loanDispersedDTOs)){
				loanDispersedDTOs = new ArrayList<>();
			}else{
				count = commonService.getDispersedLoanDetailsCount(loanDispersedForm).intValue();
			}
		}catch (ParseException e) {
			loanDispersedDTOs = new ArrayList<>();
		}
		datatableJsonResponse.setData(loanDispersedDTOs);
		datatableJsonResponse.setRecordsTotal(count);
		datatableJsonResponse.setRecordsFiltered(count);
		return datatableJsonResponse;
	}
	@RequestMapping(value = "/downloadloandispersedcsv", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public void downloadDispersedFile(HttpServletResponse response, 
		@RequestParam(required=false) String startDate,
		@RequestParam(required=false) String endDate,
		@RequestParam(required=false) String distributers) {
		
		LoanDispersedForm loanDispersedForm = new LoanDispersedForm();
		if(Format.isStringNotEmptyAndNotNull(startDate)) {
			loanDispersedForm.setTnStartDate(startDate);
		}
		if(Format.isStringNotEmptyAndNotNull(endDate)) {
			loanDispersedForm.setTnEndDate(endDate);
		}
		if(Format.isStringNotEmptyAndNotNull(distributers)) {
			loanDispersedForm.setDistributer(distributers);
		}
		loanDispersedForm.setSettleState("f");
		response.addHeader("Content-Type", "application/csv");
		response.addHeader("Content-Disposition", "attachment; filename=loan_disperesed_details.csv");
		response.setCharacterEncoding("UTF-8");
		
		List<LoanDispersedDTO> loanDispersedDTOs = null;
        try {
        	PrintWriter out = response.getWriter();
        	
        	StringBuilder sb = new StringBuilder();
        	sb.append("Txn Id");
            sb.append(',');
            sb.append("Online Payment Id");
            sb.append(',');
            sb.append("Retailer Name");
            sb.append(',');
            sb.append("Amount");
            sb.append(',');
            sb.append("Date");
            sb.append(',');
            sb.append("Is Verify");
            sb.append('\n');
            out.write(sb.toString());
            loanDispersedDTOs = commonService.getDispersedLoanDetails(loanDispersedForm);
        	if(Format.isCollectionNotEmtyAndNotNull(loanDispersedDTOs)) {
        		loanDispersedDTOs.forEach(loanDispersedDTO -> {
        			StringBuilder sb1 = new StringBuilder();
                	sb1.append(loanDispersedDTO.getTxnId());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getOnlinePaymentId());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getRetailerName());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getAmount());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getTnDate());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getVerify());
                    sb1.append('\n');
                    out.write(sb1.toString());
        		});
        	}
            out.close();
        }catch (IOException e) {
        	throw new RuntimeException("There is an error while downloading csv", e);
        } catch (ParseException e) {
        	throw new RuntimeException("Parse exception while creating csv", e);
		}
	}
	
	@RequestMapping(value = "/uploadBankStatement", method = RequestMethod.POST)
	public @ResponseBody ApiResponceForm uploadBankStatement(CSVForm csvForm) throws IOException {
		String messageType = null;
		String successOrErrorMessage;
		ApiResponceForm apiResponceForm = new ApiResponceForm();
		File file = convertMultiPartToFile(csvForm.getCsvFile());
		//List<BankStatementDTO> bankStatementDTOs = new ArrayList<BankStatementDTO>();
		try (Reader reader = new FileReader(file);) {
			@SuppressWarnings("unchecked")
			CsvToBean<BankStatementDTO> csvToBean = new CsvToBeanBuilder<BankStatementDTO>(reader).withType(BankStatementDTO.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<BankStatementDTO> statementDTOs = csvToBean.parse();
			/*Iterator<BankStatementDTO> iterator = statementDTOs.iterator();
			while (iterator.hasNext()) {
				BankStatementDTO roleJson = iterator.next();
				bankStatementDTOs.add(roleJson);
				iterator.remove();
			}*/
			if(Format.isCollectionNotEmtyAndNotNull(statementDTOs)){
				commonService.updateBankStatement(statementDTOs);
				messageType = Constants.SUCCESS;
				successOrErrorMessage = "Bank Statement Uploaded Successfully";
			}else{
				messageType = Constants.FAILURE;
				successOrErrorMessage = "Error while reading the file";
			}
		}catch (Exception e) {
			log.debug("Exception " + e);
			messageType = Constants.FAILURE;
			successOrErrorMessage = "Something went wrong";
		}
		apiResponceForm.setMessageType(messageType);
		apiResponceForm.setSuccessOrErrorMessage(successOrErrorMessage);
		return apiResponceForm;
	}
	
	@RequestMapping(value="receivedPaymentList", method=RequestMethod.POST)
	public @ResponseBody DatatableJsonResponse receivedPaymentList(LoanDispersedForm loanDispersedForm) {
		List<LoanDispersedDTO> loanDispersedDTOs = null;
		Integer count = 0;
		DatatableJsonResponse datatableJsonResponse = new DatatableJsonResponse();
		try {
			loanDispersedDTOs = commonService.getDispersedLoanDetails(loanDispersedForm);
			if(Format.isCollectionEmtyOrNull(loanDispersedDTOs)){
				loanDispersedDTOs = new ArrayList<>();
			}else{
				count = commonService.getDispersedLoanDetailsCount(loanDispersedForm).intValue();
			}
		}catch (ParseException e) {
			loanDispersedDTOs = new ArrayList<>();
		}
		datatableJsonResponse.setData(loanDispersedDTOs);
		datatableJsonResponse.setRecordsTotal(count);
		datatableJsonResponse.setRecordsFiltered(count);
		return datatableJsonResponse;
	}
	
	@RequestMapping(value="updatePaymentRecieved", method=RequestMethod.POST)
	public @ResponseBody ApiResponceForm updatePaymentRecieved(@RequestBody LoanDispersedForm loanDispersedForm){
		ApiResponceForm apiResponceForm = new ApiResponceForm();
		String messageType = null;
		String successOrErrorMessage = null;
		try{
			commonService.updatePaymentRecieved(loanDispersedForm);
			messageType = Constants.SUCCESS;
			successOrErrorMessage = "Records updated successfully";
		}catch (Exception e) {
			messageType = Constants.FAILURE;
			successOrErrorMessage = "Something went wrong";
		}
		apiResponceForm.setMessageType(messageType);
		apiResponceForm.setSuccessOrErrorMessage(successOrErrorMessage);
		return apiResponceForm;
	}
	
	@RequestMapping(value = "/downloadrecievedpaymentlist", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public void downloadrecievedpaymentlist(HttpServletResponse response, 
		@RequestParam(required=false) String startDate,
		@RequestParam(required=false) String endDate,
		@RequestParam(required=false) String distributers) {
		
		LoanDispersedForm loanDispersedForm = new LoanDispersedForm();
		if(Format.isStringNotEmptyAndNotNull(startDate)) {
			loanDispersedForm.setTnStartDate(startDate);
		}
		if(Format.isStringNotEmptyAndNotNull(endDate)) {
			loanDispersedForm.setTnEndDate(endDate);
		}
		if(Format.isStringNotEmptyAndNotNull(distributers)) {
			loanDispersedForm.setDistributer(distributers);
		}
		loanDispersedForm.setSettleState("t");
		response.addHeader("Content-Type", "application/csv");
		response.addHeader("Content-Disposition", "attachment; filename=recieved_payment_list.csv");
		response.setCharacterEncoding("UTF-8");
		
		List<LoanDispersedDTO> loanDispersedDTOs = null;
        try {
        	PrintWriter out = response.getWriter();
        	
        	StringBuilder sb = new StringBuilder();
        	sb.append("Txn Id");
            sb.append(',');
            sb.append("Online Payment Id");
            sb.append(',');
            sb.append("Retailer Name");
            sb.append(',');
            sb.append("Amount");
            sb.append(',');
            sb.append("Date");
            sb.append(',');
            sb.append("Is Verify");
            sb.append('\n');
            out.write(sb.toString());
            loanDispersedDTOs = commonService.getDispersedLoanDetails(loanDispersedForm);
        	if(Format.isCollectionNotEmtyAndNotNull(loanDispersedDTOs)) {
        		loanDispersedDTOs.forEach(loanDispersedDTO -> {
        			StringBuilder sb1 = new StringBuilder();
                	sb1.append(loanDispersedDTO.getTxnId());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getOnlinePaymentId());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getRetailerName());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getAmount());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getTnDate());
                    sb1.append(',');
                    sb1.append(loanDispersedDTO.getVerify());
                    sb1.append('\n');
                    out.write(sb1.toString());
        		});
        	}
            out.close();
        }catch (IOException e) {
        	throw new RuntimeException("There is an error while downloading csv", e);
        } catch (ParseException e) {
        	throw new RuntimeException("Parse exception while creating csv", e);
		}
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
