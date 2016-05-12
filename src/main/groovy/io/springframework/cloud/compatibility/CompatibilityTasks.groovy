package io.springframework.cloud.compatibility

import groovy.transform.PackageScope
import javaposse.jobdsl.dsl.helpers.step.StepContext

/**
 * @author Marcin Grzejszczak
 */
@PackageScope
abstract class CompatibilityTasks {

	protected static final String DEFAULT_BOOT_VERSION = '1.4.0.BUILD-SNAPSHOT'
	protected static final String SPRING_BOOT_VERSION_VAR = 'SPRING_BOOT_VERSION'

	protected static final List<String> ALL_COMPATIBILITY_TASKS = [
	        'spring-cloud-sleuth-compatibility-check',
	        'spring-cloud-netflix-compatibility-check',
	]

	Closure defaultSteps() {
		return buildStep {
			shell runTests()
			shell("""
					echo -e "Printing the list of dependencies"
					./mvnw dependency:tree -U -Dspring-boot.version=\$${SPRING_BOOT_VERSION_VAR}
					""")
		}
	}

	protected String runTests() {
		return """
					echo -e "Running the tests"
					./mvnw clean install -U -fae -Dspring-boot.version=\$${SPRING_BOOT_VERSION_VAR}"""
	}

	private Closure buildStep(@DelegatesTo(StepContext) Closure buildSteps) {
		return buildSteps
	}

}
