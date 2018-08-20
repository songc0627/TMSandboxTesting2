package co.nz.assurity.test.TMSandboxTesting2.category;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import co.nz.assurity.test.TMSandboxTesting2.apicontroller.*;
import co.nz.assurity.test.TMSandboxTesting2.jsonparser.*;

public class CategoryTest {
	
	private static final String URL = "https://api.tmsandbox.co.nz/";

	private static APIRequest request;

	@Before
	public void setUp() {
		/*
		 * Initialize APIRequest object using the end point URL
		 */
		request = new APIRequest(URL);
	}
	
    @Test
    public void testSample() throws Exception {
    	// set path for the API call
    	String path = String.format("/v1/Categories/%s/Details.json?catalogue=false", 6327);

    	// get response for service call
    	APIResponse response = request.get(path);
    	
    	// parse Category content into CategoryReader
    	CategoryReader cr = new CategoryReader(response.getResponseBody());
    	
    	// assert values
    	assertEquals(cr.getName(), "Carbon credits");
    	
    	assertEquals(cr.getCanRelist(), Boolean.TRUE);
    	
    	assertTrue(cr.getPromoGalleryDesc().contains("2x larger image"));
    }
	
}
