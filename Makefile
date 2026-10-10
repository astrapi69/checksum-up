.PHONY: build build-stacktrace build-warning clean \
	dependencies dependency-updates jacoco-coverage jacoco-report jar javadoc \
	license-format publish-central publish-local spotless-apply \
	spotless-check spotless-java spotless-misc tag-release test \
	version-catalog-format version-catalog-update

build:
	./gradlew build

build-stacktrace:
	./gradlew build --stacktrace --warning-mode all

build-warning:
	./gradlew build --warning-mode all

clean:
	./gradlew clean

test:
	./gradlew test

# --- mirrors Gradle "Run Configurations" panel ---

dependencies:
	./gradlew dependencies

dependency-updates:
	./gradlew dependencyUpdates

jacoco-coverage:
	./gradlew jacocoTestCoverageVerification

jacoco-report:
	./gradlew jacocoTestReport

jar:
	./gradlew jar

javadoc:
	./gradlew javadoc

# license headers are managed by the spotless licenseHeaderFile step
license-format:
	./gradlew spotlessApply

publish-local:
	./gradlew publishMavenJavaPublicationToMavenLocal

# publishingType is USER_MANAGED, so this uploads a bundle that still waits for a click in the
# Portal. That is a net, not a permission: a published version can never be replaced. A release is
# published by pushing its tag (.github/workflows/publish.yml), so this is for the case that fails
# there (#7)
publish-central:
	@test "$(CONFIRM)" = "yes" || { \
		echo "publish-central uploads to Maven Central. Re-run with CONFIRM=yes if that is intended."; \
		exit 1; }
	./gradlew nmcpPublishAllPublicationsToCentralPortal

spotless-apply:
	./gradlew spotlessApply

spotless-check:
	./gradlew spotlessCheck

spotless-java:
	./gradlew spotlessJavaApply

spotless-misc:
	./gradlew spotlessMiscApply

tag-release:
	./gradlew tagRelease

version-catalog-format:
	./gradlew versionCatalogFormat

version-catalog-update:
	./gradlew versionCatalogUpdate
