package com.stroeer.testAutomation.bussinesslogic;

import org.openqa.selenium.By;

public class TestAutoControlInput {
	enumControlType controlType;
	By controlIdentifier;
	String valueToUse;
	enumDropdownSelectBy dropdownSelectBy;
	boolean checked;

	public TestAutoControlInput() {
	}

	public TestAutoControlInput(enumControlType enControlType, By byControlIdentifier, String strValueToUse,
			enumDropdownSelectBy ddldropdownSelectBy, boolean bChecked) {
		controlType = enControlType;
		controlIdentifier = byControlIdentifier;
		valueToUse = strValueToUse;
		dropdownSelectBy = ddldropdownSelectBy;
		checked = bChecked;
	}
}
