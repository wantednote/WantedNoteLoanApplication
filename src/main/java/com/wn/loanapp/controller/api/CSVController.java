package com.wn.loanapp.controller.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wn.loanapp.dto.RoleJson;
import com.wn.loanapp.form.CSVForm;
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.service.RoleService;

@RestController
@RequestMapping("csv")
public class CSVController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public void downloadFile(HttpServletResponse response) {
		response.addHeader("Content-Type", "application/csv");
		response.addHeader("Content-Disposition", "attachment; filename=user_details.csv");
		response.setCharacterEncoding("UTF-8");
		
		RoleForm roleForm = new RoleForm();
        List<Role> roles = roleService.getRoles(roleForm);
        try {
        	PrintWriter out = response.getWriter();
        	roles.forEach(role -> {
        		out.write(role.getRole());
        		out.write("\n");
        	});
        	
        }catch (IOException e) {
        	throw new RuntimeException("There is an error while downloading user_details.csv", e);
        }
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public List<RoleJson> uploadFile(CSVForm csvForm) throws IOException {
		File file = convertMultiPartToFile(csvForm.getCsvFile());
		List<RoleJson> mandatoryMissedList = new ArrayList<RoleJson>();
		try (Reader reader = new FileReader(file);) {
			@SuppressWarnings("unchecked")
			CsvToBean<RoleJson> csvToBean = new CsvToBeanBuilder<RoleJson>(reader).withType(RoleJson.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<RoleJson> roleJsons = csvToBean.parse();
			Iterator<RoleJson> studentListClone = roleJsons.iterator();
			while (studentListClone.hasNext()) {
				RoleJson roleJson = studentListClone.next();
				/*if (roleJson.getRole() == null || roleJson.getRole().isEmpty()) {
					mandatoryMissedList.add(roleJson);
					studentListClone.remove();
				}*/
				mandatoryMissedList.add(roleJson);
				studentListClone.remove();
			}
		}
		return mandatoryMissedList;
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
