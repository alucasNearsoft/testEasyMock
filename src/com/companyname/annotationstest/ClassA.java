package com.companyname.annotationstest;

public class ClassA {
	
	private ICollaborator classB;

	public ICollaborator getClassB() {
		return classB;
	}

	public void setClassB(ICollaborator classB) {
		this.classB = classB;
	}
	
	public void useCollaboratorMethod() {
		if (classB == null) {
			throw new RuntimeException("The collaborator class has not yet maintained");
		}
		classB.methodCollaborator();
	}

}
