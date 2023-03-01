package com.ibm.validation.liberty;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibm.validation.liberty.bean.ApplicationMonitorInfo;
import com.ibm.validation.liberty.bean.ConfigInfo;
import com.ibm.validation.liberty.bean.EndpointInfo;
import com.ibm.validation.liberty.bean.IncludeInfo;
import com.ibm.validation.liberty.bean.LDAPRegistryInfo;
import com.ibm.validation.liberty.bean.SSLInfo;
import com.ibm.validation.liberty.bean.WebAppSecurityInfo;

/**
 * Example application to read in an XML file and validate using
 * Bean Validation outside of JEE.
 * 
 * @author Brian S Paskin (IBM Italia)
 * @version 1.0.0.0 (15/02/2019)
 * @version 2.0.0.0 (01/03/2023)
 */

public class CheckCompliance {

	private static String CIS_WORKBENCH_LEVEL = "1.1";
	private static Document doc;
	
	private static Document readInputFile(String fileName) throws ParserConfigurationException, IOException, SAXException {
		File configFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(configFile);
		return doc;
	}
	
	public static void main(String[] args) {

		// make sure that the file has been passed
		if ( args.length != 1 ) {
			System.err.println("Path to XML file is required.");
			System.exit(255);
		}
		
		List<String> validationErrors = new ArrayList<String>();

		// Bean validation setup
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
	
		try {
			// Read in XML file and place it in a Document
			doc = readInputFile(args[0]);
			doc.getDocumentElement().normalize();
			
			System.out.println("CIS WORKBENCH LEVEL : " + CIS_WORKBENCH_LEVEL + "\n");
				
			// Check the Endpoints to make sure they are compliant
			// uses Bean Validation
			// uses a loop since there could be more than 1 entry
			NodeList nodeList = doc.getElementsByTagName("httpEndpoint");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
					
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					EndpointInfo endpointInfo = new EndpointInfo();
					
					endpointInfo.setHost(element.getAttribute("host"));
					endpointInfo.setName(element.getAttribute("id"));
					endpointInfo.setHttpPort(Integer.parseInt(element.getAttribute("httpPort")));
					endpointInfo.setHttpsPort(Integer.parseInt(element.getAttribute("httpsPort")));
					
					Set<ConstraintViolation<EndpointInfo>> violations = validator.validate(endpointInfo);
					
					// Validate using Bean Validation and add violations to List
					if (!violations.isEmpty()) {						
						for (ConstraintViolation<EndpointInfo> violation : violations) {
							validationErrors.add("Endpoint: " + violation.getMessage());
						}
					}
				}
			}
			
			// Check the SSL entries
			// uses Bean Validation
			// uses a loop since there could be more than 1 entry
			nodeList = doc.getElementsByTagName("ssl");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
					
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					SSLInfo sslInfo = new SSLInfo();
						
					sslInfo.setId(element.getAttribute("id"));
					sslInfo.setProtocol(element.getAttribute("sslProtocol"));
					sslInfo.setSecurityLevel(element.getAttribute("securityLevel"));
					
					if (element.getAttribute("clientAuthenticationSupported").equalsIgnoreCase("true")) {
						sslInfo.setClientAuthSupported(true);
					} else {
						sslInfo.setClientAuthSupported(false);
					}

					Set<ConstraintViolation<SSLInfo>> violations = validator.validate(sslInfo);
					
					// Validate using Bean Validation and add violations to List
					if (!violations.isEmpty()) {						
						for (ConstraintViolation<SSLInfo> violation : violations) {
							validationErrors.add("SSL: " + violation.getMessage());
						}
					}
				}
			}	
			
			nodeList = doc.getElementsByTagName("config");
			
			if (nodeList.getLength() == 0) {
				validationErrors.add("1.6 Config: configuration element missing");
			}
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
					
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					ConfigInfo configInfo = new ConfigInfo();
					configInfo.setMonitorInterval(element.getAttribute("monitorInterval"));
					configInfo.setOnError(element.getAttribute("onError"));
					configInfo.setUpdateTrigger(element.getAttribute("updateTrigger"));
					
					Set<ConstraintViolation<ConfigInfo>> violations = validator.validate(configInfo);

					// Validate using Bean Validation and add violations to List
					if (!violations.isEmpty()) {						
						for (ConstraintViolation<ConfigInfo> violation : violations) {
							validationErrors.add("1.6 Config: " + violation.getMessage());
						}
					}
				}
			}
			
			nodeList = doc.getElementsByTagName("include");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
					
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					IncludeInfo includeInfo = new IncludeInfo();
					includeInfo.setOnConflict(element.getAttribute("onConflict"));
					
					Set<ConstraintViolation<IncludeInfo>> violations = validator.validate(includeInfo);

					// Validate using Bean Validation and add violations to List
					if (!violations.isEmpty()) {						
						for (ConstraintViolation<IncludeInfo> violation : violations) {
							validationErrors.add("1.9 Include: " + violation.getMessage());
						}
					}
				}
			}
			
			nodeList = doc.getElementsByTagName("webAppSecurity");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
					
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					WebAppSecurityInfo webAppSecurityInfo = new WebAppSecurityInfo();
					webAppSecurityInfo.setDisplayAuthenticationRealm(element.getAttribute("displayAuthenticationRealm"));
					
					Set<ConstraintViolation<WebAppSecurityInfo>> violations = validator.validate(webAppSecurityInfo);

					// Validate using Bean Validation and add violations to List
					if (!violations.isEmpty()) {						
						for (ConstraintViolation<WebAppSecurityInfo> violation : violations) {
							validationErrors.add("2.1 Display Authentication Realm : " + violation.getMessage());
						}
					}
				}
			}
			
			nodeList = doc.getElementsByTagName("basicRegistry");
			
			if (nodeList.getLength() > 0) {
				validationErrors.add("2.2 Basic User Register is being used");
			}

			nodeList = doc.getElementsByTagName("quickStartSecurity");
			
			if (nodeList.getLength() > 0) {
				validationErrors.add("2.2 Quick Start security is being used");
			}
			
			nodeList = doc.getElementsByTagName("ldapRegistry");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
					
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					LDAPRegistryInfo ldapRegistryInfo = new LDAPRegistryInfo();
					ldapRegistryInfo.setSslEnabled(element.getAttribute("sslEnabled"));
					
					Set<ConstraintViolation<LDAPRegistryInfo>> violations = validator.validate(ldapRegistryInfo);

					// Validate using Bean Validation and add violations to List
					if (!violations.isEmpty()) {						
						for (ConstraintViolation<LDAPRegistryInfo> violation : violations) {
							validationErrors.add("2.3 LDAP SSL : " + violation.getMessage());
						}
					}
				}
			}
			
			nodeList = doc.getElementsByTagName("ldapRegistry");
			
			if (nodeList.getLength() == 0) {
				validationErrors.add("3.1 Application Monitor is missing");
			}
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
					
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					ApplicationMonitorInfo applicationMonitorInfo = new ApplicationMonitorInfo();
					applicationMonitorInfo.setDropinsEnabled(element.getAttribute("dropinsEnabled"));
					applicationMonitorInfo.setUpdateTrigger(element.getAttribute("updateTrigger"));
					
					Set<ConstraintViolation<ApplicationMonitorInfo>> violations = validator.validate(applicationMonitorInfo);

					// Validate using Bean Validation and add violations to List
					if (!violations.isEmpty()) {						
						for (ConstraintViolation<ApplicationMonitorInfo> violation : violations) {
							validationErrors.add("3.1 Application Monitor : " + violation.getMessage());
						}
					}
				}
			}
			
			nodeList = doc.getElementsByTagName("javaPermission");
			
			if (nodeList.getLength() == 0) {
				validationErrors.add("3.2 SDK Security Manager is missing");
			}
			
			// Finished, so let's print out the Validation errors
			if (validationErrors.isEmpty()) {
				System.out.println("Validation found no errors");
				System.exit(0);
			}
			
			for (String error : validationErrors) {
				System.out.println(error);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace(System.err);
		}
	}

}