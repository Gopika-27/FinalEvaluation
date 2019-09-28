package com.ust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.el.parser.ParseException;
import com.ust.dao.AdminDao;
import com.ust.model.Login;
import com.ust.model.VendorContact;

@RestController
public class AdminController {

	@Autowired
	AdminDao dao;

	/*-------------------------- LOGIN ----------------------------------*/

	@RequestMapping(value = "/api/login/{username}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public Login selectlogin(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		return dao.verifylogin(username, password);
	}

	/*----------------------- VIEW VENDORS BY NAME -------------------*/

	@RequestMapping(value = "/api/vendor/{vendorname}", method = RequestMethod.GET)
	@ResponseBody
	public List<VendorContact> getAllVendor(Model m,
			@PathVariable("vendorname") String vendorname) {
		List list;
		if (vendorname.equals("null")) {
			list = dao.getAllVendor();
		} else {
			list = dao.getVendorByName(vendorname);
		}

		return list;
	}

	/*-----------------------INSERT VENDORS-------------------*/

	@ResponseBody
	@RequestMapping(value = "/api/insertvendor", method = RequestMethod.POST)
	public void addVendorContact(@RequestBody VendorContact vendorContact)
			throws ParseException {
		dao.addVendor(vendorContact);
	}

	/*-----------------------UPDATE VENDOR AND CONTACT-------------------*/

	@ResponseBody
	@RequestMapping(value = "/api/updatevendor", method = RequestMethod.PUT)
	public void updateVendor(@RequestBody VendorContact vendorContact)
			throws ParseException {
		int vendorid = vendorContact.getVendorId();
		dao.updateVendor(vendorid, vendorContact);
	}

	/*-----------------------DISABLE A VENDOR-------------------*/

	@RequestMapping(value = "/api/disableVendor/{vendorId}", method = RequestMethod.PUT)
	@ResponseBody
	public void disableVendor(@PathVariable("vendorId") int vendorId) {
		dao.disableVendor(vendorId);
	}

	

	/*-----------------------FIND VENDORS BY VENDORID------------------*/

	@RequestMapping(value = "/api/vendors/{vendorid}", method = RequestMethod.GET)
	@ResponseBody
	public VendorContact getvendors(Model m,
			@PathVariable("vendorid") int vendorId) {

		return dao.getVendorById(vendorId);
	}

}