package org.test.tests;

import org.junit.jupiter.api.*;
import org.test.*;

public abstract class TestBase {
	protected ApplicationManager app;

	@BeforeEach
	@Order(1)
	public void setUp() {
		app = ApplicationManager.getInstance();
	}

}
