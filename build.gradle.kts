plugins {
	java
	id("org.springframework.boot") version "3.5.10"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.servisub"
version = "0.0.1-SNAPSHOT"
description = "Servidor administrativo de Servisub"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation("com.github.librepdf:openpdf:1.3.30")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20230815-2.0.0")
    // Google API Client
    implementation("com.google.api-client:google-api-client:2.2.0")
// Google Drive API
    implementation("com.google.apis:google-api-services-drive:v3-rev20230822-2.0.0")
// Google Auth Library
    implementation("com.google.auth:google-auth-library-oauth2-http:1.19.0")
// HTTP Transport (necesario para las llamadas API)
    implementation("com.google.http-client:google-http-client-jackson2:1.43.3")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.jsonwebtoken:jjwt:0.13.0")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
