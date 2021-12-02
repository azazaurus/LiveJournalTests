package org.test.tests;

import org.junit.jupiter.api.*;
import org.test.*;

public abstract class TestBase {
	protected ApplicationManager app;

	@BeforeEach
	public void setUp() {
		app = ApplicationManager.getInstance();
		app.navigation.goToMainPage();
	}

}
