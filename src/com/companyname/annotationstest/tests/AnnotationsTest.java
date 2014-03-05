package com.companyname.annotationstest.tests;

import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.companyname.annotationstest.ClassA;
import com.companyname.annotationstest.ICollaborator;

public class AnnotationsTest {

	private ClassA classA;
	private ICollaborator classBmock;
	
	@Before
	public void setUp() throws Exception {
		classA = new ClassA();
		classBmock = EasyMock.createMock(ICollaborator.class);
	}

	// this test expect a RuntimeException error due to the class collaborator (B) is not set to class A
	@Test(expected=RuntimeException.class)
	public void testRTException() {
		classBmock.methodCollaborator();
		EasyMock.replay(classBmock);
		// intentionally I do not set the class collaborator to classA, so it is expected a RuntimeException
		classA.useCollaboratorMethod();
		
		EasyMock.verify(classBmock);
	}
	
	// this test do the same, but using rule to check the exception message thrown
	// and fail because the message expected "adsfadt" is different that the one thrown (see ClassA.useCollaboratorMethod)
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test2() throws RuntimeException {
		  thrown.expect(RuntimeException.class);
		  thrown.expectMessage("adsfadt");
		classBmock.methodCollaborator();
		EasyMock.replay(classBmock);
		// intentionally I do not set the class collaborator to classA, so it is expected a RuntimeException
		classA.useCollaboratorMethod();
		
		EasyMock.verify(classBmock);
	}
}
