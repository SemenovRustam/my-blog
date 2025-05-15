plugins {
    id("java")
    id("war")
}

group = "com.semenov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring 6 (обязательно используем jakarta-совместимые версии)
    implementation("org.springframework:spring-webmvc:6.1.5")
    implementation("org.springframework:spring-jdbc:6.1.5")
    implementation("org.springframework:spring-tx:6.1.5")

    // Spring 6+ (Jakarta-compatible)
    implementation("org.springframework:spring-webmvc:6.1.5")

    // Servlet API (ТОЛЬКО JAKARTA для Tomcat 10+)
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    // JUnit
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<War> {
    archiveFileName.set("my-blog.war")
}