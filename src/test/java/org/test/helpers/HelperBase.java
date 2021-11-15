package org.test.helpers;

import org.openqa.selenium.support.*;
import org.test.*;

public abstract class HelperBase {
	protected final ApplicationManager app;

	public HelperBase(ApplicationManager app) {
		this.app = app;

		PageFactory.initElements(app.driver, this);
	}
}
